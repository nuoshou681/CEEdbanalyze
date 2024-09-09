<template>
    <div class="map" ref="chartContainer"></div>
</template>

<script>
import { onMounted, ref, inject } from 'vue';

export default {
    setup() {
        const chartContainer = ref(null);
        const echarts = inject('echarts');

        const data = {
            categories: ['物理', '化学', '生物', '政治', '历史', '地理'],
            ranges: [
                { name: '700-600', values: [18499, 18471, 16199, 4907, 4154, 5414] },
                { name: '600-500', values: [97312, 93765, 77910, 67790, 57051, 79224] },
                { name: '500-400', values: [111536, 103801, 102319, 120172, 112969, 151749] },
                { name: '400-300', values: [47214, 42973, 64898, 110631, 122765, 139908] },
                { name: '300-200', values: [13686, 11556, 24782, 47571, 56383, 59365] }
            ]
        };

        onMounted(() => {
            const chart = echarts.init(chartContainer.value);
            chart.setOption({
                title: {
                    text: '各分段科目人数对比',
                    left: 'center',
                },
                tooltip: {
                    trigger: 'axis',
                },
                legend: {
                    data: data.categories,
                    top: 'bottom',
                },
                xAxis: {
                    type: 'category',
                    data: data.ranges.map(range => range.name),
                },
                yAxis: {
                    type: 'value',
                    name: '人数',
                    nameLocation: 'middle',
                    nameGap: 50,
                },
                series: data.categories.map((category, index) => ({
                    name: category,
                    type: 'bar',
                    stack: 'total',
                    data: data.ranges.map(range => range.values[index]),
                })),
            });
        });

        return {
            chartContainer,
        };
    },
};
</script>

<style scoped>
.map {
    width: 100%;
    height: 600px;
    margin-top: 30px;
}
</style>
