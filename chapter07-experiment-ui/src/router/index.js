import Vue from 'vue'
import Router from 'vue-router'
// 引入Home页面组件
import Home from '@/components/Home'
import Main from '@/components/Main'
import List from '@/components/List'
import Login from '@/components/Login'

Vue.use(Router)

let routes = [
  {
    path: '/login',
    component: Login,
    name: '',
    hidden: true
  },
  {
    path: '/',
    component: Home,
    name: '实验管理',
    iconCls: 'el-icon-message', // 图标样式class
    children: [
      { path: '/main', component: Main, name: '主页', hidden: true },
      { path: '/list', component: List, name: '实验列表' }
    ]
  }
]
export default routes
