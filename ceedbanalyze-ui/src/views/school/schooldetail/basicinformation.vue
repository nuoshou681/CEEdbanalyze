<template>
<div class="information-page">
    <div class="title-container">
        <div class="title-marker"></div>
        <h3 class="title-text">学校简介</h3>
      </div>
    <div class="text-main">  
      <p>{{ truncatedText }}<button @click="toggleText">详情</button></p>
    </div>
    <schoolintro  v-if="isCollapsed" @close="toggleText" :schoolinfromation="schoolitem.introduction"/>
    <div class="title-container">
        <div class="title-marker"></div>
        <h3 class="title-text">学校地图</h3>
      </div>
    <div class="text-main" >
        这里展示学校地图
    </div>
</div>

</template>
<script setup>
import {  ref} from 'vue';
import { computed,watch } from 'vue';
import schoolintro from '@/components/schoolintro.vue';
const isCollapsed = ref(false);

const toggleText = () => {
  isCollapsed.value = !isCollapsed.value;
};


const props = defineProps({
  schoolitem: {
    type: Object,
    required: true,
    default: () => ({
      name: '滨州医学院',
      introduction: '滨州医学院是山东省应用型本科高校、山东省省市共建高校、山东省博士学位授予立项建设单位。前身是始建于1946年的原国立山东大学医学院，1956年3月独立建制为青岛医学院，1970年10月整体搬迁至山东省惠民地区行署驻地北镇办学，1974年11月设立青岛医学院北镇分院，1981年9月改称北镇医学院，1983年3月随驻地更名为滨州医学院，2002年烟台校区投入使用。目前，学校已发展成为一所以医药学科为优势，残疾人高等教育、康复高等教育为特色，医、理、工、管、教等多学科门类渗透融合、协调发展的省属本科医学院校。',
    }),
  },
});

const fullText = ref('');
const truncatedText = computed(() => {
  return fullText.value.slice(0, 200) + '……';
});

// 监听 Props 的变化
watch(
  () => props.schoolitem,
  (newVal) => {
    if (newVal && newVal.introduction) {
      fullText.value = newVal.introduction;
    }
  },
  { immediate: true, deep: true }
);
</script>
<style scoped>
.information-page{
    width: 700px;
    height: 600px;
    margin-top: 10px;
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
.title-container {
  margin-top: 5px;
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