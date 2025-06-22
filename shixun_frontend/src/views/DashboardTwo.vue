<template>
  <div class="dashboard-two">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card class="chart-card">
          <UpRankChart />
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="chart-card">
          <SmallUpChart />
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20" class="row-two">
      <el-col :span="12">
        <el-card class="chart-card">
          <ThreeLinkChart />
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="chart-card">
          <DanmakuChart />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import UpRankChart from '@/components/charts/UpRankChart.vue'
import SmallUpChart from '@/components/charts/SmallUpChart.vue'
import ThreeLinkChart from '@/components/charts/ThreeLinkChart.vue'
import DanmakuChart from '@/components/charts/DanmakuChart.vue'
</script>

<style scoped>
.dashboard-two {
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