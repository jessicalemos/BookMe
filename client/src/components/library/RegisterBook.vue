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

export default {
  name: 'RegisterBook',
  data: () => ({
    book: {
      disponibilidade: true
    }
  }),
  methods: {
    async register () {
      const req = await ApiEmployee.registerBooks(this.book)
      console.log(req)
      this.error = -1
    }
  }
}
</script>
