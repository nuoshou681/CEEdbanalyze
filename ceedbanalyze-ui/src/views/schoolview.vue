<template>
    <div class="page_main">
    
    <top_menu_bar @update:activeMenu = "updateActiveMenu"
      @login="login"
      :isLoggedIn="isLoggedIn"
      :userAvatar="userAvatar"
    :activeMenu="activeMenu"/>
    <Login v-if="isLoggedIn" @close="isLoggedIn = false"/>
    <div class="major-detal">
    
</div> 
  <div class="major-search">

</div>

<div class="major-list">
  
</div>

<div class="more-detal">

</div>
</div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import top_menu_bar from '@/components/top_menu_bar.vue';
  import Login from '@/components/Login.vue';
  const activeMenu = ref('school');
  function updateActiveMenu(menu) {
  activeMenu.value = menu;
}
const isLoggedIn = ref(false);
const userAvatar = ref('');
function login() {
   isLoggedIn.value = true;
    userAvatar.value = 'https://avatars.githubusercontent.com/u/6791502?v=4';
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