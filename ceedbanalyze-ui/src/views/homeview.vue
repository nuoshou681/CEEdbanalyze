<template>
  <div class="page_main">
    <el-affix :offset="0">
      <top_menu_bar @update:activeMenu="updateActiveMenu" @login="login" :isLoggedIn="userStore.logintags"
        :activeMenu="activeMenu" />
    </el-affix>
    <Login v-if="isLoggedIn" @close="isLoggedIn = false" :getlogintag="getlogintag"/>
    <LoginSuccess v-if="logintag"/>
    <div class="basic_information">
      <div class="data_analyse">
          <span class="demonstration">
          </span>
          <el-carousel height="440px">
            <el-carousel-item v-for="item in pictureitems" :key="item">
              <img :src="item" style="width: 100%; height: 100%" />
            </el-carousel-item>
          </el-carousel>
      </div>
      <div class="carousel">
        <userInformation />
      </div>
    </div>

    <div class="more_option">
      <div class="icon-item" >
        <a href="https://www.sdzk.cn/Floadup/file/20240625/6385492724297110442689837.xls" target="_blank" style="text-decoration: none;">
        <img src="../img/表格.png" alt="春考志愿" />
        <div class="icon-label">一份一段表</div>
      </a>
      </div>
      <div class="icon-item"  @click="toggleChat">
        <img src="../img/aislogo.png" alt="高考小智" />
        <div class="icon-label" >高考小智</div>
      </div>
      <div class="icon-item" onclick="selectIcon(this)">
        <img src="../img/招生计划.png" alt="招生计划" />
        <div class="icon-label">招生计划</div>
      </div>
      <a href="https://www.sdzk.cn/Consultation.aspx?BCID=1072&CID=1073" style="text-decoration: none;">
      <div class="icon-item" onclick="selectIcon(this)">
        <img src="../img/咨询服务.png" alt="高考知道" />
        <div class="icon-label">高考资讯</div>
      </div>
    </a>
    </div>

    <Transition name="chat-window">
      <ChatWindow v-if="isChatVisible" @closeChatWindow="handleCloseChatWindow"/>
    </Transition>
    
    <footer class="footer">
      <div class="footer-content">
        <p>&copy; 2023 Your Company. All rights reserved.</p>
        <p>联系我们: contact@yourcompany.com</p>
      </div>
    </footer>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import top_menu_bar from '@/components/top_menu_bar.vue';
import ChatWindow from '@/components/ChatWindow.vue';
import Login from '@/components/Login.vue';
import { useUserStore } from '@/store/user';
import userInformation from '@/components/userInformation.vue';
import LoginSuccess from '@/components/LoginSuccess.vue';
const userStore = useUserStore();
const isLoggedIn = ref(false);
const activeMenu = ref('home');
const isChatVisible = ref(false);
const pictureitems = [
new URL('@/img/首页1.png', import.meta.url).href,
  new URL('@/img/首页2.png', import.meta.url).href,
  new URL('@/img/首页3.png', import.meta.url).href,
];
const toggleChat = () => {
  isChatVisible.value = !isChatVisible.value;
};
const handleCloseChatWindow =()=> {
  if(isChatVisible.value) {
    isChatVisible.value = false;
  }
}
const logintag = ref(false);
function getlogintag(data){
   logintag.value = data;
   if(logintag.value){
     isLoggedIn.value = false;
   }
}
function updateActiveMenu(menu) {
  activeMenu.value = menu;
}
function login() {
  isLoggedIn.value = true;

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
  width: 200px;
  margin-left: 10px;
}

.data_analyse {
  height: 440px;
  width: 750px;
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
.footer {
  background-color: #f8f9fa;
  padding: 20px 0 20px 0;
  text-align: center;
  bottom: 0;
  width: 100%;
}

.footer-content {
  max-width: 1200px;
  margin: 0 ;
}

.footer p {
  margin: 5px 0;
  color: #6c757d;
}

</style>