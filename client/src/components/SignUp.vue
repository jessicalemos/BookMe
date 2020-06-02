<template>
    <div class="login-clean">
        <form method="post" style="max-width: 600px;">
            <h2 class="sr-only">Login Form</h2>
            <div class="form-group">
              <input class="form-control" type="email" name="name" placeholder="Nome">
            </div>
            <div class="form-group">
              <input class="form-control" type="email" name="email" placeholder="Email">
            </div>
            <div class="form-group">
                <input class="form-control" type="email" name="phone" placeholder="Telemóvel">
            </div>
            <div class="form-group">
              <input class="form-control" type="password" name="password" placeholder="Password">
              </div>
            <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
                Sign Up
            </button>
        </form>
    </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style>
.btn {
  margin-top: 2rem;
}
</style>
