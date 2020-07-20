<template>
<div class="col-10 mx-auto my-5">
  <div class="row">
    <div class="col-lg-6">
      <div class="row">
        <div class="col-lg-12">
            <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <div class="type text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Bibliotecas</strong>
                </div>
                <div class="d-inline">
                  <button class="btn btn-outline-secondary" @click="$router.push({ name: 'Libraries-user' })">
                    Ver mais <i class="fa fa-plus-circle"></i>
                  </button>
                </div>
              </a>
              <a v-for="g in libraries" :key="g.id" class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <strong>{{g.nome}}</strong>
                  </div>
                </div>
              </a>
          </div>
        </div>
        <div class="col-lg-12">
            <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <div class="type text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Livros</strong>
                </div>
                <div class="d-inline">
                  <button class="btn btn-outline-secondary" @click="$router.push('/catalogo')">
                    Ver mais <i class="fa fa-plus-circle"></i>
                  </button>
                </div>
              </a>
              <a v-for="g in books" :key="g.id" class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <strong>{{g.titulo}}</strong><br>
                    <div class="author">{{g.autor}}</div>
                  </div>
                </div>
              </a>
          </div>
        </div>
      </div>
    </div>
    <div class="col-lg-6">
      <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <div class="type text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Recente</strong>
                </div>
                <div class="d-inline">
                  <button class="btn btn-outline-secondary" @click="$router.push('/historico')">
                    Ver mais <i class="fa fa-plus-circle"></i>
                  </button>
                </div>
              </a>
              <a v-for="g in processes" :key="g.id" class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <div class="date">
                    {{moment(g.processo.dataInicio).format('DD/MM/YYYY')}}
                    <i class="fas fa-arrows-alt-h"></i>
                    {{moment(g.processo.dataFim).format('DD/MM/YYYY')}}
                    </div>
                    <strong>{{g.processo.livro.titulo}}</strong><br>
                    {{g.biblioteca.nome}}<br>
                  </div>
                </div>
              </a>
          </div>
    </div>
</div>
</div>
</template>

<script>
import ApiUsers from '@/api/ApiUsers'
import UserHandler from '@/utils/UserHandler.js'
import moment from 'moment'
/* import ApiLibraries from '@/api/ApiLibraries' */

export default {
  name: 'HomePage',
  data: () => ({
    processes: {},
    books: {},
    libraries: {}
  }),
  mounted: function () {
    this.getProcesses()
    this.getBooks()
    this.getLibraries()
  },
  methods: {
    async getProcesses () {
      const user = UserHandler.get()
      if (user === false) {
        this.$router.push('/')
      } else if (user.role !== 'Requisitante') {
        this.$router.push('/access-denied')
      }
      const req = await ApiUsers.getProcesses(user.id)
      this.processes = req.slice(0, 4)
      console.log(this.processes)
    },
    async getBooks () {
      const req = await ApiUsers.getBooks()
      this.books = req.slice(0, 4)
    },
    async getLibraries () {
      const req = await ApiUsers.getBibliotecas()
      this.libraries = req.slice(0, 4)
      console.log(this.libraries)
    },
    moment
  }
}
</script>

<style scoped>
.col-lg-6 {
  min-height: 200px;
}

.col-lg-12 {
  min-height: 200px;
  margin-bottom: 20px;
}

.list-group-item-action {
  color: #495057;
}

.date, .author {
  font-size: 14px;
}

.type {
  font-size: 18px;
  color: rgb(29,33,37);
}

.btn.btn-outline-secondary:hover {
  background-color: rgb(140, 138, 138);
  color: rgb(246,247,248);
}

button.btn-outline-secondary {
  height: 35px;
}
</style>
