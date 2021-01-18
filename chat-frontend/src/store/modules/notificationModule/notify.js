const notifyModule = {
  namespaced: true,
  state: () => ({
    notfication: {
      timeOut: 5000,
      message: '',
      toggle: false,
      icon: '',
      color: 'error',
    },
  }),
  actions: {
    act_setAlterMessage({ commit }, { message, color, icon }) {
        console.log("Bin in notif",  message, color, icon)
      if (message && !!message) {
        commit('MUT_SET_MESSAGE', { message, color, icon });
      }
    },
  },
  mutations: {
    MUT_SET_MESSAGE(state, { message, color, icon }) {
      state.notfication.message = message;
      state.notfication.icon = icon;
      state.notfication.color = color;
      state.notfication.toggle = true;

      // setTimeout(() => {
      //   state.notfication.toggle = false;
      // }, state.notfication.timeOut);
    },
  },
};

export default notifyModule;
