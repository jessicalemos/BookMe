<template>
  <div class="col-8 mx-auto my-5 py-5">
  <div class="login-clean">
        <form @submit.prevent="register" style="max-width: 850px">
            <div class="form-row">
                <div class="col-md-6">
                  <span class="data">
                    <strong>Dados Biblioteca</strong>
                  </span>
                    <div class="form-group first">
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
                </div>
                <div class="col-md-6" style="padding-left: 25px;">
                  <span class="data">
                    <strong>Dados Responsável</strong>
                  </span>
                  <div class="form-group first">
                    <input class="form-control" v-model="employee.nome" type="text" placeholder="Nome" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" v-model="employee.email" type="email" placeholder="Email" required>
                  </div>
                  <div class="form-group">
                    <input class="form-control" v-model="employee.password" type="password" placeholder="Password" required>
                  </div>
                </div>
            </div>
              <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
                Registar biblioteca
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

<script>
import ApiLibraries from '@/api/ApiLibraries'
import UserHandler from '@/utils/UserHandler.js'

export default {
  name: 'RegisterLibrary',
  data: () => ({
    error: 0,
    library: {
      type: 'responsavel'
    },
    employee: {
      type: 'responsavel'
    }
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (this.user === false) {
      this.$router.push('/')
    } else if (user.role !== 'Administrador') {
      this.$router.push('/access-denied')
    }
  },
  methods: {
    async register () {
      const idLibrary = await ApiLibraries.registerLibrary(this.library)
      const req = await ApiLibraries.registerEmployee(idLibrary, this.employee)
      console.log(req)
      this.error = -1
    }
  }
}
</script>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.data {
  float: left;
}

.btn {
  margin-top: 1rem;
}

.form-group.first {
  margin-top: 50px;
}
</style>
