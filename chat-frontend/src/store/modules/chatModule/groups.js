import RestCall from '@/services/RestCall';

const API_BASE_PATHS = {
  group: '/group',
  user: '/user',
};

const API_PATHS = {
  group: {
    create: API_BASE_PATHS.group + '/new',
    groupInfo: API_BASE_PATHS.group + '/id',
    groupMessages: API_BASE_PATHS.group + '/messages',
    userGroups: API_BASE_PATHS.group + '/user',
    updateUserList: API_BASE_PATHS.group + /users/,
    removeUserFromList: API_BASE_PATHS.group + '/user',
  },
  user: {
    groups: API_BASE_PATHS.user + '/groups',
  },
};

const groupModule = {
  namespaced: true,
  state: () => ({
    enableWindow: false,
    activeGroupId: '',
    activeGroup: {},
    userGroups: [],
    allGroupIDS: [],
    editOptions: {
      old: '',
      infoType: null,
      title: '',
      displaySettingsWindow: false,
      displayDeleteWindow: false,
    },
  }),
  actions: {
    act_loadGroupInfos({ commit }, payload) {
      RestCall.rcRequest(API_PATHS.group.groupInfo, 'get', null, null, payload)
        .then(({ data }) => {
          console.log('act_loadGroupInfos() res: ', data);
          if (data !== null) {
            commit('MUT_SAVE_GROUP', data);
            commit('MUT_SET_ACTIVE_GROUP', data._id);
            commit('MUT_LOAD_GROUP_INFO');
          }
        })
        .catch((error) => {
          console.log('act_loadGroupInfos(): ', error);
        });
    },
    act_toggleNewGroupWindow({ commit }, toggleValue) {
      commit('MUT_TOGGLE_NEW_GROUP_WINDOW', toggleValue);
    },
    act_closeNewGroupWindow({ commit }) {
      commit('MUT_CLOSE_NEW_GROUP_WINDOW');
    },
    async act_createNewGroup({ dispatch }, newGroup) {
      await RestCall.rcRequest(API_PATHS.group.create, 'post', null, newGroup)
        .then(({ data }) => {
          console.log('act_createNewGroup() res: ', data);
          if (data !== null) {
            // commit('MUT_SAVE_GROUP', data);
            dispatch('act_loadGroupInfos', data);
          }
        })
        .catch((error) => {
          console.log('act_createNewGroup(): ', error);
        });
    },
    async act_getAllGroupsFromUser({ commit, rootState }) {
      const userId = {
        _id: rootState.user.user.id,
      };
      console.log('act_getAllGroupsFromUser', userId);
      await RestCall.rcRequest(API_PATHS.group.userGroups, 'get', null, null, userId)
        .then(({ data }) => {
          console.log('act_getAllGroupsFromUser() res: ', data);
          if (data !== null) {
            commit('MUT_LOAD_ALL_GROUPS_FROM_USER', data);
          }
        })
        .catch((error) => {
          console.log('act_getAllGroupsFromUser(): ', error);
          return null;
        });
    },

    async act_getAllGroupIdsFromUser({ commit, rootState }) {
      const userId = {
        _id: rootState.user.user.id,
      };
      console.log('act_getAllGroupIdsFromUser', userId);
      await RestCall.rcRequest(API_PATHS.user.groups, 'get', null, null, userId)
        .then(({ data }) => {
          console.log('act_getAllGroupIdsFromUser() res: ', data);
          if (data !== null) {
            commit('MUT_LOAD_ALL_IDS_FROM_GROUPS', data);
          }
        })
        .catch((error) => {
          console.log('act_getAllGroupIdsFromUser(): ', error);
          return null;
        });
    },

    act_setGroupEditWindowSettingsBasedOnType({ commit }, editOptions) {
      commit('MUT_SET_EDIT_WINDOW_DATA_FOR_ADMIN', editOptions);
    },

    act_clearGroupState({ commit }) {
      commit('MUT_CLEAR_GROUP_STATE');
    },
  },
  mutations: {
    MUT_SET_ACTIVE_GROUP(state, id) {
      state.activeGroupId = id;
    },

    MUT_LOAD_GROUP_INFO(state) {
      state.activeGroup = state.userGroups.find(({ _id }) => _id === state.activeGroupId);
    },

    MUT_TOGGLE_NEW_GROUP_WINDOW(state, tValue) {
      state.enableWindow = tValue;
    },
    MUT_CLOSE_NEW_GROUP_WINDOW(state) {
      state.enableWindow = false;
    },
    MUT_SAVE_GROUP(state, loadGroupInfo) {
      // if(state.groups && !!state.groups){

      // }
      let found = false;
      let indexOfGroup = null;
      state.userGroups.map((ig, index) => {
        if (ig._id === loadGroupInfo._id) {
          ig = loadGroupInfo;
          found = true;
          indexOfGroup = index;
        }
      });

      if (found) {
        state.activeGroupId = loadGroupInfo._id;
        state.userGroups[indexOfGroup] = loadGroupInfo;
      } else {
        state.userGroups.push(loadGroupInfo);
      }
    },
    MUT_LOAD_ALL_GROUPS_FROM_USER(state, userGroups) {
      console.log('Load USER GROUPS', userGroups);
      if (state.userGroups && !!state.userGroups && state.userGroups.length) {
        state.userGroups = new Array();
      }
      state.userGroups = userGroups;
    },
    MUT_LOAD_ALL_IDS_FROM_GROUPS(state, groupIds) {
      if (state.allGroupIDS && !!state.allGroupIDS && state.allGroupIDS.length) {
        state.allGroupIDS = new Array();
      }
      state.allGroupIDS = groupIds;
    },
    MUT_CLEAR_GROUP_STATE(state) {
      state.enableWindow = false;
      state.activeGroupId = '';
      state.activeGroup = {};
      state.userGroups = [];
      state.allGroupIDS = [];
    },

    MUT_DISABLE_EDIT_WINDOW(state) {
      state.editOptions.displaySettingsWindow = false;
    },
    MUT_TOOGLE_EDIT_WINDOW(state, value) {
      state.editOptions.displaySettingsWindow = value;
    },

    MUT_SET_EDIT_WINDOW_DATA_FOR_ADMIN(state, editOptions) {
      console.log('MUT_SET_EDIT_WINDOW_DATA_FOR_ADMIN', editOptions);
      const group = state.userGroups.find((group) => group._id === state.activeGroupId)
      console.log(group, state.activeGroupId)
      switch (editOptions) {
        case 0:
          state.editOptions.title = 'Group-Name';
          state.editOptions.old = state.activeGroup.name;
          state.editOptions.infoType = 0;
          break;
        case 1:
          state.editOptions.title = 'Group-Admin' ;
          state.editOptions.old = state.activeGroup.admin;
          state.editOptions.infoType = 1;
          break;
        default:
          break;
      }
    },
    MUT_RESET_EDIT_WINDOW_DATA_FOR_ADMIN(state) {
      state.editOptions.title = '';
      state.editOptions.old = '';
      state.editOptions.infoType = 0;
    },
  },
};

export default groupModule;
