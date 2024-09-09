<template>
    <div class="container">
      <div class="header">
        <div class="title">完善个人信息</div>
      </div>
      <div class="options">
        <div v-for="option in options" :key="option" class="option-item">
          <button 
            :class="{'selected': selectedOptions.includes(option)}"
            @click="toggleSelection(option)"
          >
            {{ option }}
          </button>
        </div>
      </div>
      <div class="score-input">
        <input type="text" placeholder="输入分数" v-model="scores" @input="validateScores">
        <div v-if="scoresError" class="error-message">{{ scoresError }}</div>
      </div>
      <div style="width: 350px;height: 200px;" ref="chart">emm</div>
      <div class="recommendation-button">
        <button @click = "analyze">开始分析</button>
        <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref ,onMounted} from 'vue';
  import { useRouter } from 'vue-router';
  import { StoreuserRank,StoreuserInformation } from '@/api/login';
  import { useUserStore } from '@/store/user';
  import * as echarts from 'echarts';

const chart = ref(null);
  const options = ['物理', '化学', '生物', '政治', '历史', '地理'];
  const userStore = useUserStore()
  const selectedOptions = ref([]);
  const scores= ref('')
  const scoresError = ref('');
  const ranks= ref('')
  const errorMessage = ref('')
  const toggleSelection = (option) => {
    if (selectedOptions.value.includes(option)) {
      // Deselect if already selected
      selectedOptions.value = selectedOptions.value.filter(
        (selected) => selected !== option
      );
    } else if (selectedOptions.value.length < 3) {
      // Select if less than 3 are selected
      selectedOptions.value.push(option);
    }
  };
  
const router = useRouter();

const analyze = () => {
  // 这里可以添加分析逻辑
    if(selectedOptions.value.length==3)
    { 
      // console.log(scores.value)
      // StoreuserRank(userStore.userID,scores.value).then((res)=>{
      //     console.log('res1',res)
      // });
      StoreuserInformation(userStore.userID,selectedOptions.value[0],selectedOptions.value[1],selectedOptions.value[2]).then((res)=>{
        console.log('res2',res)
        userStore.analyzetags = true;
      })

      console.log(userStore.analyzetags)
      router.push('/analyse/analyzedetail');
    }
     else{
        errorMessage.value = '请选择三门学科'
     }
 
};
const validateScores = () => {
  const score = parseFloat(scores.value);
  if (!scores.value) {
    scoresError.value = '分数不能为空';
  } else if (isNaN(score) || score < 100 || score > 750) {
    scoresError.value = '分数必须在 100 到 750 之间';
  } else {
    scoresError.value = '';
  }
};

onMounted(() => {
  const chartInstance = echarts.init(chart.value);

  const option = {
    title: {
    },
    tooltip: {
      trigger: 'axis',
    },
    xAxis: {
      type: 'category',
      data: ['60', '70', '80', '90', '100'], // 示例数据
      name: '分数',
    },
    yAxis: {
      type: 'value',
      name: '名次',
    },
    series: [
      {
        name: '名次',
        type: 'line',
        data: [100, 80, 60, 40, 20], // 示例数据
      },
    ],
  };

  chartInstance.setOption(option);
});
  </script>
  
  <style scoped>
  .error-message {
  color: red;
  font-size: 12px;
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
  
  .category {
    display: flex;
  }
  
  .category-item {
    padding: 5px 10px;
    background-color: #ff7f50;
    border-radius: 15px;
    color: white;
    cursor: pointer;
  }
  
  .options {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
  }
  
  .option-item {
    flex: 1;
    text-align: center;
    margin-left:5px;
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
    width: 45%;
    padding: 10px;
    margin-right: 10%;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .recommendation {
    margin-top: 20px;
    text-align: center;
  }
  
  .recommendation span {
    display: inline-block;
    width: 100px;
    height: 5px;
    background-color: #ffa500;
    margin-top: 10px;
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
  