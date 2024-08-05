<template>
    <div class="school-information">
   <div><h1>中国海洋大学</h1>本科
        综合类
        公办
        211工程
        985工程
        双一流</div>
    <p>招生官网：http://bkzs.ouc.edu.cn/</p>
    <p>招生电话：0532-66782426</p>
    <p>招生邮箱：ouczsb@ouc.edu.cn</p>
    <div class="school-more">
       <router-link to="/school/detail" class="school-tag" active-class="active-link">基本详情</router-link>
        <router-link to="/school/scoreplan" class="school-tag" active-class="active-link">分数计划</router-link>
        <router-link to="/school/schoolmajor" class="school-tag" active-class="active-link">开设专业</router-link>
    </div>
    </div>
</template>
<script setup>
import { ref ,onMounted} from 'vue';
onMounted(() => {
// 默认加载第一个子组件
})
</script>
<style scoped>
.school-tag{
    text-decoration: none; 
    color: inherit; 
}
.school-tag.active-link {
  font-weight: bold; /* 激活状态的样式，可以根据需要调整 */
  color: rgb(21, 208, 130); /* 激活状态的颜色，可以根据需要调整 */
}
h1{
    margin-top: 0;
    margin-bottom: 2px;
}

p{
    line-height: 10px;
    font-size: 12px;
}
    .school-more{
        display: flex;
        justify-content: space-around;
        margin-bottom: 0;
    }
    .school-information{
        background-color: #f0f0f0;
        width: 700px;
        padding: 10px 25px 5px 25px;
        border-radius: 10px 10px 0 0;
    }
</style>