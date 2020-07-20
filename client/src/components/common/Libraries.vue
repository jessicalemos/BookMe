<template>
  <div style="min-height: 100%">
    <div class="col-8 mx-auto my-5">
      <form @submit.prevent="searchLibrary">
        <div class="p-1 bg-light rounded rounded-pill shadow-sm mb-4">
          <div class="input-group">
            <input type="search" placeholder="Qual o livro que procura?" v-model="search" aria-describedby="button-addon1" class="form-control border-0 bg-light">
            <div class="input-group-append">
              <button id="button-addon1" type="submit" class="btn btn-link text-primary"><i class="fa fa-search"></i></button>
            </div>
          </div>
        </div>
      </form>
      <div class="row justify-center" id="Group">
        <div class="col-12 box">
          <button v-if="user_type == 'Administrador'" type="button" class="btn btn-secondary" @click="$router.push('/registar-biblioteca')">
            <i class="fa fa-plus"></i> Adicionar
          </button>
        </div>
        <div class="col-12">
          <div class="list-group">
              <a v-for="g in librariesFilter[page]" :key="g.id" class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <strong>
                  {{g.nome}}
                </strong>
                <div v-if="user_type == 'Administrador'" class="d-inline">
                  <button class="btn btn-secondary config" v-b-tooltip.hover title="Editar" type="button" @click="setLibrary(g.id)">
                    <i class="fas fa-cog"></i>
                  </button>
                  <button class="btn btn-danger remove" v-b-tooltip.hover title="Remover" @click="$bvModal.show('modal-scoped'); itemToRemove(g.id)" type="button">
                    <i class="far fa-trash-alt"></i>
                  </button>
                </div>
                <div v-else>
                  <button class="btn btn-outline-secondary" v-b-tooltip.hover title="Selecionar" @click="selectLibrary(g.id)" type="button">
                    <i class="fas fa-arrow-right"></i>
                  </button>
                </div>
              </a>
          </div>
        </div>
      </div>
    </div>
    <nav v-if="libraries.length > nrPerPage" class="pagination justify-content-center">
      <ul class="pagination pagination-circle pg-blue">
        <li class="page-item">
          <a class="page-link" v-bind:disabled="page==0" v-on:click="page = 0" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
        <li v-for="p in range(0,librariesFilter.length-1)" :key="p" v-on:click="page = p" class="page-item">
          <a class="page-link">{{p+1}}</a>
        </li>
        <li class="page-item">
          <a class="page-link" v-bind:disabled="page==Math.floor(librariesFilter.length/nrPerPage)-1" v-on:click="page = librariesFilter.length - 1" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>
    </nav>
    <div>
      <b-modal id="modal-scoped" centered>
        <template v-slot:modal-header>
          <b>Remover Biblioteca</b>
        </template>
        <template v-slot:default>Tem a certeza que pretende remover esta biblioteca?</template>
        <template v-slot:modal-footer="{}">
          <b-button size="sm" variant="outline-danger" @click="$bvModal.hide('modal-scoped')">
            <i class="fas fa-times"></i> Não
          </b-button>
          <b-button size="sm" variant="outline-success" @click="removeLibrary()">
            <i class="fas fa-check"></i> Sim
          </b-button>
        </template>
      </b-modal>
    </div>
  </div>
</template>

<script>
import UserHandler from '@/utils/UserHandler.js'
import ApiLibraries from '@/api/ApiLibraries'
import ApiUsers from '@/api/ApiUsers'

export default {
  name: 'Libraries',
  data: () => ({
    user_type: null,
    libraries: {},
    page: 0,
    nrPerPage: 5,
    librariesFilter: [],
    selected: null,
    search: null
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (user !== false) {
      this.user_type = user.role
      if (this.user_type !== 'Administrador' && this.user_type !== 'Requisitante') {
        this.$router.push('/access-denied')
      }
    } else {
      this.$router.push('/')
    }
    this.getLibraries()
  },
  methods: {
    async getLibraries () {
      if (this.user_type === 'Administrador') {
        this.libraries = await ApiLibraries.libraries()
      } else {
        this.libraries = await ApiUsers.getBibliotecas()
      }
      this.filterLibraries()
    },
    filterLibraries () {
      var i = 0
      var p = 0
      var page = []
      page[p] = []
      var nrPerPage = this.nrPerPage
      this.libraries.forEach(function (c) {
        if (i < nrPerPage) {
          page[p].push(c)
          i++
        } else {
          p++
          page[p] = []
          i = 1
          page[p].push(c)
        }
      })
      this.librariesFilter = page
    },
    range (start, end) {
      return Array(end - start + 1).fill().map((_, idx) => start + idx)
    },
    setLibrary (idLibrary) {
      localStorage.setItem('Library', idLibrary)
      this.$router.push({ name: 'EditResponsible' })
    },
    itemToRemove (library) {
      this.selected = library
    },
    async removeLibrary () {
      const req = await ApiLibraries.removeLibrary(this.selected)
      console.log(req)
      this.getLibraries()
      this.$bvModal.hide('modal-scoped')
      this.makeToast('success', 'Sucesso', 'Biblioteca removida com sucesso')
    },
    async searchLibrary () {
      if (this.search != null) {
        this.libraries = await ApiLibraries.searchLibrary(this.search)
        this.filterLibraries()
      }
    },
    makeToast (variant, titlemsg, msg) {
      this.$bvToast.toast(`${msg}`, {
        title: `${titlemsg || 'default'}`,
        variant: variant,
        solid: true
      })
    },
    selectLibrary (idLibrary) {
      localStorage.setItem('Library', idLibrary)
      this.$router.push('/catalogo')
    }
  },
  computed: {}
}
</script>

<style>
.b-toaster.b-toaster-top-right .b-toaster-slot {
    top: 82px !important;
    right: 12px;
}
</style>

<style scoped>
input {
  border-radius: 50rem !important;
}

.text-primary {
  color: #7d8285 !important;
}

a {
  cursor: pointer;
}

.list-group-item {
  height: 70px;
}

.btn.config {
  background-color: rgb(140, 138, 138);
  color: rgb(246,247,248);
}

.btn.btn-danger.remove {
  margin-left: 5px;
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
</style>
