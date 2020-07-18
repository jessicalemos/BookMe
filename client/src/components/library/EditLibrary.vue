<template>
    <div class="login-clean">
        <form @submit.prevent="editLibrary" style="max-width: 600px;">
            <div class="form-group">
              <input class="form-control" v-model="library.nome" type="text" placeholder="Nome" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="library.email" type="email" placeholder="Email" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="library.morada" type="text" placeholder="Morada" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="library.telemovel" type="text" placeholder="Telemóvel" required>
            </div>
            <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
              Guardar
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
                <span class="alert-text">Biblioteca editada com sucesso</span>
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
import ApiEmployee from '@/api/ApiEmployee'
import UserHandler from '@/utils/UserHandler.js'

export default {
  name: 'EditLibrary',
  data: () => ({
    library: {},
    error: 0
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (this.user === false) {
      this.$router.push('/')
    } else if (user.role !== 'Responsavel') {
      this.$router.push('/access-denied')
    }
    this.getLibrary()
  },
  methods: {
    async getLibrary () {
      this.library = await ApiEmployee.getLibrary()
    },
    async editLibrary () {
      const req = await ApiEmployee.editLibrary(this.library)
      console.log(req)
      this.error = -1
    }
  }
}
</script>
