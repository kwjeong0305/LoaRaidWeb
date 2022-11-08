import Vue from 'vue'
import Router from 'vue-router'
import RaidList from "../components/RaidList";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: RaidList
    }
  ]
})
