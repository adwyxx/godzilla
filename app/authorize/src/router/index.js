import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'

Vue.use(Router)
const routes = {
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
}
const router = new Router({
  routes
})

// 路由守卫：进入路由前
router.beforeEach((to, from, next) => {
  // 每次切换页面时，调用进度条
  // cache机制
  const token = localStorage.getItem('token')
  if (token) {
    next()
  } else {
    if (to.path === '/login' || to.path === '/') {
      next()
    } else {
      next('/')
    }
  }
})
// 路由守卫：进入新页面
router.afterEach(() => {
  // 在即将进入新的页面组件前，关闭掉进度条
})
export default router
