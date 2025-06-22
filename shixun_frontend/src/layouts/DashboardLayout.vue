<template>
  <div class="dashboard-layout">
    <el-header class="header">
      <div class="logo">数据可视化大屏</div>
      <el-menu mode="horizontal" :router="true" :default-active="$route.path" class="nav-menu">
        <el-menu-item index="/dashboard/content">内容与趋势分析</el-menu-item>
        <el-menu-item index="/dashboard/interaction">UP主与互动分析</el-menu-item>
      </el-menu>
      <el-button type="danger" @click="handleLogout" class="logout-button">退出登录</el-button>
    </el-header>
    <el-main class="main-content">
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()

const handleLogout = () => {
  localStorage.removeItem('isAuthenticated')
  ElMessage.success('已退出登录')
  router.push('/login')
}
</script>

<style scoped>
.dashboard-layout {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  background-color: #fff;
  border-bottom: 1px solid #e0e0e0;
}

.logo {
  font-size: 20px;
  font-weight: bold;
  color: #00a1d6;
}

.nav-menu {
  border-bottom: none;
  flex-grow: 1;
  justify-content: center;
}

.main-content {
  flex-grow: 1;
  padding: 20px;
  background-color: #f0f2f5;
  overflow-y: auto;
}
</style> 