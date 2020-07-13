<template>
    <div class="login-clean">
        <form @submit.prevent="login" style="padding-top: 0px;">
            <div class="illustration">
              <i class="fas fa-user-alt col-xl-5"></i>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="credentials.email" type="email" placeholder="Email" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="credentials.password" type="password" placeholder="Password" required>
            </div>
            <div class="form-group">
              <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
                Sign In
              </button>
            </div>
            <div class="modal-footer">Ainda não tens conta?<router-link to="/registar">Cria aqui</router-link>
              <div v-if="error === 1">
                <b-alert class="alert" variant="danger" show dismissible>
                  <b>Error</b><br/>
                  <span class="alert-text">Credenciais inválidas</span>
                </b-alert>
              </div>
            </div>
        </form>
    </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
  .btn {
    margin-top: 1rem;
  }
</style>

<script>
import UserHandler from '@/utils/UserHandler.js'
import ApiUsers from '@/api/ApiUsers'

export default {
  data: () => ({
    error: 0,
    credentials: {
      type: 'responsavel'
    }
  }),
  methods: {
    async login () {
      const req = await ApiUsers.login(this.credentials)
      if (req.success) {
        UserHandler.save(req.token.token)
        const user = UserHandler.get()
        console.log(user)
        if (user.role === 'Requisitante') {
          this.$router.push('/Home')
        }
        if (user.role === 'Administrador') {
          this.$router.push({ name: 'Libraries-admin' })
        }
        if (user.role === 'Responsavel') {
          this.$router.push('/Catalogo')
        }
      } else {
        this.error = 1
      }
    }
  }
}
</script>
