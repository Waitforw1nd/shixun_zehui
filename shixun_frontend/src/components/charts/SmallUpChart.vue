<template>
  <div class="chart-container">
    <div class="chart-header">
      <h3>小众UP主分析</h3>
      <p>分析小UP主的成长情况</p>
    </div>
    <div ref="chartRef" class="chart"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts'
import { onMounted, onUnmounted, ref } from 'vue'
import { getNicheUps } from '@/services/api'
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
    const response = await getNicheUps({ sortBy: 'score', order: 'desc' })
    const data = response.data

    const option = {
      tooltip: {
        trigger: 'item',
        formatter: (params) => {
          const item = params.data
          return `
            UP主: ${item.publisher}<br/>
            粉丝数: ${item.fans}<br/>
            综合评分: ${item.score}<br/>
            领域: ${item.category}<br/>
            特点: ${item.contentFeatures}
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
        name: '粉丝数',
        splitLine: { lineStyle: { type: 'dashed' } }
      },
      yAxis: {
        type: 'value',
        name: '综合评分',
        splitLine: { lineStyle: { type: 'dashed' } }
      },
      series: [{
        type: 'scatter',
        data: data.map(item => ({
          ...item,
          value: [item.fans, item.score]
        })),
        symbolSize: 15,
        emphasis: {
          focus: 'series',
          label: {
            show: true,
            formatter: (params) => params.data.publisher,
            position: 'top'
          }
        },
        itemStyle: {
          color: '#00a1d6'
        }
      }]
    }
    chart.setOption(option)
  } catch (error) {
    ElMessage.error('获取小众UP主数据失败')
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