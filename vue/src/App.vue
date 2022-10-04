<template>
  <div id="app">
    <header class="navbar">
      <div class="nav-container">
        <div class="label-home-01 header-item">
          <span>Raid Management</span>
        </div>
      </div>
    </header>
    <router-view/>
    <!--    <header class="navbar">
          <div class="nav-container">
            <div class="label-home-01 header-item">
              <span>Raid Management</span>
            </div>
            <span>Home</span>
          </div>
        </header>-->
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {
    // eslint-disable-next-line no-console
    log() {
      console.log('clicked');
    }
  }
}

</script>

<style>

@font-face {
  font-family: 'Noto Sans KR';
  src: url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap') format('swap');
  font-weight: 400;
  font-style: normal;
}

#app{
  font-family: 'Noto Sans KR', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

html, body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  background-color: #fafafa;
  overflow: hidden;
}

.navbar {
  position: fixed;
  top: 0;
  width: 100%;
  height: 60px;
  background-color: #b5d692;

}

::-webkit-scrollbar {
  display: none;
}

.nav-container {
  height: inherit;
  display: flex;
  align-items: center;
}

.header-item {
  /*margin side 10px*/
  margin: 0 10px;
}

.label-home-01 span {
  display: block;
  color: #000000;
  padding: 8px;
  text-decoration: none;
  text-align: center;
  font-weight: bold;
  font-size: 20px;
}

</style>
