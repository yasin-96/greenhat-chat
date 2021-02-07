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
    specUpdate: API_BASE_PATHS.group + '/specs',
  },
  user: {
    groups: API_BASE_PATHS.user + '/groups',
  },
};


/**
 * 
 */
const groupModule = {
  namespaced: true,
  state: () => ({
    enableWindow: false,
    activeGroupId: '',
    activeGroup: {},
    rawGroups: [],
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
    async act_loadGroupInfos({ commit }, payload) {
      return await RestCall.rcRequest(API_PATHS.group.groupInfo, 'get', null, null, payload)
        .then((response) => {
          console.log('act_loadGroupInfos() res: ', response);
          if (response && response.data !== null && response.status) {
            commit('MUT_SAVE_GROUP', response.data);
            commit('MUT_SET_ACTIVE_GROUP', response.data._id);
            commit('MUT_LOAD_GROUP_INFO');
          }
          return response
        })
        .catch((error) => {
          console.log('act_loadGroupInfos(): ', error);
          return error
        });
    },
    act_toggleNewGroupWindow({ commit }, toggleValue) {
      commit('MUT_TOGGLE_NEW_GROUP_WINDOW', toggleValue);
    },
    act_closeNewGroupWindow({ commit }) {
      commit('MUT_CLOSE_NEW_GROUP_WINDOW');
    },
    async act_createNewGroup({ dispatch }, newGroup) {
      return await RestCall.rcRequest(API_PATHS.group.create, 'post', null, newGroup)
        .then((response) => {
          console.log('act_createNewGroup() res: ', response);
          if (response && response.data !== null && response.status == 200) {
            // commit('MUT_SAVE_GROUP', data);
            dispatch('act_loadGroupInfos', response.data);
          }
          return response
        })
        .catch((error) => {
          console.log('act_createNewGroup(): ', error);
          return error
        });
    },
    async act_getAllGroupsFromUser({ commit, rootState }) {
      const userId = {
        _id: rootState.user.user.id,
      };
      console.log('act_getAllGroupsFromUser', userId);
      return await RestCall.rcRequest(API_PATHS.group.userGroups, 'get', null, null, userId)
        .then((response) => {
          console.log('act_getAllGroupsFromUser() res: ', response);
          if (response && response.data !== null && response.status == 200) {
            commit('MUT_LOAD_ALL_GROUPS_FROM_USER', response.data);
          }
        })
        .catch((error) => {
          console.log('act_getAllGroupsFromUser(): ', error);
          return error
        });
    },

    async act_getAllGroupIdsFromUser({ commit, rootState }) {
      const userId = {
        _id: rootState.user.user.id,
      };
      console.log('act_getAllGroupIdsFromUser', userId);
      return await RestCall.rcRequest(API_PATHS.user.groups, 'get', null, null, userId)
        .then((response) => {
          console.log('act_getAllGroupIdsFromUser() res: ', response);
          if (response && response.data !== null && response.status == 200) {
            commit('MUT_LOAD_ALL_IDS_FROM_GROUPS', response.data);
          }
          return response
        })
        .catch((error) => {
          console.log('act_getAllGroupIdsFromUser(): ', error);
          return error;
        });
    },

    async act_removeUserFromGroup({ commit, dispatch }, detailsForRemovingUser) {
      return await RestCall.rcRequest(API_PATHS.group.removeUserFromList, 'delete', null, detailsForRemovingUser, null)
        .then((response) => {
          console.log('act_removeUserFromGroup', response);
          if (response && response.data && response.status === 200) {
            commit('MUT_CHANGE_GROUP_INFORMATION', response.data);
            dispatch("act_getAllGroupsFromUser")
          }
          return response;
        })
        .catch((error) => {
          console.error('act_removeUserFromGroup', error);
          return error;
        });
    },

    async act_addUserToGroup({ commit, dispatch }, detailsForAddingUser) {
      return await RestCall.rcRequest(API_PATHS.group.updateUserList, 'put', null, detailsForAddingUser, null)
        .then((response) => {
          console.log('act_addUserToGroup', response);
          if (response && response.data && response.status === 200) {
            commit('MUT_CHANGE_GROUP_INFORMATION', response.data);
            dispatch("act_getAllGroupsFromUser")
          }
          return response;
        })
        .catch((error) => {
          console.error('act_addUserToGroup', error);
          return error;
        });
    },

    async act_updateSpecificGroupInformationen({ commit, dispatch }, specificInformationen) {
      return await RestCall.rcRequest(API_PATHS.group.specUpdate, 'patch', null, specificInformationen)
        .then((response) => {
          if (response && response.data && response.status === 200) {
            commit('MUT_CHANGE_GROUP_INFORMATION', response.data);
            dispatch("act_getAllGroupsFromUser")
          }
          return response;
        })
        .catch((error) => {
          console.error('act_updateSpecificGroupInformationen', error);
          return error;
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

      if(state.rawGroups && !!state.rawGroups && state.rawGroups.length){
        state.rawGroups = userGroups
      }
      state.rawGroups = Object.assign(new Array(),userGroups);
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
      const group = state.userGroups.find((group) => group._id === state.activeGroupId);
      console.log(group, state.activeGroupId);
      switch (editOptions) {
        case 0:
          state.editOptions.title = 'Group-Name';
          state.editOptions.old = state.activeGroup.name;
          state.editOptions.infoType = 0;
          break;
        case 1:
          state.editOptions.title = 'Group-Admin';
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
    MUT_CHANGE_GROUP_INFORMATION(state, newGroupInfo) {
      state.activeGroup = newGroupInfo;
    },
  },
};

export default groupModule;
