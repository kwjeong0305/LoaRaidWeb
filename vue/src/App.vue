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

#app {
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
  width: 100vw;
  height: 100vh;
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

body::-webkit-scrollbar {
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

.btn-positive {
  background-color: #1985f2;
  border: none;
  color: white;
  padding: 10px 32px;
  font-size: 16px;
  transition: 0.3s;
  text-decoration: none;
  cursor: pointer;
  border-radius: 0.7em;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);
}

.btn-positive:hover {
  background-color: #0e6cd5;
}

.btn-neutral {
  background-color: #ffffff;
  color: black;
  padding: 10px 32px;
  font-size: 16px;
  transition: 0.3s;
  text-decoration: none;
  cursor: pointer;
  border: #666666 solid 1px;
  border-radius: 0.7em;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);
}

.btn-neutral:hover {
  background-color: #e6e6e6;
}

.btn-negative {
  background-color: #E60A2F;
  border: none;
  color: white;
  padding: 10px 32px;
  font-size: 16px;
  transition: 0.3s;
  text-decoration: none;
  cursor: pointer;
  border-radius: 0.7em;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);
}

.btn-negative:hover {
  background-color: #993528;
}
</style>
