<template>
  <div style="min-height: 100%">
    <div class="col-8 mx-auto my-5 py-5">
      <div class="row justify-center" id="Group">
        <div class="col-12 box">
          <button type="button" class="btn btn-secondary" @click="$router.push('/admin/register-library')">
            <i class="fa fa-plus"></i> Adicionar
          </button>
        </div>
        <div class="col-12">
          <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <strong>
                  Biblioteca Pública Municipal do Porto
                </strong>
                <div class="d-inline">
                  <button class="btn btn-secondary config" type="button" @click="$router.push('/admin/edit-responsible')">
                    <i class="fas fa-cog"></i>
                  </button>
                  <button class="btn remove" type="button">
                    <i class="far fa-trash-alt"></i>
                  </button>
                </div>
              </a>
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <strong>
                  Biblioteca Pública Municipal do Porto
                </strong>
                <div class="d-inline">
                  <button class="btn btn-secondary config" type="button">
                    <i class="fas fa-cog"></i>
                  </button>
                  <button class="btn remove" type="button">
                    <i class="far fa-trash-alt"></i>
                  </button>
                </div>
              </a><a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <strong>
                  Biblioteca Pública Municipal do Porto
                </strong>
                <div class="d-inline">
                  <button class="btn btn-secondary config" type="button">
                    <i class="fas fa-cog"></i>
                  </button>
                  <button class="btn remove" type="button">
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

<style scoped>
.btn.config {
  background-color: rgb(140, 138, 138);
  color: rgb(246,247,248);
}

.btn.remove {
  background-color: rgb(213,5,17);
  color: rgb(246,247,248);
  margin-left: 5px;
}

.form-group {
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
