// shixun_frontend/vue.config.js

const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8081, // 您的前端端口
    proxy: {
      '/api': {
        // 将 target 的上下文路径修改为实际的文件夹名称
        target: 'http://localhost:8080/bilibili_food_video_web_war', // <-- **修改为这个！**
        changeOrigin: true,
        pathRewrite: { '^/api': '' }
      }
    }
  }
})