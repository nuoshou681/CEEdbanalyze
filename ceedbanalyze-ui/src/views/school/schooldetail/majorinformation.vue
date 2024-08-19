<template>
    <div class="major-page">
       <div class="major-list">
        <span style="font-size: 20px;font-weight: 1000;">学校专业</span>
        <div>
<el-table :data="MajorData" height="500" style="width: 100%">
    <el-table-column prop="majorName" label="专业名称" width="180" />
    <el-table-column prop="majorLimit" label="专业限制" width="180" />
    <el-table-column prop="address" label="学费" width="180" />
    <el-table-column prop="address" label="录取概率" width="150"/>
  </el-table>
        </div>

       </div>
    </div>
    </template>
    <script setup>
import { ref,onMounted ,watch} from 'vue'
import {getMajorById} from '@/api/school'
const props = defineProps({
    schoolid:{
        type: Number,
        required: true
    }
})
watch(() => props.schoolid, (newVal) => {
    getMajorById(newVal).then((res) => {
        MajorData.value = res.data;
    });
});
const MajorData = ref([])
onMounted(() => {
 getMajorById(props.schoolid).then(res => {
     MajorData.value = res.data
     console.log(res.data)
 })
})
    </script>
    <style scoped>
    .major-page{
        background-color: #f0f0f0;
        width: 700px;
        height: auto;
        border-radius:  0 0 10px 10px;
    }
.major-list{
    background-color: #ffffff;
    margin-top: 20px;
}
.example-pagination-block + .example-pagination-block {
  margin-top: 10px;
}
.example-pagination-block .example-demonstration {
  
  margin-bottom: 16px;
}

    </style>