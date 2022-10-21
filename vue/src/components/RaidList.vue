<template>
  <div id="RaidList" class="scroll">
    <div class="div-containerCard raid-add">
      <div class="div-containerItem raid-add-type raid-add-btn">
        <select @change="typeChange" v-model="raidAddSendData.raidType">
          <option v-for="id in Object.keys(raidList)"
                  :value="id">{{ id }}
          </option>
        </select>
        <button class="btn-positive" @click="raidInsertApi">추가</button>
      </div>
      <div class="div-containerItem raid-add-difficulty" v-if="raidAddSendData.raidType !== ''">
        <label class="label-raid-add" v-for="item in raidList[raidAddSendData.raidType].difficulty">
          <input class="input-radio input-radio-difficulty" type="radio" name="difficulty"
                 :value="item.id"
                 v-model="raidAddSendData.raidDifficulty">
          <span>{{ item.name }}</span>
        </label>
      </div>
      <div class="div-containerItem raid-add-members">
        <input class="input-text input-text-member" type="text" placeholder="공격대원을 입력해 주세요."
               v-on:change="charterApi"
               v-for="i in raidList[raidAddSendData.raidType].maxMember"
               :id="i-1"
               v-model="raidAddSendData.raidMembers[i-1]">
      </div>
    </div>

    <div class="div-containerCard raid-list">
      <div class="div-containerItem raid-list-btn">
        <button class="btn-negative" @click="raidDeleteApi">레이드 삭제</button>
      </div>
      <div class="div-containerItem raid-list-ul scroll">
        <ul v-for="type in Object.keys(raidListResultData)">
          {{ type }}
            <li :id="'id'+item.id" class="div-raid-item raid-list-li" :class="{selected:item.id === selectedId}"
                @click="raidListSelected(item.id)" v-for="(item, i) in raidListResultData[type]">
              <input :id="'id'+item.id" class="check-box-raid-list" type="checkbox" @click="raidListChecked">
              <span v-for="(member, i) in item.members">{{ member }} </span>
            </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'RaidList',
  components: {},
  data() {
    return {
      selectedId: undefined,
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
      raidAddSendData: {
        raidType: "도비스도디언",
        raidDifficulty: "",
        raidMembers: []
      },
      raidListSendData: [],
      raidListResultData: {
        "도비스도디언": [
          {
            "id": "1",
            "members": ["가나다라마바사아자차카타", "가나다라마바사아자차카타", "가나다라마바사아자차카타", "가나다라마바사아자차카타", "가나다라마바사아자차카타", "가나다라마바사아자차카타", "가나다라마바사아자차카타", "가나다라마바사아자차카타"]
          },
          {"id": "2", "members": "1,2,32"},
          {"id": "3", "members": "13"}
        ],
        "쿠쿠세이튼-노말": [
          {"id": "4", "members": "1,2,3,4,5,6"},
          {"id": "5", "members": "1,2,32"},
          {"id": "6", "members": "13"}
        ],
        "아브렐슈드-노말": [
          {"id": "7", "members": "1,2,3,4,5,6"},
          {"id": "8", "members": "1,2,32"},
          {"id": "9", "members": "13"}
        ],
        "아브렐슈드-하드": [
          {"id": "10", "members": "1,2,3,4,5,6"},
          {"id": "11", "members": "1,2,32"},
          {"id": "12", "members": "13"}
        ],
        "일리아칸-노말": [
          {"id": "13", "members": "1,2,3,4,5,6"},
          {"id": "14", "members": "1,2,32"},
          {"id": "15", "members": "13"}
        ],
        "일리아칸-하드": [
          {"id": "16", "members": "1,2,3,4,5,6"},
          {"id": "17", "members": "1,2,32"},
          {"id": "18", "members": "13"}
        ]
      }
    };
  },
  methods: {
    raidListSelected(id) {
      if(this.selectedId === id) {
        this.selectedId = undefined;
      } else {
        this.selectedId = id;
      }
    },
    // 레이드 삭제를 위한 데이터를 담는 함수
    raidListChecked() {
      const isChecked = $("#id" + this.selectedId).find("input").is(":checked");

      if (isChecked) {
        // ID 추가
        this.raidListSendData.push(this.selectedId);
      } else {
        // ID 삭제
        this.raidListSendData.splice(this.raidListSendData.indexOf(this.selectedId), 1);
      }
    },
    // raidType 변경시 특정 타입일 경우 raidAddSendData 데이터 초기화
    typeChange(event) {
      if (event.target.value === "도비스도디언") {
        this.raidAddSendData.raidDifficulty = "";
        this.raidAddSendData.raidMembers = this.raidAddSendData.raidMembers.slice(0, 4);
      }
    },
    // 추가 버튼 클릭시 API호출 함수
    raidInsertApi(event) {
      // Invalid Check
      function invalidRaidData(data) {
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

      // RaidInsert API 호출
      if (invalidRaidData(this.raidAddSendData)) {
        alert("등록되었습니다.");
        $.ajax({
            url: "http://localhost:8081/api/raid/insert",
            type: "POST",
            data: this.raidAddSendData,
            success: function (data) {
              console.log(data);
            },
          }
        );
      }
    },
    raidDeleteApi() {
      // RaidDelete API 호출
      $.ajax({
        url: "http://localhost:8081/api/raid/delete",
        type: "POST",
        data: this.raidListSendData,
        success: function (data) {
          console.log(data);
        },
      });
    },
    // Charter조회 API호출
    charterApi(event) {
      console.log(event.target)
      $.ajax({
          url: "http://localhost:8081/api/loa/charter",
          type: "POST",
          data: {member: this.raidAddSendData.raidMembers[event.target.id - 1]},
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
#RaidList {
  width: 100%;
  height: 90vh;
  overflow-y: scroll;

}

.scroll::-webkit-scrollbar {
  width: 8px; /* 스크롤바의 너비 */
  height: 8px;
  background-color: transparent;
}

.scroll::-webkit-scrollbar-thumb {
  width: 8px;
  height: 4px; /* 스크롤바의 길이 */
  background: #b0b4bb; /* 스크롤바의 색상 */

  border-radius: 4px;
}

.scroll::-webkit-scrollbar-track {
  background: #f3f4f5; /*스크롤바 뒷 배경 색상*/
}

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
  min-width: 320px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  /*justify-content: flex-start;*/
  justify-content: space-between;
}

.raid-list {
  width: 78vmax;
  padding: 20px 0 0 20px !important;
}

.raid-list-ul {
  margin: 20px 0 0 20px !important;
  padding: 0 0 20px 0 !important;
  height: 440px;
  overflow-y: scroll;
}

.div-containerItem {
  margin: 5px;
  vertical-align: middle;
  text-align: left;
}

.raid-add-members {
  margin: 0.6em 0 0 0;
}

.raid-add-btn > button {
  text-align: right !important;
  right: 0 !important;
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

label.label-raid-add {
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

.input-text-member {
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
  margin: 0 20px 20px 0;
  padding: 0;
  background-color: white;
  text-align: left;
  position: relative;
  list-style-position: inside;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
  font-size: 18px;
  font-weight: bold;
}

ul li, ol li {
  padding: 5px 0 5px 5px;
  border-bottom: 1px solid #efefef;
  font-size: 14px;
  font-weight: normal;
}

ul li:before,
ol li:before {
  content: ">";
  display: inline-block;
  vertical-align: middle;
  padding: 0 5px 6px 0;
}

ul li.selected {
  background-color: #BDBDBD;
}
</style>
