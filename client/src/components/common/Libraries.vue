<template>
  <div style="min-height: 100%">
    <div class="col-8 mx-auto my-5 py-5">
      <div class="row justify-center" id="Group">
        <div class="col-12 box">
          <button v-if="library()" type="button" class="btn btn-secondary" @click="$router.push('/registar-biblioteca')">
            <i class="fa fa-plus"></i> Adicionar
          </button>
        </div>
        <div class="col-12">
          <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <strong>
                  Biblioteca Pública Municipal do Porto
                </strong>
                <div v-if="library()" class="d-inline">
                  <button class="btn btn-secondary config" type="button" @click="$router.push('/editar-responsavel')">
                    <i class="fas fa-cog"></i>
                  </button>
                  <button class="btn btn-danger remove" type="button">
                    <i class="far fa-trash-alt"></i>
                  </button>
                </div>
              </a>
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <strong>
                  Biblioteca Pública Municipal do Porto
                </strong>
                <div v-if="library()" class="d-inline">
                  <button class="btn btn-secondary config" type="button">
                    <i class="fas fa-cog"></i>
                  </button>
                  <button class="btn btn-danger remove" type="button">
                    <i class="far fa-trash-alt"></i>
                  </button>
                </div>
              </a><a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <strong>
                  Biblioteca Pública Municipal do Porto
                </strong>
                <div v-if="library()" class="d-inline">
                  <button class="btn btn-secondary config" type="button">
                    <i class="fas fa-cog"></i>
                  </button>
                  <button class="btn btn-danger remove" type="button">
                    <i class="far fa-trash-alt"></i>
                  </button>
                </div>
              </a>
          </div>
        </div>
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

<style scoped>
.list-group-item {
  height: 70px;
}

.btn.config {
  background-color: rgb(140, 138, 138);
  color: rgb(246,247,248);
}

.btn.btn-danger.remove {
  margin-left: 5px;
}

.list-group-item-action {
  color: #495057;
}

.btn.btn-secondary{
  float: left;
  background-color: rgb(140, 138, 138);
}

.box {
  margin-bottom: 10px;
}
</style>
