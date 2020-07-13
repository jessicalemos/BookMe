<template>
    <div class="login-clean">
        <form method="post" style="max-width: 600px;">
            <div class="form-group">
              <input class="form-control" type="text" name="titulo" placeholder="Título" required>
            </div>
            <div class="form-group">
              <input class="form-control" type="text" name="autor" placeholder="Autor" required>
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="edicao" placeholder="Edição" required>
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="editor" placeholder="Editor" required>
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="categoria" placeholder="Categoria" required>
            </div>
            <div class="form-group">
              <input id="file" class="rounded d-lg-flex form-control" type="file" placeholder="Categoria" required>
            </div>
            <button class="btn btn-secondary btn-block" type="submit" style="background-color: rgb(140,138,138);">
              Registar livro
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
