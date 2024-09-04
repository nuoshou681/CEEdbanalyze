<template>
  <div class="overlay">
    <div class="message-box">
      请先完善个人信息
    </div>
  </div>
</template>
<script setup>
</script>
<style scoped>
.overlay {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  /* 暗色背景 */
  backdrop-filter: blur(1px);
  /* 模糊效果 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.message-box {
  background: white;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  font-size: 18px;
  border-radius: 5%;
  width: 300px;
  height: 200px;
  text-align: center;
}
</style>
