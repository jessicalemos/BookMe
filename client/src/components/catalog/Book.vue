<template>
    <div class="col-8 mx-auto my-5">
        <div class="row">
            <div class="col-md-6 align-self-center">
                <img src="@/assets/img/lus.jpeg" class="img-responsive"/>
            </div>
            <div class="col-md-6 text-left align-self-center">
              <h1 class="title">{{book.titulo}}</h1>
              <p class="author"><i class="fas fa-pencil-alt"></i>&nbsp; {{book.autor}}<p>
              <p><strong>ISBN </strong>{{book.isbn}}<br/>
              <strong>Edição ou reimpressão </strong>{{book.edicao}}<br/>
              <strong>Editor </strong>{{book.editor}}<br/>
              <strong>Categoria </strong>{{book.categoria}}<br/>
              <strong>Descrição </strong>{{book.descricao}}</p>
              <div v-if="user_type!=='Requisitante'">
                <button
                  class="btn btn-danger remove" @click="$bvModal.show('modal-scoped')" type="button">
                  <i class="far fa-trash-alt"></i>
                </button>
              </div>
              <div v-else>
                <select class="libraries custom-select" v-on:change="select($event);" value="branchid">
                  <option :selected="branchid === 1">Biblioteca Pública de Braga</option>
                  <option :selected="branchid === 2">Biblioteca Lúcio Craveiro da Silva</option>
                </select>
                <div v-if="branchid != 0 && success != 1">
                  <p class="unavailable">
                    <i class="far fa-times-circle"></i>
                    &nbsp;Indisponível
                  </p>
                </div>
                <div v-if="branchid != 0 && success != 0">
                  <p class="available">
                    <i class="far fa-times-circle"></i>
                    &nbsp;Indisponível
                  </p>
                </div>
              <button class="btn btn-secondary" type="button" style="background-color: rgb(140,138,138);">
                Reservar
              </button>
            </div>
            </div>
      </div>
      <div>
        <b-modal id="modal-scoped">
          <template v-slot:modal-header>
            <b>Remover Livro</b>
          </template>
          <template v-slot:default>Tem a certeza que pretende remover este livro?</template>
          <template v-slot:modal-footer>
            <b-button size="sm" variant="outline-danger" @click="$bvModal.hide('modal-scoped')">
              <i class="fas fa-times"></i> Não
            </b-button>
            <b-button size="sm" variant="outline-success" @click="remove()">
              <i class="fas fa-check"></i> Sim
            </b-button>
          </template>
        </b-modal>
      </div>
    </div>
</template>

<script>
import UserHandler from '@/utils/UserHandler.js'
import ApiUsers from '@/api/ApiUsers'

export default {
  name: 'Book',
  data () {
    return {
      branchid: 0,
      success: 0,
      user_type: null,
      book: {}
    }
  },
  mounted: function () {
    const user = UserHandler.get()
    this.user_type = user.role
    this.getBook()
  },
  methods: {
    select: function (evt) {
      this.branchid = evt.target.value
    },
    async getBook () {
      const idBook = localStorage.getItem('Book')
      this.book = await ApiUsers.getBook(idBook)
      console.log(this.book)
    },
    remove () {
      this.$router.push('/catalogo')
    }
  }
}
</script>

<style scoped>
.libraries {
  margin-bottom: 10px;
}
.available {
  color: rgb(7,143,4);
  font-weight: bold;
}
.unavailable {
  font-weight: bold;
  color: rgb(213,5,17);
}
.remove {
  background-color: rgb(213,5,17)
}
.container {
    position: relative;
    height: 100%
}
.row {
    min-height: 100%;
}
strong, .title {
  font-weight: bold;
  color: #313437;
}
.author {
  font-weight: bold;
  color: #7d8285;
}
.row img{
  width: 250px;
}
</style>
