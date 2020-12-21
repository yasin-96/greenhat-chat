import Vue from 'vue';
import Vuex from 'vuex';

import userModule from "@/store/modules/userModule";
import messageModule from "@/store/modules/userModule";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    
    userMod: userModule
  }
});
