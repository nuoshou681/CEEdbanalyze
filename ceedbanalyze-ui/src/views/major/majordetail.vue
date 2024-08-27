<template>
    <div class="major-name">
        {{MajorDetail.name}}
    </div>
    <div class="major-main">
        <h2 class="headline">
            专业介绍
        </h2>
        <div class="major-flex">
            <div class="major-box">
                <p class="fontSize">{{ type }}</p>{{ MajorDetail.name }}
            </div>
            <div class="major-box">
                <p class="fontSize">{{ MajorDetail.year }}</p>修学年限
            </div>
            <div class="major-box">
                <p class="fontSize">{{ MajorDetail.degree }}</p>授予学位
            </div>
            <div class="major-box">
                <p class="fontSize">专业代码</p>{{ MajorDetail.id}}
            </div>
        </div>
        <h2 class="headline">详解</h2>
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
        degree: String,        
    },
    // 本科还是专科
    type: String
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
    width: 750px;
    height: 50px;
    background-color: #f0f0f0;
    border-radius: 10px 10px 0 0;
}

.major-flex {
    display: flex;
    justify-content: space-around;
}

.major-box {

    width: 110px;
    text-align: center;
    background-color: #fff;
    border-radius: 10px;
}

.major-detail {
    width: 700px;
    padding: 0 10px 10px 10px;
    border-radius: 0 0 10px 10px;
}

.major-information {
    padding: 10px;
    background-color: #fff;
    border-radius: 0 0 10px 10px;
}

/* 将边框粗体显示 */
.major-main {
    border: 2px solid #f0f0f0;
}

.headline {
    margin: 0;
    padding: 10px;
}
</style>