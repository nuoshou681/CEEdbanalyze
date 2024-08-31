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
    default: '滨州医学院是山东省应用型本科高校、山东省省市共建高校、山东省博士学位授予立项建设单位。前身是始建于1946年的原国立山东大学医学院，1956年3月独立建制为青岛医学院，1970年10月整体搬迁至山东省惠民地区行署驻地北镇办学，1974年11月设立青岛医学院北镇分院，1981年9月改称北镇医学院，1983年3月随驻地更名为滨州医学院，2002年烟台校区投入使用。目前，学校已发展成为一所以医药学科为优势，残疾人高等教育、康复高等教育为特色，医、理、工、管、教等多学科门类渗透融合、协调发展的省属本科医学院校。',
  },
});
</script>
  
  <style scoped>
  .container {
    padding:0 0 10px 0;
    display: flex;
    background-color: #f0f0f0;
    width: 800px;
    height: 700px;
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