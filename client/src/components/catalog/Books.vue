<template>
<div class="col-8 mx-auto my-5">
  <div class="row justify-center">
    <div v-if="user_type!='Requisitante'" class="col-12 box">
      <button type="button" class="btn btn-secondary" @click="$router.push('/registar-livro')">
        <i class="fa fa-plus"></i>
        Adicionar
      </button>
    </div>
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
</template>

<script>
import UserHandler from '@/utils/UserHandler.js'
import ApiUsers from '@/api/ApiUsers'

export default {
  name: 'Books',
  data: () => ({
    user_type: null,
    books: {},
    page: 0,
    nrPerPage: 8,
    booksFilter: []
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (user !== false) {
      this.user_type = user.role
    }
    this.getBooks()
  },
  methods: {
    async getBooks () {
      this.books = await ApiUsers.getBooks()
      console.log(this.books)
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
    }
  }
}
</script>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
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
</style>
