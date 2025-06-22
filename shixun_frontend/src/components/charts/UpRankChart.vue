<template>
  <div class="chart-container">
    <div class="chart-header">
      <h3>高产UP主排行</h3>
      <p>展示视频数量与平均播放量的关系</p>
    </div>
    <div ref="chartRef" class="chart"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts'
import { onMounted, onUnmounted, ref } from 'vue'
import { getUpRanking } from '@/services/api'
import { ElMessage } from 'element-plus'

const chartRef = ref(null)
let chart = null

const initChart = async () => {
  if (!chartRef.value) return
  
  chart = echarts.init(chartRef.value)

  try {
    const response = await getUpRanking({ sortBy: 'videoCount', order: 'desc' })
    const data = response.data.slice(0, 10) // 获取视频数最多的前10

    const option = {
      tooltip: {
        trigger: 'axis',
        axisPointer: { type: 'cross' }
      },
      legend: {
        data: ['视频数', '平均播放量']
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '10%', // 增加底部边距以容纳旋转的标签
        containLabel: true
      },
      xAxis: {
        type: 'category',
        data: data.map(item => item.publisher),
        axisLabel: {
          rotate: 30,
          interval: 0
        }
      },
      yAxis: [
        {
          type: 'value',
          name: '视频数',
          position: 'left',
          axisLine: { show: true },
          axisLabel: { formatter: '{value}' }
        },
        {
          type: 'value',
          name: '平均播放量',
          position: 'right',
          axisLine: { show: true },
          axisLabel: { formatter: '{value}' }
        }
      ],
      series: [
        {
          name: '视频数',
          type: 'bar',
          data: data.map(item => item.videoCount),
          itemStyle: {
            color: '#5470c6'
          }
        },
        {
          name: '平均播放量',
          type: 'line',
          yAxisIndex: 1, // 关联到右侧Y轴
          data: data.map(item => item.avgViews),
          itemStyle: {
            color: '#91cc75'
          }
        }
      ]
    }
    chart.setOption(option)
  } catch (error) {
    ElMessage.error('获取UP主排行数据失败')
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