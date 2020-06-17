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
            <span class="error" v-if="error === 1"><i class="fas fa-exclamation-triangle"></i> Credenciais inválidas</span>
            <div class="form-group">
              <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
                Sign In
              </button>
            </div>
            <div class="modal-footer">Ainda não tens conta?<router-link to="/registar">Cria aqui</router-link>
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
      type: 'requisitante'
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
      } else {
        this.error = 1
      }
    }
  }
}
</script>
