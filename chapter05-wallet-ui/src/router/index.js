import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Charge from '@/components/Charge'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/charge',
      name: 'Charge',
      component: Charge
    }, {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    }]
})
