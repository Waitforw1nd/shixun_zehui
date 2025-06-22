<template>
  <div class="dashboard-one">
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card class="chart-card">
          <WordCloudChart />
        </el-card>
      </el-col>
      <el-col :span="16">
        <el-card class="chart-card">
          <PlayTrendChart />
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20" class="row-two">
      <el-col :span="8">
        <el-card class="chart-card">
          <TagPlayBoxChart />
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="chart-card">
          <TimeFlowChart />
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="chart-card">
          <TagSankeyChart />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import WordCloudChart from '@/components/charts/WordCloudChart.vue'
import PlayTrendChart from '@/components/charts/PlayTrendChart.vue'
import TagSankeyChart from '@/components/charts/TagSankeyChart.vue'
import TimeFlowChart from '@/components/charts/TimeFlowChart.vue'
import TagPlayBoxChart from '@/components/charts/TagPlayBoxChart.vue'
</script>

<style scoped>
.dashboard-one {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.chart-card {
  height: 450px;
}
.row-two {
  margin-top: 20px;
}
</style> 