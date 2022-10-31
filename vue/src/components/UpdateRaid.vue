<template>
  <div class="div-containerCard raid-add">
    <div class="div-containerItem raid-add-type raid-add-btn">
      <select v-model="raidData.raidType">
        <option v-for="id in Object.keys(raidList)" :value="id">{{ id }}</option>
      </select>
      <button class="btn-positive" @click="raidUpdateApi">수정</button>
    </div>
    <div class="div-containerItem raid-add-difficulty" v-if="raidData.raidType !== ''">
      <label class="label-raid-add" v-for="item in raidList[raidData.raidType].difficulty">
        <input class="input-radio input-radio-difficulty" type="radio" name="difficulty"
               :value="item.id"
               v-model="raidData.raidDifficulty">
        <span>{{ item.name }}</span>
      </label>
    </div>
    <div class="div-containerItem raid-add-members">
      <input class="input-text input-text-member" type="text" placeholder="공격대원을 입력해 주세요."
             v-on:change="charterApi"
             v-for="i in raidList[raidData.raidType].maxMember"
             :id="i-1"
             v-model="raidData.raidMembers[i-1]">
    </div>
  </div>
</template>

<script>
export default {
  name: "UpdateRaid",
  props: {
    raidData: {
      raidId: "",
      raidType: "",
      raidDifficulty: "",
      raidMembers: []
    },
    raidList: Object
  },
  methods: {
    raidUpdateApi() {
      $.ajax({
        url: "http://localhost:8080/raid/update",
        type: "POST",
        data: JSON.stringify(this.raidData),
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
      this.$parent.charterApi();
    },
  },
}
</script>

<style scoped>

</style>
