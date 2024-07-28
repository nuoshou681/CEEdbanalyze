<template>
	<div class="search-box">
		<el-input v-model="input" placeholder="查学校，查专业" size="large" class="search-txt" />
		<el-button icon="Search" size="large" @click="handleSearch" class="search-btn">
		</el-button>
	</div>
</template>
<script>
import { ref } from 'vue';
const input = ref('');
</script>

<style>
@font-face {
	font-family: 'FontAwesome';
	src: url('./fontawesome-webfont.eot');
	src: url('./fontawesome-webfont.eot') format('embedded-opentype'),
		url('./fontawesome-webfont.woff2') format('woff2'), url('.fontawesome-webfont.woff') format('woff'),
		url('./fontawesome-webfont.ttf') format('truetype'),
		url('./fontawesome-webfont.svg') format('svg');
	font-weight: normal;
	font-style: normal;
}

.search-box {
	font-family: FontAwesome;
	background: #ffffff;
	height: 40px;
	border: 10px;
	border-radius: 40px;
	padding: 10px;
	opacity: 1;
	width: 310px;
	padding: 0 6px;
}


.search-btn {

	font-family: FontAwesome;
	font-size: 25px;
	color: black;
	float: right;
	width: 40px;
	height: 40px;
	border: 10px;
	border-radius: 50%;
	background: #ffffff;
	display: flex;
	justify-content: center;
	align-items: center;
	text-decoration: none;
}

.search-txt {
	border: none;
	background: none;
	outline: none;

	padding-left: 10px;
	color: rgb(51, 159, 150);
	font-size: 16px;
	line-height: 40px;
	width: 250px;
	height: 40px;

}
</style>