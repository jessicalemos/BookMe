<template>
    <div class="login-clean">
        <form method="post" style="max-width: 600px;">
            <div class="form-group">
              <input class="form-control" type="text" name="name" placeholder="Nome" required>
            </div>
            <div class="form-group">
              <input class="form-control" type="email" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="phone" placeholder="Telemóvel" required>
            </div>
            <div class="form-group">
              <input class="form-control" type="password" name="password" placeholder="Password" required>
              </div>
            <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
              Registar Funcionário
            </button>
        </form>
    </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.btn {
  margin-top: 2rem;
}
</style>
