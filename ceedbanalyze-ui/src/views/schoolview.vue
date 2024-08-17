<template>
  <div class="page_main">

    <el-affix :offset="0">
      <top_menu_bar @update:activeMenu="updateActiveMenu" @login="login" :isLoggedIn="isLoggedIn"
        :userAvatar="userAvatar" :activeMenu="activeMenu" />
    </el-affix>
    <Login v-if="isLoggedIn" @close="isLoggedIn = false" />
    <div class="school-detal">
      <schoolindex :schoolitem="schoolitem" />
    </div>

    <div class="school-search">
      <searchSchool />
    </div>
    <div class="scroll-bar">
      <el-scrollbar height="500px">
        <p v-for="item in paginatedSchoolList" :key="item.id" class="scrollbar-demo-item"
          :class="{ 'selected-item': item.selected }" @click="handleItemClick(item)">{{ item.name }}</p>
      </el-scrollbar>
      <el-pagination class="Pagination" @current-change="handleCurrentChange" :current-page="currentPage"
          :page-size="pageSize" layout="prev, pager, next, jumper"
          :total="schoolitems.length" size = "small"/>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import top_menu_bar from '@/components/top_menu_bar.vue';
import Login from '@/components/Login.vue';
import schoolindex from './school/schoolindex.vue';
import searchSchool from '@/components/searchSchool.vue';
import { onMounted,computed } from 'vue';
import { getAllSchool, getSchool } from '@/api/school';

const currentPage = ref(1);
const pageSize = ref(10);
const selectedCategory = ref('全部')
const paginatedSchoolList = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return schoolitems.value.slice(start, end);
});
const handleCurrentChange = (page) => {
  currentPage.value = page;
};



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
const schoolitems = ref([]);
const schoolitem = ref({});
const handleItemClick = (item) => {
  schoolitems.value.forEach(schoolitem => {
    schoolitem.selected = false;
  })
  item.selected = true;
  handleDetailClick(item);
};

// 专业详情点击事件
const handleDetailClick = (item) => {
  schoolitem.value = item;
};
function getschool(){
  getSchool(1,10).then((res) => {
    schoolitems.value = res.data;
  });
}
onMounted(() => {
  getAllSchool().then((res) => {
    schoolitems.value = res.data;
  });
  //将第一个学校信息显示在页面上
  schoolitem.value = schoolitems.value[0];
});
</script>

<style scoped>
.scroll-bar {
  width: 400px;
  height: 500px;
}

.nav-item.active {
  color: #42b983;
  /* 示例: 激活状态的文本颜色 */
  font-weight: bold;
}

.page_main {
  margin-top: 0;
  background-color: azure;
  width: 1200px;
  position: relative;
  left: 138px;
  height: 1200px;
}

.scrollbar-demo-item {
  display: flex;
  align-items: center;
  padding-left: 20px;
  height: 50px;
  width: 350px;
  margin: 10px;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}

.school-search {
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

.school-detal {
  width: 750px;
  padding: 25px;
  float: right;
  /* position:relative;
    left:400px;
    bottom: 700px; */
}

.search-container {
  margin-top: 10px;
  display: flex;
  align-items: center;
  border: 2px solid rgb(47, 106, 244);
  border-radius: 5px;
  overflow: hidden;
  width: 300px;
  margin-left: 20px;
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
.Pagination {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
</style>