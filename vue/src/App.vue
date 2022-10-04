<template>
  <div id="App">
    <Button @click="get">API 테스트</Button>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      datas: "",
    };
  },
  methods: {
    mounted() {
      this.get();
    }
    ,
  },

}

</script>

<style>
@font-face {
  font-family: 'Noto Sans KR';
  src: url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap') format('swap');
  font-weight: 400;
  font-style: normal;
}

html, body {
  font-family: 'Noto Sans KR', sans-serif;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

.nav-container {
  position: fixed;
  top: 0;
  width: 100%;
  height: 8%;
  left: 0;
  right: 0;
  padding: 0;
  margin: 0;
  background-color: aqua;
  display: flex;
  /* 생략 */
}

.nav-container div {
  padding: 10px;
  margin: 0;
  float: left;
  background-color: #333;
}

.nav-container span {
  padding: 10px;
  margin: 0 0 0 0;
  float: left;
  align-content: center;
}

.nav-container .logo {
  margin: 0 0 0 auto;
  width: 12%;
  align-content: center;
  font-size: 20px;
  font-weight: bold;
  color: #FFF;
}

.nav-container .nav-item{
  margin: 0 0 0 auto;
  width: 80%;
  align-content: center;
  font-size: 15px;
  font-weight: bold;
  color: #FFF;
}

.nav-container .github {
  margin: 0 0 0 auto;
  width: 8%;
  align-content: center;
  font-size: 15px;
  font-weight: bold;
  color: #FFF;
}
</style>
