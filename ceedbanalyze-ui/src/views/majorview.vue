<template>
  <div class="page_main">

    <top_menu_bar @update:activeMenu="updateActiveMenu" @login="login" :isLoggedIn="isLoggedIn" :userAvatar="userAvatar"
      :activeMenu="activeMenu" />
    <Login v-if="isLoggedIn" @close="isLoggedIn = false" />
    <el-scrollbar height="700px" class="major-detal">
      <majordetail/>
    </el-scrollbar>
    <div class="major-search">

    </div>
    <el-scrollbar height="600px">
      <p v-for="item in 20" :key="item" class="scrollbar-demo-item">{{ item }}</p>
    </el-scrollbar>

    <div class="more-detal"></div>
    <FooterBar />
  </div>

</template>

<script setup>
import { ref } from 'vue';
import top_menu_bar from '@/components/top_menu_bar.vue';
import Login from '@/components/Login.vue';
import FooterBar from '@/components/FooterBar.vue';
import majordetail from './major/majordetail.vue';
const activeMenu = ref('major');
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
.major-search {
  background-color: blanchedalmond;
  width: 400px;
  height: 140px;
}

.major-list {
  width: 400px;
  height: 600px;
}

.major-detal {
  width: 750px;
  padding: 25px;
  float: right;
  /* position:relative;
    left:400px;
    bottom: 700px; */
}

.nav-item.active {
  color: #42b983;
  /* 示例: 激活状态的文本颜色 */
  font-weight: bold;
}

.page_main {
  background-color: azure;
  width: 1200px;
  position: relative;
  left: 150px;
}

.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}
</style>