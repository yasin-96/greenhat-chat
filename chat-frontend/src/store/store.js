import Vue from 'vue';
import Vuex from 'vuex';
import VuexPersistence from 'vuex-persist'

import sidePanelModule from "@/store/modules/sidePanelModule/panel";
import userModule from "@/store/modules/userModule/user";
import chatModule from "@/store/modules/chatModule/chat";
import notifyModule from "@/store/modules/notificationModule/notify";
import gameModule from "@/store/modules/gameModule/game";
import settingsModule from "@/store/modules/settingsModule/settings";

Vue.use(Vuex);

const vuexLocal = new VuexPersistence({
  storage: window.localStorage,
  key:'greenhat-chat',
  // asyncStorage: true
})

const store = new Vuex.Store({
  modules: {
    sidePanel: sidePanelModule,
    user: userModule,
    chat: chatModule,
    notify: notifyModule,
    game: gameModule,
    settings: settingsModule
  },
  plugins: [vuexLocal.plugin]
});


export default store;