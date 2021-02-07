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
      sidebarMini: {
        homeLogo: '@/assets/logo_01.svg',
      },
      sidebarLeft: {
        admin: {
          adminLogo: '',
          group: {
            addUserToGroupDialog: false,
            editValue: false,
            deleteGroup: false
          },
          adminDialog: false,
        },
        sidebarLeftDrawer: true,
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
    act_setDefaultLanguage({commit}, localKey){
      commit("MUT_SET_DEFAULT_LANGUAGE", localKey);
    },

    //#region Admin Settings
    act_openAdminSettingsDialogForGroup({ commit }) {
      commit('MUT_OPEN_SETTINS_DIALOG_FOR_GROUP_ADMIN');
    },

    act_toggleAdminSettingsDialogForGroup({ commit }, value) {
      commit('MUT_TOGGLE_SETTINS_DIALOG_FOR_GROUP_ADMIN', value);
    },

    //#region Admin Group Options
    act_openDialogForAddUserToGroup({ commit }) {
      commit('MUT_OPEN_DIALOG_FOR_ADD_USER_TO_GROUP');
    },

    act_toggleDialogForAddUserToGroup({ commit }, value) {
      commit('MUT_TOGGLE_DIALOG_FOR_ADD_USER_TO_GROUP', value);
    },
    //#endregion

    //#region Admin Group Edit Options
    act_openDialogForChangeValueForGroup({ commit }) {
      commit('MUT_OPEN_DIALOG_FOR_CHANGE_VALUE_FOR_GROUP');
    },

    act_toggleDialogForChangeValueForGroup({ commit }, value) {
      commit('MUT_TOGGLE_DIALOG_FOR_CHANGE_VALUE_FOR_GROUP', value);
    },
    //#endregion

    //#endregion

    act_toggleSidePanel({ commit }) {
      commit('MUT_TOOGLE_SIDE_PANEL');
    },
    act_toggleSidePanelWithValue({ commit }, payload) {
      commit('MUT_TOOGLE_SIDE_PANEL_WITH_FLAG', payload);
    },

    act_toggleDialogForDeletingGroup({commit}, toggleValue){
      commit("MUT_TOGGLE_DIALOG_FOR_DELETING_GROUP",toggleValue)
    }
  },
  mutations: {
    /**
     *
     * @param {*} state
     */
    MUT_OPEN_SETTINGS_DIALOG(state) {
      state.enableDialog = true;
    },
    /**
     *
     * @param {*} state
     * @param {*} payload
     */
    MUT_OPEN_SETTINGS_DIALOG_WITH_VALUE(state, payload) {
      state.enableDialog = payload;
    },

    /**
     *
     * @param {*} state
     * @param {*} index
     */
    MUT_CHANGE_LOCAL(state, index) {
      state.languages.map((lang, i) => {
        if (i == index) {
          lang.active = true;
        } else {
          lang.active = false;
        }
      });
    },

    MUT_SET_DEFAULT_LANGUAGE(state, localKey){
      Object.keys(state.languages).map((pos) => {
        if(state.languages[pos].local == localKey){
          state.languages[pos].active = true
          console.log(state.languages[pos])
        }
      })
    },
    /**
     *
     * @param {*} state
     * @param {*} index
     */
    MUT_OPEN_SETTINS_DIALOG_FOR_GROUP_ADMIN(state) {
      state.sidebar.sidebarLeft.admin.adminDialog = true;
    },
    /**
     *
     * @param {*} state
     * @param {*} index
     */
    MUT_TOGGLE_SETTINS_DIALOG_FOR_GROUP_ADMIN(state, toggleValue) {
      state.sidebar.sidebarLeft.admin.adminDialog = toggleValue;
    },

    /**
     *
     * @param {*} state
     * @param {*} index
     */
    MUT_OPEN_DIALOG_FOR_ADD_USER_TO_GROUP(state) {
      state.sidebar.sidebarLeft.admin.group.addUserToGroupDialog = true;
    },
    /**
     *
     * @param {*} state
     * @param {*} index
     */
    MUT_TOGGLE_DIALOG_FOR_ADD_USER_TO_GROUP(state, toggleValue) {
      state.sidebar.sidebarLeft.admin.group.addUserToGroupDialog = toggleValue;
    },

    /**
     *
     * @param {*} state
     * @param {*} index
     */
    MUT_OPEN_DIALOG_FOR_CHANGE_VALUE_FOR_GROUP(state) {
      state.sidebar.sidebarLeft.admin.group.editValue = true;
    },
    /**
     *
     * @param {*} state
     * @param {*} index
     */
    MUT_TOGGLE_DIALOG_FOR_CHANGE_VALUE_FOR_GROUP(state, toggleValue) {
      state.sidebar.sidebarLeft.admin.group.editValue = toggleValue;
    },

    /**
     * 
     * @param {*} state 
     */
    MUT_TOOGLE_SIDE_PANEL(state) {
      state.sidebar.sidebarLeft.sidebarLeftDrawer = !state.sidebar.sidebarLeft.sidebarLeftDrawer;
    },
    /**
     * 
     * @param {*} state 
     * @param {*} data 
     */
    MUT_TOOGLE_SIDE_PANEL_WITH_FLAG(state, toggleValue) {
      state.sidebar.sidebarLeft.sidebarLeftDrawer = toggleValue;
    },

    /**
     * 
     * @param {*} state 
     * @param {*} data 
     */
    MUT_TOGGLE_DIALOG_FOR_DELETING_GROUP(state, toggleValue) {
      state.sidebar.sidebarLeft.admin.group.deleteGroup = toggleValue;
    },
  },
};

export default settingsModule;
