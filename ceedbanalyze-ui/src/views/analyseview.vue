<template>
    <div class="page_main">
    
    <top_menu_bar @update:activeMenu = "updateActiveMenu"
    :activeMenu="activeMenu"/>
    <div class="analyse-page">
    <router-view/>
    </div>
    <div class="sidebar">
     
    </div>
     <FooterBar/>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import top_menu_bar from '@/components/top_menu_bar.vue';
  import FooterBar from '@/components/FooterBar.vue';
  const activeMenu = ref('analyse');
  function updateActiveMenu(menu) {
  activeMenu.value = menu;
}
  </script>
  
  <style>
  .analyse-page {
    width: 1000px;
    height: 600px;
    background-color: #9b1c74;
    float:right;
  }
  .sidebar{
    width:200px;
    height:600px;
    background-color: #493c3c;
  }
  .nav-item.active {
    color: #42b983; /* 示例: 激活状态的文本颜色 */
    font-weight: bold;
  }
  .page_main {
  background-color: azure;
  width: 1200px;
  position: relative;
  left: 130px;
  }
  </style>