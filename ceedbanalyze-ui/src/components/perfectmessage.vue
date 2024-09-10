<template>
  <div class="overlay">
    <div class="message-box">
      请登陆并完善个人信息
    </div>
  </div>
</template>
<script setup>
import { onMounted } from 'vue';
onMounted(() => {
   console.log('mounted');
});
</script>
<style scoped>
.overlay {
  position: fixed; /* 绝对定位 */
  top:75px;
  width: 1200px;
  height: 100%;
  background: rgba(0, 0, 0, 0.3); /* 更轻的暗色背景 */
  backdrop-filter: blur(5px); /* 更明显的模糊效果 */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100; /* 确保在最上层 */
}

.message-box {
  background: rgba(255, 255, 255, 0.8); /* 半透明白色背景 */
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  width: 200px;
  height: 100px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  font-size: 18px;
  border-radius: 10px; /* 圆角 */
}
</style>
