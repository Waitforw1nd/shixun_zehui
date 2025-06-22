<template>
  <div class="chart-container">
    <div class="chart-header">
      <h3>标签关联分析</h3>
      <p>分析视频标签之间的关联关系</p>
    </div>
    <div ref="chartRef" class="chart"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts'
import { onMounted, onUnmounted, ref } from 'vue'
import { getTagCombinations } from '@/services/api'
import { ElMessage } from 'element-plus'

const chartRef = ref(null)
let chart = null

const initChart = async () => {
  if (!chartRef.value) return;
  chart = echarts.init(chartRef.value);

  try {
    const response = await getTagCombinations();
    const links = response.data.map(item => {
      const tags = item.tagPair.split(',');
      return {
        source: tags[0],
        target: tags[1],
        value: item.count
      };
    });

    const nodeSet = new Set();
    links.forEach(link => {
      nodeSet.add(link.source);
      nodeSet.add(link.target);
    });
    const nodes = Array.from(nodeSet).map(name => ({ name }));

    const option = {
      tooltip: {
        trigger: 'item',
        triggerOn: 'mousemove'
      },
      series: [{
        type: 'sankey',
        layout: 'none',
        emphasis: { focus: 'adjacency' },
        data: nodes,
        links: links,
        lineStyle: {
          color: 'gradient',
          curveness: 0.5
        },
        itemStyle: {
          borderWidth: 1,
          borderColor: '#aaa'
        },
        label: {
          color: '#333',
          fontSize: 14,
          fontWeight: 'bold'
        }
      }]
    };
    chart.setOption(option);
  } catch (error) {
    ElMessage.error('获取标签组合数据失败');
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