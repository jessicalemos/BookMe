<template>
  <div>
    <div class="container-fluid slides">
      <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img class="image" src="@/assets/img/slide.png"
            alt="First slide">
        </div>
        <div class="carousel-item">
          <img class="image" src="@/assets/img/slide.png"
            alt="Second slide">
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
    </div>
    <div class="container">
      <div class="row">
          <div class="col-sm-6 col-md-4 item">
            <i class="fas fa-search-location fa-5x"></i>
            <h3 class="name">Encontra a tua biblioteca</h3>
            <p class="description">São dezenas de bibliotecas localizadas em todo o país</p>
          </div>
          <div class="col-sm-6 col-md-4 item">
            <i class="fas fa-book-open fa-5x"></i>
            <h3 class="name">Encontra o teu livro</h3>
            <p class="description">Temos centenas de livros disponíveis nas bibliotecas associadas</p>
          </div>
          <div class="col-sm-6 col-md-4 item">
            <i class="far fa-clipboard fa-5x"></i>
            <h3 class="name">Faz a tua reserva</h3>
            <p class="description">Podes reservar, requisitar e renovar inúmeros livros nas mais diversas bibliotecas associadas</p>
            </div>
      </div>
    </div>
  </div>
</template>

<style scoped src="@/assets/css/style.css"></style>
<style scoped>
.container {
  padding: 20px;
  margin-top: 40px;
  margin-bottom: 40px;
}

.container-fluid.slides {
  background-color: rgb(38,40,41);
  height: 280px;
  margin-top: -40px;
}

.carouselExampleControls {
  background-color: rgb(38,40,41);
  height: 280px;
  margin-top: -40px;
}
</style>
