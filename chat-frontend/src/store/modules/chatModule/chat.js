import groupModule from '@/store/modules/chatModule/groups';

import RestCall from '@/services/RestCall';
// import { checkUser, messageBasedOnReturnValue } from '@/utils/util';

const chatModule = {
  namespaced: true,
  modules: {
    groupModule,
  },
  state: () => ({}),
  actions: {
    act_createNewMessage({commit}, newMessage) {
      RestCall.sendMessage(newMessage).then((status) => {
        console.log(status);
        commit("");
      });
    },
  },
  mutations: {
    // MUT_CREATE_NEW_MESSAGE(state, messageToSave) {},
  state: () => ({
   messages:[]
  }),
  actions: {
    act_socket_(){

    }
  },
  mutations: {
    MUT_SOCKET_CONNECT(state, payload){
      console.log("Connected",payload)
    }
  },
};

export default chatModule;
