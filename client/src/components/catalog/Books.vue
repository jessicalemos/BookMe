<template>
<div>
    <div>
    <b-sidebar id="sidebar-1" right>
      <div class="panel-group" id="accordion">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" class="filter" href="#collapse">
              Biblioteca <i class="fas fa-angle-down"></i></a>
            </h4>
          </div>
          <div id="collapse" class="panel-collapse collapse">
            <div class="panel-body">
              <div class="custom-control custom-radio">
                <input type="radio" class="custom-control-input" id="defaultUnchecked" name="biblioteca">
                <label class="custom-control-label" for="defaultUnchecked">Default unchecked</label>
              </div>
            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" class="filter" href="#collapse2">
              Autor <i class="fas fa-angle-down"></i></a>
            </h4>
          </div>
          <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
              <div v-for="g in autores" :key="g" class="custom-control custom-radio">
                <input type="radio" class="custom-control-input" :id="g" name="autor">
                <label class="custom-control-label" :for="g">{{g}}</label>
              </div>
            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" class="filter" href="#collapse3">
              Editores <i class="fas fa-angle-down"></i></a>
            </h4>
          </div>
          <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
              <div v-for="g in editores" :key="g" class="custom-control custom-radio">
                <input type="radio" class="custom-control-input" :id="g" name="editor">
                <label class="custom-control-label" :for="g">{{g}}</label>
              </div>
            </div>
          </div>
        </div>
      </div>
    </b-sidebar>
  </div>
<div class="col-8 mx-auto my-5">
  <div v-if="user_type=='Funcionario'" class="row">
    <div class="col-10">
      <form @submit.prevent="searchBook">
        <div class="p-1 bg-light rounded rounded-pill shadow-sm mb-4">
          <div class="input-group">
            <input type="search" placeholder="Qual o livro que procura?" v-model="search" aria-describedby="button-addon1" class="form-control border-0 bg-light">
            <div class="input-group-append">
              <button id="button-addon1" type="submit" class="btn btn-link text-primary"><i class="fa fa-search"></i></button>
            </div>
          </div>
        </div>
      </form>
    </div>
    <div class="col-2">
      <div class="col-16 box">
        <button type="button" class="btn btn-secondary" @click="$router.push('/registar-livro')">
          <i class="fa fa-plus"></i>
          Adicionar
        </button>
      </div>
    </div>
  </div>
  <div v-else>
    <form @submit.prevent="searchBook">
        <div class="p-1 bg-light rounded rounded-pill shadow-sm mb-4">
          <div class="input-group">
            <input type="search" placeholder="Qual o livro que procura?" v-model="search" aria-describedby="button-addon1" class="form-control border-0 bg-light">
            <div class="input-group-append">
              <button id="button-addon1" type="submit" class="btn btn-link text-primary"><i class="fa fa-search"></i></button>
            </div>
          </div>
        </div>
      </form>
  </div>
  <div class="row justify-center">
    <div v-for="g in booksFilter[page]" :key="g.id" class="col-12 col-sm-6 col-lg-3 info">
      <a @click="setBook(g.id)">
        <img class="book" :src="g.imagem">
        <h3 class="name">{{g.titulo}}</h3>
        <p class="description">
          <strong>&nbsp;</strong>
          <i class="fas fa-pencil-alt"></i>
          <strong>&nbsp;{{g.autor}}</strong>
        </p>
      </a>
    </div>
  </div>
  <nav v-if="books.length > nrPerPage" class="pagination justify-content-center">
      <ul class="pagination">
        <li class="page-item">
          <a class="page-link" v-bind:disabled="page==0" v-on:click="page = 0" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
        <li v-for="p in range(0,booksFilter.length-1)" :key="p" v-on:click="page = p" class="page-item">
          <a class="page-link">{{p+1}}</a>
        </li>
        <li class="page-item">
          <a class="page-link" v-bind:disabled="page==Math.floor(booksFilter.length/nrPerPage)-1" v-on:click="page = booksFilter.length - 1" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</div>
</template>

<script>
import UserHandler from '@/utils/UserHandler.js'
import ApiUsers from '@/api/ApiUsers'
import ApiEmployee from '@/api/ApiEmployee'

export default {
  name: 'Books',
  data: () => ({
    user_type: null,
    books: {},
    page: 0,
    nrPerPage: 8,
    booksFilter: [],
    autores: {},
    editores: {},
    search: null
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (user !== false) {
      this.user_type = user.role
      console.log(this.user_type)
    }
    this.getBooks()
    this.getAutores()
    this.getEditores()
  },
  methods: {
    async getBooks () {
      console.log(this.user_type)
      if (this.user_type === 'Requisitante') {
        this.books = await ApiUsers.getBooks()
        console.log('req' + this.books)
      }
      if (this.user_type === 'Funcionario') {
        this.books = await ApiEmployee.getBooks()
        console.log('func' + this.books)
      }
      this.filterBooks()
    },
    filterBooks () {
      var i = 0
      var p = 0
      var page = []
      page[p] = []
      var nrPerPage = this.nrPerPage
      this.books.forEach(function (c) {
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
      this.booksFilter = page
    },
    async getAutores () {
      this.autores = await ApiUsers.getAutores()
      console.log(this.editores)
    },
    async getEditores () {
      this.editores = await ApiUsers.getEditores()
      console.log(this.editores)
    },
    range (start, end) {
      return Array(end - start + 1).fill().map((_, idx) => start + idx)
    },
    setBook (idBook) {
      localStorage.setItem('Book', idBook)
      if (this.user_type === 'Requisitante') {
        this.$router.push({ name: 'Book-user' })
      } else {
        this.$router.push({ name: 'Book-library' })
      }
    },
    async searchBook () {
      console.log(this.search)
      if (this.search != null) {
        this.books = await ApiUsers.searchBook(this.search)
        this.filterBooks()
      }
    }
  }
}
</script>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
input {
  border-radius: 50rem !important;
}
.text-primary {
  color: #7d8285 !important;
}
.btn.btn-secondary{
  float: left;
  background-color: rgb(140, 138, 138);
}
.box {
  margin-bottom: 20px;
}
.info {
  padding-bottom: 10px;
}
.book {
  height: 226px;
  width: 163px;
}
a {
  cursor: pointer;
}
::v-deep .b-sidebar-body {
  background-color: rgb(240, 240, 240);
}
::v-deep .b-sidebar-header {
  background-color: rgb(240, 240, 240);
}
::v-deep .b-sidebar {
  margin-top: 60px !important;
  width: 200px;
}
::v-deep .b-sidebar:not(.b-sidebar-right) {
  left: auto;
  right: 0;
}
.sidebar-1 {
  position: relative;
  z-index: 8;
}
.filter {
  color: #7d8285;
  font-weight: bold;
  font-size: 16px;
  margin-top: 20px;
}
.btn {
  margin-top: 5px;
}
</style>
