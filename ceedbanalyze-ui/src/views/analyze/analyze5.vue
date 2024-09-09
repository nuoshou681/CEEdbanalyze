<template>
    <div class="chart" ref="chartContainer"></div>
</template>

<script>
import { onMounted, ref } from 'vue';
import * as echarts from 'echarts';

export default {
    setup() {
        const chartContainer = ref(null);

        onMounted(() => {
            const myChart = echarts.init(chartContainer.value);

            const data = {
                years: [2020, 2021, 2022, 2023, 2024],
                totalCandidates: [769325, 795000, 867000, 977560, 998000],
                applications: [654824, 688509, 743747, 790714, 818731], 
            };

            myChart.setOption({
                title: {
                    text: '高考人数及录取人数对比',
                    left: 'center',
                },
                tooltip: {
                    trigger: 'axis',
                },
                legend: {
                    data: ['高考总人数', '录取人数'],
                    top: 'bottom',
                },
                xAxis: {
                    type: 'category',
                    data: data.years,
                },
                yAxis: {
                    type: 'value',
                    name: '人数',
                    nameLocation: 'middle',
                    nameGap: 50,
                },
                series: [
                    {
                        name: '高考总人数',
                        type: 'bar',
                        data: data.totalCandidates,
                        color: '#409EFF',
                    },
                    {
                        name: '录取人数',
                        type: 'bar',
                        data: data.applications,
                        color: '#67C23A',
                    },
                ],
            });
        });

        return {
            chartContainer,
        };
    },
};
</script>

<style scoped>
.chart {
    width: 100%;
    height: 600px;
    margin-top: 30px;
}
</style>
