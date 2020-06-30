<template>
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
        </form>
    </div>
</template>

<script>
import ApiLibraries from '@/api/ApiLibraries'

export default {
  name: 'RegisterLibrary',
  data: () => ({
    library: {
      type: 'responsavel'
    },
    employee: {
      type: 'responsavel'
    }
  }),
  mounted: function () {

  },
  methods: {
    async register () {
      const req = await ApiLibraries.registerLibrary(this.library)
      console.log(req)
      const reqe = await ApiLibraries.registerEmployee(this.employee)
      console.log(reqe)
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
