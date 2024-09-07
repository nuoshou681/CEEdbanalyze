import json
import os
import pandas as pd
from sklearn.model_selection import train_test_split, GridSearchCV, KFold
from sklearn.ensemble import RandomForestRegressor
from joblib import dump
from sklearn.preprocessing import StandardScaler
import numpy as np
import warnings
from concurrent.futures import ThreadPoolExecutor, as_completed

# 捕获并忽略特定的 RuntimeWarning
warnings.filterwarnings("ignore", category=RuntimeWarning)
def load_data(file_path):
    #读取和解析JSON文件
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            major_data = json.load(file)
            return major_data
    except FileNotFoundError:
        print(f"文件未找到：{file_path}")
        return None
    except json.JSONDecodeError as e:
        print(f"解析错误：{e}")
        return None


def preprocess_data(major_data):
    #提取和预处理数据
    data = []
    for major in major_data:
        major_id = major.get('id')
        major_name = major.get('majorName')  # 获取志愿名称
        # 提取年份和排名
        year_rank_pairs = [(year, major.get(f'rank{year}')) for year in range(2020, 2024)]

        # 反向填补缺失的排名
        last_valid_rank = None
        for year, rank in reversed(year_rank_pairs):
            if rank is not None:
                last_valid_rank = rank
            else:
                # 使用最近的有效排名填补缺失
                rank = last_valid_rank
            data.append([major_id, major_name, year, rank])

        # 由于是反向填补的，需要将结果排序回年份顺序
        data = sorted(data, key=lambda x: x[2])
    return pd.DataFrame(data, columns=['ID', '志愿名称', '年份', '排名'])

def calculate_trend_adjustment(college_df):
    #计算排名趋势的调整因子
    college_df = college_df.sort_values('年份')
    rank_diff = np.diff(college_df['排名'])

    # 计算平均排名变化率
    avg_rank_change = np.mean(rank_diff)

    # 如果排名变大，录取概率应增加；反之，减少
    if avg_rank_change > 0:
        adjustment_factor = 1 + min(0.05, avg_rank_change / 15000)  # 最大增加5%
    elif avg_rank_change < 0:
        adjustment_factor = 1 - min(0.05, -avg_rank_change / 15000)  # 最大减少5%
    else:
        adjustment_factor = 1  # 无变化

    return adjustment_factor

def adjust_probability(predicted_probability):
    #调整概率范围到1%-100%，并将大于100%的概率修正为100%
    # 将 7%-88% 的概率映射到 1%-100%
    if 0.07 <= predicted_probability <= 0.88:
        adjusted_probability = 1 + (predicted_probability - 0.07) * (99 / (0.88 - 0.07))
    elif predicted_probability > 1:
        adjusted_probability = 100  # 修正所有大于 100% 的概率
    else:
        adjusted_probability = predicted_probability * 100

    return adjusted_probability

def train_and_save_model(args):
    #训练模型并保存
    major, df, test_rank, model_save_folder, param_grid = args
    college_df = df[df['ID'] == major]

    # 检查数据集大小
    if len(college_df) < 2:
        print(f"样本数不足，跳过该志愿: ID={major}")
        return None

    X = college_df[['排名']]

    # 根据排名计算录取概率，确保目标变量是适合回归的连续值
    try:
        # 修改基于 test_rank 的计算方式，确保高排名（数值大）对应低概率
        base_probability = 1 / (1 + np.exp((college_df['排名'] - np.mean(college_df['排名'])) / 5000))
        y = base_probability  # 去除趋势调整
    except Exception as e:
        print(f"计算目标变量时出现错误: {e}")
        return None

    # 确认 y 是连续的数值型数据
    if not np.issubdtype(y.dtype, np.number):
        print(f"目标变量类型错误，跳过该志愿: ID={major}")
        return None

    # 分割数据集为训练集和测试集
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=0)

    # 特征标准化
    scaler = StandardScaler()
    X_train_scaled = scaler.fit_transform(X_train)
    X_test_scaled = scaler.transform(X_test)

    # 确保有足够的样本进行 KFold
    n_splits = min(5, len(X_train)) if len(X_train) > 2 else 1

    # 检查样本数
    if len(X_train) < 2:
        print(f"样本数不足，跳过该志愿: ID={major}")
        return None

    try:
        # 使用 KFold 和 GridSearchCV 进行模型调优
        if n_splits < 2:
            model = RandomForestRegressor(random_state=0)
            model.fit(X_train_scaled, y_train)
        else:
            kf = KFold(n_splits=n_splits, shuffle=True, random_state=0)
            grid_search = GridSearchCV(RandomForestRegressor(random_state=0), param_grid, cv=kf,
                                       scoring='neg_mean_squared_error',
                                       n_jobs=-1)
            grid_search.fit(X_train_scaled, y_train)
            model = grid_search.best_estimator_
    except Exception as e:
        print(f"模型训练出现错误: {e}")
        return None

    # 保存每个志愿的模型
    model_filename = f"{model_save_folder}/trained_model_{str(major).replace(' ', '_')}.joblib"
    dump(model, model_filename)
    dump(scaler, f'{model_save_folder}/scaler_{str(major).replace(" ", "_")}.joblib')

    # 标准化测试数据
    test_df = pd.DataFrame([[test_rank]], columns=['排名'])
    test_df_scaled = scaler.transform(test_df)
    predicted_probability = model.predict(test_df_scaled)[0]

    # 调整预测的概率范围
    adjusted_probability = adjust_probability(predicted_probability)

    # 记录结果
    return (major, college_df['志愿名称'].iloc[0], adjusted_probability)

# 训练并保存模型代码部分
if __name__ == '__main__':
    # 读取数据
    # 构建相对路径
    file_path = os.path.join('..', 'ceedbanalyze', 'sas.json')
    major_data = load_data(file_path)
    if major_data is None:
        exit()

    # 预处理数据
    df = preprocess_data(major_data)

    # 检查数据集缺失值情况
    print("数据集缺失值情况：")
    print(df.isnull().sum())

    # 强制转换排名列为数值类型，无法转换的将变为 NaN
    df['排名'] = pd.to_numeric(df['排名'], errors='coerce')

    # 移除无效值的行
    df.dropna(subset=['排名'], inplace=True)

    # 再次检查是否有无效值
    if df['排名'].isnull().any():
        print("仍然发现无效值，可能有问题。")
    else:
        print("无无效值。")

    # 测试排名
    test_rank = 200000

    # 存储预测概率
    result = []  # 初始化结果列表
    model_save_folder = os.path.join(os.path.dirname(__file__), 'models')

    # 定义更丰富的参数网格
    param_grid = {
        'n_estimators': [100, 200],
        'max_depth': [None, 10, 20],
        'min_samples_split': [2, 5],
        'min_samples_leaf': [1, 2],
        'max_features': ['sqrt', 'log2'],
    }

    # 使用 ThreadPoolExecutor 来并行训练和保存模型
    with ThreadPoolExecutor() as executor:
        futures = {executor.submit(train_and_save_model, (major, df, test_rank, model_save_folder, param_grid)): major
                   for major in df['ID'].unique()}
        for future in as_completed(futures):
            try:
                result_item = future.result()
                # 只在 result_item 不为 None 时添加到结果列表
                if result_item is not None:
                    result.append(result_item)
            except Exception as e:
                print(f"模型训练失败: {e}")

    # 过滤出有效的结果
    prediction_results = {(major_id, major_name): probability for major_id, major_name, probability in result}

    # 过滤出概率大于0.01的志愿
    probabilities = {k: v for k, v in prediction_results.items() if v > 0.01}

    # 按概率从低到高排序
    sorted_major = sorted(probabilities.items(), key=lambda x: x[1], reverse=False)

    # 输出结果
    if len(sorted_major) > 0:
        print(f"以下是预测的志愿录取概率：")
        for (major_id, major_name), probability in sorted_major:
            print(f"{major_name} (ID: {major_id}): {probability/100:.2%}")
    else:
        print("没有找到录取概率大于 1% 的志愿。")
