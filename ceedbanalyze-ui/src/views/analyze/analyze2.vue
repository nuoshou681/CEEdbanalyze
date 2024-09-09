<template>
  <div class="map" id="map" style="width: 100%; height: 500px;"></div>
</template>

<script>
import { inject } from 'vue';
import { onMounted, reactive, ref } from 'vue';
import { getMap } from '@/api/getMap.js';

export default {
  setup() {
      const echarts = inject('echarts');
      const mapData = reactive({ features: [] });
      const mapLoaded = ref(false);

      async function getMapData() {
          const res = await getMap();
          mapData.features = res.data.features;
          mapLoaded.value = true;
      }

      onMounted(async () => {
          await getMapData();
          if (mapLoaded.value) {
              console.log('地图数据请求成功');
              echarts.registerMap("山东", mapData);
              let myChart = echarts.init(document.getElementById('map'));
              myChart.setOption({
                  geo: {
                      map: '山东',
                      roam: true,
                      label: {
                          show: true,
                          color: '#000'
                      },
                      itemStyle: {
                          areaColor: '#eee',
                          borderColor: '#333', 
                          borderWidth: 1, 
                          shadowColor: 'rgba(230, 130, 70, 0.5)',
                          shadowBlur: 20,
                      },
                      emphasis: {
                          itemStyle: {
                              focus: 'self',
                              areaColor: '#add8e6',
                          }
                      }
                  },
                  tooltip: {
                      trigger: 'item',
                      formatter: function(params) {
                          return `${params.name}<br> ${params.data.value[2]} 所高校`;
                      }
                  },
                  title: {
                      text: '山东',
                      left: 'center',
                      textStyle: {
                          color: '#fff',
                          fontSize: 20,
                          textShadowBlur: 10,
                          textShadowColor: '#333'
                      }
                  },
                  visualMap: {
                      type: 'continuous',
                      min: 1, 
                      max: 48, 
                      calculable: true,
                      inRange: {
                          color: ['#50a3ba', '#eac736', '#d94e5d']
                      },
                      textStyle: {
                          color: '#fff'
                      }
                  },
                  series: [
                      {
                          type: 'scatter',
                          itemStyle: {
                              color: 'red'
                          },
                          coordinateSystem: 'geo',
                          data: [
                              { name: '济南', value: [117.00, 36.65, 48]},
                              { name: '青岛', value: [120.33, 36.07, 25]},
                              { name: '淄博', value: [118.05, 36.78, 9]},
                              { name: '枣庄', value: [117.57, 34.86, 3]},
                              { name: '东营', value: [118.49, 37.43, 3]},
                              { name: '烟台', value: [121.39, 37.52, 16]},
                              { name: '潍坊', value: [119.10, 36.62, 8]},
                              { name: '济宁', value: [116.59, 35.41, 8]},
                              { name: '泰安', value: [117.09, 36.18, 4]},
                              { name: '威海', value: [122.10, 37.50, 9]},
                              { name: '日照', value: [119.46, 35.42, 3]},
                              { name: '莱芜', value: [117.67, 36.19, 1]},
                              { name: '临沂', value: [118.35, 35.05, 6]},
                              { name: '德州', value: [116.29, 37.44, 4]},
                              { name: '聊城', value: [115.97, 36.45, 3]},
                              { name: '滨州', value: [118.03, 37.36, 3]},
                              { name: '菏泽', value: [115.43, 35.23, 3]}
                          ],
                          symbolSize: 10,
                          label: {
                              show: true,
                              formatter: '{b}'
                          }
                      }
                  ]
              });
          }
      });

      return {
          getMapData,
          mapData
      };
  }
}
</script>

<style>
.map {
  width: 100%;
  height: 500px;
  margin-top: 40px;
}
</style>
