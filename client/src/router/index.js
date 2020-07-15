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
import AccessDenied from '../components/common/AccessDenied.vue'

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
    path: '/gerir-bibliotecas',
    name: 'Libraries-admin',
    component: Libraries
  },
  {
    path: '/bibliotecas',
    name: 'Libraries-user',
    component: Libraries,
    meta: {
      breadcrumb: [
        { text: 'Home', href: '/home' },
        { text: 'Bibliotecas', active: true }
      ]
    }
  },
  {
    path: '/registar-biblioteca',
    name: 'RegisterLibrary',
    component: RegisterLibrary,
    meta: {
      breadcrumb: [
        { text: 'Bibliotecas', href: '/bibliotecas' },
        { text: 'Registar Biblioteca', active: true }
      ]
    }
  },
  {
    path: '/editar-responsavel/',
    name: 'EditResponsible',
    component: EditResponsible,
    meta: {
      breadcrumb: [
        { text: 'Bibliotecas', href: '/bibliotecas' },
        { text: 'Editar Responsável', active: true }
      ]
    }
  },
  {
    path: '/catalogo-biblioteca',
    name: 'Books-library',
    component: Books
  },
  {
    path: '/catalogo',
    name: 'Books-user',
    component: Books,
    meta: {
      breadcrumb: [
        { text: 'Home', href: '/home' },
        { text: 'Livros', active: true }
      ]
    }
  },
  {
    path: '/editar-livro',
    name: 'Book-library',
    component: Book
  },
  {
    path: '/livro',
    name: 'Book-user',
    component: Book,
    meta: {
      breadcrumb: [
        { text: 'Home', href: '/home' },
        { text: 'Catálogo', href: '/catalogo' },
        { text: 'Livro', active: true }
      ]
    }
  },
  {
    path: '/registar-livro',
    name: 'RegisterBook',
    component: RegisterBook,
    meta: {
      breadcrumb: [
        { text: 'Catálogo', href: '/catalogo-biblioteca' },
        { text: 'Registar Livro', active: true }
      ]
    }
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
    component: Bookings,
    meta: {
      breadcrumb: [
        { text: 'Catálogo', href: '/catalogo-biblioteca' },
        { text: 'Reservas', active: true }
      ]
    }
  },
  {
    path: '/requisicoes',
    name: 'Requests',
    component: Requests,
    meta: {
      breadcrumb: [
        { text: 'Catálogo', href: '/catalogo-biblioteca' },
        { text: 'Requisições', active: true }
      ]
    }
  },
  {
    path: '/editar-biblioteca',
    name: 'EditLibrary',
    component: EditLibrary,
    meta: {
      breadcrumb: [
        { text: 'Catálogo', href: '/catalogo-biblioteca' },
        { text: 'Editar Biblioteca', active: true }
      ]
    }
  },
  {
    path: '/perfil-funcionario',
    name: 'EditEmployee',
    component: EditProfile
  },
  {
    path: '/perfil',
    name: 'EditProfile',
    component: EditProfile,
    meta: {
      breadcrumb: [
        { text: 'Home', href: '/home' },
        { text: 'Editar Perfil', active: true }
      ]
    }
  },
  {
    path: '/historico',
    name: 'History',
    component: History,
    meta: {
      breadcrumb: [
        { text: 'Home', href: '/home' },
        { text: 'Histórico', active: true }
      ]
    }
  },
  {
    path: '/home',
    name: 'UserHomePage',
    component: UserHome
  },
  {
    path: '/access-denied',
    name: 'AccessDenied',
    component: AccessDenied
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
