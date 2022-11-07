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
                :checked="raidData.raidDifficulty==item.id"
               v-model="raidUpdateData.raidDifficulty">
        <span>{{ item.name }}</span>
      </label>
    </div>
    <div class="div-containerItem raid-add-members">
      <input class="input-text input-text-member" type="text" placeholder="공격대원을 입력해 주세요."
             v-on:change="charterApi"
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
        url: "http://localhost:8080/raid/update",
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
    },
    charterApi() {
      console.log('Call API charterApi');
    },
  },
}
</script>

<style scoped>

</style>
