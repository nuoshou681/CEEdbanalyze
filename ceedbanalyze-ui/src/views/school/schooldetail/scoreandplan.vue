<template>
    <div class="score-page">
      <div class="title-container">
        <div class="title-marker"></div>
        <h3 class="title-text">专业分数线</h3>
      </div>
        <div class="plan">
          <div class="select-box">
            <el-select
      v-model="value"
      placeholder="年份"
      size="small"
      style="width: 65px"
    >
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        @click="getScorePlan(item.value)"
      />
    </el-select>
</div>
    <el-table :data="tableData" height="500" style="width: 100%">
    <el-table-column prop="majorName" label="专业名称" />
    <el-table-column prop="type" label="录取批次"/>
    <el-table-column :prop="year" label="专业分数" />
    <el-table-column prop="address" label="录取概率" />
  </el-table>
</div>
</div>
</template>
<script setup>
import { onMounted, ref,watch } from 'vue'
import {getMajorById} from '@/api/school'
const props = defineProps({
  schoolitem: {
    type: Object,
    required: true,
    default: () => ({
      name: '山东大学',
      introduction: '山东大学是一所历史悠久、学科齐全、实力雄厚、特色鲜明的教育部直属重点综合性大学，在国内外具有重要影响，2017年顺利迈入世界一流大学建设高校（A类）行列。山东大学前身是1901年创办的山东大学堂，被誉为中国近代高等教育起源性大学。其医学学科起源于1864年，开启近代中国高等医学教育之先河。从诞生起，学校先后历经了山东大学堂、国立青岛大学、国立山东大学、山东大学以及由原山东大学、山东医科大学、山东',
      id:3,

    }),
  },
});
onMounted(() => {
  getMajorById(props.schoolitem.id).then((res) => {
    tableData.value = res.data;
  });
})
watch(() => props.schoolitem, (newVal) => {
  getMajorById(newVal.id).then((res) => {
    tableData.value = res.data;
  });
});
const year = ref('score2023')
const value = ref('')
const tableData = ref([])
const getScorePlan = (value) => {
  getMajorById(props.schoolitem.id).then((res) => {
    tableData.value = res.data;
  });
  if(value == 'Option2'){
     //遍历tableData，将年份为2024的数据筛选出来重新赋值给tableData
      tableData.value = tableData.value.filter(item => item.score2023 !== null)
      year.value = 'score2023'
      console.log(tableData.value)
  }
  if(value == 'Option3'){
    tableData.value = tableData.value.filter(item => item.score2022 !== null)
    year.value = 'score2022'
    console.log(tableData.value)
  }
  if(value == 'Option4'){
    tableData.value = tableData.value.filter(item => item.score2021 !== null)
    year.value = 'score2021'
    console.log(tableData.value)
  }
  if(value == 'Option5'){
    tableData.value = tableData.value.filter(item => item.score2020 !== null)
    year.value = 'score2020'
    console.log(tableData.value)
  }
}
const options = [
  {
    value: 'Option1',
    label: '2024',
  },
  {
    value: 'Option2',
    label: '2023',
  },
  {
    value: 'Option3',
    label: '2022',
  },
  {
    value: 'Option4',
    label: '2021',
  },
  {
    value: 'Option5',
    label: '2020',
  },
]
    </script>
<style scoped>
    .score-page{
        background-color: #f0f0f0;
        width: 700px;
        height: auto;
        border-radius:  0 0 10px 10px;
        margin-top: 10px;
    }
    .plan{
        background-color: #ffffff;
        height: 600px;
        margin-top: 10px;
    }
    .select-box{
     float:right;
   
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