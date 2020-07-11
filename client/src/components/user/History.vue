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
                <div v-for="g in returned" :key="g.id" class="text-left">
                  <div class="date">
                    {{moment(g.processo.dataInicio).format('DD/MM/YYYY')}}
                    <i class="fas fa-arrows-alt-h"></i>
                    {{moment(g.processo.dataFim).format('DD/MM/YYYY')}}
                  </div>
                  <strong>{{g.processo.livro.titulo}}</strong><br>
                  {{g.biblioteca.nome}}<br>
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
              <a v-if="requested.length!=0" class="list-group-item list-group-item-action">
                <div v-for="g in requested" :key="g.id" class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <div class="date">
                      {{moment(g.processo.dataInicio).format('DD/MM/YYYY')}}
                      <i class="fas fa-arrows-alt-h"></i>
                      {{moment(g.processo.dataFim).format('DD/MM/YYYY')}}
                    </div>
                    <strong>{{g.processo.livro.titulo}}</strong><br>
                    {{g.biblioteca.nome}}<br>
                  </div>
                  <div class="d-inline">
                    <button class="btn btn-secondary" @click="$bvModal.show('modal-renew'); selectProcess(g)" type="button">
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
              <a v-if="reserved.length!=0" class="list-group-item list-group-item-action">
                <div v-for="g in reserved" :key="g.id" class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <div class="date">
                      {{moment(g.processo.dataInicio).format('DD/MM/YYYY')}}
                      <i class="fas fa-arrows-alt-h"></i>
                      {{moment(g.processo.dataFim).format('DD/MM/YYYY')}}
                    </div>
                    <strong>{{g.processo.livro.titulo}}</strong><br>
                    {{g.biblioteca.nome}}<br>
                  </div>
                  <div class="d-inline">
                    <button class="btn btn-danger" @click="$bvModal.show('modal-remove'); selectProcess(g)" type="button">
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
      <b-modal id="modal-remove" centered>
        <template v-slot:modal-header>
          <b>Remover Reserva</b>
        </template>
        <template v-slot:default>Tem a certeza que pretende remover a reserva do livro <b>{{select.processo.livro.titulo}}</b>?</template>
        <template v-slot:modal-footer>
          <b-button size="sm" variant="outline-danger" @click="$bvModal.hide('modal-remove')">
            <i class="fas fa-times"></i> Não
          </b-button>
          <b-button size="sm" variant="outline-success" @click="remove()">
            <i class="fas fa-check"></i> Sim
          </b-button>
        </template>
      </b-modal>
    </div>
    <div>
      <b-modal id="modal-renew" centered>
        <template v-slot:modal-header>
          <b>Renovar Requisição</b>
        </template>
        <template v-slot:default>Tem a certeza que pretende renovar a requisição do livro <b>{{select.processo.livro.titulo}}</b>?</template>
        <template v-slot:modal-footer>
          <b-button size="sm" variant="outline-danger" @click="$bvModal.hide('modal-renew')">
            <i class="fas fa-times"></i> Não
          </b-button>
          <b-button size="sm" variant="outline-success" @click="renew()">
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
    user: null,
    reserved: {},
    returned: {},
    requested: {},
    select: null
  }),
  mounted: function () {
    this.getUserInfo()
  },
  methods: {
    async getUserInfo () {
      this.user = UserHandler.get()
      this.reserved = await ApiUsers.getReserved(this.user.id)
      console.log(this.reserved)
      this.returned = await ApiUsers.getReturned(this.user.id)
      console.log(this.returned)
      this.requested = await ApiUsers.getRequested(this.user.id)
      console.log(this.requested)
    },
    selectProcess (reservation) {
      this.select = reservation
      console.log(this.select)
    },
    async remove () {
      const info = {}
      info.idRequisitante = this.user.id
      info.idProcesso = this.select.processo.id
      info.biblioteca = this.select.biblioteca.nome
      console.log(info)
      const req = await ApiUsers.removeReservation(info)
      console.log(req)
      this.reserved = await ApiUsers.getReserved(this.user.id)
      console.log(this.reserved)
      this.$bvModal.hide('modal-remove')
    },
    async renew () {
      const info = {}
      info.idRequisitante = this.user.id
      info.idProcesso = this.select.processo.id
      const req = await ApiUsers.renewRequest(info)
      console.log(req)
      this.requested = await ApiUsers.getRequested(this.user.id)
      console.log(this.requested)
      this.$bvModal.hide('modal-renew')
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
