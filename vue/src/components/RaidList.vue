<template>
  <div id="RaidList">
    <div class="container-raid">
      <div class="container-card container-raid-add">
        <select @change="raidTypeChange">
          <option v-for="item in raidList" :value="item.order">{{ item.name }}</option>
        </select>

        <!--        <button class="btn btn-raid-add" @click="addRaid">추가</button>-->

        <div class="container-difficulty" v-if="selectedRaid">
          <label v-for="(item, i) in raidData.raidDifficulty">
            <input class="input-radio input-radio-difficulty" type="radio" name="difficulty"
                   :value="item">{{ item }}
          </label>
        </div>

        <input class="input-text input-text-member" type="text" v-if="selectedRaid" v-for="i in raidData.maxMember"
               :id="'member'+i"
               v-on:change="loaApi"
               v-model="raidData.raidMember[i-1]">
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
      raidList: [
        {'name': '선택', 'order': '0'},
        {'name': '도비스도디언', 'order': '1', 'maxMember': 4},
        {'name': '쿠크세이튼', 'order': '2', 'maxMember': 4, 'difficulty': ['노말']},
        {'name': '아브렐슈드', 'order': '3', 'maxMember': 8, 'difficulty': ['노말', '하드']},
        {'name': '일리아칸', 'order': '4', 'maxMember': 8, 'difficulty': ['노말', '하드']}
      ],
      raidData: {
        raidType: '',
        raidDifficulty: [],
        maxMember: 0,
        raidMember: []
      },
      selectedRaid: false,
    }
  },
  methods: {
    raidTypeChange(event) {
      const val = event.target.value;
      this.selectedRaid = false;
      if (val !== '') {
        this.raidData.raidType = this.raidList[val].name;
        this.raidData.maxMember = this.raidList[val].maxMember;
        this.raidData.raidDifficulty = this.raidList[val].difficulty;
        this.selectedRaid = true;
        console.log(this.raidData.raidDifficulty);
      } else {
        this.selectedRaid = false;
      }

    },
    addRaid() {
      $.ajax({
          url: 'http://localhost:8081/raid',
          type: 'POST',
          data: this.ajaxData,
          success: function (data) {
            console.log(data);
          },
        }
      )
    },
    loaApi() {
      $.ajax({
          url: 'http://localhost:8081/api/loa/charter',
          type: 'POST',
          data: {member: this.raidMember[0]},
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
