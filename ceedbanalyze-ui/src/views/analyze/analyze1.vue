<template>
  <div>
    i am analyze1
    <LoadingSpinner/>
    <chartshow/>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import chartshow from '@/components/chartshow.vue';
import LoadingSpinner from '@/components/LoadingSpinner.vue';


const loading = ref(true);

onMounted(() => {
  // 模拟网络请求
  setTimeout(() => {
    loading.value = false;
  }, 2000); // 2秒后隐藏加载圆圈
});
</script>
<style>
</style>