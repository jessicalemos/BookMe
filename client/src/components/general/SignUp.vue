<template>
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
            <span class="error" v-if="error === 1"><i class="fas fa-exclamation-triangle"></i> Email já em uso</span>
            <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
                Sign Up
            </button>
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
      }
    }
  }
}
</script>
