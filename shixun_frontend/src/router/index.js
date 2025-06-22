import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import DashboardLayout from '../layouts/DashboardLayout.vue'
import DashboardOne from '../views/DashboardOne.vue'
import DashboardTwo from '../views/DashboardTwo.vue'
import TestPageView from '../views/TestPageView.vue'; 

const routes = [
  {
    path: '/',
    redirect: '/test' // <-- 修改：根路径重定向到 /test
  },
  {
    path: '/test', // <-- 新增：独立的 /test 路由
    name: 'TestPage',
    component: TestPageView //
  },
  {
    path: '/login',
    name: 'LoginView',
    component: LoginView
  },
  {
    path: '/register',
    name: 'RegisterView',
    component: RegisterView
  },
  {
    path: '/dashboard',
    component: DashboardLayout,
    meta: { requiresAuth: true }, // Dashboard 路径仍然需要认证
    children: [
      {
        path: '',
        redirect: '/dashboard/content'
      },
      {
        path: 'content',
        name: 'DashboardContent',
        component: DashboardOne
      },
      {
        path: 'interaction',
        name: 'DashboardInteraction',
        component: DashboardTwo
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('isAuthenticated')
  
  // 检查目标路径是否是公开的（不需要认证）
  const publicPaths = ['/login', '/register', '/test']; // <-- 修改：将 '/test' 加入公开路径
  const authRequired = !publicPaths.includes(to.path);

  if (authRequired && !isAuthenticated) {
    next('/login')
  } else {
    next()
  }
})

export default router