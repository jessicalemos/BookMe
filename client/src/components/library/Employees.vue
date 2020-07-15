<template>
  <div style="min-height: 100%">
    <div class="col-8 mx-auto my-5">
      <div class="row justify-center" id="Group">
        <div class="col-12 box">
          <button type="button" class="btn btn-secondary" @click="$router.push('/registar-funcionario')">
            <i class="fa fa-plus"></i> Adicionar
          </button>
        </div>
        <div class="col-12">
          <div class="list-group">
              <a v-for="g in employess" :key="g[0]" class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <i class="fa fa-envelope"></i>
                    &nbsp;{{g[1]}}<br>
                    <i class="fa fa-user"></i>&nbsp;
                    <strong>{{g[2]}}</strong>
                  </div>
                  <div class="d-inline">
                    <button class="btn btn-danger remove" @click="$bvModal.show('modal-scoped'); itemToRemove(g[0])" type="button">
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
      <b-modal id="modal-scoped" centered>
        <template v-slot:modal-header>
          <b>Remover Funcionário</b>
        </template>
        <template v-slot:default>Tem a certeza que pretende remover esta biblioteca?</template>
        <template v-slot:modal-footer="{}">
          <b-button size="sm" variant="outline-danger" @click="$bvModal.hide('modal-scoped')">
            <i class="fas fa-times"></i> Não
          </b-button>
          <b-button size="sm" variant="outline-success" @click="removeEmployee()">
            <i class="fas fa-check"></i> Sim
          </b-button>
        </template>
      </b-modal>
    </div>
  </div>
</template>

<style scoped>
.btn.config {
  background-color: rgb(140, 138, 138);
  color: rgb(246,247,248);
}

.list-group-item {
  height: 70px;
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

<script>
import ApiLibraries from '@/api/ApiLibraries'
import UserHandler from '@/utils/UserHandler.js'

export default {
  name: 'Employees',
  data: () => ({
    user_id: null,
    error: 0,
    employess: {},
    selected: null
  }),
  mounted: function () {
    const user = UserHandler.get()
    if (user !== false) {
      this.user_id = user.id
    }
    this.getEmployees()
  },
  methods: {
    async getEmployees () {
      this.employess = await ApiLibraries.getEmployees(this.user_id)
      console.log(this.employess)
      this.error = -1
    },
    itemToRemove (employee) {
      this.selected = employee
      console.log(this.selected)
    },
    async removeEmployee () {
      const req = await ApiLibraries.removeEmployee(this.selected)
      console.log(req)
      this.getEmployees()
      this.$bvModal.hide('modal-scoped')
    }
  }
}
</script>
