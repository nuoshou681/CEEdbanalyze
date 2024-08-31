<template>
  <div class="school-information" v-if="schoolitem">
    <div class="title-container">
        <div class="title-marker"></div>
        <h1 class="title-text">{{ schoolitem.name }}</h1>
      </div>
    <div>
      {{ schoolitem.type1 }} {{ schoolitem.type2 }} {{ schoolitem.type3 }}
    </div>
    <p>招生官网：<a :href=schoolitem.website>{{ schoolitem.website }}</a></p>
    <p>招生电话：{{ schoolitem.phone }}</p>
    <p>招生邮箱：{{ schoolitem.email}}</p>
    <div class="school-more">
      <div class="school-tag" :class="{ 'active-link': activeTab === '基本详情' }" @click="setActiveTab('基本详情')">
        基本详情
      </div>
      <div class="school-tag" :class="{ 'active-link': activeTab === '分数计划' }" @click="setActiveTab('分数计划')">
        分数计划
      </div>
      <div class="school-tag" :class="{ 'active-link': activeTab === '开设专业' }" @click="setActiveTab('开设专业')">
        开设专业
      </div>
    </div>
    <basicinformation v-if="activeTab === '基本详情'" :schoolitem="schoolitem" />
    <majorinformation v-if="activeTab === '开设专业'" :schoolid="schoolitem.id" />
    <scoreandplan v-if="activeTab === '分数计划'" :schoolitem="schoolitem" />
  </div>
  <div v-else>
    <div class="school-information">
      <div class="title-container">
        <div class="title-marker"></div>
        <h1 class="title-text">滨州医学院</h1>
      </div>
      <div class="types">
        本科 医药类 公办
      </div>
      <p>招生官网：<a href="http://zb.bzmc.edu.cn/">http://zb.bzmc.edu.cn/</a></p>
      <p>招生电话：0535-6913058</p>
      <p>招生邮箱：zkb@bzmc.edu.cn</p>
      <div class="school-more">
        <div class="school-tag" :class="{ 'active-link': activeTab === '基本详情' }" @click="setActiveTab('基本详情')">
          基本详情
        </div>
        <div class="school-tag" :class="{ 'active-link': activeTab === '分数计划' }" @click="setActiveTab('分数计划')">
          分数计划
        </div>
        <div class="school-tag" :class="{ 'active-link': activeTab === '开设专业' }" @click="setActiveTab('开设专业')">
          开设专业
        </div>
      </div>
      <basicinformation v-if="activeTab === '基本详情'" />
      <majorinformation v-if="activeTab === '开设专业'" />
      <scoreandplan v-if="activeTab === '分数计划'" />
    </div>
  </div>

</template>
<script setup>
import { ref } from 'vue';
import basicinformation from '@/views/school/schooldetail/basicinformation.vue';
import majorinformation from '@/views/school/schooldetail/majorinformation.vue';
import scoreandplan from '@/views/school/schooldetail/scoreandplan.vue';
const activeTab = ref('基本详情');
const setActiveTab = (tab) => {
  activeTab.value = tab;
};

// props
const props = defineProps({
  schoolitem: {
    type: Object,
    required: true
  }
})
</script>
<style scoped>
.school-tag {
  padding: 10px 0 10px 0;
  cursor: pointer;
  border-radius: 5px;
  width: 200px;
  text-align: center;

}

.active-link {
  background-color: rgb(21, 208, 130);
  color: rgb(255, 255, 255);
  font-weight: bold;

}

h1 {
  margin-top: 0;
  margin-bottom: 2px;
}

p {
  line-height: 10px;
  font-size: 12px;
}
.school-more {
  display: flex;
  justify-content: space-around;
  margin-bottom: 0;
}

.school-information {
  background-color: #f0f0f0;
  width: 700px;
  padding: 10px 25px 5px 25px;
  border-radius: 10px 10px 0 0;
}
.title-container {
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
.types{
  margin-top: 5px;
}
</style>