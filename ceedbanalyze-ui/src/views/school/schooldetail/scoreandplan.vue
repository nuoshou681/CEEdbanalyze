<template>
    <div class="score-page">
        <div class="plan">
          <span style="font-size: 20px;font-weight: 1000;">专业分数线</span>
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
      />
    </el-select>
          </div>
          <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="date" label="专业名称" />
    <el-table-column prop="name" label="录取批次"/>
    <el-table-column prop="address" label="专业分数" />
    <el-table-column prop="address" label="录取概率" />
  </el-table>
        </div>
        <div class="pagination">
          <div>
          <el-pagination layout="prev, pager, next" :total="100" :pager-count="5"  :page-size="20" size = "small" />
        </div>  
          </div>
          
        
    </div>
    </template>
<script setup>
import { ref } from 'vue'
    const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
const value = ref('')

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
        margin-top: 20px;
    }
    .plan{
        background-color: #ffffff;
    }
    .select-box{
     float:right;
   
    }
    .example-pagination-block + .example-pagination-block {
  margin-top: 10px;
}
.example-pagination-block .example-demonstration {
  
  margin-bottom: 16px;
}
.pagination{
  position: relative;
  width: 700px;
  background-color: #ffffff;
   & > div {
    position: relative;
    left:300px;
}
}
    </style>