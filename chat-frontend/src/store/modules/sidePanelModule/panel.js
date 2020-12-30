const sidePanelModule = {
  namespaced: true,
  state: () => ({
    drawer: true,
  }),
  actions: {
    act_toggleSidePanel({ commit }) {
      commit('MUT_TOOGLE_SIDE_PANEL');
    },
    act_toggleSidePanelWithValue({ commit }, payload) {
      commit('MUT_TOOGLE_SIDE_PANEL_WITH_FLAG', payload);
    },
  },
  mutations: {
    MUT_TOOGLE_SIDE_PANEL(state) {
      state.drawer = !state.drawer;
    },
    MUT_TOOGLE_SIDE_PANEL_WITH_FLAG(state, data) {
      state.drawer = data;
    },
  },
};

export default sidePanelModule;
