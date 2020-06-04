<template>
  <div id="app">
    <Header/>
    <router-view/>
    <Footer/>
  </div>
</template>

<script>
import Header from '@/components/layout/Header'
import Footer from '@/components/layout/Footer'

export default {
  name: 'App',
  components: { Header, Footer }
}
</script>

<style>
/*@import url('https://fonts.googleapis.com/css?family=Proza+Libre');*/

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  height: 100%;
  position: relative;
}
html, body {
  background-color: rgb(248,248,248) !important;
  height: 100%;
  position: relative;
}
</style>
