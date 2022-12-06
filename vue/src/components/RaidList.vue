<template>
  <div id="RaidList" class="scroll">
    <Modal v-if="isModalViewed" @closeModal="isModalViewed = false">
      <UpdateRaid :raidData="raidUpdateData" :raidList="raidList" :is-modal-viewed="isModalViewed"/>
    </Modal>
    <div class="div-containerCard raid-add">
      <div class="div-containerItem raid-add-type raid-add-btn">
        <select @change="typeChange" v-model="raidInsertData.raidType">
          <option v-for="id in bossList" :value="id">{{ id }}</option>
        </select>
        <button class="btn-positive" @click="raidInsertApi">추가</button>
      </div>
      <div class="div-containerItem raid-add-difficulty"
           v-if="raidInsertData.raidType != '도비스도디언'">
        <label class="label-raid-add" v-for="(item, i) in raidList[raidInsertData.raidType].difficulty">
          <input class="input-radio input-radio-difficulty" type="radio" name="difficulty"
                 :value="item.id"
                 v-model="raidInsertData.raidDifficulty">
          <span>{{ item.name }}</span>
        </label>
      </div>
      <div class="div-containerItem raid-add-members">
        <input class="input-text input-text-member" type="text" placeholder="공격대원을 입력해 주세요."
               v-for="(item, item_i) in raidList[item_i].maxMember"
               :id="i-1"
               v-model="raidInsertData.raidMembers[item_i-1]">
      </div>
    </div>

    <div class="div-containerCard raid-list">
      <div class="div-containerItem raid-list-btn">
        <button id="UpdateBtn" class="btn-neutral btn-disable" @click="OpenUpdateModal">레이드 수정</button>
        <button id="DeleteBtn" class="btn-negative btn-disable" @click="raidDeleteApi">레이드 삭제</button>
      </div>
      <div class="div-containerItem raid-list-ul scroll">
        <ul v-for="(type, i) in raidResultData">
          <span>{{ Object.keys(type)[0] }}</span>
          <div v-for="items in type">
            <li v-for="item in items"
                :id="'id'+item.id"
                class="div-raid-item raid-list-li"
                :class="{selected:item.id == selectedId}"
                @click="raidUpdateSelected(item.id, Object.keys(type)[0], item.difficulty, item.members)">
              <input :id="'id'+item.id" class="check-box-raid-list" type="checkbox" @click="raidDeleteChecked(item.id)">
              <span v-for="member in item.members">{{ member }} </span>
            </li>
          </div>
        </ul>

      </div>
    </div>
  </div>
</template>

<script>
import Modal from "./Modal";
import UpdateRaid from "./UpdateRaid";
import Vue from "vue";

export default {
  name: 'RaidList',
  components: {
    Modal,
    UpdateRaid
  },
  mounted() {
    this.bossSelectApi();
  },
  data() {
    return {
      selectedId: undefined,
      isModalViewed: false,
      // 레이드 타입, 난이도, 공격대원
      raidInsertData: {
        raidType: "도비스도디언",
        raidDifficulty: "none",
        raidMembers: []
      },
      // 레이드 ID, 레이드 타입, 난이도, 공격대원
      raidUpdateData: {
        raidId: "",
        raidType: "",
        raidDifficulty: "",
        raidMembers: []
      },
      // 레이드 ID 삭제전용
      raidDeleteData: [],
      bossList: ["도비스도디언", "카양겔", "쿠크세이튼", "아브렐슈드", "일리아칸"],
      raidList: {
        "도비스도디언": {
          "difficulty": [{ "name": "", "id": "none" }],
          "maxMember": 4,
          "name": "도비스도디언"
        },
        "쿠크세이튼": {
          "difficulty": [{ "name": "노말", "id": "normal" }],
          "maxMember": 4,
          "name": "쿠크세이튼"
        },
        "카양겔": {
          "difficulty": [
            { "name": "노말", "id": "normal" },
            { "name": "하드1", "id": "hard1" },
            { "name": "하드2", "id": "hard2" },
            { "name": "하드3", "id": "hard3" }
          ],
          "maxMember": 4,
          "name": "카양겔"
        },
        "일리아칸": {
          "difficulty": [
            { "name": "노말", "id": "normal" },
            { "name": "하드", "id": "hard" }
          ],
          "maxMember": 8,
          "name": "일리아칸"
        },
        "아브렐슈드": {
          "difficulty": [
            { "name": "노말", "id": "normal" },
            { "name": "하드", "id": "hard" }
          ],
          "maxMember": 8,
          "name": "아브렐슈드"
        }
      },
      raidResultData: this.raidSelectApi()
    }
  },
  methods: {
    OpenUpdateModal() {
      if ($('#UpdateBtn').hasClass('btn-disable')) {
        return false;
      } else {
        this.isModalViewed = true;
      }
    },
    raidUpdateSelected(id, type, difficulty, members) {
      this.selectedId = id;
      this.raidUpdateData.raidId = id;
      this.raidUpdateData.raidType = type.substring(0, type.indexOf('-') === -1 ? type.length : type.indexOf('-'));
      this.raidUpdateData.raidDifficulty = difficulty;
      this.raidUpdateData.raidMembers = members;

      if (this.selectedId) {
        $('#UpdateBtn').removeClass('btn-disable');
      } else {
        $('#UpdateBtn').addClass('btn-disable');
      }
    },
    // 레이드 체크박스 선택 시 함수
    raidDeleteChecked(val) {
      const isChecked = $("#id" + val).find("input").is(":checked");
      if (isChecked) {
        // ID 추가
        this.raidDeleteData.push(val);
      } else {
        // ID 삭제
        this.raidDeleteData.splice(this.raidDeleteData.indexOf(val), 1);
      }

      if (this.raidDeleteData.length > 0) {
        $('#DeleteBtn').removeClass('btn-disable');
      } else {
        $('#DeleteBtn').addClass('btn-disable');
      }
    },
    // raidType 변경시 특정 타입일 경우 데이터 초기화
    typeChange(event) {
      const maxMember = this.raidList[event.target.value].maxMember;
      console.log(maxMember);
      if (maxMember === 4) {
        this.raidInsertData.raidMembers = this.raidInsertData.raidMembers.slice(0, 4);
      } else if (maxMember === 8) {
        this.raidInsertData.raidMembers = this.raidInsertData.raidMembers.slice(0, 8);
      }

      if (event.target.value == "도비스도디언") {
        this.raidInsertData.raidDifficulty = "none";
      }
    },
    bossSelectApi() {
      var result1 = {};
      var result2 = [];
      $.ajax({
        url: '/api/bosses/select',
        type: 'GET',
        async: false,
        dataType: 'json',
        success: function (data) {
          console.log('bosses1 : ' + data.data1.toString());
          console.log('bosses2 : ' + data.data2);
          result1 = data.data1;
          result2 = data.data2;
        },
      });
      this.raidList = result1;
      this.bossList = result2;
    },
    raidSelectApi() {
      var result = {};
      $.ajax({
        url: '/api/raids/select',
        type: 'GET',
        async: false,
        dataType: 'json',
        success: function (data) {
          console.log('raids' + data.data);
          result = data.data;
        },
      });
      this.raidResultData = result;
      return result;
    },
    // 추가 버튼 클릭시 API호출 함수
    raidInsertApi() {
      console.log(JSON.stringify(this.raidInsertData));

      // Invalid Check
      function invalidRaidData(data) {
        // 난이도가 선택되지 않을 경우(raidType 0인경우는 제외)
        if (data.raidDifficulty == "none" && data.raidType != "도비스도디언") {
          alert("난이도를 선택해 주세요.");
          return false;
        }
        if (data.raidMembers.length == 0) {
          alert("공격대원을 1명 이상 입력해 주세요.");
          return false;
        } else if (data.raidMembers.length > 0) {
          for (const member of data.raidMembers) {
            if (member != "") {
              return true;
            }
          }
          alert("공격대원을 1명 이상 입력해 주세요.");
          return false;
        }
      }

      // RaidInsert API 호출
      if (invalidRaidData(this.raidInsertData)) {
        $.ajax({
          url: "/api/raids/insert",
          type: "POST",
          data: JSON.stringify(this.raidInsertData),
          contentType: "application/json",
          async: false,
          success: function (data) {
            alert("등록되었습니다.");
          },
        });
        this.raidSelectApi();
        // 레이드 타입, 난이도, 공격대원
        this.raidInsertData = {
          raidType: "도비스도디언",
          raidDifficulty: "",
          raidMembers: []
        }
      }
    },
    raidDeleteApi() {
      if ($('#DeleteBtn').hasClass('btn-disable') == false && confirm("삭제하시겠습니까?")) {
        $.ajax({
          url: "/api/raids/delete",
          type: "POST",
          data: JSON.stringify({"deleteRaidId": this.raidDeleteData}),
          contentType: "application/json",
          async: false,
          success: function () {
            alert("삭제되었습니다.");
          },
        });
        this.raidSelectApi();
      }
    },
    /*    // Charter조회 API호출
        charterApi(event) {
          console.log(event.target)
          $.ajax({
              url: "/api/loa/charter",
              type: "POST",
              data: {member: this.raidInsertData.raidMembers[event.target.id - 1]},
              success: function (data) {
                console.log(data);
              }
            }
          )
        }*/
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
  cursor: default;
}

.div-containerItem {
  margin: 5px;
  vertical-align: middle;
  text-align: left;
}

.raid-list-btn {
  text-align: right;
  margin: 0 20px 20px 0;
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

.btn-disable {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>
