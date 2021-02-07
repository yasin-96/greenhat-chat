import RestCall from '@/services/RestCall';

const API_BASE_PATHS = {
  message: '/message',
};

const API_PATHS = {
  message: {
    create: API_BASE_PATHS.message + '/new',
    messageInfo: API_BASE_PATHS.message + '/id',
    groupMessages: API_BASE_PATHS.message + '/group',
  },
};

const chatModule = {
  namespaced: true,
  state: () => ({
    messagesOfGroup: [],
    socketInfo: {
      clientConnected: false,
      recievedMessages: '',
      reconnectError: '',
      reconnectionCounter: 0,
    },
    createMessages: 0,
  }),
  actions: {
    async act_createNewMessage({ commit }, newMessage) {
      await RestCall.rcRequest(API_PATHS.message.create, 'post', null, newMessage).then(({ status }) => {
        if (status === 200) {
          console.log('act_createNewMessage', status);
          commit('MUT_INCREASE_MESSAGE_COUNTER');
        }
      });
    },
    async act_loadAllMessagesFromGroup({ commit }, gId) {
      await RestCall.rcRequest(API_PATHS.message.groupMessages, 'get', null, null, gId).then(({ data, status }) => {
        if (status === 200) {
          console.log('act_loadAllMessagesFromGroup', status, data);
          commit('MUT_SAVE_MESSAGES_FROM_GROUP', { gid: gId._id, gMessages: data });
        }
      });
    },
    act_addNewMessagesFromWS({ commit }, gId) {
      commit('MUT_ADD_MESSAGE_FROM_WEBSOCKET', gId);
    },
    act_clearChatState({ commit }) {
      commit('MUT_CLEAT_CHAT_STATE');
    },
  },

  mutations: {
    MUT_INCREASE_MESSAGE_COUNTER(state) {
      state.createMessages++;
    },

    MUT_SAVE_MESSAGES_FROM_GROUP(state, { gid, gMessages }) {
      if (state.messagesOfGroup == null) {
        state.messagesOfGroup = new Array();
      }

      let groupExist = state.messagesOfGroup.find((g) => g.gid == gid);

      console.log('Groupe gefunden ', gid, groupExist);

      if (groupExist != null || groupExist !== undefined) {
        groupExist.messages = gMessages;
        state.messagesOfGroup.map((g) => {
          if (g.gid == groupExist.gid) {
            g = groupExist;
          }
        });
      } else {
        state.messagesOfGroup.push({ gid: gid, messages: gMessages });
      }
    },
    MUT_ADD_MESSAGE_FROM_WEBSOCKET(state, gid) {
      let groupExist = state.messagesOfGroup.find((g) => g.gid == gid);

      console.log('Groupe gefunden um ws message hinzuzufuegen ', gid, groupExist);

      if (groupExist != null || groupExist !== undefined) {
        groupExist.messages.push(state.socketInfo.recievedMessages);
        state.messagesOfGroup.map((g) => {
          if (g.gid == groupExist.gid) {
            g = groupExist;
          }
        });
      }
    },
    MUT_CLEAT_CHAT_STATE(state) {
      state.messagesOfGroup = [];
      state.socketInfo = {
        clientConnected: false,
        recievedMessages: '',
        reconnectError: '',
        reconnectionCounter: 0,
      };
      state.createMessages = 0;
    },
  },
};

export default chatModule;
