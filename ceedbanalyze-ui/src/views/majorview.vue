<template>
  <div class="page_main">

    <top_menu_bar @update:activeMenu="updateActiveMenu" @login="login" :isLoggedIn="isLoggedIn" :userAvatar="userAvatar"
      :activeMenu="activeMenu" />
    <Login v-if="isLoggedIn" @close="isLoggedIn = false" />
    <el-scrollbar height="700px" class="major-detal">
      <majordetail/>
    </el-scrollbar>
    <div class="major-search">
      <div class="search-container">
    <input type="text" placeholder="输入院校名称">
    <button type="submit">搜索</button>
    </div>
     <searchMajor/>
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
import searchMajor from '@/components/searchMajor.vue';
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
  width: auto;
  height: auto;
  max-width: 100%; /* 防止超出父容器 */
  max-height: 100%; /* 防止超出父容器 */
  margin-left: 20px;
  overflow: hidden; /* 隐藏溢出的内容 */
  transition:  height 0.5s ease; /* 过渡动画 */
}

.major-list {
  width: 400px;
  height: 600px;
  margin-left: 20px;
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
  left: 130px;
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
.search-container {
            margin-top: 20px;
            display: flex;
            align-items: center;
            border: 2px solid rgb(47, 106, 244);
            border-radius: 5px;
            overflow: hidden;
            width: 300px;
            margin-left: 10px;
        }
        .search-container input {
            border: none;
            padding: 10px;
            flex: 1;
            font-size: 14px;
        }
        .search-container input:focus {
            outline: none;
        }
        .search-container button {
            background-color: rgb(47, 106, 244);
            border: none;
            padding: 10px 20px;
            color: white;
            cursor: pointer;
            font-size: 14px;
        }
</style>