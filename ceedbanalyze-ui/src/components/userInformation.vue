<template>
  <div class="container">
    <div class="header">
      <div class="title">完善个人信息</div>
    </div>
    <div class="options">
      <div v-for="option in options" :key="option" class="option-item">
        <button
          :class="{ selected: selectedOptions.includes(option) }"
          @click="toggleSelection(option)"
        >
          {{ option }}
        </button>
      </div>
    </div>
    <div class="score-input">
      <input
        type="text"
        placeholder="输入分数"
        v-model="scores"
        @input="validateScores"
      />
      <div v-if="scoresError" class="error-message">{{ scoresError }}</div>
    </div>
    <div style="width: 350px; height: 200px;" ref="chart"></div>
    <div class="recommendation-button">
      <button @click="analyze">开始分析</button>
      <div v-if="loading" class="loading-message">正在分析，时间可能较长，请稍等...</div>
      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRouter } from 'vue-router';
import { StoreuserRank, StoreuserInformation } from '@/api/login';
import { useUserStore } from '@/store/user';
import * as echarts from 'echarts';
import { getNum } from '@/api/yifenyiduan'; // 导入 API 方法
import { start } from '@/api/prediction'; // 导入 start 函数

const chart = ref(null);
const options = ['物理', '化学', '生物', '政治', '历史', '地理'];
const userStore = useUserStore();
const selectedOptions = ref([]);
const scores = ref('');
const scoresError = ref('');
const errorMessage = ref('');
const loading = ref(false); // 新增 loading 状态
let chartInstance = null; // 图表实例

const toggleSelection = (option) => {
  if (selectedOptions.value.includes(option)) {
    selectedOptions.value = selectedOptions.value.filter(
      (selected) => selected !== option
    );
  } else if (selectedOptions.value.length < 3) {
    selectedOptions.value.push(option);
  }
};

const router = useRouter();

const analyze = async () => {
  if (selectedOptions.value.length === 3) {
    loading.value = true; // 开始分析时设置 loading 为 true
    try {
      // 获取用户排名
      const response = await StoreuserRank(userStore.userID, scores.value);
      userStore.rank = response.data.ranking;

      const subjectsMap = {
        物理: 1,
        化学: 2,
        生物: 3,
        地理: 4,
        历史: 5,
        政治: 6,
      };

      const subjects = selectedOptions.value
        .map(option => subjectsMap[option])
        .join('');

      // 调用 start API
      const predictionResponse = await start(userStore.rank, subjects);
      console.log('预测结果:', predictionResponse.data); // 输出预测结果

      // 判断 start 的返回值
      if (String(predictionResponse.data) === 'true') { // 更新判断条件
        // 存储用户信息
        await StoreuserInformation(userStore.userID, ...selectedOptions.value);
        userStore.analyzetags = true;
        userStore.major1 = selectedOptions.value[0];
        userStore.major2 = selectedOptions.value[1];
        userStore.major3 = selectedOptions.value[2];
        userStore.score = scores.value;

        // 跳转到分析详情页
        router.push('/analyze/analyzedetail1');
      } else {
        errorMessage.value = '分析失败，请重试。';
      }
    } catch (error) {
      console.error('分析错误:', error);
      errorMessage.value = '分析失败，请重试。';
    } finally {
      loading.value = false; // 无论成功与否，结束分析时将 loading 设置为 false
    }
  } else {
    errorMessage.value = '请选择三门学科';
  }
};

const validateScores = () => {
  const score = parseFloat(scores.value);
  if (!scores.value) {
    scoresError.value = '分数不能为空';
  } else if (isNaN(score) || score < 100 || score > 750) {
    scoresError.value = '分数必须在 150 到 750 之间';
  } else {
    scoresError.value = '';
    fetchChartData(); // 分数有效时更新图表
  }
};

const fetchChartData = async () => {
  const inputScore = parseFloat(scores.value);
  try {
    const response = await getNum();
    const data = response.data;
    if (Array.isArray(data)) {
      const filteredData = data.filter(
        (item) => item.score >= inputScore - 40 && item.score <= inputScore + 40
      );

      // 计算纵轴范围
      const minRank = Math.min(...filteredData.map(item => item.rank));
      const maxRank = Math.max(...filteredData.map(item => item.rank));

      updateChart(filteredData, minRank, maxRank, inputScore);
    } else {
      console.error('API 返回的数据不是数组:', data);
    }
  } catch (error) {
    console.error('获取数据失败:', error);
  }
};

const updateChart = (data, minRank, maxRank, inputScore) => {
  if (chartInstance) {
    const scoresData = data.map(item => item.score);
    const ranksData = data.map(item => item.rank);

    const option = {
      title: {
        text: '请先登录，否则无法分析',
      },
      tooltip: {
        trigger: 'axis',
      },
      xAxis: {
        type: 'category',
        data: scoresData, // 分数
        name: '分数',
        boundaryGap: false, 
        splitLine: { show: false }, 
      },
      yAxis: {
        type: 'value',
        name: '名次',
        min: minRank,
        max: maxRank,
        axisLabel: {
          formatter: '{value}',
        },
        splitLine: { show: true }, 
      },
      series: [
        {
          name: '名次',
          type: 'line',
          data: ranksData, // 对应的排名
          markLine: {
            data: [
              {
                name: '输入分数',
                xAxis: inputScore, // 添加竖线标记
                label: {
                  formatter: '分数: {b}',
                },
                lineStyle: {
                  color: 'red',
                  type: 'dashed',
                },
              },
            ],
          },
        },
      ],
    };

    chartInstance.setOption(option, true); // 更新图表配置
  }
};

const initializeChart = () => {
  if (chartInstance) {
    chartInstance.dispose(); // 销毁旧的实例
  }
  chartInstance = echarts.init(chart.value); // 初始化新的实例
  chartInstance.setOption({
    title: { text: '请先登录，否则无法分析' },
    xAxis: { type: 'category', data: [] },
    yAxis: { type: 'value', name: '名次' },
    series: [{ name: '名次', type: 'line', data: [] }],
  });
};

onMounted(() => {
  initializeChart(); // 初始化图表
});

watch(scores, (newVal) => {
  if (!scoresError.value && newVal) {
    fetchChartData();
  }
});
</script>

<style scoped>
.error-message {
  color: red;
  font-size: 12px;
}
.loading-message {
  color: orange; 
  font-size: 22px; 
  font-weight: bold; 
}
.container {
  width: 350px;
  height: 400px;
  border-radius: 10px;
  background-color: #f5f5f5;
  padding: 20px;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.title {
  font-size: 18px;
  font-weight: bold;
}
.options {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}
.option-item {
  flex: 1;
  text-align: center;
  margin-left: 5px;
}
.option-item button {
  width: 100%;
  padding: 10px;
  background-color: #f0f0f0;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}
.option-item button.selected {
  background-color: #ffa500;
  color: white;
}
.score-input {
  margin-top: 20px;
  display: flex;
}
.score-input input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.recommendation-button {
  width: 100%;
  margin-top: 20px;
}
.recommendation-button button {
  width: 100%;
  padding: 15px;
  background-color: #ffa500;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-size: 16px;
}
</style>

