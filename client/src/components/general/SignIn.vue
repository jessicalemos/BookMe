<template>
    <div class="login-clean">
        <form method="post" style="padding-top: 0px;">
            <div class="illustration">
              <i class="fas fa-user-alt col-xl-5"></i>
            </div>
            <div class="form-group">
              <input class="form-control" type="email" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
              <input class="form-control" type="password" name="password" placeholder="Password" required>
            </div>
            <div class="form-group">
              <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
                Sign In
              </button>
            </div>
            <div class="modal-footer">Ainda não tens conta?<router-link to="/register">Cria aqui</router-link>
            </div>
        </form>
    </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.btn {
  margin-top: 1rem;
}
</style>
