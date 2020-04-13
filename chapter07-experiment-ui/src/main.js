// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import routes from './router'

// 引入ElementUI
import ElementUI from 'element-ui'
// 引入ElementUI框架的样式文件
import 'element-ui/lib/theme-chalk/index.css'

import VueRouter from 'vue-router'
import store from './vuex/store'

// import Vuex from 'vuex'

// Vue使用ElementUI
Vue.use(ElementUI)
Vue.use(VueRouter)
// Vue.use(Vuex)

Vue.config.productionTip = false

const router = new VueRouter({
  routes
})

// 验证会话是否存在，不存在则跳转到登录页面
/* routes.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    sessionStorage.removeItem('user')
  }
  let user = JSON.parse(sessionStorage.getItem('user'))
  if (!user && to.path !== '/login') {
    next({ path: '/login' })
  } else {
    next()
  }
}) */

/* eslint-disable no-new */
new Vue({
  // el: '#app',
  router,
  store,
  // components: { App },
  // template: '<App/>'
  render: h => h(App)
}).$mount('#app')
