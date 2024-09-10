<template>
    <div class="chart-container">
      <canvas ref="chart"></canvas>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, onBeforeUnmount } from 'vue';
  import { Chart, registerables } from 'chart.js';
  
  Chart.register(...registerables);
  
  const chart = ref(null);
  let chartInstance = null;
  
  const data = [
    { name: '项目A', value: 80 },
    { name: '项目B', value: 70 },
    { name: '项目C', value: 60 },
    { name: '项目D', value: 90 },
    { name: '项目E', value: 50 },
    { name: '项目F', value: 40 },
    { name: '项目G', value: 30 },
    { name: '项目H', value: 20 },
    { name: '项目I', value: 10 },
    { name: '项目J', value: 100 },
    { name: '项目k', value: 80 },
    { name: '项目l', value: 70 },
    { name: '项目m', value: 60 },
    { name: '项目m', value: 90 },
    { name: '项目o', value: 50 },
    { name: '项目p', value: 40 },
    { name: '项目q', value: 30 },
    { name: '项目r', value: 20 },
    { name: '项目s', value: 10 },
    { name: '项目t', value: 100 },
    { name: '项目A', value: 80 },
    { name: '项目B', value: 70 },
    { name: '项目C', value: 60 },
    { name: '项目D', value: 90 },
    { name: '项目E', value: 50 },
    { name: '项目F', value: 40 },
    { name: '项目G', value: 30 },
    { name: '项目H', value: 20 },
    { name: '项目I', value: 10 },
    { name: '项目J', value: 100 },
    { name: '项目k', value: 80 },
    { name: '项目l', value: 70 },
    { name: '项目m', value: 60 },
    { name: '项目m', value: 90 },
    { name: '项目o', value: 50 },
    { name: '项目p', value: 40 },
    { name: '项目q', value: 30 },
    { name: '项目r', value: 20 },
    { name: '项目s', value: 10 },
    { name: '项目t', value: 100 },
    
  ];
  const getColor = (value) => {
  if (value >= 80) return '#008000'; // 绿色
  if (value >= 60) return '#FFA500'; // 橙色
  if (value >= 40) return '#FFFF00'; // 黄色
  if (value >= 20) return '#FF0000'; // 红色
  return '#0000FF'; // 蓝色
};
  const initChart = () => {
    const ctx = chart.value.getContext('2d');
    chartInstance = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: data.map(item => item.name),
        datasets: [{
          label: '百分比',
          data: data.map(item => item.value),
          backgroundColor: data.map(item => getColor(item.value)),
          borderRadius: 5,
          barPercentage: 0.5,
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
                return context.raw + '%';
              }
            }
          }
        }
      }
    });
  };
  
  onMounted(() => {
    initChart();
  });
  
  onBeforeUnmount(() => {
    if (chartInstance) {
      chartInstance.destroy();
    }
  });
  </script>
  
  <style scoped>
  .chart-container {
    width: 800px;
    height: 500px;
    overflow-y: scroll;
    border: 1px solid #ccc;
    padding: 10px;
    position:absolute;
    top:100px;
  }
  </style>