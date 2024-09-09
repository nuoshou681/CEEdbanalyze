<template>
    <div>
      <chartshow />
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { ElLoading } from 'element-plus';
  import 'element-plus/dist/index.css';
  import chartshow from '@/components/chartshow.vue';
  
  const loadingInstance = ref(null);
  
  onMounted(() => {
    // 显示加载状态
    loadingInstance.value = ElLoading.service({
      lock: true,
      text: '加载中...',
      background: 'rgba(255, 255, 255, 0.8)',
    });
    // 模拟网络请求
    setTimeout(() => {
      // 隐藏加载状态
      loadingInstance.value.close();
    }, 2000); // 2秒后隐藏加载圆圈
  });
  </script>
  
  <style>
  </style>