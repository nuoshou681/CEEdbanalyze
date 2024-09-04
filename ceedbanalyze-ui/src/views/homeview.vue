<template>
  <div class="page_main">
    <el-affix :offset="0">
      <top_menu_bar @update:activeMenu="updateActiveMenu" @login="login" :isLoggedIn="userStore.logintags"
        :userAvatar="userAvatar" :activeMenu="activeMenu" />
    </el-affix>
    <Login v-if="isLoggedIn" @close="isLoggedIn = false" />
    <div class="basic_information">
      <div class="data_analyse">
        <div class="block text-center">
          <span class="demonstration">
          </span>
          <el-carousel height="440px">
            <el-carousel-item v-for="item in 4" :key="item">
              <h3 class="small justify-center" text="2xl">{{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
      <div class="carousel">
        <userInformation />
      </div>
    </div>

    <div class="more_option">
      <div class="icon-item" onclick="selectIcon(this)">
        <img src="https://via.placeholder.com/50" alt="春考志愿" />
        <div class="icon-label">春考志愿</div>
      </div>
      <div class="icon-item" onclick="selectIcon(this)">
        <img src="https://via.placeholder.com/50" alt="艺考专区" />
        <div class="icon-label">艺考专区</div>
      </div>
      <div class="icon-item" onclick="selectIcon(this)">
        <img src="https://via.placeholder.com/50" alt="高考小智" />
        <div class="icon-label">高考小智</div>
      </div>
      <div class="icon-item" onclick="selectIcon(this)">
        <img src="https://via.placeholder.com/50" alt="招生计划" />
        <div class="icon-label">招生计划</div>
      </div>
      <div class="icon-item" onclick="selectIcon(this)">
        <img src="https://via.placeholder.com/50" alt="专家讲志愿" />
        <div class="icon-label">专家讲志愿</div>
      </div>
      <div class="icon-item" onclick="selectIcon(this)">
        <img src="https://via.placeholder.com/50" alt="高考直播" />
        <div class="icon-label">高考直播</div>
      </div>
      <div class="icon-item" onclick="selectIcon(this)">
        <img src="https://via.placeholder.com/50" alt="高考知道" />
        <div class="icon-label">高考知道</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

import top_menu_bar from '@/components/top_menu_bar.vue';
import Login from '@/components/Login.vue';
import { useUserStore } from '@/store/user';
import userInformation from '@/components/userInformation.vue';
const userStore = useUserStore();
const isLoggedIn = ref(false);
const userAvatar = ref('');
const activeMenu = ref('home');
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
  color: #42b983;
  /* 示例: 激活状态的文本颜色 */
  font-weight: bold;
}

.basic_information {
  display: flex;
  flex-direction: row;
  /* 水平排列子元素 */
  background-color: #f5f5f5;
}



.carousel {
  height: 400px;
  width: 600px;
  background-color: aqua;
  margin-left: 10px;
}

.data_analyse {
  height: 440px;
  width: 600px;
  margin-left: 10px;
}

.page_main {
  background-color: azure;
  width: 1200px;
  position: relative;
  left: 138px;
}

.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.more_option {
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 200px;
  width: 1200px;
  background-color: #f5f5f5;
}

.icon-item {
  text-align: center;
  cursor: pointer;
  transition: transform 0.3s, background-color 0.3s;
  padding: 10px;
  border-radius: 10px;
}

.icon-item:hover {
  transform: translateY(-5px);
  background-color: #f0f0f0;
}

.icon-item img {
  width: 50px;
  height: 50px;
}

.icon-label {
  margin-top: 10px;
  font-size: 14px;
  color: #333;
}

.icon-item.selected {
  background-color: #ffe5b4;
  border-radius: 10px;
}
</style>