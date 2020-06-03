import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/HomePage.vue'
import SignIn from '../components/SignIn.vue'
import SignUp from '../components/SignUp.vue'
import Libraries from '../components/admin/Libraries.vue'
import RegisterLibrary from '../components/admin/RegisterLibrary.vue'
import EditResponsible from '../components/admin/EditResponsible.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'SignIn',
    component: SignIn
  },
  {
    path: '/register',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/admin/libraries',
    name: 'Libraries',
    component: Libraries
  },
  {
    path: '/admin/register-library',
    name: 'RegisterLibrary',
    component: RegisterLibrary
  },
  {
    path: '/admin/edit-responsible',
    name: 'EditResponsible',
    component: EditResponsible
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
