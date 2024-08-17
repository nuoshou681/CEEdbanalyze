<template>

  
  <div class="container" v-if="isVisible">
  <button class="close-button" @click="closeBox">×</button>  
    <div class="info-box"> 
      <h2>学校简介</h2>
   <el-scrollbar max-height="550px">
      <p>
      {{ schoolinfromation }}
      </p>
  </el-scrollbar>
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
const props= defineProps({
  schoolinfromation: {
    type: String,
    required: true,
    default: '山东大学是一所好大学',
  },
});
</script>
  
  <style scoped>
  .container {
    padding:10px 0 10px 0;
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
    padding: 0 20px 0 20px;
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  .close-button {
  position: absolute;
  top: 2px;
  right: 2px;
  background: none;
  border: none;
  font-size: 30px;
  cursor: pointer;
}
  </style>