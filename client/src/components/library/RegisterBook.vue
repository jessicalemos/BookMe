<template>
    <div class="login-clean">
        <form @submit.prevent="register" style="max-width: 600px;">
            <div class="form-group">
              <input class="form-control" v-model="book.titulo" type="text" placeholder="Título" required>
            </div>
            <div class="form-group">
              <input class="form-control" v-model="book.autor" type="text" placeholder="Autor" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="book.edicao" type="text" placeholder="Edição" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="book.editor" type="text" placeholder="Editor" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="book.categoria" type="text" placeholder="Categoria" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="book.ano" type="text" placeholder="Ano" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="book.isbn" type="text" placeholder="ISBN" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="book.descricao" type="text" placeholder="Descrição" required>
            </div>
            <div class="form-group">
                <input class="form-control" v-model="book.imagem" type="text" placeholder="Imagem" required>
            </div>
            <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
              Registar livro
            </button>
            <div v-if="error === 1">
              <b-alert class="alert" variant="danger" show dismissible>
                <b>Error</b><br/>
                <span class="alert-text">{{msg}}</span>
              </b-alert>
            </div>
            <div v-if="error === -1">
              <b-alert class="alert" variant="success" show dismissible>
                <b>Sucesso</b><br/>
                <span class="alert-text">Livro registado com sucesso</span>
              </b-alert>
            </div>
        </form>
    </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.btn {
  margin-top: 2rem;
}
</style>

<script>
import ApiEmployee from '@/api/ApiEmployee'
import UserHandler from '@/utils/UserHandler.js'

export default {
  name: 'RegisterBook',
  data: () => ({
    book: {
      disponibilidade: true
    },
    error: 0
  }),
  mounted: function () {
    this.user = UserHandler.get()
    if (this.user === false) {
      this.$router.push('/')
    } else if (this.user.role !== 'Funcionario' && this.user.role !== 'Responsavel') {
      this.$router.push('/access-denied')
    }
  },
  methods: {
    async register () {
      const req = await ApiEmployee.registerBooks(this.book)
      console.log(req)
      this.error = -1
    }
  }
}
</script>
