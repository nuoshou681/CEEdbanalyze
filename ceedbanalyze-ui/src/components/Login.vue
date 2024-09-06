<template>
      <div class="modal-content">
        <div class="left-section">
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
            <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
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
            <div class="confirm-code">
              <input type="text" id="confirm-code" placeholder="请输入验证码" v-model="confirmcode" required >
              <img :src="captcha" @click="getcaptcha" class="captcha-code"/>
            </div>
            <button type="submit" class="register-button">注册</button>
          </form>
          
        </div>
      </div>
  </template>
  
  <script setup>
  import { ref,onMounted } from 'vue';
  import{useUserStore} from '@/store/user';
  import{userlogin,GetCaptcha,userregister} from '@/api/login';
  import axios from 'axios';

  const props = defineProps({
    getlogintag:{
      type:Function,
      required:true
    }
  });
  const confirmcode = ref('');
  const isLogin = ref(true);
  const username = ref('');
  const password = ref('');
  const newUsername = ref('');
  const newPassword = ref('');
  const confirmPassword = ref('');
  const userStore = useUserStore();
  const errorMessage = ref(''); // 用于存储错误信息
  const captcha = ref(''); // 用于存储验证码图片地址
  const emits = defineEmits(['close']);
const closeLogin = () => {
  emits('close');
}; 
  const getcaptcha = () => {
      GetCaptcha().then((res) => { 
      captcha.value = window.URL.createObjectURL(res.data)
      console.log('获取验证码成功:', res);
      
      let code = sessionStorage.getItem("code");
      console.log('code:', code);
    });
  };
  onMounted(() => {
    getcaptcha();
  });
  const login = () => {
      userlogin(username.value, password.value).then((res) => {
      userStore.logintags = true
      props.getlogintag(userStore.logintags);
      console.log('登录成功:', res);
    }).catch((err) => {
      errorMessage.value = '密码错误，请重试'; // 设置错误信息
      console.log('登录失败:', err);
    });
      
  };


function register(){

 const response =  axios.post('http://localhost:5173/user/login',{
    username:newUsername.value,
    password:newPassword.value,
    inputCode:confirmcode.value
  },{
    headers:{
      'Content-Type':'application/x-www-form-urlencoded'
    },
    withCredentials:true
  });
  console.log('response:',response);
  //  userregister(newUsername.value, newPassword.value,confirmcode.value).then((res) => {
  //   console.log('注册成功:', res);
  // }).catch((err) => {
  //   console.log('注册失败:', err);
  // });

}
  </script>
  
  <style scoped>
  .captcha-code {
    
    width: 120px;
    height: 30px;
    position: relative;
    left: 10px;
  }
  #confirm-code {
    width: 30%;
  }
  .confirm-code {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    position: relative;
    left:5%;
  } 
  .error-message {
  color: red;
  position: relative;
  left:7%;
  font-size: smaller;
}
  .close-button {
    left: 90%;
    position: relative;
    background: rgb(213, 212, 212);
    border:none;
  }
.close-button:hover {
  background: #ccc;
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
    background: rgb(213, 212, 212);

    border-radius: 5px;
    width: 600px;
    height: 400px;
    display: flex;
    position: fixed;
    left: 30%;
    top:20%;
    z-index: 300;
  }
  
  .left-section {
    flex: 1;

    background-image: url(../img/登录背景图.png);
    overflow: hidden;
  }
  
  
  .right-section {
    flex: 1;
  }
  
  .tabs {
    display: flex;
    
    margin-bottom: 20px;
  }
  
  .tabs button {
    background: rgb(213, 212, 212);
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