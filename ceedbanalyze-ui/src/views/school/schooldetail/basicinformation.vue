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
      introduction: '山东大学是一所好大学',
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
    height: auto;

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