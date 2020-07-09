<template>
    <footer class="phone-viewport">
      <p class="copyright">Made with <i class="fa fa-heart pulse"></i> Book Me © 2020</p>
    </footer>
</template>

<style scoped>
  .phone-viewport {
      bottom: 0;
      width: 100%;
      height: 1.5cm;
      line-height: 50px;
      overflow: hidden;
      background: rgb(240,240,240);
      box-shadow: rgba(0, 0, 0, 0.15) 0px 1px 10px 0px;
      position: fixed;
      text-align: center;
      right: 0;
      left: 0;
      background-repeat: no-repeat;
      z-index: 1111;
  }
  .copyright {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    color: rgba(0,0,0,.5);
    font-weight: bold;
  }
</style>
