<template>
  <div id="RaidList">
    <div class="container-raid">
      <div class="container-card container-raid-add">
        <select @change="change">
          <option v-for="item in raidTypeList" :value="item.value">{{ item.name }}</option>
        </select>
        <button class="btn-raid-add" @click="addRaid">추가</button>
      </div>
      <div class="container-card container-raid-list">
        <span>
          test
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RaidList',
  data() {
    return {
      raidTypeList: [
        {'name': '선택', 'value': ''},
        {'name': '도비스도디언', 'value': '1'},
        {'name': '쿠크세이튼', 'value': '2'},
        {'name': '아브렐슈드', 'value': '3'},
        {'name': '일리아칸', 'value': '4'}
      ],
      ajaxData: {
        raidType: ''
      }
    }
  },
  methods: {
    change(event) {
      this.ajaxData.raidType = event.target.value;
    },
    addRaid() {
      $.ajax({
          url: 'http://localhost:8090/raid',
          type: 'POST',
          data: this.ajaxData,
          success: function (data) {
            console.log(data);
          },
        }
      )
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#RaidList {

}

.container-raid {
  display: grid;
  grid-template-columns: 3fr 7fr;
  /*grid-template-rows: 1fr 1fr 1fr;*/
  grid-template-rows: repeat(1, minmax(200px, auto));
  gap: 10px;
  overflow: hidden;
}

.container-raid-add {
  text-align: left;
}

.container-raid-add:nth-child(1) {
  grid-column: 1 / 2;
  grid-row: 1 / 2;
}

.container-card {
  margin: 20px;
  padding: 10px;
  width: auto;
  height: auto;
  background-color: #ffffff;
  border-radius: 5px;
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1);
  align-items: start;
}
</style>
