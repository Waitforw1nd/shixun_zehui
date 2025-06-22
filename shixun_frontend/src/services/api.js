// shixun_frontend/src/services/api.js

import axios from 'axios'

const apiClient = axios.create({
  baseURL: '/api', // 这样才能触发 vue.config.js 中的代理
  headers: {
    'Content-Type': 'application/json'
  }
})

export const getTop100WordCloud = () => apiClient.get('/top100-word-cloud')
export const getTagCombinations = () => apiClient.get('/tag-combinations')
export const getUpRanking = (params) => apiClient.get('/up-ranking', { params })
export const getInteractionRatio = (params) => apiClient.get('/interaction-ratio', { params })
export const getTrueTripleVideos = (params) => apiClient.get('/true-triple-videos', { params })
export const getMonthlyPlayTrend = (params) => apiClient.get('/monthly-play-trend', { params })
export const getNicheUps = (params) => apiClient.get('/niche-ups', { params })
export const getReleaseTimeImpact = (params) => apiClient.get('/release-time-impact', { params })
export const getTagPlayStats = (params) => apiClient.get('/tag-play-stats', { params })

export const getTestApi = () => apiClient.get('/testApi'); // <-- 将 'api' 修改为 'apiClient'