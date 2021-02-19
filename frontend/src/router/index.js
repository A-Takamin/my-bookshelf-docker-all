import { createRouter, createWebHistory } from 'vue-router'
import Top from '../views/Top.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Home from '../views/Home.vue'
import BookDetail from '../views/BookDetail.vue'
import BookAdd from '../views/BookAdd.vue'

import firebase from 'firebase'

const routes = [
  {
    path: '/',
    name: 'Top',
    component: Top
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },

  /* 以下は認証後の画面 */

  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: { requiresAuth: true }
  },
  {
    path: '/bookDetail',
    name: 'BookDetail',
    component: BookDetail,
    meta: { requiresAuth: true },
    props: true
  },
  {
    path: '/bookAdd',
    name: 'BookAdd',
    component: BookAdd,
    meta: { requiresAuth: true }
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach( (to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  
  if(requiresAuth) {
    firebase.auth().onAuthStateChanged( (user) => {
      // console.log(user)
      if(user) {
        console.log("auth success by index.js")
        next()
      }else {
        console.log("auth failed by index.js")
        next('/login')
      }
  })
  } else {
    next()
  }
})
export default router
