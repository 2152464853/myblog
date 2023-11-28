<template>
  <div id="app">
    <router-view></router-view>
  </div>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to='/login'>登录</router-link>
    <router-link to="/about">About</router-link>
  </nav> -->
  <!-- <router-view/> -->
</template>

<style lang="scss">

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #226db9;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
