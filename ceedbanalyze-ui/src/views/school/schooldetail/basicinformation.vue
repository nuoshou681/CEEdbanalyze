<template>
<div class="information-page">
    
    <div class="text-main">
      <h3>学校简介</h3>
      <p>{{ truncatedText }}<button @click="toggleText">详情</button></p>
    </div>
    <schoolintro  v-if="isCollapsed" @close="toggleText" :schoolinfromation="schoolitem.introduction"/>
    <div class="text-main" >
        <h3>学校地图</h3>
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
      name: '山东大学',
      introduction: '山东大学是一所历史悠久、学科齐全、实力雄厚、特色鲜明的教育部直属重点综合性大学，在国内外具有重要影响，2017年顺利迈入世界一流大学建设高校（A类）行列。山东大学前身是1901年创办的山东大学堂，被誉为中国近代高等教育起源性大学。其医学学科起源于1864年，开启近代中国高等医学教育之先河。从诞生起，学校先后历经了山东大学堂、国立青岛大学、国立山东大学、山东大学以及由原山东大学、山东医科大学、山东',
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