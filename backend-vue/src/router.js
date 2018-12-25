import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'home',
      hidden: true,
      component: Home
    },
    {
      path: '/',
      name: 'Login',
      component: () => import('./views/Login.vue')
    }
  ]
})
