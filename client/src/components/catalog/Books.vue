<template>
<div class="col-8 mx-auto my-5">
  <div class="row justify-center">
    <div v-if="user_type!='Requisitante'" class="col-12 box">
      <button type="button" class="btn btn-secondary" @click="$router.push('/registar-livro')">
        <i class="fa fa-plus"></i>
        Adicionar
      </button>
    </div>
    <div v-for="g in books" :key="g.id" class="col-12 col-sm-6 col-lg-3 info">
      <router-link :to="user_type==='Requisitante' ? { name: 'Book-user' } : { name: 'Book-library' }">
        <img class="book" src="@/assets/img/lus.jpeg">
        <h3 class="name">{{g.titulo}}</h3>
        <p class="description">
          <strong>&nbsp;</strong>
          <i class="fas fa-pencil-alt"></i>
          <strong>&nbsp;{{g.autor}}</strong>
        </p>
      </router-link>
    </div>
  </div>
</div>
</template>

<script>
import UserHandler from '@/utils/UserHandler.js'
import ApiUsers from '@/api/ApiUsers'

export default {
  name: 'Books',
  data: () => ({
    user_type: null,
    books: {}
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
</style>
