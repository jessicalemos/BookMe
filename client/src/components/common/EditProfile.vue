<template>
    <div class="login-clean">
        <form @submit.prevent="editRequester" style="max-width: 600px;">
            <div class="form-group">
              <input class="form-control" v-model="credentials.nome" type="text" name="nome" placeholder="Nome" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="credentials.email" type="email" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="credentials.telemovel" type="text" name="telemovel" placeholder="Telemóvel" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="credentials.password" type="password" name="password" placeholder="Password" required>
            </div>
            <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
              Guardar
            </button>
            <div v-if="error === 1">
              <b-alert class="alert" variant="danger" show dismissible>
                <b>Error</b><br/>
              </b-alert>
            </div>
            <div v-if="error === -1">
              <b-alert class="alert" variant="success" show dismissible>
                <b>Sucesso</b><br/>
                <span class="alert-text">Editado com sucesso</span>
              </b-alert>
            </div>
        </form>
    </div>
</template>

<script>
import ApiUsers from '@/api/ApiUsers'
import UserHandler from '@/utils/UserHandler.js'

export default {
  name: 'EditProfile',
  data: () => ({
    error: 0,
    credentials: {}
  }),
  mounted: function () {
    this.getUserInfo()
  },
  methods: {
    async getUserInfo () {
      const user = UserHandler.get()
      this.credentials = await ApiUsers.getRequester(user.id)
      this.credentials.type = 'requisitante'
      this.credentials.password = ''
    },
    async editRequester () {
      const req = await ApiUsers.editRequester(this.credentials)
      console.log(req)
      this.error = -1
    }
  }
}
</script>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.btn {
  margin-top: 2rem;
}
</style>
