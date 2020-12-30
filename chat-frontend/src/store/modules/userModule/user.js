import groupModule from "@/store/modules/chatModule/groups";


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
  },
  mutations: {
    MUT_LOAD_GROUP_INFO(state, id) {
      //data

      state.activeGroup = state.allGroups.groups.find(({ _id }) => _id === id);
    },
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
