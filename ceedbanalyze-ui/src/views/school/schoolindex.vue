<template>
    <div class="school-information" v-if="schoolitem">
   <div><h1>{{ schoolitem.name }}</h1>
    {{ schoolitem.type1 }} {{ schoolitem.type2 }} {{ schoolitem.type3 }}
        </div>
    <p>招生官网：www.baidu.com</p>
    <p>招生电话：{{ schoolitem.phone }}</p>
    <p>招生邮箱：{{ schoolitem.website }}</p>
    <div class="school-more">
       <router-link to="/school/detail" class="school-tag" active-class="active-link" @click=" sendSchooldata()">基本详情</router-link>
        <router-link to="/school/scoreplan" class="school-tag" active-class="active-link" @click=" sendSchooldata()">分数计划</router-link>
        <router-link to="/school/schoolmajor" class="school-tag" active-class="active-link" @click=" sendSchooldata()">开设专业</router-link>
    </div>
    </div>
    <div v-else>
        <div class="school-information">
   <div><h1>山东大学</h1>
    本科 综合类 公办 211工程 985工程 双一流
        </div>
    <p>招生官网：www.baidu.com</p>
    <p>招生电话：0531-59626977</p>
    <p>招生邮箱：sdbkzs@email.sdu.edu.cn</p>
    <div class="school-more">
        <router-link to="/school/detail" class="school-tag" active-class="active-link" @click=" sendSchooldata()">基本详情</router-link>
        <router-link to="/school/scoreplan" class="school-tag" active-class="active-link" @click=" sendSchooldata()">分数计划</router-link>
        <router-link to="/school/schoolmajor" class="school-tag" active-class="active-link" @click=" sendSchooldata()">开设专业</router-link>
    </div>
    </div>
    </div>

</template>
<script setup>
import { computed, watch } from 'vue';
import eventBus from '@/api/eventBus';
// props
const props = defineProps({
    schoolitem:{
        type: Object,
        required: true
    }
})
function sendSchooldata(){
    eventBus.emit('schooldata', props.schoolitem);
}
// 计算属性，检测schoolitem是否发生变化，如果发生变化，发送schoolitem数据
const schoolitemWatcher = computed(() => props.schoolitem);

// 监听计算属性的变化
watch(schoolitemWatcher, (newVal, oldVal) => {
    if (newVal !== oldVal) {
        sendSchooldata();
    }
});

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