<template>
    <div class="filter-container">
      <div class="filter-group">
        <span class="label">院校所属:</span>
        <span
          v-for="region in regions"
          :key="region"
          class="option"
          :class="{ selected: selectedRegion === region }"
          @click="selectedRegion = region"
        >
          {{ region }}
        </span>
      </div>
      <div class="filter-group">
        <span class="label">院校类型:</span>
        <span
          v-for="type in types"
          :key="type"
          class="option"
          :class="{ selected: selectedType === type }"
          @click="selectedType = type"   
        >
          {{ type }}
        </span>
      </div>
      <div class="filter-group">
        <span class="label">办学类型:</span>
        <span
          v-for="category in categories"
          :key="category"
          class="option"
          :class="{ selected: selectedCategory === category }"
          @click="selectedCategory = category"
        >
          {{ category }}
        </span>
      </div>

    </div>
  </template>
  <script setup>
  import { ref ,watch} from 'vue';
        const selectedRegion=  ref('全部')
        const selectedType = ref('全部')
        const selectedCategory = ref('全部')
        const regions = ref([
          '全部', '济南', '青岛', '烟台', '潍坊', '威海', '日照', '济宁', '泰安', '淄博', '枣庄', '东营', '临沂', '德州', '聊城',
          '聊城', '滨州', '菏泽', '莱芜', '其他'
        ])
        const types = ref([
          '全部', '综合', '理工', '农林', '医药', '师范', '语言', '财经', '政法', '体育', '艺术', '民族', '军事', '其他'
        ])
        const categories = ref([
          '全部', '公办', '民办'
        ])
 //props接受父组件传递的函数
const props = defineProps({
  schooltag: Function
})
//监听selectedRegion,selectedType,selectedCategory的变化
watch([selectedRegion,selectedType,selectedCategory],(newValue)=>{
  const [region,type,category] = newValue
  props.schooltag(region,type,category)
})
  </script>
  <style scoped>

  .filter-container {
    margin: 20px;
  }
  .filter-group {
    margin-bottom: 10px;
  }
  .filter-group span.label {
    font-weight: bold;
    margin-right: 10px;
  }
  .filter-group span.option {
    margin-right: 10px;
    color: black;
    cursor: pointer;
    line-height: 25px;
  }
  .filter-group span.option.selected {
    color: orange;
  }

  </style>
  