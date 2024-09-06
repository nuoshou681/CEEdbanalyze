<template>
    <div class="container">
      <div class="header">
        <div class="title">信息完善</div>
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
        <input type="text" placeholder="输入分数">
        <input type="text" placeholder="输入位次">
      </div>
      <div class="recommendation-button">
        <button>开始分析</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const options = ['物理', '化学', '生物', '政治', '历史', '地理'];
  const selectedOptions = ref([]);
  
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
  </script>
  
  <style scoped>
  .container {
    width: 550px;
    height: 400px;
    border-radius: 10px;
    background-color: #f5f5f5;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
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
  