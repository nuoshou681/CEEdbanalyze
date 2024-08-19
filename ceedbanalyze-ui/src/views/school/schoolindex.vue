<template>
    <div class="school-information" v-if="schoolitem">
   <div><h1>{{ schoolitem.name }}</h1>
    {{ schoolitem.type1 }} {{ schoolitem.type2 }} {{ schoolitem.type3 }}
        </div>
    <p>招生官网：www.baidu.com</p>
    <p>招生电话：{{ schoolitem.phone }}</p>
    <p>招生邮箱：{{ schoolitem.website }}</p>
    <div class="school-more">
    <div
      class="school-tag"
      :class="{ 'active-link': activeTab === '基本详情' }"
      @click="setActiveTab('基本详情')"
    >
      基本详情
    </div>
    <div
      class="school-tag"
      :class="{ 'active-link': activeTab === '分数计划' }"
      @click="setActiveTab('分数计划')"
    >
      分数计划
    </div>
    <div
      class="school-tag"
      :class="{ 'active-link': activeTab === '开设专业' }"
      @click="setActiveTab('开设专业')"
    >
      开设专业
    </div>
  </div>
    <basicinformation v-if="activeTab === '基本详情'" :schoolitem="schoolitem"/>
    <majorinformation v-if="activeTab === '开设专业'" :schoolid="schoolitem.id"/>
    <scoreandplan v-if="activeTab === '分数计划'" :schoolitem="schoolitem"/>
    </div>
    <div v-else>
        <div class="school-information">
   <div><h1>山东大学</h1>
    本科 综合类 公办 211工程 985工程 双一流
        </div>
    <p>招生官网：www.baidu.com</p>
    <p>招生电话：0531-59626977</p>
    <p>招生邮箱：sdbkzs@email.sdu.edu.cn</p>
    <div class="school-more">
    <div
      class="school-tag"
      :class="{ 'active-link': activeTab === '基本详情' }"
      @click="setActiveTab('基本详情')"
    >
      基本详情
    </div>
    <div
      class="school-tag"
      :class="{ 'active-link': activeTab === '分数计划' }"
      @click="setActiveTab('分数计划')"
    >
      分数计划
    </div>
    <div
      class="school-tag"
      :class="{ 'active-link': activeTab === '开设专业' }"
      @click="setActiveTab('开设专业')"
    >
      开设专业
    </div>
  </div>
  <basicinformation v-if="activeTab === '基本详情'"/>
  <majorinformation v-if="activeTab === '开设专业'"/>
  <scoreandplan v-if="activeTab === '分数计划'"/>
    </div>
    </div>

</template>
<script setup>
import { computed, watch,ref } from 'vue';
import eventBus from '@/api/eventBus';
import {onMounted} from 'vue';
import basicinformation from '@/views/school/schooldetail/basicinformation.vue';
import majorinformation from '@/views/school/schooldetail/majorinformation.vue';
import scoreandplan from '@/views/school/schooldetail/scoreandplan.vue';
const activeTab = ref('基本详情');
const setActiveTab = (tab) => {
  activeTab.value = tab;
};

// props
const props = defineProps({
    schoolitem:{
        type: Object,
        required: true
    }
})


</script>
<style scoped>


.school-tag {
  padding: 0 20px 0 20px;
  cursor: pointer;
  border-radius: 10px;
}

.active-link {
  background-color:  rgb(21, 208, 130);
  color: rgb(255, 255, 255);
  font-weight: bold;
}


h1{
    margin-top: 0;
    margin-bottom: 2px;
}

p{
    line-height: 10px;
    font-size: 12px;
}
    .school-more{
        display: flex;
        justify-content: space-around;
        margin-bottom: 0;
    }
    .school-information{
        background-color: #f0f0f0;
        width: 700px;
        padding: 10px 25px 5px 25px;
        border-radius: 10px 10px 0 0;
    }
</style>