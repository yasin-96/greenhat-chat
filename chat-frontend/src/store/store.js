import Vue from 'vue';
import Vuex from 'vuex';

import sidePanelModule from "@/store/modules/sidePanelModule/panel";
import userModule from "@/store/modules/userModule/user";
import chatModule from "@/store/modules/chatModule/chat";
import notifyModule from "@/store/modules/notificationModule/notify";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    sidePanel: sidePanelModule,
    user: userModule,
    chat: chatModule,
    notify: notifyModule
  }
});


export default store;