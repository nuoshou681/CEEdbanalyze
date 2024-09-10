<template>
  <div v-if="userStore.analyzetags">
    <LoadingSpinner/>
    <chartshow/>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import chartshow from '@/components/chartshow.vue';
import LoadingSpinner from '@/components/LoadingSpinner.vue';
import { useUserStore } from '@/store/user';
const userStore = useUserStore();
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