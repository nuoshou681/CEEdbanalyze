<template>
    <div v-if="visible" class="login-success">
      登录成功
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  
  const visible = ref(false);
  
  function showLoginSuccess() {
    visible.value = true;
    setTimeout(() => {
      visible.value = false;
    }, 1000);
  }
  
  onMounted(() => {
    // 模拟登录成功后调用 showLoginSuccess
    showLoginSuccess();
  });
  </script>
  
  <style scoped>
  .login-success {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 20px;
    background-color: #4caf50;
    color: white;
    border-radius: 5px;
    text-align: center;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    z-index: 1000;
  }
  </style>