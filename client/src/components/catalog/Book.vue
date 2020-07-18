<template>
    <div class="col-8 mx-auto my-5 py-5">
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
              <div v-if="user_type==='Requisitante'">
                <select class="libraries custom-select" v-on:change="select($event);" value="branchid">
                  <option>Selecione a sua biblioteca</option>
                  <option v-for="option in libraries" :key="option" :selected="library === option">
                    {{option}}
                  </option>
                </select>
                <div v-if="estado == 2 || estado == 3">
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
              <button class="btn btn-secondary" type="button" style="background-color: rgb(140,138,138);" v-b-tooltip.hover.bottom :title="description" @click="reservar()">
                Reservar
              </button>
            </div>
            </div>
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
      feedback: null,
      library: null,
      estado: -1,
      msg: null,
      process: null,
      user_type: null,
      user_id: null,
      book: {},
      libraries: [],
      description: null
    }
  },
  mounted: function () {
    const user = UserHandler.get()
    this.user_type = user.role
    if (user === false) {
      this.$router.push('/')
    } else {
      if (this.user_type !== 'Funcionario' &&
          this.user_type !== 'Responsavel' &&
          this.user_type !== 'Requisitante') {
        this.$router.push('/access-denied')
      }
    }
    this.user_id = user.id
    this.getBook()
  },
  methods: {
    select: function (evt) {
      const selected = evt.target.value
      if (selected !== 'Selecione a sua biblioteca') {
        this.library = evt.target.value
        const info = {
          biblioteca: this.library,
          livro: this.book.isbn
        }
        this.availability(info)
      } else {
        this.estado = -1
      }
    },
    async availability (info) {
      const req = await ApiUsers.bookAvailability(info)
      if (req === '') {
        this.estado = 3 // não pode reservar
        this.msg = 'Indisponível'
      } else if (req.estado === 'disponivel') {
        this.estado = 1 // pode reservar
        this.msg = 'Disponível'
        this.description = 'Levantamento até 4 dias'
        this.process = req
      } else if (req.estado === 'requisitado') {
        this.estado = 2 // pode reservar
        this.msg = 'Temporariamente Indisponível. Previsão de levantamento: ' + req.dataFim
        this.description = 'Levantamento até 4 dias após receber notificação'
        this.process = req
      }
    },
    async getBook () {
      const idBook = localStorage.getItem('Book')
      this.book = await ApiUsers.getBook(idBook)
      this.libraries = await ApiUsers.librariesBook(this.book.isbn)
    },
    remove () {
      this.$router.push('/catalogo')
    },
    async reservar () {
      const req = await ApiUsers.requestBook(this.user_id, this.process)
      console.log(req)
      if (this.estado === 1) {
        this.feedback = 'Livro reservado com sucesso, tem 4 dias para efetuar o levantamento'
        this.makeToast('success', 'Sucesso', this.feedback)
      } else if (this.estado === 2) {
        this.feedback = 'Livro reservado com sucesso, irá receber uma notificação para ' +
        'efetuar o levantamento dentro de 4 dias'
        this.makeToast('success', 'Sucesso', this.feedback)
      } else {
        this.feedback = 'Não foi possível efetuar a reserva'
        this.makeToast('danger', 'Error', this.feedback)
      }
      this.estado = -1
      this.getBook()
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

<style>
.b-toaster.b-toaster-top-right .b-toaster-slot {
    top: 82px !important;
    right: 12px;
}
</style>

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
