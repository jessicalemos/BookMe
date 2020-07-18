<template>
  <div class="col-8 mx-auto my-5 py-5">
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
  </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
  .btn {
    margin-top: 1rem;
  }
</style>

<script src="@/api/ApiUsers.js?t=<?=time()?>" type="text/javascript"></script>
<script>
import UserHandler from '@/utils/UserHandler.js'
import ApiUsers from '@/api/ApiUsers'
import ApiEmployee from '@/api/ApiEmployee'
import ApiLibraries from '@/api/ApiLibraries'

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
        if (user.role === 'Requisitante') {
          ApiUsers.refresh(req.token.token)
          this.$router.push('/home')
        }
        if (user.role === 'Administrador') {
          ApiLibraries.refresh(req.token.token)
          this.$router.push('/gerir-bibliotecas')
        }
        if (user.role === 'Responsavel' || user.role === 'Funcionario') {
          ApiEmployee.refresh(req.token.token)
          this.$router.push('/catalogo-biblioteca')
        }
      } else {
        this.error = 1
      }
    }
  }
}
</script>
