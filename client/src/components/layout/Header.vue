<template>
<div class="bs-example">
    <nav class="navbar navbar-expand-md navbar-light">
        <router-link to="/"><img class="logo" src="@/assets/img/logo_app.png"></router-link>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ml-auto">
              <router-link v-if="!loggedIn" to="/registar" class="nav-item nav-link"><i class="fas fa-user"></i> Sign Up</router-link>
              <router-link v-if="!loggedIn" to="/login" class="nav-item nav-link"><i class="fas fa-sign-in-alt"></i> Sign In</router-link>
              <router-link v-if="loggedIn && user_type==='Funcionario'" to="/reservas" class="nav-item nav-link"><i class="fas fa-bookmark"></i> Reservas</router-link>
              <router-link v-if="loggedIn && user_type==='Funcionario'" to="/requisicoes" class="nav-item nav-link"><i class="fas fa-book"></i> Requisições</router-link>
              <router-link v-if="loggedIn && user_type==='Responsavel'" to="/editar-biblioteca" class="nav-item nav-link"><i class="fas fa-home"></i>Biblioteca</router-link>
              <router-link v-if="loggedIn && user_type==='Responsavel'" to="/funcionarios" class="nav-item nav-link"><i class="fas fa-user-friends"></i> Funcionários</router-link>
              <div v-if="loggedIn && user_type==='Requisitante'" class="btn-group">
                <a class="dropdown-toggle nav-item nav-link info-number" data-toggle="dropdown">
                  <strong><i class="far fa-bell"></i></strong>
                  <span class="badge bg-green">{{notifications.length}}</span>
                </a>
                <ul v-if="notifications.length!=0" class="dropdown-menu" role="menu">
                  <li v-for="g in notifications" :key="g.id">
                  <a>{{g}}</a>
                  </li>
                </ul>
              </div>
              <div v-if="loggedIn && user_type!='Administrador'" class="btn-group">
                <a class="dropdown-toggle nav-item nav-link" data-toggle="dropdown">
                  <strong><i class="fas fa-user"></i> {{username}}</strong>
                </a>
                <div class="dropdown-menu">
                  <router-link
                    :to="user_type==='Funcionario' ? { name: 'EditEmployee'} : {name: 'EditProfile'}"
                    class="dropdown-item nav-item">
                    <i class="far fa-user-circle"></i>
                    <strong>&nbsp;Perfil</strong>
                  </router-link>
                  <router-link v-if="user_type==='Requisitante'" to="/historico" class="dropdown-item nav-item">
                    <i class="fas fa-history"></i>
                    <strong>&nbsp;Histórico</strong>
                  </router-link>
                  <a class="dropdown-item nav-item" @click="logout()">
                    <i class="fas fa-sign-out-alt"></i>
                    <strong>&nbsp;Sign Out</strong>
                  </a>
                </div>
              </div>
              <div v-if="loggedIn && user_type==='Administrador'">
                  <a class="nav-item nav-link" @click="logout()">
                      <i class="fas fa-sign-out-alt"></i>
                      <strong>&nbsp;Sign Out</strong>
                    </a>
                </div>
              </div>
        </div>
    </nav>
    <div class="nav-container">
      <b-breadcrumb :items="breadcrumbList"></b-breadcrumb>
      <a v-if="this.$route.name === 'Books-user' || this.$route.name === 'Books-library'" class="sidebar" v-b-toggle.sidebar-1>
        Filtrar
      </a>
    </div>
</div>
</template>

<style scoped>
  .sidebar {
    position:absolute;
    right:1rem;
    top:50%;
    transform:translateY(-50%);
    color: #7d8285;
    font-weight: bold;
  }
  .nav-container {
    position:relative;
  }
  .logo {
    width: 90px;
  }
  .navbar {
    z-index: 1111;
    background-color: rgb(240,240,240);
    position: fixed;
    top: 0;
    width: 100%;
    box-shadow: rgba(0, 0, 0, 0.15) 0px 1px 10px 0px;
  }
  .nav-item {
    font-style: normal;
    font-size: 15px;
    font-weight: bold;
    text-decoration: none;
    color: #7d8285;
  }
  .ml-auto .dropdown-menu {
    left: auto !important;
    right: 0px;
    background-color: rgb(240,240,240);
  }
  a {
    background-color: transparent !important;
    cursor: pointer;
  }
  ::v-deep .breadcrumb {
    background-color: transparent;
    margin-top: 60px;
  }
  ::v-deep a {
    color: #7d8285;
    font-weight: bold;
  }
  ::v-deep a:hover {
    color: rgba(0, 0, 0, 0.7);
  }
  .info-number .badge {
    font-size: 10px;
    font-weight: normal;
    line-height: 13px;
    padding: 2px 6px;
    position: absolute;
    right: 2px;
    top: 8px;
  }
  .bg-green {
      background: #32CD32 !important;
      border: 1px solid #32CD32 !important;
      color: #fff;
  }
</style>

<script>
import UserHandler from '@/utils/UserHandler.js'
import ApiUsers from '@/api/ApiUsers'

export default {
  name: 'Header',
  data: () => ({
    loggedIn: false,
    user_type: null,
    username: null,
    breadcrumbList: [],
    user: null,
    notifications: {}
  }),
  watch: {
    '$route' () {
      this.updateList()
    }
  },
  mounted: function () {
    this.updateList()
    const user = UserHandler.get()
    if (user !== false) {
      this.user_type = user.role
      this.username = user.username
      this.user = user.id
      this.loggedIn = true
    }
    this.getNotifications()
  },
  methods: {
    logout () {
      UserHandler.remove()
      this.loggedIn = false
      this.user_type = null
      this.$router.push('/')
    },
    routeTo (pRouteTo) {
      if (this.breadcrumbList[pRouteTo].link) {
        this.$router.push(this.breadcrumbList[pRouteTo].link)
      }
    },
    updateList () {
      this.breadcrumbList = this.$route.meta.breadcrumb
    },
    async getNotifications () {
      if (this.loggedIn === true && this.user_type === 'Requisitante') {
        this.notifications = await ApiUsers.getNotifications(this.user)
        console.log(this.notifications)
      }
    }
  }
}
</script>
