import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = require.context('@/pages', true, /\.router\.js$/)
let routes = []

router.keys().forEach(v => {
  routes = [...routes, ...router(v).default
  ]
})

export default new Router({
  routes
})
