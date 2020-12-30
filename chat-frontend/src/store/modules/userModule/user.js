import groupModule from "@/store/modules/chatModule/groups";
import ResCall from "@/services/RestCall";

const userModule = {
  namespaced: true,
  modules: {
    allGroups: groupModule
  },
  state: () => ({
    user: {},
    activeGroup: {

    },
    groups: [
      1,3,4,
      
    ]
  }),
  actions: {
    async act_loadGroupInfos({ commit }, payload) {
      commit('MUT_LOAD_GROUP_INFO', payload);
    },
    async act_logUserIn({commit}, userLoginCred){
      ResCall.login(userLoginCred).then((response) => {
        commit("MUT_SAVE_USER", response);
      }).catch((error) => {
        console.error("act_logUserIn()", error)
      })
    },

    async act_registerUser({commit}, newUser){
      ResCall.newAccount(newUser).then((response) => {
        commit("MUT_SAVE_USER", response);
      }).catch((error) => {
        console.error("act_logUserIn()", error)
      })
    }
  },
  mutations: {
    MUT_LOAD_GROUP_INFO(state, id) {
      //data

      state.activeGroup = state.allGroups.groups.find(({ _id }) => _id === id);
    },

    MUT_SAVE_USER(state, userCred){
      state.user = userCred;
    }
  },
  getters:{
    usersGroups(state){
      return state.groups.forEach(id => {
        return state.allGroups.groups.find(({ _id }) => _id === id);
      });
    }
  }
};

export default userModule;
