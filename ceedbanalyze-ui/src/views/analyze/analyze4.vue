<template>
    <div>
        <select v-model="selectedYear" @change="updateChart">
            <option disabled value="">请选择年份</option>
            <option value="2020">2020</option>
            <option value="2021">2021</option>
            <option value="2022">2022</option>
            <option value="2023">2023</option>
            <option value="2024">2024</option>
        </select>
        <div class="zhiyuanChart" ref="zhiyuan"></div>
    </div>
</template>

<script>
import { inject, onMounted, ref, nextTick, watch } from 'vue';

export default {
    setup() {
        const echarts = inject('echarts');
        const zhiyuan = ref(null);
        const selectedYear = ref('2024'); 
        const yearData = {
            '2020': [
                { value: 300, name: '哲学' }, { value: 1000, name: '经济学' }, { value: 800, name: '法学' },
                { value: 1500, name: '教育学' }, { value: 2000, name: '文学' }, { value: 1600, name: '历史学' },
                { value: 1400, name: '理学' }, { value: 3000, name: '工学' }, { value: 600, name: '农学' },
                { value: 2200, name: '医学' }, { value: 1800, name: '管理学' }, { value: 1100, name: '艺术学' }
            ],
            '2021': [
                { value: 320, name: '哲学' }, { value: 1100, name: '经济学' }, { value: 850, name: '法学' },
                { value: 1550, name: '教育学' }, { value: 2050, name: '文学' }, { value: 1650, name: '历史学' },
                { value: 1450, name: '理学' }, { value: 3050, name: '工学' }, { value: 650, name: '农学' },
                { value: 2250, name: '医学' }, { value: 1850, name: '管理学' }, { value: 1150, name: '艺术学' }
            ],
            '2022': [
                { value: 340, name: '哲学' }, { value: 1200, name: '经济学' }, { value: 900, name: '法学' },
                { value: 1600, name: '教育学' }, { value: 2100, name: '文学' }, { value: 1700, name: '历史学' },
                { value: 1500, name: '理学' }, { value: 3100, name: '工学' }, { value: 700, name: '农学' },
                { value: 2300, name: '医学' }, { value: 1900, name: '管理学' }, { value: 1200, name: '艺术学' }
            ],
            '2023': [
                { value: 360, name: '哲学' }, { value: 1300, name: '经济学' }, { value: 950, name: '法学' },
                { value: 1650, name: '教育学' }, { value: 2150, name: '文学' }, { value: 1750, name: '历史学' },
                { value: 1550, name: '理学' }, { value: 3150, name: '工学' }, { value: 750, name: '农学' },
                { value: 2350, name: '医学' }, { value: 1950, name: '管理学' }, { value: 1250, name: '艺术学' }
            ],
            '2024': [
                { value: 350, name: '哲学' }, { value: 1200, name: '经济学' }, { value: 800, name: '法学' },
                { value: 1500, name: '教育学' }, { value: 2000, name: '文学' }, { value: 1600, name: '历史学' },
                { value: 1400, name: '理学' }, { value: 3000, name: '工学' }, { value: 600, name: '农学' },
                { value: 2200, name: '医学' }, { value: 1800, name: '管理学' }, { value: 1100, name: '艺术学' }
            ]
        };

        const updateChart = () => {
            if (zhiyuan.value && selectedYear.value) {
                const chart = echarts.init(zhiyuan.value);
                const option = {
                    title: {
                        text: `${selectedYear.value}年各专业门类录取情况`,
                        subtext: '数据来源：学校录取信息',
                        left: 'center',
                        textStyle: {
                            fontSize: 18,
                            fontWeight: 'bold'
                        },
                        subtextStyle: {
                            fontSize: 14
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{b}: {d}%'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [
                        {
                            name: '录取情况',
                            type: 'pie',
                            radius: '50%',
                            data: yearData[selectedYear.value],
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                chart.setOption(option);
            } else {
                console.error('DOM element for chart is not available');
            }
        };

        onMounted(() => {
            nextTick(() => {
                updateChart();
            });
        });

        watch(selectedYear, () => {
            nextTick(() => {
                updateChart();
            });
        });

        return {
            zhiyuan,
            selectedYear
        };
    }
};
</script>

<style>
.zhiyuanChart {
    width: 100%;
    height: 650px;
    margin-top: 30px;
}
</style>
