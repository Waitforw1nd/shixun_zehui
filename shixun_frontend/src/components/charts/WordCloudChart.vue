<template>
  <div class="chart-container">
    <div class="chart-header">
      <h3>美食视频标签词云</h3>
      <p>展示美食视频中最常见的标签和关键词</p>
    </div>
    <div ref="chartRef" class="chart"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts'
import 'echarts-wordcloud'
import { onMounted, onUnmounted, ref } from 'vue'
import { getTop100WordCloud } from '@/services/api'
import { ElMessage } from 'element-plus'

const chartRef = ref(null)
let chart = null

const initChart = async () => {
  if (!chartRef.value) return;
  chart = echarts.init(chartRef.value);

  try {
    const response = await getTop100WordCloud();
    const data = response.data.map(item => ({
      name: item.word,
      value: item.freq
    }));

    const option = {
      tooltip: { show: true },
      series: [{
        type: 'wordCloud',
        shape: 'circle',
        sizeRange: [12, 60],
        rotationRange: [-90, 90],
        rotationStep: 45,
        gridSize: 8,
        drawOutOfBound: false,
        textStyle: {
          fontFamily: 'sans-serif',
          fontWeight: 'bold',
          color: function () {
            return 'rgb(' + [
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160)
            ].join(',') + ')'
          }
        },
        emphasis: {
          focus: 'self',
          textStyle: { shadowBlur: 10, shadowColor: '#333' }
        },
        data: data
      }]
    };
    chart.setOption(option);
  } catch (error) {
    ElMessage.error('获取词云数据失败');
    console.error(error);
  }
  
  window.addEventListener('resize', handleResize);
};

const handleResize = () => {
  if (chart) chart.resize();
};

onMounted(() => {
  initChart();
});

onUnmounted(() => {
  if (chart) {
    chart.dispose();
    window.removeEventListener('resize', handleResize);
  }
});

</script>

<style scoped>
.chart-container {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
}

.chart-header {
  margin-bottom: 20px;
}

.chart-header h3 {
  color: #00a1d6;
  margin: 0 0 8px 0;
  font-size: 18px;
}

.chart-header p {
  color: #666;
  margin: 0;
  font-size: 14px;
}

.chart {
  width: 100%;
  height: 100%;
}
</style>