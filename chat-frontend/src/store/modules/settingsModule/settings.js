const settingsModule = {
  namespaced: true,
  modules: {},
  state: () => ({
    localStorageKey: 'greenhat-chat',
    enableDialog: false,
    languages: [
      { local: 'de-DE', title: 'chatView.navigationbar.translate.de', active: false },
      { local: 'en-EN', title: 'chatView.navigationbar.translate.en', active: false },
    ],
    sidebar: {
      sidebarMini: {},
      sidebarLeft: {
        admin:{
          group:{
            addUserToGroupDialog: false,
            editValue: false,
          },
          adminDialog: false,
        }
      },
    },
  }),
  actions: {
    act_openSettingsDialog({ commit }) {
      commit('MUT_OPEN_SETTINGS_DIALOG');
    },
    act_openSettingsDialogWithValue({ commit }, payload) {
      commit('MUT_OPEN_SETTINGS_DIALOG_WITH_VALUE', payload);
    },
    act_changeLanguage({ commit }, indexOfLocal) {
      commit('MUT_CHANGE_LOCAL', indexOfLocal);
    },




    //#region Admin Settings 
    act_openAdminSettingsDialogForGroup({commit}){
      commit("MUT_OPEN_SETTINS_DIALOG_FOR_GROUP_ADMIN")
    },

    act_toggleAdminSettingsDialogForGroup({commit}, value){
      commit("MUT_TOGGLE_SETTINS_DIALOG_FOR_GROUP_ADMIN", value)
    },

      //#region Admin Group Options
      act_openDialogForAddUserToGroup({commit}){
        commit("MUT_OPEN_DIALOG_FOR_ADD_USER_TO_GROUP")
      },
  
      act_toggleDialogForAddUserToGroup({commit}, value){
        commit("MUT_TOGGLE_DIALOG_FOR_ADD_USER_TO_GROUP", value)
      },
      //#endregion

      //#region Admin Group Edit Options
      act_openDialogForChangeValueForGroup({commit}){
        commit("MUT_OPEN_DIALOG_FOR_CHANGE_VALUE_FOR_GROUP")
      },
  
      act_toggleDialogForChangeValueForGroup({commit}, value){
        commit("MUT_TOGGLE_DIALOG_FOR_CHANGE_VALUE_FOR_GROUP", value)
      }
      //#endregion


    //#endregion


  },
  mutations: {
    MUT_OPEN_SETTINGS_DIALOG(state) {
      state.enableDialog = true;
    },
    MUT_OPEN_SETTINGS_DIALOG_WITH_VALUE(state, payload) {
      state.enableDialog = payload;
    },

    MUT_CHANGE_LOCAL(state, index) {
      state.languages.map((lang, i) => {
        if (i == index) {
          lang.active = true;
        } else {
          lang.active = false;
        }
      });
    },
    MUT_OPEN_SETTINS_DIALOG_FOR_GROUP_ADMIN(state){
      state.sidebar.sidebarLeft.admin.adminDialog = true
    },
    MUT_TOGGLE_SETTINS_DIALOG_FOR_GROUP_ADMIN(state, toggleValue){
      state.sidebar.sidebarLeft.admin.adminDialog = toggleValue
    },

    MUT_OPEN_DIALOG_FOR_ADD_USER_TO_GROUP(state){
      state.sidebar.sidebarLeft.admin.group.addUserToGroupDialog = true
    },
    MUT_TOGGLE_DIALOG_FOR_ADD_USER_TO_GROUP(state, toggleValue){
      state.sidebar.sidebarLeft.admin.group.addUserToGroupDialog = toggleValue
    },

    MUT_OPEN_DIALOG_FOR_CHANGE_VALUE_FOR_GROUP(state){
      state.sidebar.sidebarLeft.admin.group.editValue = true
    },
    MUT_TOGGLE_DIALOG_FOR_CHANGE_VALUE_FOR_GROUP(state, toggleValue){
      state.sidebar.sidebarLeft.admin.group.editValue = toggleValue
    }
  },
};

export default settingsModule;
