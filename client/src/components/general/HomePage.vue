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
          <img class="image" src="@/assets/img/slide7.png"
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
  <section class="testimonials text-center bg-light">
    <div class="container">
      <h2 class="title">Book Me?</h2>
      <div id="bookme" class="row">
        <div class="col-lg-4">
          <div class="testimonial-item mx-auto mb-5 mb-lg-0">
            <img class="img-fluid rounded-circle mb-3" src="@/assets/img/one.png" alt="">
            <h5 class="name">Escolhe o teu livro e biblioteca</h5>
            <p class="description">Se o teu livro estiver disponível efetua o levantamento até quatro dias após a tua reserva ou então fica atento às tuas notificações para saberes quando já está disponível</p>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="testimonial-item mx-auto mb-5 mb-lg-0">
            <img class="img-fluid rounded-circle mb-3" src="@/assets/img/two.png" alt="">
            <h5 class="name">Desfruta do teu livro</h5>
            <p class="description">A requisição tem uma duração máxima de 15 dias</p>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="testimonial-item mx-auto mb-5 mb-lg-0">
            <img class="img-fluid rounded-circle mb-3" src="@/assets/img/three.png" alt="">
            <h5 class="name">Efetua a devolução ou renovação</h5>
            <p class="description">Se ainda não terminaste a tua leitura não há problema! Podes renovar caso o mesmo já não tenha sido reservado, caso contrário não te esqueças de o devolver no prazo indicado</p>
          </div>
        </div>
      </div>
    </div>
  </section>
  </div>
</template>
<style scoped src="@/assets/css/style.css"></style>
<style scoped>
#bookme {
  margin-top: 60px;
  margin-bottom: 20px;
}
.img-fluid {
  height: 40px;
}
.title {
  font-weight: bold !important;
}
.container {
  padding: 20px;
  margin-top: 40px;
  margin-bottom: 40px;
}

.container-fluid.slides {
  background-color: rgb(38,40,41);
  height: 468px;
  margin-top: -40px;
}

.carouselExampleControls {
  background-color: rgb(38,40,41);
  height: 280px;
  margin-top: -40px;
}
</style>
