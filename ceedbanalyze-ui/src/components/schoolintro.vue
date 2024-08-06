<template>
  <div class="container" v-if="isVisible">
    <div class="info-box">
      <button class="close-button" @click="closeBox">×</button>
      <h2>学校简介</h2>
      <p>
        学校有4个校区，其中，崂山校区、鱼山校区和浮山校区共占地2400余亩；
        西海岸校区规划占地约2800亩，2022年9月一期建成启用。
        设有1个学部、20个学院和1个基础教学中心。
        现有在校生35400余人，其中本科生17200余人、硕士研究生14500余人、
        博士研究生3400余人、外国留学生330余人。
        教职工4048人，其中专任教师2167人，博士生导师702人，
        正高级专业技术人员771人、副高级专业技术人员1034人，
        中国科学院院士6人、中国工程院院士9人。
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const isVisible= ref(true)
const emits = defineEmits(['close']);
const closeBox = () => {
  emits('close');
}; 
</script>
  
  <style scoped>
  .container {

    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f0f0f0;
    width: 800px;
    height: 600px;
    display: flex;
    position: fixed;
    left: 22%;
    top:15%;
    z-index: 100;
  }
  
  .info-box {
    padding: 20px;
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  .close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
}
  </style>