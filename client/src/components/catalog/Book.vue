<template>
    <div class="col-8 mx-auto my-5">
        <div v-if="error === 1">
          <b-alert class="alert" variant="danger" show dismissible>
            <b>Error</b><br/>
          </b-alert>
        </div>
        <div v-if="error === -1">
          <b-alert class="alert" variant="success" show dismissible>
            <b>Sucesso</b><br/>
            <span class="alert-text">Livro reservado com sucesso, tem 4 dias para efetuar o levantamento.</span>
          </b-alert>
        </div>
        <div class="row">
            <div class="col-md-6 align-self-center">
                <img :src="book.imagem" class="img-responsive"/>
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
                  <option selected="">Selecione a sua biblioteca</option>
                  <option v-for="option in libraries" :key="option" :selected="library === option">
                    {{option}}
                  </option>
                </select>
                <div v-if="estado == 2">
                  <p class="unavailable">
                    <i class="far fa-times-circle"></i>
                    &nbsp;{{msg}}
                  </p>
                </div>
                <div v-if="estado == 1">
                  <p class="available">
                    <i class="far fa-check-circle"></i>
                    &nbsp;{{msg}}
                  </p>
                </div>
              <button class="btn btn-secondary" type="button" style="background-color: rgb(140,138,138);" @click="reservar()">
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
      error: 0,
      library: null,
      estado: -1,
      msg: null,
      process: null,
      user_type: null,
      user_id: null,
      book: {},
      libraries: []
    }
  },
  mounted: function () {
    const user = UserHandler.get()
    this.user_type = user.role
    this.user_id = user.id
    this.getBook()
  },
  methods: {
    select: function (evt) {
      const selected = evt.target.value
      if (selected !== '') {
        this.library = evt.target.value
        const info = {
          biblioteca: this.library,
          livro: this.book.isbn
        }
        console.log(info)
        this.availability(info)
      } else {
        this.estado = -1
      }
    },
    async availability (info) {
      console.log(info)
      const req = await ApiUsers.bookAvailability(info)
      if (req.estado === 'disponivel') {
        this.estado = 1 // pode reservar
        this.msg = 'Disponível'
        this.process = req
      } else if (req.estado === 'requisitado') {
        this.estado = 2 // pode reservar
        this.msg = 'Temporariamente Indisponível. Previsão de levantamento: ' + req.dataFim
        this.process = req
      } else if (req.estado === 'reservado') {
        this.estado = 2 // não pode reservar
        this.msg = 'Indisponível'
      }
      console.log(req)
      console.log(this.msg)
    },
    async getBook () {
      const idBook = localStorage.getItem('Book')
      this.book = await ApiUsers.getBook(idBook)
      console.log(this.book)
      this.libraries = await ApiUsers.librariesBook(this.book.isbn)
      console.log(this.libraries)
    },
    remove () {
      this.$router.push('/catalogo')
    },
    async reservar () {
      const req = await ApiUsers.requestBook(this.user_id, this.process)
      console.log(req)
      this.estado = -1
      this.error = -1
      this.getBook()
    }
  }
}
</script>

<style scoped src="@/assets/css/style.css"></style>
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
