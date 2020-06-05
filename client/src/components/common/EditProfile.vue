<template>
    <div class="login-clean">
        <form method="post" style="max-width: 600px;">
            <div class="form-group">
              <input class="form-control" type="text" name="nome" placeholder="Nome">
            </div>
            <div class="form-group">
              <input class="form-control" type="email" name="email" placeholder="Email">
            </div>
            <div class="form-group">
              <input class="form-control" type="text" name="telemovel" placeholder="Telemóvel">
            </div>
            <div class="form-group">
                <input class="form-control" type="password" name="password" placeholder="Password">
            </div>
            <div class="row">
              <div class="col-sm-6">
                <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
                  Guardar
                </button>
              </div>
              <div class="col-sm-6">
                <button class="btn btn-secondary btn-block" style="background-color: rgb(140,138,138); margin-left:5px" @click="$router.push('/bibliotecas')">
                  Cancelar
              </button>
            </div>
          </div>
        </form>
    </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.btn {
  margin-top: 2rem;
}
</style>
