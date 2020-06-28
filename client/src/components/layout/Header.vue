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
              <!--<router-link to="/reservas" class="nav-item nav-link"><i class="fas fa-bookmark"></i> Reservas</router-link>
              <router-link to="/requisicoes" class="nav-item nav-link"><i class="fas fa-book"></i> Requisições</router-link>-->
              <div v-if="user_type==='Requisitante'">
                <a class="dropdown-toggle nav-item nav-link" data-toggle="dropdown" aria-expanded="false">
                  <strong><i class="fas fa-user"></i> {{username}}</strong>
                </a>
                <div class="dropdown-menu">
                    <router-link to="/perfil" class="dropdown-item nav-item">
                      <i class="far fa-user-circle"></i>
                      <strong>&nbsp;Perfil</strong>
                    </router-link>
                    <router-link to="/historico" class="dropdown-item nav-item">
                      <i class="fas fa-history"></i>
                      <strong>&nbsp;Histórico</strong>
                    </router-link>
                  <a class="dropdown-item nav-item" @click="logout()">
                    <i class="fas fa-sign-out-alt"></i>
                    <strong>&nbsp;Sign Out</strong>
                  </a>
                </div>
              </div>
              <div v-if="user_type==='Administrador'">
                <a class="nav-item nav-link" @click="logout()">
                    <i class="fas fa-sign-out-alt"></i>
                    <strong>&nbsp;Sign Out</strong>
                  </a>
              </div>
            </div>
        </div>
    </nav>
</div>
</template>

<style scoped>
  .logo {
    width: 90px;
  }
  .navbar {
    z-index: 5;
    background-color: rgb(240,240,240);
    box-shadow: rgba(0, 0, 0, 0.15) 0px 1px 10px 0px;
    position: fixed;
    top: 0;
    width: 100%;
  }
  .nav-item {
    font-style: normal;
    font-size: 15px;
    font-weight: bold;
    text-decoration: none;
    color: #465765;
  }
  .ml-auto .dropdown-menu {
    left: auto !important;
    right: 0px;
    background-color: rgb(240,240,240);
  }
  a {
    background-color:transparent !important;
    cursor: pointer;
  }
</style>

<script>
import UserHandler from '@/utils/UserHandler.js'

export default {
  name: 'Header',
  data: () => ({
    loggedIn: false,
    user_type: null,
    username: null
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (user !== false) {
      this.user_type = user.role
      this.username = user.username
      this.loggedIn = true
    }
  },
  methods: {
    logout () {
      UserHandler.remove()
      this.loggedIn = false
      this.user_type = null
      this.$router.push('/')
    }
  }
}
</script>
