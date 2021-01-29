const notifyModule = {
  namespaced: true,
  state: () => ({
    notfication: {
      timeOut: 10000,
      message: '',
      toggle: false,
      icon: '',
      color: 'error',
    },
  }),
  actions: {
    act_setAlterMessage({ commit, dispatch }, { message, color, icon }) {
      if (message && !!message) {
        commit('MUT_SET_MESSAGE', { message, color, icon });
        setTimeout(() => {
          dispatch('act_clearNotificationState');
        }, 15000);
      }
    },
    act_clearNotificationState({ commit }) {
      commit('MUT_CLEAR_NOTIFICATION_STATE');
    },
  },
  mutations: {
    MUT_TOGGLE_VISIABLE(state) {
      state.notfication.toggle = false;
    },
    MUT_SET_MESSAGE(state, { message, color, icon }) {
      state.notfication.message = message;
      state.notfication.icon = icon;
      state.notfication.color = color;
      state.notfication.toggle = true;
      setTimeout((state) => {
        state.notfication.toggle = false;
      }, state.notfication.timeOut);
    },
    MUT_CLEAR_NOTIFICATION_STATE(state) {
      state.notfication = {
        timeOut: 10000,
        message: '',
        toggle: false,
        icon: '',
        color: 'error',
      };
    },
  },
};

export default notifyModule;
