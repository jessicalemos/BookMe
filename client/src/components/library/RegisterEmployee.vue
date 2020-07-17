<template>
    <div class="login-clean">
        <form @submit.prevent="register" style="max-width: 600px;">
            <div class="form-group">
              <input class="form-control" v-model="credentials.nome" type="text" placeholder="Nome" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="credentials.email" type="email" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="credentials.password" type="password" name="password" placeholder="Password" required>
              </div>
            <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
              Registar Funcionário
            </button>
            <div v-if="error === 1">
              <b-alert class="alert" variant="danger" show dismissible>
                <b>Error</b><br/>
                <span class="alert-text">{{msg}}</span>
              </b-alert>
            </div>
            <div v-if="error === -1">
              <b-alert class="alert" variant="success" show dismissible>
                <b>Sucesso</b><br/>
                <span class="alert-text">{{msg}}</span>
              </b-alert>
            </div>
        </form>
    </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.btn {
  margin-top: 2rem;
}
</style>

<script>
import ApiLibraries from '@/api/ApiLibraries'
import UserHandler from '@/utils/UserHandler.js'

export default {
  name: 'RegisterEmployee',
  data: () => ({
    user_id: null,
    error: 0,
    msg: null,
    credentials: {
      type: 'funcionario'
    }
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (user !== false) {
      this.user_id = user.id
      if (this.user.role !== 'Responsavel') {
        this.$router.push('/access-denied')
      }
    } else {
      this.$router.push('/')
    }
  },
  methods: {
    async register () {
      console.log(this.credentials)
      this.msg = await ApiLibraries.registerResponsible(this.user_id, this.credentials)
      if (this.msg === 'Username already exists') {
        this.msg = 'Email já em uso'
        this.error = 1
      } else {
        this.error = -1
      }
    }
  }
}
</script>
