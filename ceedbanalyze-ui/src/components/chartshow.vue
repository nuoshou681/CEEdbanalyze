<template>
  <div class="chart-container" :style="{ height: chartHeight }">
    <canvas ref="chart"></canvas>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { Chart, registerables } from 'chart.js';
import { getPrediction } from '@/api/prediction'; // 导入 API 方法

Chart.register(...registerables); // 注册 Chart.js 的模块

const chart = ref(null);
let chartInstance = null;

// 定义用于存储从 API 获取的数据
const data = ref([]);
const chartHeight = ref('500px'); // 初始化图表高度

const getColor = (value) => {
  if (value >= 80) return '#008000'; 
  if (value >= 60) return '#FFA500'; 
  if (value >= 40) return '#FFFF00'; 
  if (value >= 20) return '#FF0000'; 
  return '#0000FF'; 
};

const initChart = () => {
  const ctx = chart.value.getContext('2d');
  
  // 设置 canvas 高度和宽度
  chart.value.height = parseInt(chartHeight.value);
  chart.value.width = chart.value.clientWidth; // 设置 canvas 的宽度

  // 初始化图表
  chartInstance = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: data.value.map(item => item.majorName),
      datasets: [{
        label: '概率',
        data: data.value.map(item => item.probability),
        backgroundColor: data.value.map(item => getColor(item.probability)),
        borderRadius: 5,
        barThickness: 10, // 设置柱形图的厚度
        barPercentage: 0.4, // 调整柱形图的占比
        categoryPercentage: 1.0, // 减小每条柱形图之间的间隔
      }]
    },
    options: {
      indexAxis: 'y',
      scales: {
        x: {
          beginAtZero: true,
          max: 100,
          ticks: {
            callback: function(value) {
              return value + '%';
            }
          }
        }
      },
      plugins: {
        legend: {
          display: false
        },
        tooltip: {
          callbacks: {
            label: function(context) {
              return `${context.label}: ${context.raw}%`; // 显示标签和对应的概率
            }
          }
        }
      },
      layout: {
        padding: {
          left: 10,
          right: 10,
          top: 10,
          bottom: 10
        }
      }
    }
  });
};

// 调用 API 获取预测数据
const fetchPredictionData = async () => {
  try {
    const response = await getPrediction();
    data.value = response.data; 
    updateChartHeight(); 
    if (chartInstance) {
      chartInstance.destroy(); 
    }
    initChart(); // 初始化图表
  } catch (error) {
    console.error('获取预测数据失败:', error);
  }
};

// 根据数据条目数量更新图表高度
const updateChartHeight = () => {
  const itemCount = data.value.length;
  const itemHeight = 30; 
  const padding = 20; 
  chartHeight.value = `${itemCount * itemHeight + padding}px`; 
  
  // 更新 canvas 高度
  if (chart.value) {
    chart.value.height = parseInt(chartHeight.value);
  }
};

onMounted(() => {
  fetchPredictionData(); 
});

onBeforeUnmount(() => {
  if (chartInstance) {
    chartInstance.destroy(); 
  }
});
</script>

<style scoped>
.chart-container {
  width: 80%; 
  border: 1px solid #ccc;
  padding: 10px;
  position: absolute;
  top: 100px;
  overflow: hidden; 
}
canvas {
  width: 100% !important; 
  height: auto !important; 
}
</style>
