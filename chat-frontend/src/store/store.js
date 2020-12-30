import Vue from 'vue';
import Vuex from 'vuex';

import sidePanelModule from "@/store/modules/sidePanelModule/panel";
import userModule from "@/store/modules/userModule/user";
import chatModule from "@/store/modules/chatModule/chat";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    sidePanel: sidePanelModule,
    user: userModule,
    chat: chatModule
  }
});


export default store;