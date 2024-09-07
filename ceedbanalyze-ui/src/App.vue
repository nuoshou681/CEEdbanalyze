<script setup>


</script>

<template>
  <div class="container">
    <router-view />
  </div>
</template>

<style scoped>
body {
  font-size: 16px;
}

/* 针对2K屏幕的样式 */
@media (min-width: 2560px) {
  body {
    font-size: 18px; /* 调整字体大小 */
  }

  .container {
    max-width: 1200px; /* 调整容器宽度 */
  }
}
</style>
