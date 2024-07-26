<template>
    <div class="page_main">
    
    <top_menu_bar @update:activeMenu = "updateActiveMenu"
    :activeMenu="activeMenu"/>
    <div class="basic_information">
       <div class="data_analyse">
  
    </div>
    <div class="carousel">
     
    </div>
    </div>
  
    <div class="more_option">
  
    </div> 
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  const activeMenu = ref('home');
  import top_menu_bar from '@/components/top_menu_bar.vue';
function updateActiveMenu(menu) {
    activeMenu.value = menu;
}
  </script>
  
  <style>
  .nav-item.active {
    color: #42b983; /* 示例: 激活状态的文本颜色 */
    font-weight: bold;
  }
  .basic_information {
    display: flex;
    flex-direction: row; /* 水平排列子元素 */
    background-color: #f5f5f5;
  }
  .more_option {
    height: 200px;
    width: 1200px;
    background-color: #0cbb55;
  
  }
  .carousel {
    height: 400px;
    width: 600px;
    background-color: aqua;
    margin-left: 50px;
  }
  .data_analyse {
    height: 400px;
    width: 500px;
    background-color:#582222;
  }
  
  .page_main {
  background-color: azure;
  width: 1200px;
  position: relative;
  left: 150px;
  }
  </style>