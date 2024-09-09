<template>
    <div class="map">echarts</div>
</template>
<script>
import {inject} from 'vue'
export default {
    setup(){
        let $echarts=inject('echarts')
        console.log($echarts)
    }
}
</script>
<style>

</style>