<template>
  <div id="RaidList">
    <div class="div-containerCard raid-add">
      <div class="div-containerItem raid-add-btn">
        <button class="btn-positive" @click="insertRaidApi">추가</button>
      </div>
      <div class="div-containerItem raid-add-type">
        <select @change="changeSelect" v-model="raidSendData.raidType">
          <option v-for="id in Object.keys(raidList)"
                  :value="id">{{ id }}
          </option>
        </select>
      </div>
      <div class="div-containerItem raid-add-difficulty" v-if="raidSendData.raidType !== ''">
        <label v-for="item in raidList[raidSendData.raidType].difficulty">
          <input class="input-radio input-radio-difficulty" type="radio" name="difficulty"
                 :value="item.id"
                 v-model="raidSendData.raidDifficulty">
          <span>{{ item.name }}</span>
        </label>
      </div>
      <div class="div-containerItem raid-add-members">
        <input class="input-text input-text-member" type="text" placeholder="공격대원을 입력해 주세요."
               v-on:change="loaApi"
               v-for="i in raidList[raidSendData.raidType].maxMember"
               :id="i-1"
               v-model="raidSendData.raidMembers[i-1]">
      </div>
    </div>

    <div class="div-containerCard raid-list">
      <div class="div-containerItem raid-list-btn">
        <button class="btn-negative" @click="isModalViewed = true">레이드 삭제</button>
      </div>
      <div class="div-containerItem raid-list-ul scroll">
        <ul v-for="type in Object.keys(raidResultData)">
          {{ type }}
          <li class="div-raid-item raid-list-item" v-for="(item, i) in raidResultData[type]">
            <input type="hidden" :value="item.id" :id="item.id">
            파티{{ i + 1 }} {{ item.members }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'RaidList',
  components: {
  },
  data() {
    return {
      isModalViewed: false,
      raidList: {
        "도비스도디언": {
          "name": "도비스도디언",
          "maxMember": 4,
        },
        "쿠크세이튼": {
          "name": "쿠크세이튼",
          "maxMember": 4,
          "difficulty": [{"id": "normal", "name": "노말"}]
        },
        "아브렐슈드": {
          "name": "아브렐슈드",
          "maxMember": 8,
          "difficulty": [
            {"id": "normal", "name": "노말"},
            {"id": "hard", "name": "하드"}
          ]
        },
        "일리아칸": {
          "name": "일리아칸",
          "maxMember": 8,
          "difficulty": [
            {"id": "normal", "name": "노말"},
            {"id": "hard", "name": "하드"}
          ]
        }
      },
      raidSendData: {
        raidType: "도비스도디언",
        raidDifficulty: "",
        raidMembers: []
      },
      raidResultData: {
        "도비스도디언": [
          {"id": "30010203", "members": "1,2,3,4,5,6"},
          {"id": "30010201", "members": "1,2,32"},
          {"id": "30010204", "members": "13"}
        ],
        "쿠쿠세이튼-노말": [
          {"id": "30010203", "members": "1,2,3,4,5,6"},
          {"id": "30010201", "members": "1,2,32"},
          {"id": "30010204", "members": "13"}
        ],
        "아브렐슈드-노말": [
          {"id": "30010203", "members": "1,2,3,4,5,6"},
          {"id": "30010201", "members": "1,2,32"},
          {"id": "30010204", "members": "13"}
        ],
        "아브렐슈드-하드": [
          {"id": "30010203", "members": "1,2,3,4,5,6"},
          {"id": "30010201", "members": "1,2,32"},
          {"id": "30010204", "members": "13"}
        ],
        "일리아칸-노말": [
          {"id": "30010203", "members": "1,2,3,4,5,6"},
          {"id": "30010201", "members": "1,2,32"},
          {"id": "30010204", "members": "13"}
        ],
        "일리아칸-하드": [
          {"id": "30010203", "members": "1,2,3,4,5,6"},
          {"id": "30010201", "members": "1,2,32"},
          {"id": "30010204", "members": "13"}
        ]
      }
    };
  },
  methods: {
    changeSelect(event) {
      if (event.target.value === "도비스도디언") {
        this.raidSendData.raidDifficulty = "";
        this.raidSendData.raidMembers = this.raidSendData.raidMembers.slice(0, 4);
      }
    },
    insertRaidApi(event) {
      function checkRaidData(data) {
        // 난이도가 선택되지 않을 경우(raidType 0인경우는 제외)
        if (data.raidDifficulty === "" && data.raidType !== "도비스도디언") {
          alert("난이도를 선택해 주세요.");
          return false;
        }
        if (data.raidMembers.length === 0) {
          alert("공격대원을 1명 이상 입력해 주세요.");
          return false;
        } else if (data.raidMembers.length > 0) {
          for (const member of data.raidMembers) {
            if (member !== "") {
              return true;
            }
          }
          alert("공격대원을 1명 이상 입력해 주세요.");
          return false;
        }
      }

      // DB에 등록을 하기 위한 API
      if (checkRaidData(this.raidSendData)) {
        alert("등록되었습니다.");
        $.ajax({
            url: "http://localhost:8081/raid",
            type: "POST",
            data: this.raidSendData,
            success: function (data) {
              console.log(data);
              event.$emit("close-modal");
            },
          }
        );
      }
    },
    // 캐릭터 조회를 위한 API
    loaApi(event) {
      console.log(event.target.value)
      $.ajax({
          url: "http://localhost:8081/api/loa/charter",
          type: "POST",
          data: {member: this.raidSendData.raidMembers[event.target.id - 1]},
          success: function (data) {
            console.log(data);
          }
        }
      )
    }
  }
}
</script>
<style>
.div-containerCard {
  margin: 20px;
  padding: 20px;
  width: auto;
  background-color: #ffffff;
  border-radius: 5px;
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1);
  float: left;
}

.raid-add {
  width: 16%;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  /*justify-content: flex-start;*/
  justify-content: space-between;
}

.raid-list {
  padding: 20px 0 20px 20px !important;
}

.raid-list-ul {
  height: 480px;
  overflow-y: scroll;
}

.raid-add, .raid-list {

}

.div-containerItem {
  margin: 5px;
  vertical-align: middle;
  text-align: left;
}

.raid-add-members {
  margin: 0.6em 0 0 0;
}

.raid-add-btn {
  text-align: right;
  left: 0;
  bottom: 0;
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

label {
  font-size: 18px;
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

.raid-list-ul > ul {
  padding: 10px 10px;
  background-color: white;
  border-radius: 1.0em;
  text-align: left;
  position: relative;
  list-style-position: inside;
}
</style>
