// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'element-ui/lib/theme-chalk/index.css'
import './assets/style.scss'
import Vue from 'vue'
import ElementUI from 'element-ui'
import App from './App'
import router from './router'
import store from './store'

Vue.config.productionTip = false
Vue.use(ElementUI, {size: 'small'})
// 登录页
router.addRoutes([{path: '*', redirect: {name: 'login'}}])
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
