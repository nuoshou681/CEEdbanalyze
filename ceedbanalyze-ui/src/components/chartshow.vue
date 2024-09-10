<template>
  <div class="chart-container" :style="{ height: chartHeight }">
    <canvas ref="chart"></canvas>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { Chart, registerables } from 'chart.js';
import { getPrediction } from '@/api/prediction';

Chart.register(...registerables);

const chart = ref(null);
let chartInstance = null;

const data = ref([]);
const chartHeight = ref('500px');

const getColor = (value) => {
  if (value >= 80) return '#008000'; 
  if (value >= 60) return '#FFA500'; 
  if (value >= 40) return '#FFFF00'; 
  if (value >= 20) return '#FF0000'; 
  return '#0000FF'; 
};

const initChart = () => {
  const ctx = chart.value.getContext('2d');
  
  chart.value.height = parseInt(chartHeight.value);
  chart.value.width = chart.value.clientWidth;

  chartInstance = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: data.value.map(item => item.majorName),
      datasets: [{
        label: '概率',
        data: data.value.map(item => item.probability),
        backgroundColor: data.value.map(item => getColor(item.probability)),
        borderRadius: 5,
        barThickness: 10,
        barPercentage: 0.4,
        categoryPercentage: 1.0,
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
              return `${context.label}: ${context.raw}%`;
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
    const schoolData = [
    {"id": 1, "name": "滨州医学院"},
    {"id": 2, "name": "青岛酒店管理职业技术学院"},
    {"id": 3, "name": "山东大学"},
    {"id": 4, "name": "中国海洋大学"},
    {"id": 5, "name": "中国石油大学（华东）"},
    {"id": 6, "name": "山东师范大学"},
    {"id": 7, "name": "青岛大学"},
    {"id": 8, "name": "山东科技大学"},
    {"id": 9, "name": "齐鲁工业大学"},
    {"id": 10, "name": "山东农业大学"},
    {"id": 11, "name": "青岛科技大学"},
    {"id": 12, "name": "曲阜师范大学"},
    {"id": 13, "name": "济南大学"},
    {"id": 14, "name": "山东财经大学"},
    {"id": 15, "name": "烟台大学"},
    {"id": 16, "name": "山东理工大学"},
    {"id": 17, "name": "山东第一医科大学"},
    {"id": 18, "name": "青岛理工大学"},
    {"id": 19, "name": "青岛农业大学"},
    {"id": 20, "name": "鲁东大学"},
    {"id": 21, "name": "聊城大学"},
    {"id": 22, "name": "山东建筑大学"},
    {"id": 23, "name": "山东中医药大学"},
    {"id": 24, "name": "山东第二医科大学"},
    {"id": 25, "name": "山东交通学院"},
    {"id": 26, "name": "临沂大学"},
    {"id": 27, "name": "山东航空学院"},
    {"id": 28, "name": "泰山学院"},
    {"id": 29, "name": "潍坊学院"},
    {"id": 30, "name": "德州学院"},
    {"id": 31, "name": "济宁医学院"},
    {"id": 32, "name": "枣庄学院"},
    {"id": 33, "name": "齐鲁师范学院"},
    {"id": 34, "name": "山东工商学院"},
    {"id": 35, "name": "山东女子学院"},
    {"id": 36, "name": "山东管理学院"},
    {"id": 37, "name": "山东农业工程学院"},
    {"id": 38, "name": "山东青年政治学院"},
    {"id": 39, "name": "菏泽学院"},
    {"id": 40, "name": "济宁学院"},
    {"id": 41, "name": "山东体育学院"},
    {"id": 42, "name": "山东政法学院"},
    {"id": 43, "name": "山东石油化工学院"},
    {"id": 44, "name": "山东警察学院"},
    {"id": 45, "name": "山东大学（威海）"},
    {"id": 46, "name": "青岛港湾职业技术学院"},
    {"id": 47, "name": "日照职业技术学院"},
    {"id": 48, "name": "青岛城市学院"},
    {"id": 49, "name": "山东职业学院"},
    {"id": 50, "name": "齐鲁医药学院"},
    {"id": 51, "name": "烟台理工学院"},
    {"id": 52, "name": "山东商业职业技术学院"},
    {"id": 53, "name": "哈尔滨工业大学（威海）"},
    {"id": 54, "name": "齐鲁理工学院"},
    {"id": 55, "name": "山东医学高等专科学校"},
    {"id": 56, "name": "山东现代学院"},
    {"id": 57, "name": "淄博职业学院"},
    {"id": 58, "name": "山东协和学院"},
    {"id": 59, "name": "青岛工学院"},
    {"id": 60, "name": "威海职业学院"},
    {"id": 61, "name": "青岛滨海学院"},
    {"id": 62, "name": "菏泽医学专科学校"},
    {"id": 63, "name": "山东交通职业学院"},
    {"id": 64, "name": "烟台科技学院"},
    {"id": 65, "name": "山东中医药高等专科学校"},
    {"id": 66, "name": "山东科技职业学院"},
    {"id": 67, "name": "济南职业学院"},
    {"id": 68, "name": "山东财经大学东方学院"},
    {"id": 69, "name": "潍坊理工学院"},
    {"id": 70, "name": "泰山科技学院"},
    {"id": 71, "name": "山东理工职业学院"},
    {"id": 72, "name": "山东水利职业学院"},
    {"id": 73, "name": "烟台南山学院"},
    {"id": 74, "name": "山东畜牧兽医职业学院"},
    {"id": 75, "name": "聊城大学东昌学院"},
    {"id": 76, "name": "青岛农业大学海都学院"},
    {"id": 77, "name": "聊城职业技术学院"},
    {"id": 78, "name": "滨州职业学院"},
    {"id": 79, "name": "山东城市建设职业学院"},
    {"id": 80, "name": "山东英才学院"},
    {"id": 81, "name": "山东商务职业学院"},
    {"id": 82, "name": "青岛职业技术学院"},
    {"id": 83, "name": "青岛黄海学院"},
    {"id": 84, "name": "潍坊科技学院"},
    {"id": 85, "name": "淄博师范高等专科学校"},
    {"id": 86, "name": "潍坊职业学院"},
    {"id": 87, "name": "山东财经大学燕山学院"},
    {"id": 88, "name": "东营职业学院"},
    {"id": 89, "name": "山东艺术学院"},
    {"id": 90, "name": "济宁职业技术学院"},
    {"id": 91, "name": "济南幼儿师范高等专科学校"},
    {"id": 92, "name": "山东工业职业学院"},
    {"id": 93, "name": "山东信息职业技术学院"},
    {"id": 94, "name": "济南工程职业技术学院"},
    {"id": 95, "name": "山东传媒职业学院"},
    {"id": 96, "name": "济南护理职业学院"},
    {"id": 97, "name": "山东外贸职业学院"},
    {"id": 98, "name": "烟台职业学院"},
    {"id": 99, "name": "枣庄职业学院"},
    {"id": 100, "name": "山东劳动职业技术学院"},
    {"id": 101, "name": "烟台工程职业技术学院"},
    {"id": 102, "name": "山东电子职业技术学院"},
    {"id": 103, "name": "泰山职业技术学院"},
    {"id": 104, "name": "山东工程职业技术大学"},
    {"id": 105, "name": "临沂职业学院"},
    {"id": 106, "name": "山东药品食品职业学院"},
    {"id": 107, "name": "山东电力高等专科学校"},
    {"id": 108, "name": "潍坊护理职业学院"},
    {"id": 109, "name": "威海海洋职业学院"},
    {"id": 110, "name": "山东经贸职业学院"},
    {"id": 111, "name": "山东工艺美术学院"},
    {"id": 112, "name": "潍坊工程职业学院"},
    {"id": 113, "name": "中国人民解放军海军航空大学"},
    {"id": 114, "name": "枣庄科技职业学院"},
    {"id": 115, "name": "山东司法警官职业学院"},
    {"id": 116, "name": "山东轻工职业学院"},
    {"id": 117, "name": "山东旅游职业学院"},
    {"id": 118, "name": "青岛恒星科技学院"},
    {"id": 119, "name": "山东华宇工学院"},
    {"id": 120, "name": "德州职业技术学院"},
    {"id": 121, "name": "山东服装职业学院"},
    {"id": 122, "name": "威海工业职业学院"},
    {"id": 123, "name": "山东外国语职业学院"},
    {"id": 124, "name": "山东司法职业学院"},
    {"id": 125,"name": "山东外国语职业技术大学"},
    {"id": 125, "name": "山东外国语职业技术大学"},
    {"id": 126, "name": "烟台汽车工程职业学院"},
    {"id": 127, "name": "山东外事职业大学"},
    {"id": 128, "name": "山东力明科技职业学院"},
    {"id": 129, "name": "莱芜职业技术学院"},
    {"id": 130, "name": "青岛电影学院"},
    {"id": 131, "name": "青岛工程职业学院"},
    {"id": 132, "name": "青岛求实职业技术学院"},
    {"id": 133, "name": "德州科技职业学院"},
    {"id": 134, "name": "青岛远洋船员职业学院"},
    {"id": 135, "name": "菏泽职业学院"},
    {"id": 136, "name": "山东文化产业职业学院"},
    {"id": 137, "name": "山东职业学院西校区"},
    {"id": 138, "name": "烟台黄金职业学院"},
    {"id": 139, "name": "菏泽家政职业学院"},
    {"id": 140, "name": "山东海事职业学院"},
    {"id": 141, "name": "山东铝业职业学院"},
    {"id": 142, "name": "曲阜远东职业技术学院"},
    {"id": 143, "name": "东营科技职业学院"},
    {"id": 144, "name": "山东胜利职业学院"},
    {"id": 145, "name": "山东化工职业学院"},
    {"id": 146, "name": "山东圣翰财贸职业学院"},
    {"id": 147, "name": "康复大学"},
    {"id": 148, "name": "海军潜艇学院"},
    {"id": 149, "name": "滨州科技职业学院"},
    {"id": 150, "name": "山东艺术设计职业学院"},
    {"id": 151, "name": "潍坊环境工程职业学院"},
    {"id": 152, "name": "青岛幼儿师范高等专科学校"},
    {"id": 153, "name": "山东特殊教育职业学院"},
    {"id": 154, "name": "日照航海工程职业学院"},
    {"id": 155, "name": "潍坊食品科技职业学院"},
    {"id": 156, "name": "烟台幼儿师范高等专科学校"},
    {"id": 157, "name": "烟台文化旅游职业学院"},
    {"id": 158, "name": "青岛航空科技职业学院"},
    {"id": 159, "name": "山东城市服务职业学院"},
    {"id": 160, "name": "烟台城市科技职业学院"},
    {"id": 161, "name": "日照康养职业学院"},
    {"id": 162, "name": "烟台卫生健康职业学院"},
    {"id": 163, "name": "山东文化艺术职业学院"},
    {"id": 164, "name": "德州理工职业学院"},
    {"id": 165, "name": "德州工程职业学院"}
];


    // 更新数据，将 schoolId 和 school name 结合到 majorName 中
    data.value = response.data.map(item => {
      const school = schoolData.find(schoolItem => schoolItem.id === item.schoolId);
      return {
        ...item,
        majorName: `${school ? school.name : '未知学校'}：${item.majorName}`
      };
    });

    updateChartHeight();
    
    if (chartInstance) {
      chartInstance.destroy(); // 销毁旧的图表实例
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
