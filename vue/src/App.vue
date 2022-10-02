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
    get() {
      $.ajax({
          url: "http://localhost:8080/api/raid/guild/1",
          type: "GET",
          dataType: "json",
          data: '',
          success: function (data) {
            // jsonobject to string
            var json = JSON.stringify(data);
            alert(json);
          },
          error: function (data) {
            // jsonobject to string
            var json = JSON.stringify(data);
            alert(json);
          }
          ,
        }
      )
      ;
    },
    mounted() {
      this.get();
    }
    ,
  },

}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
