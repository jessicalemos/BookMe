<template>
<div class="col-10 mx-auto my-5 py-5">
  <div class="row">
    <div class="col-lg-6">
      <div class="row">
        <div class="col-lg-12">
            <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <div class="type text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Bibliotecas</strong>
                </div>
                <div class="d-inline">
                  <button class="btn btn-outline-secondary" @click="$router.push('/bibliotecas')">
                    Ver mais <i class="fa fa-plus-circle"></i>
                  </button>
                </div>
              </a>
              <a class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <strong>Biblioteca Pública de Braga</strong>
                  </div>
                </div>
              </a>
          </div>
        </div>
        <div class="col-lg-12">
            <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <div class="type text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Livros</strong>
                </div>
                <div class="d-inline">
                  <button class="btn btn-outline-secondary" @click="$router.push('/catalogo')">
                    Ver mais <i class="fa fa-plus-circle"></i>
                  </button>
                </div>
              </a>
              <a class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <strong>Memorial do Convento</strong><br>
                    <div class="author">José Saramago</div>
                  </div>
                </div>
              </a>
          </div>
        </div>
      </div>
    </div>
    <div class="col-lg-6">
      <div class="list-group">
              <a class="list-group-item d-flex align-items-center justify-content-between list-group-item-action">
                <div class="type text-left">
                  <i class="fas fa-list-ul"></i><strong>&nbsp; Recente</strong>
                </div>
                <div class="d-inline">
                  <button class="btn btn-outline-secondary" @click="$router.push('/historico')">
                    Ver mais <i class="fa fa-plus-circle"></i>
                  </button>
                </div>
              </a>
              <a class="list-group-item list-group-item-action">
                <div class="d-flex align-items-center justify-content-between">
                  <div class="text-left">
                    <div class="date">20-04-2020 11:30h</div>
                    <strong>Memorial do Convento</strong><br>
                    Biblioteca Pública de Braga<br>
                  </div>
                </div>
              </a>
          </div>
    </div>
</div>
</div>
</template>

<style scoped>
.col-lg-6 {
  min-height: 200px;
}

.col-lg-12 {
  min-height: 200px;
  margin-bottom: 20px;
}

.list-group-item-action {
  color: #495057;
}

.date, .author {
  font-size: 14px;
}

.type {
  font-size: 18px;
  color: rgb(29,33,37);
}

.btn.btn-outline-secondary:hover {
  background-color: rgb(140, 138, 138);
  color: rgb(246,247,248);
}

button.btn-outline-secondary {
  height: 35px;
}
</style>
