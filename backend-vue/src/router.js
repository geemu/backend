import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '登录页面',
      component: Login
    }, {
      path: '/Home',
      name: '首页',
      component: Home
    }, {
      path: '/About',
      name: '关于',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      //  当访问路由时，它是惰性加载的。
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    }, {
      path: '/about',
      name: '登录'
    }
  ]
})
