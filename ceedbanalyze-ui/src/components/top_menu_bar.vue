<template>
  <div class="top_group">
    <div class="top-nav">
      <div class="img-item"><img src="../img/网站名称.png" alt=""></div>
      <router-link to="/" class="nav-item" :class="{ active: activeMenu === 'home' }"
        @click="activateMenu('home')">首页</router-link>
      <router-link to="/school/detail" class="nav-item" :class="{ active: activeMenu === 'school' }"
        @click="activateMenu('school')">查学校</router-link>
      <router-link to="/major" class="nav-item" :class="{ active: activeMenu === 'major' }"
        @click="activateMenu('major')">查专业</router-link>
      <router-link to="/analyse" class="nav-item" :class="{ active: activeMenu === 'analyse' }"
        @click="activateMenu('analyse')">高考志愿分析</router-link>
      <div v-if="userStore.logintags">
        <img src="../img/人物头像.png" alt="用户头像" style="width: 30px; height: 30px; border-radius: 50%;"  @click="clearuser" />
      </div>
      <template v-else>
        <button class="login-btn" @click="login">登录</button>
      </template>
    </div>
  </div>
</template>
<script setup>
import {useUserStore}from '../store/user';
const props = defineProps({
  activeMenu: String,
  isLoggedIn: Boolean,
});
const userStore = useUserStore();
const emit = defineEmits(['update:activeMenu', 'login']);
function activateMenu(menu) {
  emit('update:activeMenu', menu);
}
function login() {
  // 模拟登录逻辑
  emit('login');

}
function clearuser(){
  userStore.clearUser();
}
</script>
<style>
.nav-item.active {
  color: #42b983;
  /* 示例: 激活状态的文本颜色 */
  font-weight: bold;
}

.top_group {
  background-color: #f5f5f5;
  z-index: 300;
}

.img-item img {
  width: 200px;
  height: 50px;
}

.top-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 50px;
  background-color: #f5f5f5;
}

.nav-item {
  text-decoration: none;
  color: #333;
  margin: 0 15px;
}

.search-box {
  font-family: FontAwesome;
  background: #ffffff;
  height: 50px;
  border: 10px;
  border-radius: 20px;
  padding: 10px;
  opacity: 1;
  width: 310px;
  padding: 0 6px;
}

.search-btn {

  font-family: FontAwesome;
  font-size: 25px;
  color: black;
  float: right;
  width: 40px;
  height: 40px;
  border: 10px;
  border-radius: 40%;
  margin-top: 5px;
  background: #ffffff;
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
}

.search-txt {
  outline: none;
  padding-left: 15px;
  margin-top: 5px;
  font-size: 16px;
  line-height: 40px;
  width: 250px;
  height: 40px;

}
</style>