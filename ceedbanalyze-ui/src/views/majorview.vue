<template>
  <div class="page_main">
    <!-- 顶部菜单栏 -->
    <el-affix :offset="0">
      <top_menu_bar @update:activeMenu="updateActiveMenu" @login="login" :isLoggedIn="userStore.logintags"
        :activeMenu="activeMenu" />
    </el-affix>
    <Login v-if="isLoggedIn" @close="isLoggedIn = false"  :getlogintag="getlogintag"/>
    <LoginSuccess v-if="logintag"/>
    <div class="more-detal">
      <!-- 交互 专业详情 -->
      <div class="major-detal">
        <majordetail :MajorDetail="MajorDetail" :type="selectedlevel" :category="selectedcategory"/>
      </div>
    </div>
    
    <div class="major-search">
      <!-- 搜索框 -->
      <div class="search-container">
        <input type="text" placeholder="输入专业名称" v-model="schoolName">
        <button type="submit" @click="handleSearch">搜索</button>
      </div>
      <!-- 专业分类 -->
      <searchMajor @update:selected-category="handleSelectedCategory" />
    </div>

    <!-- 交互 专业列表 -->

      <div class="sider-bar">
        <p v-for="item in paginatedMajorList" :key="item.id" class="scrollbar-demo-item"
          :class="{ 'selected-item': item.selected }" @click="handleItemClick(item)">{{ item.name }}</p>
        <el-pagination class="Pagination" @current-change="handleCurrentChange" :current-page="currentPage"
          :page-sizes="[10, 20, 30, 40]" :page-size="pageSize" layout="prev, pager, next, jumper" size="small"
          :total="majorlen" />   
      </div>
    <footer class="footer">
      <div class="footer-content">
        <p>&copy; 2023 Your Company. All rights reserved.</p>
        <p>联系我们: contact@yourcompany.com</p>
      </div>
    </footer>
  </div>

</template>

<script setup>
import { ref, watch } from 'vue';
import top_menu_bar from '@/components/top_menu_bar.vue';
import Login from '@/components/Login.vue';
import majordetail from './major/majordetail.vue';
import searchMajor from '@/components/searchMajor.vue';
import userApi from '@/api/user'
import { computed } from 'vue';
import { useUserStore } from '@/store/user';
import LoginSuccess from '@/components/LoginSuccess.vue';
const userStore = useUserStore();
const activeMenu = ref('major');
const isLoggedIn = ref(false);
const MajorList = ref([]);
const MajorDetail = ref({});
const schoolName = ref('');
const currentPage = ref(1);
const pageSize = ref(10);
const selectedlevel = ref('本科');
const selectedcategory = ref('全部');
const majorlen = ref(0);
const logintag = ref(false);
// 分页专业列表
const paginatedMajorList = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return MajorList.value.slice(start, end);
});
function getlogintag(data){
   logintag.value = data;
   if(logintag.value){
     isLoggedIn.value = false;
   }
}

// 函数1: 登陆函数
function login() {
  isLoggedIn.value = true;
}
function updateActiveMenu(menu) {
  activeMenu.value = menu;
}
// 函数2: 专业列表点击事件
const handleItemClick = (item) => {
  MajorList.value.forEach((major) => {
    major.selected = false;
  });
  item.selected = true;
  // 异步请求2: 专业详情点击事件: 请求后端发送专业详情数据 将数据reponse以参数形式传入 handleDetailClick(response)

  handleDetailClick(item);
};
// 函数3: 专业详情点击事件
const handleDetailClick = (item) => {
  MajorDetail.value = item;
};
// 函数4: 改变页码
const handleCurrentChange = (page) => {
  currentPage.value = page;
};
// 函数5: 搜索专业的专业门类
function handleSelectedCategory(level, category) {
  console.log('-传递的参数level为: ', level)
  console.log('-传递的参数category为: ', category)
  if (category == '全部') {
    selectedcategory.value = '';
  } else {
    selectedcategory.value = category;
  }
  selectedlevel.value = level;
  // 获取专业的数量并渲染成分页
  userApi.cout(selectedlevel.value, selectedcategory.value).then((response) => {
    majorlen.value = response.data;
  });
  // console.log('搜索的专业门类', selectedlevel.value, selectedcategory.value);
  userApi.search(selectedlevel.value, selectedcategory.value, 0, currentPage.value * 10).then((response) => {
    if (response.data == '') {
      // console.log('搜索的专业门类为空');
      MajorList.value = [];
      return;
    }
    // console.log('搜索的专业门类', response.data);
    MajorList.value = response.data;
    MajorDetail.value = response.data[0];
    MajorList.value[0].selected = true;
  });
}
watch(currentPage, (oldVal, newVal) => {
  userApi.search(selectedlevel.value, selectedcategory.value, 0, oldVal*10).then((response) => {
    if (response.data == '') {
      // console.log('搜索的专业门类为空');
      MajorList.value = [];
      return;
    }
    // console.log('搜索的专业门类', response.data);
    MajorList.value = response.data;
    MajorDetail.value = response.data[0];
    MajorList.value[0].selected = true;
  });
})
// 函数6: 按照专业名字搜索
function handleSearch() {
  // console.log('搜索的院校名字', schoolName.value);
  userApi.fingByName(schoolName.value, 0, 1560).then((response) => {
    MajorList.value = response.data;
    MajorDetail.value = response.data[0];
    MajorList.value[0].selected = true;
    majorlen.value = MajorList.value.length;
  });
}

</script>

<style>
.sider-bar {
  width: 380px;
}
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
  margin-top: 50px;
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
  height: 1000px;
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

.Pagination {
  margin: 10px;
  display: flex;
  justify-content: center;
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