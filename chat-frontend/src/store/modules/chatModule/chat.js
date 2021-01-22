import groupModule from '@/store/modules/chatModule/groups';

import RestCall from '@/services/RestCall';
// import { checkUser, messageBasedOnReturnValue } from '@/utils/util';

const chatModule = {
  namespaced: true,
  modules: {
    groupModule,
  },
  state: () => ({
    messages: [],
    socketInfo: {
      clientConnected: false,
      recievedMessages: "",
    },
  }),
  actions: {
    async act_createNewMessage({ commit }, newMessage) {
      await RestCall.sendMessage(newMessage).then((status) => {
        console.log(status);
        commit('MUT_ADD_MESSAGE_FROM_WEBSOCKET');
      });
    },

    act_addNewMessagesFromWS({commit}, messageFromWS){
      commit("MUT_ADD_MESSAGE_FROM_WEBSOCKET", messageFromWS)
    }
  },

  mutations: {
    MUT_ADD_MESSAGE_FROM_WEBSOCKET(state, messageObject){
      state.messages.push(messageObject)
    }
  },
};

export default chatModule;
