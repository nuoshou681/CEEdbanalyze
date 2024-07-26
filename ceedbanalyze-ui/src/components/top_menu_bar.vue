<template>
        <div class="top_group">
      <div class="top-nav">
        <div class="img-item"><img src="../img/高考通.png" alt=""></div>
        <router-link to="/" class="nav-item" :class="{ active: activeMenu === 'home' }" @click="activateMenu('home')">首页</router-link>
        <router-link to="/major" class="nav-item" :class="{ active: activeMenu === 'major' } " @click="activateMenu('major')">查专业</router-link>
        <router-link to="/school" class="nav-item" :class="{ active: activeMenu === 'school' }"  @click="activateMenu('school')">查学校</router-link>
        <router-link to="/analyse" class="nav-item" :class="{ active: activeMenu === 'analyse' }" @click="activateMenu('analyse')">高考志愿分析</router-link>
        <div class="search-bar">
      <el-input
        v-model="input"
        placeholder="查学校，查专业"
        size="large"
        class="search-input"
      />
      <el-button
        type="primary"
        icon="el-icon-search"
        size="large"
        @click="handleSearch"
        class="search-button"
      >
        搜索
      </el-button>
    </div>
        <!-- 登录之后显示用户头像 -->
        <div v-if="isLoggedIn" >
        <img :src="userAvatar" alt="用户头像" style="width: 30px; height: 30px; border-radius: 50%;"/>
        </div>
        <template v-else>
          <button class="login-btn" @click="login">登录</button>
        </template>
      </div>
    </div>
</template>
<script setup>
import { ref ,defineEmits,defineProps } from 'vue';
const isLoggedIn = ref(false);
const userAvatar = ref('');
const input = ref('');
const props = defineProps({
    activeMenu: String,
});
const emit = defineEmits(['update:activeMenu']);
function activateMenu(menu) {
    emit('update:activeMenu', menu);
}
function login() {
  // 模拟登录逻辑
  isLoggedIn.value = true;
  userAvatar.value = 'https://example.com/user-avatar.png'; // 假设的用户头像URL
}
</script>
<style scoped>
.nav-item.active {
  color: #42b983; /* 示例: 激活状态的文本颜色 */
  font-weight: bold;
}
.top_group {
  background-color: #f5f5f5;
}
.search-bar {
  display: flex;
  align-items: center;
}

.search-input {
  flex: 1;
}


.img-item img {
  width: 100px;
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
  border: 1px solid #ccc;
  padding: 5px 10px;
  border-radius: 15px;
  outline: none;
}

.login-btn {
  padding: 5px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.login-btn:hover {
  background-color: #0056b3;
}
</style>