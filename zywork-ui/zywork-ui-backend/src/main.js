// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import router from './router'

import ElementUI from 'element-ui'
// Don't change next two lines position!!!
import 'element-ui/lib/theme-chalk/index.css'
import './theme/style.scss'

import 'font-awesome/css/font-awesome.min.css'

import {checkLogin} from './utils/Auth'

Vue.config.productionTip = false

Vue.use(ElementUI)

/* eslint-disable no-new */
router.beforeEach((to, from, next) => {
  let self = router.app
  if (to.meta !== undefined && to.meta.requiresAuth === false) {
    checkLogin().then(data => {
      if (data) {
        next({path: '/home'})
      } else {
        next()
      }
    }).catch(error => {
      console.log(error)
    })
  } else {
    checkLogin().then(data => {
      if (data) {
        next()
      } else {
        self.$message({
          showClose: true,
          message: '您还未登录系统，请先登录！',
          center: true,
          type: 'warning',
          duration: 5000
        })
        next({path: '/login'})
      }
    }).catch(error => {
      console.log(error)
    })
  }
})

new Vue({
  el: '#app',
  router,
  components: {}
})
