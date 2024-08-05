<template>
    <div class="major-page">
       <div class="major-list">
        <span style="font-size: 20px;font-weight: 1000;">学校专业</span>
        <div>
<el-table :data="tableData" height="500" style="width: 100%">
    <el-table-column prop="date" label="专业名称" width="180" />
    <el-table-column prop="name" label="学制" width="180" />
    <el-table-column prop="address" label="学费" />
    <el-table-column prop="address" label="录取概率"/>
  </el-table>
        </div>
       </div>
    </div>
    </template>
    <script setup>

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
  {
    date: '2016-05-08',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-06',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-07',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
    </script>
    <style scoped>
    .major-page{
        background-color: #f0f0f0;
        width: 730px;
        height: auto;
        padding: 10px;
        border-radius:  0 0 10px 10px;
    }
.major-list{
    background-color: #ffffff;;
}
    </style>