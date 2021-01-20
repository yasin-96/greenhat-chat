import groupModule from '@/store/modules/chatModule/groups';
import RestCall from '@/services/RestCall';
import { checkUser, messageBasedOnReturnValue } from '@/utils/util';

const API_BASE_PATHS = {
  user: '/user',
  message: '/message',
  group: '/group',
};

const API_PATHS = {
  user: {
    create: API_BASE_PATHS.user + '/register',
    login: API_BASE_PATHS.user + '/authenticate',
    specUpdate: API_BASE_PATHS.user + '/specs',
    allUser: API_BASE_PATHS.user + '/allUsers',
  },
};

const userModule = {
  namespaced: true,
  modules: {
    allGroups: groupModule,
  },
  state: () => ({
    allRegisterdUsers:[],
    user: {},
    
    // groups: [1, 3, 4],
    editOptions: {
      old: '',
      suffix: null,
      infoType: null,
      title: '',
      displaySettingsWindow: false,
    },
  }),
  actions: {
    
    


    /**
     * 
     * @param {state} param0 
     * @param {Object} payload login cred from user (username & password) 
     */
    async act_logUserIn({ commit }, userLoginCred) {
      return await RestCall.login(userLoginCred)
        .then(({ data, status }) => {
          commit('MUT_SAVE_USER', data);
          if (checkUser(data) === null) {
            return messageBasedOnReturnValue(status, 'login');
          }

          return messageBasedOnReturnValue(status, 'login');
        })
        .catch((error) => {
          console.error('act_logUserIn()', error);
          return null
        });
    },

    /**
     * 
     * @param {state} param0 
     * @param {Object} newUser new user that will be created
     */
    async act_registerUser({ commit }, newUser) {
      return await RestCall.newAccount(newUser)
        .then(({ data, status }) => {
          if (checkUser(data) !== null) {
            commit('MUT_SAVE_USER', data);
          }
          return messageBasedOnReturnValue(status, 'register');
        })
        .catch((error) => {
          console.error('act_logUserIn()', error);
          return null
        });
    },

    /**
     * 
     * @param {*} param0 
     * @param {*} newInfo 
     */
    async act_updateSpecificUserInformationen({ commit }, newInfo) {
      console.log('act_updateSpecificUserInformationen', newInfo);
      return await RestCall.rcRequest(API_PATHS.user.specUpdate, 'patch', null, newInfo)
        .then(({ data }) => {
          console.log('act_updateSpecificUserInformationen() res: ', data);
          if (data !== null) {
            commit('MUT_SAVE_USER', data);
          }
        })
        .catch((error) => {
          console.log('act_updateSpecificUserInformationen(): ', error);
          return null
        });
    },

    act_getAllUserWithIdAndUserName({ commit }) {
      console.log('act_getAllUserWithIdAndUserName');
      RestCall.rcRequest(API_PATHS.user.allUser, 'get', null, null)
        .then(({ data }) => {
          console.log('act_getAllUserWithIdAndUserName() res: ', data);
          if (data !== null) {
            commit('MUT_SAVE_ALL_USERS_WITH_ID_UNAME', data);
          }
        })
        .catch((error) => {
          console.log('act_getAllUserWithIdAndUserName(): ', error);
          return null
        });
    },

    

    act_closeEditWindowForUser({ commit }) {
      commit('MUT_DISABLE_EDIT_WINDOW');
      // commit("MUT_RESET_EDIT_WINDOW_DATA")
    },
    act_toggleEditWindowForUserWithValue({ commit }, toggelValue) {
      commit('MUT_TOOGLE_EDIT_WINDOW', toggelValue);
    },
    act_setEditWindowSettingsBasedOnType({ commit }, editOptions) {
      commit('MUT_SET_EDIT_WINDOW_DATA', editOptions);
    },
  },
  mutations: {
    

    MUT_SAVE_USER(state, userCred) {
      state.user = userCred;
    },
    MUT_DISABLE_EDIT_WINDOW(state) {
      state.editOptions.displaySettingsWindow = false;
    },
    MUT_TOOGLE_EDIT_WINDOW(state, value) {
      state.editOptions.displaySettingsWindow = value;
    },
    MUT_SET_EDIT_WINDOW_DATA(state, editOptions) {
      console.log('MUT_SET_EDIT_WINDOW_DATA', editOptions);
      switch (editOptions) {
        case 0:
          state.editOptions.title = 'Email';
          state.editOptions.old = state.user.email;
          state.editOptions.infoType = 0;
          state.editOptions.suffix = '@greenhat.de';
          break;
        case 1:
          state.editOptions.title = 'Nickname';
          state.editOptions.old = state.user.username;
          state.editOptions.infoType = 1;
          state.editOptions.suffix = null;
          break;
        case 3:
          state.editOptions.title = 'Avatar Picture';
          state.editOptions.old = state.user.avatarPicture;
          state.editOptions.infoType = 3;
          state.editOptions.suffix = null;
          break;
        default:
          break;
      }
    },
    MUT_RESET_EDIT_WINDOW_DATA(state) {
      state.editOptions.title = '';
      state.editOptions.old = '';
      state.editOptions.infoType = 0;
    },

    MUT_SAVE_ALL_USERS_WITH_ID_UNAME(state, usersList){
      state.allRegisterdUsers = usersList;
    }
  },
  getters: {
    usersGroups(state) {
      return state.groups.forEach((id) => {
        return state.allGroups.groups.find(({ _id }) => _id === id);
      });
    },
  },
};

export default userModule;
