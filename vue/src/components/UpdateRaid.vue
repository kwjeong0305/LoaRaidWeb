<template>
  <div class="div-containerCard raid-add">
    <div class="div-containerItem raid-add-type raid-add-btn">
      <select v-model="raidUpdateData.raidType">
        <option v-for="id in Object.keys(raidList)" :value="id">{{ id }}</option>
      </select>
      <button class="btn-positive" @click="raidUpdateApi">수정</button>
    </div>
    <div class="div-containerItem raid-add-difficulty" v-if="raidUpdateData.raidType !== ''">
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
      raidUpdateData: this.copyObject(this.raidData)
    }
  },
  methods: {
    copyObject(json) {
      return JSON.parse(JSON.stringify(json));
    },
    raidUpdateApi() {
      $.ajax({
        url: "http://localhost:8080/api/raid/update",
        type: "POST",
        data: JSON.stringify(this.raidUpdateData),
        contentType: "application/json",
        success: function (data) {
          console.log(data);
        },
        error: function (data) {
          console.log(data);
        }
      });
    },    // raidType 변경시 특정 타입일 경우 raidAddSendData 데이터 초기화
    typeChange(event) {
      if (event.target.value == "도비스도디언") {
        this.raidUpdateData.raidDifficulty = "";
        this.raidUpdateData.raidMembers = this.raidUpdateData.raidMembers.slice(0, 4);
      }
    }
    /*    charterApi() {
          console.log('Call API charterApi');
        },*/
  },
}
</script>

<style scoped>

</style>
