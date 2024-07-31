<template>
    <div class="page_main">
    
      <top_menu_bar @update:activeMenu = "updateActiveMenu"
      @login="login"
      :isLoggedIn="isLoggedIn"
      :userAvatar="userAvatar"
    :activeMenu="activeMenu"/>
    <Login v-if="isLoggedIn" @close="isLoggedIn = false"/>
    <div class="basic_information">
       <div class="data_analyse">
  
    </div>
    <div class="carousel">
     
    </div>
    </div>
  
    <div class="more_option">
  
    </div> 
    <FooterBar/>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  const activeMenu = ref('home');
  import top_menu_bar from '@/components/top_menu_bar.vue';
  import Login from '@/components/Login.vue';
  import FooterBar from '@/components/FooterBar.vue';
  const isLoggedIn = ref(false);
  const userAvatar = ref('');
function updateActiveMenu(menu) {
    activeMenu.value = menu;
}
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
  left: 130px;
  }
  </style>