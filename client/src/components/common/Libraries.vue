<template>
  <div style="min-height: 100%">
    <div class="col-8 mx-auto my-5 py-5">
      <div class="row justify-center" id="Group">
        <div class="col-12 box">
          <button v-if="user_type == 'Administrador'" type="button" class="btn btn-secondary" @click="$router.push('/registar-biblioteca')">
            <i class="fa fa-plus"></i> Adicionar
          </button>
        </div>
        <div class="col-12">
          <div class="list-group">
              <a v-for="g in libraries" :key="g.id" class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <strong>
                  {{g.nome}}
                </strong>
                <div v-if="user_type == 'Administrador'" class="d-inline">
                  <button class="btn btn-secondary config" type="button" @click="$router.push('/editar-responsavel')">
                    <i class="fas fa-cog"></i>
                  </button>
                  <button class="btn btn-danger remove" @click="$bvModal.show('modal-scoped')" type="button">
                    <i class="far fa-trash-alt"></i>
                  </button>
                </div>
              </a>
          </div>
        </div>
      </div>
    </div>
    <div>
      <b-modal id="modal-scoped">
        <template v-slot:modal-header>
          <b>Remover Biblioteca</b>
        </template>
        <template v-slot:default>Tem a certeza que pretende remover esta biblioteca?</template>
        <template v-slot:modal-footer="{ remove }">
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
import ApiAdmin from '@/api/ApiAdmin'

export default {
  name: 'Libraries',
  data: () => ({
    user_type: null,
    libraries: {}
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (user !== false) {
      this.user_type = user.role
    }
    this.getLibraries()
  },
  methods: {
    async getLibraries () {
      this.libraries = await ApiAdmin.libraries()
      console.log(this.libraries)
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
