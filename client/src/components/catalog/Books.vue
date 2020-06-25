<template>
<div class="col-8 mx-auto my-5 py-5">
  <div class="row justify-center">
    <div v-if="library()" class="col-12 box">
      <button type="button" class="btn btn-secondary" @click="$router.push('/registar-livro')">
        <i class="fa fa-plus"></i>
        Adicionar
      </button>
    </div>
    <div class="col-12 col-sm-6 col-lg-3 info">
      <router-link to="/livro">
        <img class="book" src="@/assets/img/lus.jpeg">
        <h3 class="name">Ensaio sobre a lucidez</h3>
        <p class="description">
          <strong>&nbsp;</strong>
          <i class="fas fa-pencil-alt"></i>
          <strong>&nbsp;José Saramago</strong>
        </p>
      </router-link>
    </div>
    <div class="col-12 col-sm-6 col-lg-3 info">
      <router-link to="/login">
        <img class="book" src="@/assets/img/lus.jpeg">
        <h3 class="name">Ensaio sobre a lucidez</h3>
        <p class="description">
          <strong>&nbsp;</strong>
          <i class="fas fa-pencil-alt"></i>
          <strong>&nbsp;José Saramago</strong>
        </p>
      </router-link>
    </div>
    <div class="col-12 col-sm-6 col-lg-3 info">
      <router-link to="/login">
        <img class="book" src="@/assets/img/lus.jpeg">
        <h3 class="name">Ensaio sobre a lucidez</h3>
        <p class="description">
          <strong>&nbsp;</strong>
          <i class="fas fa-pencil-alt"></i>
          <strong>&nbsp;José Saramago</strong>
        </p>
      </router-link>
    </div>
    <div class="col-12 col-sm-6 col-lg-3 info">
      <img class="book" src="@/assets/img/lus.jpeg">
    </div>
    <div class="col-12 col-sm-6 col-lg-3 info">
      <img class="book" src="@/assets/img/lus.jpeg">
    </div>
    <div class="col-12 col-sm-6 col-lg-3 info">
      <img class="book" src="@/assets/img/lus.jpeg">
    </div>
  </div>
</div>
</template>

<script>
export default {
  methods: {
    library: function () {
      return true
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
