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
      placeholder="2023"
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
    <el-table-column :prop="score" label="专业分数" />
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
      name: '滨州医学院',
      introduction: '滨州医学院是山东省应用型本科高校、山东省省市共建高校、山东省博士学位授予立项建设单位。前身是始建于1946年的原国立山东大学医学院，1956年3月独立建制为青岛医学院，1970年10月整体搬迁至山东省惠民地区行署驻地北镇办学，1974年11月设立青岛医学院北镇分院，1981年9月改称北镇医学院，1983年3月随驻地更名为滨州医学院，2002年烟台校区投入使用。目前，学校已发展成为一所以医药学科为优势，残疾人高等教育、康复高等教育为特色，医、理、工、管、教等多学科门类渗透融合、协调发展的省属本科医学院校。',
      id:1,

    }),
  },
});
onMounted(() => {
  getMajorById(props.schoolitem.id).then((res) => {
    tableData.value = res.data;
    // 将score2023为空的剔除
    tableData.value = tableData.value.filter(item => item.score2023 !== null)
  });
})
watch(() => props.schoolitem, (newVal) => {
  getMajorById(newVal.id).then((res) => {
    tableData.value = res.data;
    tableData.value = tableData.value.filter(item => item.score2023 !== null)
  });
});
const score = ref('score2023')
const value = ref('')
const tableData = ref([])
const getScorePlan = (value) => {
  if(value == 'Option2'){
     //遍历tableData，将年份为2024的数据筛选出来重新赋值给tableData
      tableData.value = tableData.value.filter(item => item.score2023 !== null)
      score.value = 'score2023'

  }
  if(value == 'Option3'){
    tableData.value = tableData.value.filter(item => item.score2022 !== null)
    score.value = 'score2022'

  }
  if(value == 'Option4'){
    tableData.value = tableData.value.filter(item => item.score2021 !== null)
    score.value = 'score2021'

  }
  if(value == 'Option5'){
    tableData.value = tableData.value.filter(item => item.score2020 !== null)
    score.value = 'score2020'
 
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