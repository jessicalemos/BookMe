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
              <a v-for="g in bookingsFilter[page]" :key="g[0]" class="list-group-item list-group-item-action">
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
                    <button class="btn btn-secondary" type="button" @click="$bvModal.show('modal-renew'); selectProcess(g)" v-b-tooltip.hover title="Requisitar">
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
          <b>Requisição</b>
        </template>
        <template v-slot:default>Tem a certeza que pretende registar como requisitado o livro <b>{{select.processo.livro.titulo}}</b> de <b>{{select.requisitante.nome}}</b>?</template>
        <template v-slot:modal-footer>
          <b-button size="sm" variant="outline-danger" @click="$bvModal.hide('modal-renew')">
            <i class="fas fa-times"></i> Não
          </b-button>
          <b-button size="sm" variant="outline-success" @click="requisitar()">
            <i class="fas fa-check"></i> Sim
          </b-button>
        </template>
      </b-modal>
    </div>
    <nav v-if="bookings.length > nrPerPage" class="pagination justify-content-center">
      <ul class="pagination">
        <li class="page-item">
          <a class="page-link" v-bind:disabled="page==0" v-on:click="page = 0" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
        <li v-for="p in range(0,bookingsFilter.length-1)" :key="p" v-on:click="page = p" class="page-item">
          <a class="page-link">{{p+1}}</a>
        </li>
        <li class="page-item">
          <a class="page-link" v-bind:disabled="page==Math.floor(bookingsFilter.length/nrPerPage)-1" v-on:click="page = bookingsFilter.length - 1" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<style scoped>
.reservas {
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
  name: 'Bookings',
  data: () => ({
    bookings: {},
    error: 0,
    search: null,
    select: null,
    bookingsFilter: [],
    page: 0,
    nrPerPage: 4
  }),
  mounted: function () {
    this.user = UserHandler.get()
    if (this.user === false) {
      this.$router.push('/')
    } else if (this.user.role !== 'Funcionario' && this.user.role !== 'Responsavel') {
      this.$router.push('/access-denied')
    }
    this.getReserved()
  },
  methods: {
    async getReserved () {
      this.bookings = await ApiEmployee.getReserved('')
      this.error = -1
      this.filterBookings()
    },
    filterBookings () {
      var i = 0
      var p = 0
      var page = []
      page[p] = []
      var nrPerPage = this.nrPerPage
      this.bookings.forEach(function (c) {
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
      this.bookingsFilter = page
    },
    selectProcess (book) {
      this.select = book
    },
    async requisitar () {
      const req = await ApiEmployee.requisitionBook(this.select.processo.id)
      this.$bvModal.hide('modal-renew')
      this.makeToast('success', 'Sucesso', req)
      this.bookings = await ApiEmployee.getReserved('')
      this.filterBookings()
    },
    async searchProcess () {
      if (this.search != null) {
        this.bookings = await ApiEmployee.getReserved(this.search)
        this.filterBookings()
      }
    },
    range (start, end) {
      return Array(end - start + 1).fill().map((_, idx) => start + idx)
    },
    makeToast (variant, titlemsg, msg) {
      this.$bvToast.toast(`${msg}`, {
        title: `${titlemsg || 'default'}`,
        variant: variant,
        solid: true
      })
    }
  }
}
</script>
