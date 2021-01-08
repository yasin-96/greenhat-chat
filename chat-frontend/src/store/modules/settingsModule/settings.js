const settingsModule = {
  namespaced: true,
  modules: {},
  state: () => ({
    enableDialog: false,
  }),
  actions: {
    act_openSettingsDialog({ commit }) {
      commit('MUT_OPEN_SETTINGS_DIALOG');
    },
    act_openSettingsDialogWithValue({ commit }, payload) {
      commit('MUT_OPEN_SETTINGS_DIALOG_WITH_VALUE', payload);
    },
  },
  mutations: {
    MUT_OPEN_SETTINGS_DIALOG(state) {
      state.enableDialog = true;
    },
    MUT_OPEN_SETTINGS_DIALOG_WITH_VALUE(state, payload) {
      state.enableDialog = payload;
    },
  },
};

export default settingsModule;
