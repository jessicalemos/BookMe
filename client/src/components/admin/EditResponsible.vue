<template>
  <div class="col-8 mx-auto my-5 py-5">
    <div class="login-clean">
        <form @submit.prevent="editResponsible" style="max-width: 600px;">
            <div class="form-group">
              <input class="form-control" v-model="responsible.nome" type="text" name="nome" placeholder="Nome" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="responsible.email" type="email" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="responsible.password" type="password" name="password" placeholder="Password" required>
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
  </div>
</template>

<script>
import ApiLibraries from '@/api/ApiLibraries'
import UserHandler from '@/utils/UserHandler.js'

export default {
  name: 'EditResponsible',
  data: () => ({
    error: 0,
    responsible: {}
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (this.user === false) {
      this.$router.push('/')
    } else if (user.role !== 'Administrador') {
      this.$router.push('/access-denied')
    }
    this.getResponsibleInfo()
  },
  methods: {
    async getResponsibleInfo () {
      const idLibrary = localStorage.getItem('Library')
      this.responsible = await ApiLibraries.responsibleInfo(idLibrary)
      this.responsible.password = ''
    },
    async editResponsible () {
      const req = await ApiLibraries.editResponsible(this.responsible)
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
