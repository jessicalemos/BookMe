<template>
  <div style="min-height: 100%">
    <div class="col-8 mx-auto my-5">
      <form @submit.prevent="searchProcess">
        <div class="p-1 bg-light rounded rounded-pill shadow-sm mb-4">
          <div class="input-group">
            <input type="search" placeholder="Qual o utilizador que procura?" v-model="search" aria-describedby="button-addon1" class="form-control border-0 bg-light">
            <div class="input-group-append">
              <button id="button-addon1" type="submit" class="btn btn-link text-primary"><i class="fa fa-search"></i></button>
            </div>
          </div>
        </div>
      </form>
      <div class="row justify-center" id="Group">
        <div class="col-12">
          <div class="list-group">
              <a v-for="g in resquests" :key="g[0]" class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <i class="fas fa-book"></i>&nbsp;
                    <strong>{{g.processo.livro.titulo}}</strong><br>
                    <i class="fa fa-envelope"></i>
                    &nbsp;{{g.requisitante.email}}<br>
                    <i class="fa fa-user"></i>&nbsp;
                    {{g.requisitante.nome}}
                  </div>
                  <div class="d-inline">
                    <button class="btn btn-secondary" type="button" @click="$bvModal.show('modal-renew'); selectProcess(g)" v-b-tooltip.hover title="Devolver">
                      <i class="far fa-check-circle"></i>
                    </button>
                  </div>
                </div>
              </a>
          </div>
        </div>
      </div>
    </div>
    <div>
      <b-modal id="modal-renew" centered>
        <template v-slot:modal-header>
          <b>Devolução</b>
        </template>
        <template v-slot:default>Tem a certeza que pretende registar como devolvido o livro <b>{{select.processo.livro.titulo}}</b> de <b>{{select.requisitante.nome}}</b>?</template>
        <template v-slot:modal-footer>
          <b-button size="sm" variant="outline-danger" @click="$bvModal.hide('modal-renew')">
            <i class="fas fa-times"></i> Não
          </b-button>
          <b-button size="sm" variant="outline-success" @click="devolver()">
            <i class="fas fa-check"></i> Sim
          </b-button>
        </template>
      </b-modal>
    </div>
  </div>
</template>

<style scoped>
.requisicoes {
  font-size: 18px;
  color: rgb(29,33,37);
}

.list-group-item {
  min-height: 70px;
}

.list-group-item-action {
  color: #495057;
}

.btn.btn-secondary{
  float: left;
  background-color: rgb(140, 138, 138);
}

.box {
  margin-bottom: 10px;
}

input {
  margin-top: 4px;
  border-radius: 50rem !important;
}
.text-primary {
  color: #7d8285 !important;
}
</style>

<script>
import ApiEmployee from '@/api/ApiEmployee'
import UserHandler from '@/utils/UserHandler.js'

export default {
  name: 'Requests',
  data: () => ({
    resquests: {},
    error: 0,
    select: null,
    search: null
  }),
  mounted: function () {
    this.user = UserHandler.get()
    if (this.user === false) {
      this.$router.push('/')
    } else if (this.user.role !== 'Funcionario' && this.user.role !== 'Responsavel') {
      this.$router.push('/access-denied')
    }
    this.getRequested()
  },
  methods: {
    async getRequested () {
      this.resquests = await ApiEmployee.getRequested('')
      console.log(this.resquests)
      this.error = -1
    },
    selectProcess (book) {
      this.select = book
      console.log(this.select)
    },
    async devolver () {
      console.log(this.select.processo.id)
      const req = await ApiEmployee.returnBook(this.select.processo.id)
      console.log(req)
      this.$bvModal.hide('modal-renew')
      this.resquests = await ApiEmployee.getRequested('')
    },
    async searchProcess () {
      console.log(this.search)
      if (this.search != null) {
        this.resquests = await ApiEmployee.getRequested(this.search)
        console.log(this.resquests)
      }
    }
  }
}
</script>
