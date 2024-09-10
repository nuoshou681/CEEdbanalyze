<template>
  <div class="page_main">
    <el-affix :offset="0">
      <top_menu_bar @update:activeMenu="updateActiveMenu" @login="login" :isLoggedIn="userStore.logintags"
         :activeMenu="activeMenu" />
    </el-affix>
    <Login v-if="isLoggedIn" @close="isLoggedIn = false" :getlogintag="getlogintag" />
    <LoginSuccess v-if="logintag" />

      <div class="analyse-page">
        <router-view/>
      </div>
      <div class="sidebar">
      <div class="personal-image">
      <img src="../img/用户默认头像1.png" alt="个人头像" class="centered-image" />
  </div>
  <p style="font-size: smaller;margin-left: 10px;">选科：{{ userStore.major1 }},{{ userStore.major2 }},{{ userStore.major3 }}</p>
    <p style="font-size: smaller;margin-left: 10px;">分数：{{ userStore.score }}</p>
    <p style="font-size: smaller;margin-left: 10px;">排名：{{ userStore.rank }}</p>
        <div class="select-page">
          <el-menu :default-active="activeMenugt" class="el-menu-vertical-demo" @select="selecthetag">
            <el-menu-item index="analyze1" >
              <router-link to="/analyze/analyzedetail1" class="menu">志愿分析</router-link>
            </el-menu-item>
            <el-menu-item index="analyse2">
              <router-link to="/analyze/analyzedetail2" class="menu">学校地域分析</router-link>
            </el-menu-item>
            <el-menu-item index="analyse3">
              <router-link to="/analyze/analyzedetail3" class="menu">分数排名</router-link> 
            </el-menu-item>
            <el-menu-item index="analyse4">
              <router-link to="/analyze/analyzedetail4" class="menu">专业分析</router-link> 
            </el-menu-item>
            <el-menu-item index="analyse5">
              <router-link to="/analyze/analyzedetail5" class="menu">高考及录取分析</router-link> 
            </el-menu-item>
            <el-menu-item index="analyse5">
              <router-link to="/analyze/analyzedetail6" class="menu">学科分析</router-link> 
            </el-menu-item>
          </el-menu>
        </div>
      </div>
      <footer class="footer">
      <div class="footer-content">
        <p>&copy; 2023 Your Company. All rights reserved.</p>
        <p>联系我们: contact@yourcompany.com</p>
      </div>
    </footer>
    <div v-if="userStore.logintags==false&&userStore.analyzetags==false">
    <Login v-if="isLoggedIn" @close="isLoggedIn = false" :getlogintag="getlogintag" />
    <LoginSuccess v-if="logintag" />
    <perfectmessage />
    </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import top_menu_bar from '@/components/top_menu_bar.vue';
import perfectmessage from '@/components/perfectmessage.vue';
import { useUserStore } from '@/store/user';
import Login from '@/components/Login.vue';
import LoginSuccess from '@/components/LoginSuccess.vue';
const userStore = useUserStore();
const activeMenu = ref('analyse');
const isLoggedIn = ref(false);
const logintag = ref(false);
function updateActiveMenu(menu) {
  activeMenu.value = menu;
}
function login() {
  isLoggedIn.value = true;
}
function getlogintag(data) {
  logintag.value = data;
  if (logintag.value) {
    isLoggedIn.value = false;
  }
}
const activeMenugt = ref('analyse1');
// selecthetag 动态高亮显示
function selecthetag(index) {
  activeMenugt.value = index;
}
</script>

<style>
.menu {
  text-decoration: none;
}

.personal-image {

padding-top: 20px;

}


.centered-image {
  display: block;
  margin: 0 auto; /* 确保图片居中 */
  width: 100px; /* 设置图片宽度 */
  height: 100px; /* 设置图片高度 */
  border-radius: 50%; /* 将图片切成圆形 */
  object-fit: cover; /* 确保图片内容适应容器 */
}

.personal-image p {
  margin-top: 10px; 
  margin-left: 30px;/* 添加一些间距，使文本与图片之间有一定的距离 */

}

.analyse-page {
  width: 1000px;
  height: auto;
  float: right;
}

.sidebar {
  width: 150px;
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
  height: 800px;
  left: 138px;
}

.centered-image {
  max-width: 100%;
  height: auto;
  border: 1px solid #5e5d5d;
}
.footer {
  background-color: #f8f9fa;
  padding: 30px 0 30px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;

}

.footer-content {
  max-width: 1200px;
  margin: 0;
}

.footer p {
  margin: 5px 0;
  color: #6c757d;
}
</style>