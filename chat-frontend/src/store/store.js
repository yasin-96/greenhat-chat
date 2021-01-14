import Vue from 'vue';
import Vuex from 'vuex';
import VuexPersistence from 'vuex-persist';
import VueNativeSock from 'vue-native-websocket';

import sidePanelModule from '@/store/modules/sidePanelModule/panel';
import userModule from '@/store/modules/userModule/user';
import chatModule from '@/store/modules/chatModule/chat';
import notifyModule from '@/store/modules/notificationModule/notify';
import gameModule from '@/store/modules/gameModule/game';
import settingsModule from '@/store/modules/settingsModule/settings';


console.log('WS', process.env.VUE_APP_BACKEND_WS), {store};

Vue.use(Vuex);
// console.log('WS', process.env.VUE_APP_BACKEND_WS, process.env.VUE_APP_BACKEND_WEBSOCKET_INTERFACE);

const vuexLocal = new VuexPersistence({
  storage: window.localStorage,
  key: 'greenhat-chat',
  // asyncStorage: true
});

const store = new Vuex.Store({
  modules: {
    sidePanel: sidePanelModule,
    user: userModule,
    chat: chatModule,
    notify: notifyModule,
    game: gameModule,
    settings: settingsModule,
    // socket: chatModule.socket
  },
  actions:{
    SOCKET_ONOPEN(){
      console.log("act SOCKET_ONOPEN")
    }
  },
  mutations: {
    SOCKET_ONOPEN(state, event) {
      Vue.prototype.$socket = event.currentTarget
      console.log('SOCKET_ONOPEN', event);
      state.chat.socketInfo.clientConnected = true;
    },
    SOCKET_ONCLOSE(state, event) {
      console.log('SOCKET_CLOSE:', event);
      state.chat.socketInfo.clientConnected = false;
    },
    SOCKET_ONERROR(state, event) {
      console.error(state, event);
    },
    // default handler called for all methods
    SOCKET_ONMESSAGE(state, message) {
      console.log("SOCKET_ONMESSAGE",message, state)
      state.chat.socketInfo.recievedMessages = message
      if(state.chat.messages === null ){
        state.chat.messages = []
      } 
      state.chat.messages.push(message)
    },
    // mutations for reconnect methods
    SOCKET_RECONNECT(state, count) {
      console.info(state, count);
    },
    SOCKET_RECONNECT_ERROR(state) {
      state.chat.socketInfo.reconnectError = true;
    },
  },
  plugins: [vuexLocal.plugin],
});



Vue.use(VueNativeSock, process.env.VUE_APP_BACKEND_WS, {
  store: store,
  format: 'json',
  reconnection: false, //reconnect automatically (false)
  reconnectionDelay: 30000, // wait before reconnect
});


export default store;
