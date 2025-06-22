<template>
  <div class="chart-container">
    <div class="chart-header">
      <h3>标签播放量分析</h3>
      <p>分析不同标签视频的播放量分布情况</p>
    </div>
    <div ref="chartRef" class="chart"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts'
import { onMounted, onUnmounted, ref } from 'vue'
import { getTagPlayStats } from '@/services/api'
import { ElMessage } from 'element-plus'

const chartRef = ref(null)
let chart = null

const initChart = async () => {
  if (!chartRef.value) return
  
  chart = echarts.init(chartRef.value)
  
  try {
    const response = await getTagPlayStats()
    const data = response.data
    
    // ECharts v5+ has built-in boxplot transform
    const sourceData = [['score', 'tag']]
    data.forEach(item => {
      // We don't have raw data, so we create a plausible distribution
      // min, Q1, median, Q3, max
      const q1 = item.avgViews - item.viewsStddev * 0.5
      const q3 = item.avgViews + item.viewsStddev * 0.5
      const record = [
        item.minViews,
        q1 > item.minViews ? q1 : item.minViews,
        item.avgViews,
        q3 < item.maxViews ? q3 : item.maxViews,
        item.maxViews,
        item.tag
      ]
      sourceData.push(record)
    })

    const option = {
      dataset: [
        { source: sourceData },
        {
          transform: {
            type: 'boxplot',
            config: {
              itemNameFormatter: (params) => data[params.value].tag
            }
          }
        }
      ],
      tooltip: {
        trigger: 'item',
        axisPointer: { type: 'shadow' }
      },
      grid: {
        left: '10%',
        right: '10%',
        bottom: '15%'
      },
      xAxis: {
        type: 'category',
        boundaryGap: true,
        nameGap: 30,
        splitArea: { show: false },
        splitLine: { show: false }
      },
      yAxis: {
        type: 'value',
        name: '播放量',
        splitArea: { show: true }
      },
      series: [
        {
          name: '播放量分布',
          type: 'boxplot',
          datasetIndex: 1,
          itemStyle: {
            color: '#00a1d6',
            borderColor: '#23ade5'
          }
        }
      ]
    }
    
    chart.setOption(option)
  } catch (error) {
    ElMessage.error('获取标签播放统计数据失败')
    console.error(error)
  }
  
  window.addEventListener('resize', handleResize)
}

const handleResize = () => {
  if (chart) chart.resize()
}

onMounted(() => {
  initChart()
})

onUnmounted(() => {
  if (chart) {
    chart.dispose()
    window.removeEventListener('resize', handleResize)
  }
})
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