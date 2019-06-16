import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const module = require.context('@/pages', true, /\.module\.js$/)
let modules = {}

module.keys().forEach(v => {
  modules = {...modules, ...module(v)}
})

console.log('%c ----- start -----', 'color: #2980b9')
console.log(modules)
console.log('%c ----- end -----', 'color: #e74c3c')

export default new Vuex.Store({
  modules,
})
