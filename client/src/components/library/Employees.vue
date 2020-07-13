<template>
  <div style="min-height: 100%">
    <div class="col-8 mx-auto my-5 py-5">
      <div class="row justify-center" id="Group">
        <div class="col-12 box">
          <button type="button" class="btn btn-secondary" @click="$router.push('/registar-funcionario')">
            <i class="fa fa-plus"></i> Adicionar
          </button>
        </div>
        <div class="col-12">
          <div class="list-group">
              <a class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <i class="fa fa-envelope"></i>
                    &nbsp;joao.teixeira@hotmail.com<br>
                    <i class="fa fa-user"></i>&nbsp;
                    <strong>João Carlos Teixeira</strong>
                  </div>
                  <div class="d-inline">
                    <button class="btn btn-danger remove" type="button">
                      <i class="far fa-trash-alt"></i>
                    </button>
                  </div>
                </div>
              </a>
              <a class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <i class="fa fa-envelope"></i>
                    &nbsp;ana.pereira9@gmail.com<br>
                    <i class="fa fa-user"></i>&nbsp;
                    <strong>Ana Pereira Santos</strong>
                  </div>
                  <div class="d-inline">
                    <button class="btn btn-danger remove" type="button">
                      <i class="far fa-trash-alt"></i>
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
