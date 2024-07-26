<template>
    <div class="page_main">
    
    <top_menu_bar @update:activeMenu = "updateActiveMenu"
    :activeMenu="activeMenu"/>
  
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import top_menu_bar from '@/components/top_menu_bar.vue';
  const activeMenu = ref('school');
  function updateActiveMenu(menu) {
  activeMenu.value = menu;
}
  </script>
  
  <style>
  .nav-item.active {
    color: #42b983; /* 示例: 激活状态的文本颜色 */
    font-weight: bold;
  }
  .page_main {
  background-color: azure;
  width: 1200px;
  position: relative;
  left: 150px;
  }
  </style>