<template>
  <div style="min-height: 100%">
    <div class="col-10 mx-auto">
      <div class="row justify-center" id="Group">
        <div class="col-lg-4">
          <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <div class="history text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Devolvidos</strong>
                </div>
              </a>
              <a v-if="returned.length!=0" class="list-group-item list-group-item-action">
                <div class="text-left">
                  <div class="date">
                    {{moment(g.dataInicio).format('DD/MM/YYYY')}}
                    <i class="fas fa-arrows-alt-h"></i>
                    {{moment(g.dataFim).format('DD/MM/YYYY')}}
                  </div>
                  <strong>{{g.livro.titulo}}</strong><br>
                  Biblioteca Pública de Braga<br>
                </div>
              </a>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="list-group">
              <a class="list-group-item history d-flex align-items-center justify-content-between list-group-item-action">
                <div class="history text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Requisitados</strong>
                </div>
              </a>
              <a v-if="reserved.length!=0" class="list-group-item list-group-item-action">
                <div v-for="g in reserved" :key="g.id" class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <div class="date">
                      {{moment(g.dataInicio).format('DD/MM/YYYY')}}
                      <i class="fas fa-arrows-alt-h"></i>
                      {{moment(g.dataFim).format('DD/MM/YYYY')}}
                    </div>
                    <strong>{{g.livro.titulo}}</strong><br>
                    Biblioteca Pública de Braga<br>
                  </div>
                  <div class="d-inline">
                    <button class="btn btn-secondary" type="button">
                      <i class="far fa-check-circle"></i>
                    </button>
                  </div>
                </div>
              </a>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="list-group">
              <a class="list-group-item history d-flex align-items-center justify-content-between list-group-item-action">
                <div class="history text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Reservados</strong>
                </div>
              </a>
              <a v-if="requested.length!=0" class="list-group-item list-group-item-action">
                <div v-for="g in requested" :key="g.id" class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <div class="date">
                      {{moment(g.dataInicio).format('DD/MM/YYYY')}}
                      <i class="fas fa-arrows-alt-h"></i>
                      {{moment(g.dataFim).format('DD/MM/YYYY')}}
                    </div>
                    <strong>{{g.livro.titulo}}</strong><br>
                    Biblioteca Pública de Braga<br>
                  </div>
                  <div class="d-inline">
                    <button class="btn btn-danger" @click="$bvModal.show('modal-scoped')" type="button">
                      <i class="far fa-trash-alt"></i>
                    </button>
                  </div>
                </div>
              </a>
          </div>
        </div>
      </div>
    </div>
    <div>
      <b-modal id="modal-scoped">
        <template v-slot:modal-header>
          <b>Remover Reserva</b>
        </template>
        <template v-slot:default>Tem a certeza que pretende remover esta reserva?</template>
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
import ApiUsers from '@/api/ApiUsers'
import UserHandler from '@/utils/UserHandler.js'
import moment from 'moment'

export default {
  name: 'History',
  data: () => ({
    reserved: {},
    returned: {},
    requested: {}
  }),
  mounted: function () {
    this.getUserInfo()
  },
  methods: {
    async getUserInfo () {
      const user = UserHandler.get()
      this.reserved = await ApiUsers.getReserved(user.id)
      this.returned = await ApiUsers.getReturned(user.id)
      this.requested = await ApiUsers.getRequested(user.id)
    },
    moment
  }
}
</script>

<style scoped>
.date {
  font-size: 14px;
}

.history {
  font-size: 18px;
  color: rgb(29,33,37);
}

.list-group-item {
  min-height: 70px;
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
