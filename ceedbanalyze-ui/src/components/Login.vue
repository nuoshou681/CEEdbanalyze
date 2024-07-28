<template>
      <div class="modal-content">
        <div class="left-section">
          <p>这里是介绍内容。</p>
        </div>
        
        <div class="right-section">
          <button class="close-button" @click="closeLogin">×</button> 
          <div class="tabs">
            <button :class="{ active: isLogin }" @click="isLogin = true">登录</button>
            <button :class="{ active: !isLogin }" @click="isLogin = false">注册</button>
                    
          </div>
          <form @submit.prevent="login" v-if="isLogin">
            <div class="login-model">
              <label for="username"></label>
              <input type="text" id="username" v-model="username" placeholder="请输入用户名" required>
            </div>
            <div class="login-model">
              <label for="password"></label>
              <input type="password" id="password" v-model="password" placeholder="请输入密码" required>
            </div>
            <button type="submit" class="login-button">登录</button>
          </form>
          <form @submit.prevent="register" v-else>
            <div class="register-model">
              <label for="new-username"></label>
              <input type="text" id="new-username" v-model="newUsername" placeholder="请输入用户名"  required>
            </div>
            <div class="register-model">
              <label for="new-password"></label>
              <input type="password" id="new-password" v-model="newPassword" placeholder="请输入密码" required>
            </div>
            <div class="register-model">
            <label for="confirm-password"></label>
           <input type="password" id="confirm-password" v-model="confirmPassword" placeholder="请再次输入密码" required>
            </div>
            <button type="submit" class="register-button">注册</button>
          </form>
        </div>
      </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const isLogin = ref(true);
  const username = ref('');
  const password = ref('');
  const newUsername = ref('');
  const newPassword = ref('');
  const confirmPassword = ref('');
  const login = () => {
    // 登录逻辑
    console.log('用户名:', username.value);
    console.log('密码:', password.value);
  };
  
  const register = () => {
    // 注册逻辑
    console.log('新用户名:', newUsername.value);
    console.log('新密码:', newPassword.value);
  };

  const emits = defineEmits(['close']);
const closeLogin = () => {
  emits('close');
}; 
  </script>
  
  <style scoped>
  .close-button {
    left: 90%;
    position: relative;
  }

  .login-model ,.register-model{
    margin-bottom: 20px;
    
  }
  .login-button,.register-button {
    width: 80%;
    position: relative;
    left:7%;
  }
  .login-model input,.register-model input{
    width:80%;
    height: 30px;
    position: relative;
    left:5%;
  }
  .login-modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .modal-content {
    background: rgb(92, 90, 90);
    padding: 20px;
    border-radius: 5px;
    width: 600px;
    height: 400px;
    display: flex;
    position: fixed;
    left: 30%;
    top:20%;
    z-index: 100;
  }
  
  .left-section {
    flex: 1;
    padding: 20px;
    background-color: #007bff;
  }
  
  
  .right-section {
    flex: 1;
  }
  
  .tabs {
    display: flex;
    
    margin-bottom: 20px;
  }
  
  .tabs button {
    background: none;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
  }
  
  .tabs button.active {
    font-weight: bold;
    border-bottom: 2px solid #000;
  }
  
  form {
    display: flex;
    flex-direction: column;
  }
  
  form div {
    margin-bottom: 10px;
  }
  
  form label {
    margin-bottom: 5px;
  }
  
  form input {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  form button {
    padding: 10px;
    background: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  form button:hover {
    background: #0056b3;
  }
  </style>