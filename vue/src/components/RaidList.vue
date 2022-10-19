<template>
  <div id="RaidList">
    <Modal v-if="isModalViewed">
      <AddRaidContent @close-modal="isModalViewed = false"/>
    </Modal>
    <div class="container-raid">
      <div class="container-card">
        <div class="container-btn">
          <button class="positive-btn" @click="isModalViewed = true">레이드 추가</button>
        </div>
        <div class="container-raid-list">
          <div class="container-raid-type" v-for="type in Object.keys(raidData)">
            <h3>{{type}}</h3>
            <div class="container-raid-item" v-for="item in raidData[type]">
                {{item.id}}
               {{item.difficulty}}
               {{item.members}}
<!--                  <span>{{item.id}}</span>-->
<!--                  <span>{{item.difficulty}}</span>-->
<!--                  <span>{{item.members}}</span>-->

              <!--              <div class="container-raid-element-members">-->
              <!--                <div class="container-raid-element-member" v-for="member in item.members">-->
              <!--                  <h5>{{member}}</h5>-->
              <!--                </div>-->
              <!--              </div>-->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Modal from "./Modal";
import AddRaidContent from "./AddRaidContent";

export default {
  name: 'RaidList',
  components: {
    Modal,
    AddRaidContent
  },
  data() {
    return {
      isModalViewed: false,
      raidData: {
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
  }
}
</script>
<style>
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

.positive-btn {
  background-color: #1985f2;
  border: none;
  color: white;
  padding: 10px 32px;
  font-size: 16px;
  transition: 0.3s;
  text-decoration: none;
  cursor: pointer;
  border-radius: 0.7em;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);
}

.positive-btn:hover {
  background-color: #0e6cd5;
}

.neutral-btn {
  background-color: #ffffff;
  color: black;
  padding: 10px 32px;
  font-size: 16px;
  transition: 0.3s;
  text-decoration: none;
  cursor: pointer;
  border: #666666 solid 1px;
  border-radius: 0.7em;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, 0.04);
}

.neutral-btn:hover {
  background-color: #e6e6e6;
}
</style>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


.container-raid {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  /*justify-content: flex-start;*/
  justify-content: space-between;
}
</style>
