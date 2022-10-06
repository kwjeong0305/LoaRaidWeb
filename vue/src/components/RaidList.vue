<template>
  <div id="RaidList">
    <div class="container-raid">
      <div class="container-card container-raid-add">
        <div class="content container-type">
          <select @change="changeSelect" v-model="raidData.raidType">
            <option v-for="item in raidList"
                    :key="item.name"
                    :value="item.id">{{ item.name }}
            </option>
          </select>
          <button class="btn btn-raid-add" @click="insertRaidApi">추가</button>
        </div>


        <div class="content container-difficulty">
          <label v-for="item in raidList[raidData.raidType].difficulty">
            <input class="input-radio input-radio-difficulty" type="radio" name="difficulty"
                   :value="item.id"
                   v-if="item.id !== 'none'"
                   v-model="raidData.raidDifficulty">
            <span>{{ item.name }}</span>
          </label>
        </div>

        <div class="content container-members">
          <input class="input-text input-text-member" type="text" placeholder="공격대원을 입력해 주세요."
                 v-on:change="loaApi"
                 v-for="i in raidList[raidData.raidType].maxMember"
                 :id="i-1"
                 v-model="raidData.raidMembers[i-1]">
        </div>
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
        {'id': '0', 'name': '도비스도디언', 'maxMember': 4, 'difficulty': [{'id': 'none'}]},
        {'id': '1', 'name': '쿠크세이튼', 'maxMember': 4, 'difficulty': [{'id': 'normal', 'name': '노말'}]},
        {
          'id': '2',
          'name': '아브렐슈드',
          'maxMember': 8,
          'difficulty': [{'id': 'normal', 'name': '노말'}, {'id': 'hard', 'name': '하드'}]
        },
        {
          'id': '3',
          'name': '일리아칸',
          'maxMember': 8,
          'difficulty': [{'id': 'normal', 'name': '노말'}, {'id': 'hard', 'name': '하드'}]
        }
      ],
      raidData: {
        raidType: 0,
        raidDifficulty: 'none',
        raidMembers: []
      },
      selectedRaid: true,
    }
  },
  methods: {
    changeSelect(event) {
      if (event.target.value === '0') {
        this.raidData.raidDifficulty = 'none';
        this.raidData.raidMembers = this.raidData.raidMembers.slice(0, 4);
      }
    },
    insertRaidApi() {
      function checkRaidData(data) {
        // 난이도가 선택되지 않을 경우(raidType 0인경우는 제외)
        if (data.raidDifficulty === 'none' && data.raidType !== 0) {
          alert('난이도를 선택해 주세요.');
          return false;
        }
        if (data.raidMembers.length === 0) {
          alert('공격대원을 1명 이상 입력해 주세요.');
          return false;
        } else if (data.raidMembers.length > 0) {
          for (const member of data.raidMembers) {
            if (member !== '') {
              return true;
            }
          }
          alert('공격대원을 1명 이상 입력해 주세요.');
          return false;
        }
      }

      // DB에 등록을 하기 위한 API
      if (checkRaidData(this.raidData)) {
        alert('등록되었습니다.');
        $.ajax({
            url: 'http://localhost:8081/raid',
            type: 'POST',
            data: this.raidData,
            success: function (data) {
              console.log(data);
            },
          }
        )
      }
    },

    loaApi(event) {     // 캐릭터 조회를 위한 API
      console.log(event.target.value)
      $.ajax({
          url: 'http://localhost:8081/api/loa/charter',
          type: 'POST',
          data: {member: this.raidData.raidMembers[event.target.id - 1]},
          success: function (data) {
            console.log(data);
          }
        }
      )
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container-raid {
  display: grid;
  grid-template-columns: 22fr 78fr;
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

.container-raid {
}

.container-card {
  margin: 20px;
  padding: 20px;
  width: auto;
  height: auto;
  background-color: #ffffff;
  border-radius: 5px;
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1);
  float: left;
}

.container-raid-add {
  height: auto;
  min-height: 440px;
}

.container-raid-list {
  height: auto;

}

.container-members {
  margin: 0.6em 0 0 0;
}

.content {
  vertical-align: middle;
  text-align: left;
}

select {
  /* 생략 */
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;

  color: #444;
  background-color: #fff;

  padding: 0.6em 1.4em 0.5em 0.8em;
  margin: 0;
  border: 1px solid #aaa;
  border-radius: 0.5em;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);

}

select:hover {
  background-color: #efefef;
}

select:focus {
  background-color: #ffffff;
}

.btn-raid-add {
  background-color: #1e5bf4;
  border: none;
  color: white;
  padding: 10px 32px;
  text-align: center;
  font-size: 16px;
  opacity: 0.6;
  transition: 0.3s;
  display: inline-block;
  text-decoration: none;
  cursor: pointer;
  border-radius: 0.7em;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);
  float: right;
  vertical-align: middle;
}

.btn-raid-add:hover {
  opacity: 1;
}

label {
  font-size: 18px;
  line-height: 2rem;
  padding: 0.2em 0.4em;
  display: inline-block;
}

span {
  vertical-align: middle;
}

[type="radio"] {
  vertical-align: middle;
  appearance: none;
  border: max(2px, 0.1em) solid gray;
  border-radius: 50%;
  width: 1.25em;
  height: 1.25em;
  transition: border 0.1s ease-in-out;
}

[type="radio"]:checked {
  border: 0.4em solid tomato;
}

[type="radio"]:focus-visible {
  outline-offset: max(2px, 0.1em);
  outline: max(2px, 0.1em) dotted tomato;
}

[type="radio"]:hover {
  box-shadow: 0 0 0 max(4px, 0.2em) lightgray;
  cursor: pointer;
}

[type="radio"]:disabled {
  background-color: lightgray;
  box-shadow: none;
  opacity: 1;
  cursor: not-allowed;
}

[type="radio"]:disabled + span {
  opacity: 1;
  cursor: not-allowed;
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

input[type="text"] {
  margin: 0 0 8px 0;
  width: 100%;
  height: 32px;
  font-size: 15px;
  border: 0.15em solid #000000;
  border-radius: 0.5em;
  outline: none;
  padding-left: 10px;
  background-color: #fefefe;
}
</style>
