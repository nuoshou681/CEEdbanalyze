<template>
	<div class="filter-container">
	  <div class="filter-group">
		<span class="label">专业层次:</span>
		<span
		  class="option"
		  :class="{ selected: selectedLevel === 'undergraduate' }"
		  @click="selectLevel('undergraduate')"
		>
		  本科
		</span>
		<span
		  class="option"
		  :class="{ selected: selectedLevel === 'associate' }"
		  @click="selectLevel('associate')"
		>
		  专科
		</span>
	  </div>
	  <div class="filter-group" v-if="selectedLevel">
		<span class="label">专业门类:</span>
		<span
		  v-for="category in categories[selectedLevel]"
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
  
  <script>
  export default {
	data() {
	  return {
		selectedLevel: 'undergraduate',
		selectedCategory: '',
		selectedRule: '',
		categories: {
		  undergraduate: [
			'全部', '哲学', '经济学', '法学', '教育学', '文学', '历史学', '理学', '工学', '农学', '医学', '管理学', '艺术学'
		  ],
		  associate: [
			'全部', '农林牧渔大类', '资源环境与安全大类', '能源动力与材料大类', '土木建筑大类', '水利大类', '装备制造大类',
			'生物与化工大类', '轻工纺织大类', '食品药品与粮食大类', '交通运输大类', '电子与信息大类', '医药卫生大类',
			'财经商贸大类', '旅游大类', '文化艺术大类', '新闻传播大类', '教育与体育大类', '公安与司法大类', '公共管理与服务大类'
		  ]
		}
	  };
	},
	methods: {
	  selectLevel(level) {
		this.selectedLevel = level;
		this.selectedCategory = ''; // 清空选中的专业门类
	  }
	}
  };
  </script>
  
  <style scoped>
  .filter-container {
	margin: 10px;
	width: 380px;
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
	line-height: 30px;
  }
  .filter-group span.option.selected {
	color: orange;
  }

  </style>
  