<template>
    <div class="major-page">
        <div class="title-container">
        <div class="title-marker"></div>
        <h3 class="title-text">学校专业</h3>
      </div>
        <div class="major-list">
            <el-table :data="MajorData" height="500" style="width: 100%">
                <el-table-column prop="majorName" label="专业名称" width="180" />
                <el-table-column prop="majorLimit" label="专业限制" width="180" />
                <el-table-column prop="address" label="学费" width="180" />
                <el-table-column prop="address" label="录取概率" width="150" />
            </el-table>
        </div>
    </div>
</template>
<script setup>
import { ref, onMounted, watch } from 'vue'
import { getMajorById } from '@/api/school'
const props = defineProps({
    schoolid: {
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
.major-page {
    background-color: #f0f0f0;
    width: 700px;
    height: auto;
    border-radius: 0 0 10px 10px;
    margin-top: 10px;
}

.major-list {
    background-color: #ffffff;
    margin-top: 10px;
    width: 700px;
}
.title-container {
  display: flex;
  align-items: center;

  .title-marker {
    width: 5px;
    height: 24px;
    background-color:rgb(21, 208, 130); /* 蓝色标记的颜色 */
    margin-right: 8px;
    margin-left: 0;
  }

  .title-text {
    font-weight: bold;
    margin: 0;
    margin-right: 16px;
  }

  .title-line {
    flex-grow: 1;
    height: 1px;
    background-color: #ccc; /* 线条的颜色 */
  }
}
</style>