<template>
  <div class="page_main">
    <el-affix :offset="0">
      <top_menu_bar @update:activeMenu="updateActiveMenu" @login="login" :isLoggedIn="userStore.logintags"
        :userAvatar="userAvatar" :activeMenu="activeMenu" />
    </el-affix>
    <div class="analyse-page">
      <router-view />
    </div>
    <div class="sidebar">
      <div class="personal-image">
        <img src="../img/用户默认头像1.png" alt="个人头像"  class="centered-image"/>
      </div>
      <div class="select-page">
        <el-menu :default-active="activeMenugt" class="el-menu-vertical-demo" @select="selecthetag">
          <el-menu-item index="analyse1"><router-link to="/analyse/analyzedetail" class="menu">数据分析1</router-link></el-menu-item>
          <el-menu-item index="analyse2">数据分析2</el-menu-item>
          <el-menu-item index="analyse3">数据分析3</el-menu-item>
          <el-menu-item index="analyse4">数据分析4</el-menu-item>
          <el-menu-item index="analyse5">数据分析5</el-menu-item>
          <el-menu-item index="report">报告</el-menu-item>
          <el-menu-item index="setting">设置</el-menu-item>
        </el-menu>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import top_menu_bar from '@/components/top_menu_bar.vue';
import { useUserStore } from '@/store/user';
import user from '@/api/user';
const userStore = useUserStore();
const activeMenu = ref('analyse');
function updateActiveMenu(menu) {
  activeMenu.value = menu;
}
const activeMenugt = ref('analyse1');
// selecthetag 动态高亮显示
function selecthetag(index) {
  activeMenugt.value = index;
}
</script>

<style>
.menu{
  text-decoration: none;
}
.personal-image {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 200px;

}
.analyse-page {
  width: 1000px;
  height: 600px;
  background-color: #9b1c74;
  float: right;
}

.sidebar {
  width: 200px;
  height: 600px;
  background-color: #ffffff;
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
  left: 138px;
}
.centered-image {
  max-width: 100%;
  height: auto;
  border: 1px solid #5e5d5d;
}
</style>