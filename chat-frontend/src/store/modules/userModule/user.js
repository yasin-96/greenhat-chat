import groupModule from "@/store/modules/chatModule/groups";
import ResCall from "@/services/RestCall";
import {checkUser, messageBasedOnReturnValue} from "@/utils/util";


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
      return ResCall.login(userLoginCred).then(({data, status}) => {
        
        if(checkUser(data) === null){
          return messageBasedOnReturnValue(status, "login")
        }
        
        commit("MUT_SAVE_USER", data);
        return messageBasedOnReturnValue(status, "login")
      }).catch((error) => {
        console.error("act_logUserIn()", error)
      })
    },

    async act_registerUser({commit}, newUser){
      return ResCall.newAccount(newUser).then(({data, status}) => {
        if(checkUser(data) !== null){
          commit("MUT_SAVE_USER", data);
        }
        return messageBasedOnReturnValue(status, "register")
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
