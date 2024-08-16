<template>
<div class="information-page">
    
    <div class="text-main">
      <h3>学校简介</h3>
      <p>{{ truncatedText }}<button @click="toggleText">详情</button></p>
    </div>
    <schoolintro  v-if="isCollapsed" @close="toggleText"/>
    <div class="text-main" >
        <h3>学校地图</h3>
    </div>
</div>
</template>
<script setup>
import { onMounted, ref,onUnmounted } from 'vue';
import schoolintro from '@/components/schoolintro.vue';
import eventBus from '@/api/eventBus';
const isCollapsed = ref(false);
const schoolitem = ref({});
const fullText = ref('');
const truncatedText = ref('');
const toggleText = () => {
  isCollapsed.value = !isCollapsed.value;
};
function handleData(data){
    //判断是否为空
    if(data == null){
      console.log('Received school data is null');
        return;
    }
    else{
    schoolitem.value = data;
    fullText.value = schoolitem.value.introduction;
    truncatedText.value = schoolitem.value.introduction.slice(0, 200)+'……';
    console.log('Received school data:', data);
    console.log('Received school introduction:', schoolitem.value.introduction);
    console.log('Received school truncated introduction:', truncatedText.value);
    }

}


onMounted(() => {
  eventBus.on('schooldata', (data)=>{
    handleData(data);
  });
});
onUnmounted(() => {
  eventBus.off('schooldata', (data)=>{
    handleData(data);});
});
</script>
<style scoped>
.information-page{
    width: 730px;
    height: auto;
    padding: 10px;
    white-space: pre-wrap; /* 保留空格和换行符 */
    background-color: #f0f0f0;
    border-radius:  0 0 10px 10px;

}
.text-main{
    background-color: #ffffff;
    padding: 0 10px 0 10px;
    height: auto;
}
button {
  margin-top: 10px;
  cursor: pointer;
}
</style>