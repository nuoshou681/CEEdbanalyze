<template>
  <div class="page_main">
    <!-- 顶部菜单栏 -->
    <el-affix :offset="0">
      <top_menu_bar @update:activeMenu="updateActiveMenu" @login="login" :isLoggedIn="isLoggedIn"
        :userAvatar="userAvatar" :activeMenu="activeMenu" />
    </el-affix>
    <Login v-if="isLoggedIn" @close="isLoggedIn = false" />

    <div class="more-detal">
      <!-- 交互 专业详情 -->
      <el-scrollbar height="700px" class="major-detal">
        <majordetail :MajorDetail="MajorDetail" />
      </el-scrollbar>
    </div>

    <div class="major-search">
      <!-- 搜索框 -->
      <div class="search-container">
        <input type="text" placeholder="输入院校名称">
        <button type="submit">搜索</button>
      </div>
      <!-- 专业分类 -->
      <searchMajor />
    </div>

    <!-- 交互 专业列表 -->
    <el-scrollbar height="500px">
      <p v-for="item in MajorList" :key="item.id" class="scrollbar-demo-item"
        :class="{ 'selected-item': item.selected }" @click="handleItemClick(item)">{{ item.name }}</p>
    </el-scrollbar>


  </div>

</template>

<script setup>
import { ref } from 'vue';
import top_menu_bar from '@/components/top_menu_bar.vue';
import Login from '@/components/Login.vue';
import majordetail from './major/majordetail.vue';
import searchMajor from '@/components/searchMajor.vue';
import userApi from '@/api/user'

const activeMenu = ref('major');
const isLoggedIn = ref(false);
const userAvatar = ref('');
const MajorList = ref([
  {
    name: '计算机科学与技术', id: 1, selected: false, introduction: '计算机科学与技术是一门研究计算机系统、计算机软件及其应用的学科。', year: '四年',
    id: '020109T',
    degree: '工学学士',
  },
  {
    name: '软件工程', id: 2, selected: false, introduction: '软件工程是一门研究软件开发的学科。', year: '四年',
    id: '020109T',
    degree: '工学学士',
  },
  {
    name: '信息安全', id: 3, selected: false, introduction: '信息安全是一门研究信息保护的学科。', year: '四年',
    id: '020109T',
    degree: '工学学士',
  },
  {
    name: '物联网工程', id: 4, selected: false, introduction: '物联网工程是一门研究物联网技术的学科。', year: '四年',
    id: '020109T',
    degree: '工学学士',
  },
  {
    name: '数字媒体技术', id: 5, selected: false, introduction: '数字媒体技术是一门研究数字媒体技术的学科。', year: '四年',
    id: '020109T',
    degree: '工学学士',
  },
  {
    name: '网络工程', id: 6, selected: false, introduction: '网络工程是一门研究网络技术的学科。', year: '四年',
    id: '020109T',
    degree: '工学学士',
  },
  {
    name: '电子信息工程', id: 7, selected: false, introduction: '电子信息工程是一门研究电子信息技术的学科。', year: '四年',
    id: '020109T',
    degree: '工学学士',
  },
  {
    name: '通信工程', id: 8, selected: false, introduction: '通信工程是一门研究通信技术的学科。', year: '四年',
    id: '020109T',
    degree: '工学学士',
  },
]);
const MajorDetail = ref({
  name: '计算机科学与技术',
  introduction: '计算机科学与技术是一门研究计算机系统、计算机软件及其应用的学科。',
  year: '四年',
  id: '020109T',
  degree: '工学学士',
});

function updateActiveMenu(menu) {
  activeMenu.value = menu;
}

function login() {
  isLoggedIn.value = true;
  userAvatar.value = 'https://avatars.githubusercontent.com/u/6791502?v=4';
}

// 专业列表点击事件
const handleItemClick = (item) => {
  MajorList.value.forEach((major) => {
    major.selected = false;
  });
  item.selected = true;
  handleDetailClick(item);
};

// 专业详情点击事件
const handleDetailClick = (item) => {
  MajorDetail.value = item;
};

</script>

<style>
.major-search {
  width: auto;
  height: auto;
  max-width: 100%;
  /* 防止超出父容器 */
  max-height: 100%;
  /* 防止超出父容器 */
  margin-left: 20px;
  overflow: hidden;
  /* 隐藏溢出的内容 */
  transition: height 0.5s ease;
  /* 过渡动画 */
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
  left: 138px;
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

.selected-item {
  background: #42b983;
  color: #fff;
}
</style>