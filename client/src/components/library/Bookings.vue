<template>
  <div style="min-height: 100%">
    <div class="col-8 mx-auto my-5 py-5">
      <div class="row justify-center" id="Group">
        <div class="col-12">
          <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <div class="reservas text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Reservas</strong>
                </div>
              </a>
              <a class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <i class="fas fa-book"></i>&nbsp;
                    <strong>LP01 - A Mensagem</strong><br>
                    <i class="fa fa-envelope"></i>
                    &nbsp;joao.teixeira@hotmail.com<br>
                    <i class="fa fa-user"></i>&nbsp;
                    João Teixeira
                  </div>
                  <div class="d-inline">
                    <button class="btn btn-secondary" type="button">
                      <i class="far fa-check-circle"></i>
                    </button>
                  </div>
                </div>
              </a>
              <a class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <i class="fas fa-book"></i>&nbsp;
                    <strong>LP01 - A Mensagem</strong><br>
                    <i class="fa fa-envelope"></i>
                    &nbsp;ana.pereira9@gmail.com<br>
                    <i class="fa fa-user"></i>&nbsp;
                    Ana Santos<br>
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
      </div>
    </div>
  </div>
</template>

<style scoped>
.reservas {
  font-size: 18px;
  color: rgb(29,33,37);
}

.list-group-item {
  min-height: 70px;
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
