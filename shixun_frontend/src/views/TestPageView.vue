<template>
    <div class="test-page">
        <h1>API 测试页面</h1>
        <p>点击按钮获取来自后端测试API的数据。</p>
        <button @click="fetchTestData">获取测试数据</button>
        <div v-if="loading" class="loading">加载中...</div>
        <div v-if="testData" class="data-display">
            <h2>返回数据:</h2>
            <pre>{{ JSON.stringify(testData, null, 2) }}</pre>
        </div>
        <div v-if="error" class="error-message">
            <h2>错误:</h2>
            <p>{{ error }}</p>
            <p>请检查后端服务是否运行，API路径是否正确，以及是否存在跨域问题。</p>
        </div>
    </div>
</template>

<script>
import { getTestApi } from '@/services/api';

export default {
    name: 'TestPageView',
    data() {
        return {
            testData: null,
            error: null,
            loading: false,
        };
    },
    methods: {
        async fetchTestData() {
            this.loading = true;
            this.testData = null;
            this.error = null;

            try {
                const response = await getTestApi();
                this.testData = response.data;
                console.log("测试API返回数据:", response.data);
            } catch (err) {
                this.error = '获取测试数据失败: ' + (err.response ? JSON.stringify(err.response.data) : err.message);
                console.error("测试API请求失败:", err);
                if (!err.response) {
                    this.error += '\n(可能是网络问题或跨域(CORS)配置错误)';
                }
            } finally {
                this.loading = false;
            }
        },
    },
};
</script>

<style scoped>
.test-page {
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
    font-family: Arial, sans-serif;
}

button {
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    margin-bottom: 20px;
}

button:hover {
    background-color: #0056b3;
}

.loading {
    color: #007bff;
    font-style: italic;
}

.data-display {
    background-color: #f8f8f8;
    border: 1px solid #ddd;
    padding: 15px;
    border-radius: 5px;
    margin-top: 20px;
}

pre {
    white-space: pre-wrap;
    word-wrap: break-word;
}

.error-message {
    color: #dc3545;
    background-color: #f8d7da;
    border: 1px solid #f5c6cb;
    padding: 15px;
    border-radius: 5px;
    margin-top: 20px;
}
</style>