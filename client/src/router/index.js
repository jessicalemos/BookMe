import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/general/HomePage.vue'
import SignIn from '../components/general/SignIn.vue'
import SignUp from '../components/general/SignUp.vue'
import Libraries from '../components/common/Libraries.vue'
import RegisterLibrary from '../components/admin/RegisterLibrary.vue'
import EditResponsible from '../components/admin/EditResponsible.vue'
import Books from '../components/catalog/Books.vue'
import Book from '../components/catalog/Book.vue'
import RegisterBook from '../components/library/RegisterBook.vue'
import Employees from '../components/library/Employees.vue'
import RegisterEmployee from '../components/library/RegisterEmployee.vue'
import Bookings from '../components/library/Bookings.vue'
import Requests from '../components/library/Requests.vue'
import EditLibrary from '../components/library/EditLibrary.vue'
import EditProfile from '../components/common/EditProfile.vue'
import History from '../components/user/History.vue'
import UserHome from '../components/user/HomePage.vue'

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
    path: '/registar',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/bibliotecas',
    name: 'Libraries',
    component: Libraries
  },
  {
    path: '/registar-biblioteca',
    name: 'RegisterLibrary',
    component: RegisterLibrary
  },
  {
    path: '/editar-responsavel',
    name: 'EditResponsible',
    component: EditResponsible
  },
  {
    path: '/catalogo',
    name: 'Books',
    component: Books
  },
  {
    path: '/livro',
    name: 'Book',
    component: Book
  },
  {
    path: '/registar-livro',
    name: 'RegisterBook',
    component: RegisterBook
  },
  {
    path: '/funcionarios',
    name: 'Employees',
    component: Employees
  },
  {
    path: '/registar-funcionario',
    name: 'RegisterEmployee',
    component: RegisterEmployee
  },
  {
    path: '/reservas',
    name: 'Bookings',
    component: Bookings
  },
  {
    path: '/requisicoes',
    name: 'Requests',
    component: Requests
  },
  {
    path: '/editar-biblioteca',
    name: 'EditLibrary',
    component: EditLibrary
  },
  {
    path: '/editar-perfil',
    name: 'EditProfile',
    component: EditProfile
  },
  {
    path: '/historico',
    name: 'History',
    component: History
  },
  {
    path: '/Home',
    name: 'UserHomePage',
    component: UserHome
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
