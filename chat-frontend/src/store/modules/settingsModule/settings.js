const settingsModule = {
  namespaced: true,
  modules: {},
  state: () => ({
    enableDialog: false,
    languages: [
      { local: 'de-DE', title: 'chatView.navigationbar.translate.de', active: true },
      { local: 'en-EN', title: 'chatView.navigationbar.translate.en', active: false },
    ],
  }),
  actions: {
    act_openSettingsDialog({ commit }) {
      commit('MUT_OPEN_SETTINGS_DIALOG');
    },
    act_openSettingsDialogWithValue({ commit }, payload) {
      commit('MUT_OPEN_SETTINGS_DIALOG_WITH_VALUE', payload);
    },
    act_changeLanguage({ commit }, indexOfLocal) {
      commit("MUT_CHANGE_LOCAL", indexOfLocal)
    },
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
  },
};

export default settingsModule;
