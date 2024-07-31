<template>
            <div class="major-name">
        哲学
    </div>
    <div class="major-intro">
        <h2>
            专业介绍
        </h2>
    </div>
    <div class="major-flex">
     <div class="major-box"><p class="fontSize">本科</p>哲学</div>
     <div class="major-box"><p class="fontSize">四年</p>修学年限</div>
     <div class="major-box"><p class="fontSize">哲学学士</p>授予学位</div>
     <div class="major-box"><p class="fontSize">专业代码</p>103213T</div>
    </div>
    <div>
        <h2>详解</h2>

    </div>
</template>
<script setup>

</script>
<style scoped>

.fontSize {
    font-size: 20px;
    margin-bottom: 0;
  }
  .major-name {
        text-align: center;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 30px;
        font-weight: bold;
    }
    .major-flex {
        display: flex;
        justify-content: space-around;
        
    }
    .major-box{
      border-radius: 40%;
        background-color: rgb(236, 228, 217);
        width: 110px;
        text-align: center;
    }
</style>