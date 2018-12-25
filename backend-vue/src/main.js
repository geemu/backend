import Vue from 'vue'
import App from './App.vue'
import Router from './router'
import Store from './store'
import ElementUI from 'element-ui'
import JsCookies from 'js-cookie'
import I18n from './lang'

Vue.config.productionTip = false
Vue.use(ElementUI, {
  size: JsCookies.get('size') || 'medium',
  I18n: (key, value) => I18n.t(key, value)
})

let vm = new Vue({
  el: '#app',
  Router,
  Store,
  I18n,
  render: h => h(App)
})

Vue.use({
  vm
})
