<template>
  <div class="col-8 mx-auto my-5 py-5">
    <div class="login-clean">
        <form @submit.prevent="register" style="max-width: 600px;">
            <div class="form-group">
              <input class="form-control" v-model="user.nome" type="text" placeholder="Nome" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="user.email" type="email" placeholder="Email" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="user.telemovel" type="text" placeholder="Telemóvel" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="user.password" type="password" placeholder="Password" required>
            </div>
            <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
                Sign Up
            </button>
            <div v-if="error === 1">
              <b-alert class="alert" variant="danger" show dismissible>
                <b>Error</b><br/>
                <span class="alert-text">Email já em uso</span>
              </b-alert>
            </div>
            <div v-if="error === -1">
              <b-alert class="alert" variant="success" show dismissible>
                <b>Sucesso</b><br/>
                <span class="alert-text">Registado com sucesso</span>
              </b-alert>
            </div>
        </form>
    </div>
  </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.btn {
  margin-top: 2rem;
}
</style>

<script>
import ApiUsers from '@/api/ApiUsers'

export default {
  data: () => ({
    error: 0,
    user: {
      type: 'requisitante',
      email: '',
      password: '',
      nome: '',
      telemovel: ''
    }
  }),
  methods: {
    async register () {
      const req = await ApiUsers.register(this.user)
      if (req.success && req.token === 'Username already exists') {
        this.error = 1
      } else if (req.success) {
        this.error = -1
      }
    }
  }
}
</script>
