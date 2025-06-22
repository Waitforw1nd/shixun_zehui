<template>
  <div class="chart-container">
    <div class="chart-header">
      <h3>三连比例分析</h3>
      <p>分析视频的点赞、投币、收藏及三连比例</p>
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
  
  chart = echarts.init(chartRef.value)
  
  try {
    // 获取一个有代表性的视频数据用于展示
    const response = await getInteractionRatio({ limit: 1 })
    const video = response.data[0]

    // 计算三连数
    const threeLinkCount = Math.min(video.likes, video.coins / 2, video.favorites)

    const option = {
      tooltip: {
        trigger: 'item',
        formatter: (params) => `${params.name}: ${params.value}`
      },
      legend: {
        data: ['互动数据'],
        bottom: 10
      },
      radar: {
        indicator: [
          { name: '点赞数', max: Math.max(video.likes, 1) * 1.2 },
          { name: '投币数', max: Math.max(video.coins, 1) * 1.2 },
          { name: '收藏数', max: Math.max(video.favorites, 1) * 1.2 },
          { name: '三连数', max: Math.max(threeLinkCount, 1) * 1.2 }
        ],
        center: ['50%', '50%'],
        radius: '65%'
      },
      series: [{
        name: '互动数据',
        type: 'radar',
        data: [{
          value: [video.likes, video.coins, video.favorites, threeLinkCount],
          name: '互动数据',
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              { offset: 0, color: 'rgba(0, 161, 214, 0.5)' },
              { offset: 1, color: 'rgba(0, 161, 214, 0.1)' }
            ])
          },
          lineStyle: { color: '#00a1d6' },
          itemStyle: { color: '#00a1d6' }
        }]
      }]
    }
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