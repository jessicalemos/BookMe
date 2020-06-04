<template>
    <div class="container">
        <div class="row">
            <div class="col-md-6 align-self-center">
                <img src="@/assets/img/lus.jpeg" class="img-responsive"/>
            </div>
            <div class="col-md-6 text-left align-self-center">
              <h1 class="title">Ensaio sobre a lucidez</h1>
              <p class="author"><i class="fas fa-pencil-alt"></i>&nbsp; José Saramago<p>
              <p><strong>ISBN </strong>978-972-0-04652-9<br/>
              <strong>Edição ou reimpressão </strong>2019<br/>
              <strong>Editor </strong>Porto Editora<br/>
              <strong>Categoria </strong>Romance</p>
              <div v-if="library()">
                <button
                  class="btn btn-secondary remove" type="button">
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
    </div>
</template>

<script>
export default {
  data () {
    return {
      branchid: 0,
      success: 0
    }
  },
  methods: {
    select: function (evt) {
      this.branchid = evt.target.value
    },
    library: function () {
      return false
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
