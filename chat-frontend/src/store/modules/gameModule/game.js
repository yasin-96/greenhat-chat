const gameModule = {
  namespaced: true,
  state: () => ({
    gameWasPlayed: false,
    openDialog: false,
    gamePrices: [
      {
        textFillStyle: '#fff',
        fillStyle: '#7CB342',
        text: 'Whisky Set',
      },
      {
        textFillStyle: '#fff',
        fillStyle: '#FFD54F',
        text: 'Pizza',
      },
      {
        textFillStyle: '#fff',
        fillStyle: '#7CB342',
        text: 'E-Bike',
      },
      {
        textFillStyle: '#fff',
        fillStyle: '#FFD54F',
        text: 'Kasten Bier',
      },
      {
        textFillStyle: '#fff',
        fillStyle: '#7CB342',
        text: 'PS5 Pro',
      },
      {
        textFillStyle: '#fff',
        fillStyle: '#FFD54F',
        text: 'Parfume',
      },
      {
        textFillStyle: '#fff',
        fillStyle: '#7CB342',
        text: 'iPad 10"',
      },
      {
        textFillStyle: '#fff',
        fillStyle: '#FFD54F',
        text: 'Badeöle',
      },
    ],
  }),
  actions: {
    act_toggleGameWindow({ commit }) {
      commit('MUT_TOOGLE_GAME_WINDOW');
    },
    act_toggleGameWindowWithValue({ commit }, value) {
      commit('MUT_TOOGLE_GAME_WINDOW_WITH_VALUE', value);
    },
    act_gameWasPlayed({ commit }) {
      commit('MUT_GAME_WAS_PLAYED');
    },
    act_clearGameState({commit}){
        commit("MUT_CLEAR_GAME_STATE")
    }
  },
  mutations: {
    MUT_TOOGLE_GAME_WINDOW(state) {
      if (!state.gameWasPlayed) {
        state.openDialog = !state.openDialog;
      }
    },
    MUT_TOOGLE_GAME_WINDOW_WITH_VALUE(state, value) {
      state.openDialog = value;
    },
    MUT_GAME_WAS_PLAYED(state) {
      state.gameWasPlayed = true;
      state.openDialog = false;
    },
    MUT_CLEAR_GAME_STATE(state) {
      state.gameWasPlayed = false;
      state.openDialog = false;
    },
  },
};

export default gameModule;
