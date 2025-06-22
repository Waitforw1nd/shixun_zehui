<template>
  <div class="chart-container">
    <div class="chart-header">
      <h3>视频互动数据洞察</h3>
      <p>分析视频的点赞、收藏与播放量关系</p>
    </div>
    <div ref="chartRef" class="chart"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts'
import { onMounted, onUnmounted, ref } from 'vue'
import { getInteractionRatio } from '@/services/api'
import { ElMessage } from 'element-plus'

const chartRef = ref(null)
let chart = null

const initChart = async () => {
  if (!chartRef.value) return
  
  // 存在图表实例，先销毁
  if (chart) {
    chart.dispose()
  }
  
  try {
    const response = await getInteractionRatio()
    const data = response.data

    const option = {
      tooltip: {
        trigger: 'item',
        formatter: (params) => {
          const item = params.data
          return `
            标题: ${item.title.substring(0, 20)}...<br/>
            点赞数: ${item.likes}<br/>
            收藏数: ${item.favorites}<br/>
            总播放量: ${item.totalViews}
          `
        }
      },
      grid: {
        left: '3%',
        right: '7%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'value',
        name: '点赞数',
        splitLine: { lineStyle: { type: 'dashed' } }
      },
      yAxis: {
        type: 'value',
        name: '收藏数',
        splitLine: { lineStyle: { type: 'dashed' } }
      },
      series: [{
        type: 'scatter',
        data: data.map(item => ({
          ...item,
          value: [item.likes, item.favorites, item.totalViews]
        })),
        symbolSize: (params) => {
          // 用播放量的对数来缩放气泡大小，避免差距过大
          return Math.log(params[2]) * 2
        },
        emphasis: {
          focus: 'series',
          label: {
            show: true,
            formatter: (params) => params.data.title.substring(0, 10) + '...',
            position: 'top'
          }
        },
        itemStyle: {
          color: '#fb7299'
        }
      }]
    }
    chart = echarts.init(chartRef.value)
    chart.setOption(option)
  } catch (error) {
    ElMessage.error('获取互动数据失败')
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