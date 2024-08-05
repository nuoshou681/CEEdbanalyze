<template>
    <div class="score-page">
        <div class="plan">
          <span style="font-size: 20px;font-weight: 1000;">专业分数线</span>
          <div class="select-box">
            <el-select v-model="value" placeholder="年份" style="width: 100px">
      <el-option
        v-for="item in option1"
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
    </div>
    </template>
<script setup>
import { ref } from 'vue'
const value = ref('')
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
    const option1 = [
  {
    value: '2024',
    label: '2024',
  },
  {
    value: '2023',
    label: '2023',
  },
  {
    value: '2022',
    label: '2022',
  },
  {
    value: '2021',
    label: '2021',
  },
  {
    value: '2020',
    label: '2020',
  }
]
    </script>
    <style scoped>
    .score-page{
        background-color: #f0f0f0;
        width: 730px;
        height: auto;
        padding: 10px;
        border-radius:  0 0 10px 10px;
    }
    .plan{
        background-color: #ffffff;
    }
    .select-box{
     float:right;

    }
    </style>