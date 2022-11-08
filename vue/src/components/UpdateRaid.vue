<template>
  <div class="div-containerCard raid-add">
    <div class="div-containerItem raid-add-type raid-add-btn">
      <select v-model="raidUpdateData.raidType" @change="typeChange">
        <option v-for="id in Object.keys(raidList)" :value="id">{{ id }}</option>
      </select>
      <button class="btn-positive" @click="raidUpdateApi">수정</button>
    </div>
    <div class="div-containerItem raid-add-difficulty" v-if="raidUpdateData.raidType !== '도비스도디언'">
      <label class="label-raid-add" v-for="item in raidList[raidUpdateData.raidType].difficulty">
        <input id="" class="input-radio input-radio-difficulty" type="radio" name="difficulty"
               :value="item.id"
               :checked="raidUpdateData.raidDifficulty==item.id"
               v-model="raidUpdateData.raidDifficulty">
        <span>{{ item.name }}</span>
      </label>
    </div>
    <div class="div-containerItem raid-add-members">
      <input class="input-text input-text-member" type="text" placeholder="공격대원을 입력해 주세요."
             v-for="i in raidList[raidUpdateData.raidType].maxMember"
             :id="i-1"
             v-model="raidUpdateData.raidMembers[i-1]">
    </div>
  </div>
</template>

<script>
export default {
  name: "UpdateRaid",
  props: {
    raidList: Object,
    raidData: Object
  },
  data() {
    return {
      // 레이드 ID, 레이드 타입, 난이도, 공격대원
      raidUpdateData: this.copyObject(this.raidData),
    }
  },
  methods: {
    copyObject(json) {
      return JSON.parse(JSON.stringify(json));
    },
    raidUpdateApi() {
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
      if (invalidRaidData(this.raidUpdateData)) {
        $.ajax({
          url: "/api/raids/update",
          type: "POST",
          data: JSON.stringify(this.raidUpdateData),
          async: false,
          contentType: "application/json",
          success: function (data) {
            alert("수정되었습니다.");
          },
          error: function (data) {
            alert("에러가 발생했습니다.");
          }
        });
        this.$parent.$emit('closeModal');
        this.$parent.$parent.raidSelectApi(this);
        this.$parent.$parent.raidUpdateData = this.copyObject(this.raidUpdateData);
      }
    },    // raidType 변경시 특정 타입일 경우 raidAddSendData 데이터 초기화
    typeChange(event) {
      var maxMember = this.raidList[event.target.value].maxMember;
      if (maxMember == 4) {
        this.raidUpdateData.raidMembers.slice(0, 4);
      } else if (maxMember == 8) {
        this.raidUpdateData.raidMembers.slice(0, 8);
      }
        this.raidUpdateData.raidDifficulty = "none";
    }
    /*    charterApi() {
          console.log('Call API charterApi');
        },*/
  },
}
</script>

<style scoped>

</style>
