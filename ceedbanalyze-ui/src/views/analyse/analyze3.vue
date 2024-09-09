<template>
    <div class="lineChart" ref="lineRef"></div>
</template>

<script>
import { inject, onMounted, ref, nextTick } from 'vue';
import { getNum } from '@/api/yifenyiduan.js';

export default {
    setup() {
        const echarts = inject('echarts');
        const lineRef = ref(null);

        const fetchData = async () => {
            try {
                const response = await getNum();
                return response.data;
            } catch (error) {
                console.error('Failed to fetch data', error);
                return [];
            }
        };

        onMounted(async () => {
            nextTick(async () => {
                if (lineRef.value) {
                    const data = await fetchData();
                    const scores = data.map(item => item.score);
                    const nums = data.map(item => item.num);

                    const lineInstance = echarts.init(lineRef.value);
                    const option = {
                        tooltip: {
                            trigger: 'axis'
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: scores
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name: '考生数量',
                                type: 'line',
                                data: nums,
                                markPoint: {
                                    data: [] // 不显示标记点
                                },
                                markLine: {
                                    data: [
                                        { type: 'average', name: '平均值' }
                                    ]
                                }
                            }
                        ]
                    };
                    lineInstance.setOption(option);
                } else {
                    console.error('DOM element for chart is not available');
                }
            });
        });

        return {
            lineRef
        };
    }
};
</script>

<style>
.lineChart {
    width: 100%;
    height: 400px;
    margin-top: 80px;
}
</style>
