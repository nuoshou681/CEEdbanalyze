<template>
    <div class="major-name">
        {{MajorDetail.name}}
    </div>
    <div class="major-main">
        <div class="title-container">
        <div class="title-marker"></div>
        <h2 class="title-text">专业介绍</h2>
      </div>
        <div class="major-flex">
            <div class="major-box">
                <p class="fontSize">{{ type }}</p>{{ MajorDetail.name }}
            </div>
            <div class="major-box">
                <p class="fontSize">{{ MajorDetail.year }}</p>修学年限
            </div>
            <div class="major-box">
                <p class="fontSize">{{ degree }}</p>授予学位
            </div>
            <div class="major-box">
                <p class="fontSize">专业代码</p>{{ MajorDetail.id}}
            </div>
        </div>
        <div class="title-container">
        <div class="title-marker"></div>
        <h2 class="title-text">详情</h2>
      </div>
        <div class="major-detail">
            <p class="major-information">{{ MajorDetail.introduction }}</p>
        </div>
    </div>

</template>
<script setup>
const props = defineProps({
    MajorDetail: {
        name: String,
        introduction: String,
        year: String,
        id: String,  
    },
    // 本科还是专科
    type: String,
    degree: String, 
})
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
    padding-top: 50px;
    width: 750px;
    height: 50px;
    background-color: #f0f0f0;
    border-radius: 10px 10px 0 0;
}

.major-flex {
    display: flex;
    justify-content: space-around;
    background-color: #f0f0f0;
}

.major-box {

    width: 110px;
    text-align: center;
    background-color: #fff;
    border-radius: 10px;
}

.major-detail {
    width: 730px;
    height: 400px;
    padding: 0 10px 10px 10px;
    border-radius: 0 0 10px 10px;
    background-color: #f0f0f0;
}

.major-information {
    margin-top: 0;
    padding: 10px;
    height: 380px;
    background-color: #fff;
    border-radius: 0 0 10px 10px;
}

/* 将边框粗体显示 */
.major-main {
    border: 2px solid #f0f0f0;
    height: 600px;
}

.headline {
    margin: 0;
    padding: 10px 10px 0 10px;
    background-color: #f0f0f0;
}
.title-container {
  display: flex;
  align-items: center;
  background-color: #f0f0f0;
  .title-marker {
    width: 5px;
    height: 24px;
    background-color:rgb(21, 208, 130); /* 蓝色标记的颜色 */
    margin-right: 8px;
    margin-left: 0;
  }

  .title-text {
    font-weight: bold;
    margin-right: 16px;
    
  }

  .title-line {
    flex-grow: 1;
    height: 1px;
    background-color: #ccc; /* 线条的颜色 */
  }
}
</style>