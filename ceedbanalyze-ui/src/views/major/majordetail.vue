<template>
    <div class="major-name">
        哲学
    </div>
    <div class="major-main">
        <h2 class="headline">
            专业介绍
        </h2>
        <div class="major-flex">
            <div class="major-box">
                <p class="fontSize">本科</p>哲学
            </div>
            <div class="major-box">
                <p class="fontSize">四年</p>修学年限
            </div>
            <div class="major-box">
                <p class="fontSize">哲学学士</p>授予学位
            </div>
            <div class="major-box">
                <p class="fontSize">专业代码</p>103213T
            </div>
        </div>
        <h2 class="headline">详解</h2>
        <div class="major-detail">
            <p class="major-information">
                专业简介
                专业介绍：哲学研习中西现代哲学、中西哲学史、伦理学、逻辑学、人类学等方面的基本理论和知识，以世界本原为探究对象，研究自然、社会和人类最普遍的问题，探索人的价值观、人生观和世界观的问题。例如：世界的本质是什么，人类应该怎样活着，人类如何看待死亡等。该专业侧重于学术方面，选择需谨慎。
                关键词：马克思主义 三观 逻辑 伦理
                开设课程：《西方哲学史》、《中国哲学史》、《马克思主义哲学史》、《现代中国哲学》、《西方现代哲学》、《马克思主义哲学原理》、《技术哲学》、《自然哲学》、《现代西方伦理学》、《逻辑学》
                部分高校按以下专业方向培养：国学。
                未来就业：深造：考研或者保研，继续哲学方面的学习深造，完成硕士、博士学业后，成为大学教授或者哲学学者； 转行：向公务员、编辑、记者、教师等方向发展。
            </p>
        </div>
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
.major-main{
    border: 2px solid #f0f0f0;
}
.headline{
    margin: 0;
    padding: 10px;
}
</style>