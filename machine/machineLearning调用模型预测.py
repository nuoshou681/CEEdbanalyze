import os
from joblib import load
import json
import pandas as pd
import sys
import requests
import numpy as np
from machineLearning_随机森林 import calculate_trend_adjustment, adjust_probability

# 设置模型文件夹路径
model_save_folder = os.path.join(os.path.dirname(__file__), 'models')

# 处理输入的排名
score = 10000  # 测试排名

def parse_score(score):
    try:
        return float(score)
    except ValueError:
        return None

parsed_score = parse_score(score)
if parsed_score is None:
    print("无效的分数输入")
    sys.exit(1)

# 读取和解析JSON文件
file_path = os.path.join('..', 'ceedbanalyze', 'sas.json')
with open(file_path, 'r', encoding='utf-8') as file:
    try:
        major_data = json.load(file)
    except json.JSONDecodeError as e:
        print(f"解析错误：{e}")
        sys.exit(1)

# 提取数据
data = []
for major in major_data:
    major_id = major.get('id')
    major_name = major.get('majorName')  # 获取志愿名称
    school_id = major.get('schoolId')  # 提取学校ID
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
        data.append([major_id, major_name, school_id, year, rank])

    # 由于是反向填补的，需要将结果排序回年份顺序
    data = sorted(data, key=lambda x: x[3])

# 转换为DataFrame
df = pd.DataFrame(data, columns=['ID', '专业名称', '学校ID', '年份', '排名'])

# 存储预测概率
prediction_results = {}
result = []  # 初始化结果列表

# 对每所学校使用对应的模型进行预测
for major in df['ID'].unique():
    major_df = df[df['ID'] == major]

    if len(major_df) > 0:
        # 如果排名小于阈值，加载模型并进行预测
        if (major_df['排名'] < parsed_score + 45000).any() and (major_df['排名'] > parsed_score - 45000).any():
            model_filename = f"{model_save_folder}/trained_model_{str(major).replace(' ', '_')}.joblib"
            try:
                model = load(model_filename)  # 加载模型
            except FileNotFoundError:
                continue

            # 创建测试数据，确保包含`parsed_score`
            test_df = pd.DataFrame([[parsed_score]], columns=['排名'])

            # 加载标准化器
            scaler_filename = f'{model_save_folder}/scaler_{str(major).replace(" ", "_")}.joblib'
            try:
                scaler = load(scaler_filename)  # 加载已经训练的Scaler
                test_df_scaled = scaler.transform(test_df)
            except Exception as e:
                continue

            # 计算排名趋势调整因子
            trend_adjustment = calculate_trend_adjustment(major_df)
            # 进行预测
            predicted_probability = model.predict(test_df_scaled)[0]
            # 应用趋势调整到预测概率
            predicted_probability *= trend_adjustment
            # 根据预测排名计算录取概率
            admission_probability = adjust_probability(predicted_probability)
            # 记录结果
            prediction_results[(major, major_df['专业名称'].iloc[0], major_df['学校ID'].iloc[0])] = admission_probability

# 过滤出概率大于0.01的志愿
result = {f"{major_id}_{major_name}_{school_id}": float(probability)
          for (major_id, major_name, school_id), probability in prediction_results.items() if probability > 0.01}

# 转换 numpy 对象为原生 Python 类型
def convert_numpy_types(obj):
    if isinstance(obj, np.int64):
        return int(obj)
    elif isinstance(obj, np.float64):
        return float(obj)
    elif isinstance(obj, list):
        return [convert_numpy_types(i) for i in obj]
    elif isinstance(obj, dict):
        return {k: convert_numpy_types(v) for k, v in obj.items()}
    return obj

# 将所有值从 prediction_results 转换为原生 Python 类型
result_list = [{'majorName': major_name, 'schoolId': int(school_id), 'probability': convert_numpy_types(probability)}
                for (major_id, major_name, school_id), probability in prediction_results.items() if probability > 0.01]

# 打印 `result_list` 以进行调试
print("Result List for Debugging:", result_list)

# 发送结果到Spring Boot服务器
url = "http://localhost:5173/prediction/receive"
try:
    response = requests.post(url, json=result_list)
    print("Response from Spring Boot:", response.text)
except requests.exceptions.RequestException as e:
    print(f"请求失败: {e}")
