<template>
  <div class="login-clean">
        <form method="post" style="max-width: 850px">
            <div class="form-row">
                <div class="col-md-6">
                  <span class="data">
                    <strong>Dados Biblioteca</strong>
                  </span>
                    <div class="form-group first">
                      <input class="form-control" type="email" placeholder="Nome" name="name">
                    </div>
                    <div class="form-group">
                      <input class="form-control" type="email" name="email" placeholder="Email">
                    </div>
                    <div class="form-group">
                      <input class="form-control" type="email" name="morada" placeholder="Morada">
                    </div>
                    <div class="form-group">
                      <input class="form-control" type="email" name="phone" placeholder="Telemóvel">
                    </div>
                </div>
                <div class="col-md-6" style="padding-left: 25px;">
                  <span class="data">
                    <strong>Dados Responsável</strong>
                  </span>
                  <div class="form-group first">
                    <input class="form-control" type="email" placeholder="Nome" name="name">
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="email" name="email" placeholder="Email">
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="email" name="Telemóvel" placeholder="Telemóvel">
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="password" name="password" placeholder="Password">
                  </div>
                </div>
            </div>
              <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
                Registar biblioteca
              </button>
        </form>
    </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.data {
  float: left;
}

.btn {
  margin-top: 1rem;
}

.form-group.first {
  margin-top: 50px;
}
</style>
