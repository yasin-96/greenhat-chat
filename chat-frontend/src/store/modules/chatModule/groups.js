import RestCall from '@/services/RestCall';


const API_BASE_PATHS = {
  group: '/group',
};

const API_PATHS = {
  user: {
    create: API_BASE_PATHS.user + '/new',
  },
};

const groupModule = {
  namespaced: true,
  state: () => ({
    enableWindow: false,
    groups: [
      {
        _id: '2',
        groupName: 'LL',
        admin: 'Alex',
        userIDs: [],
      },
      {
        _id: '3',
        groupName: 'D1asdja',
        admin: 'Dennis',
        userIDs: [],
      },
      {
        _id: '1',
        groupName: 'MS',
        admin: 'Musatfa',
        userIDs: [],
      },
      {
        _id: '4',
        groupName: 'YA',
        admin: 'Yasin',
        userIDs: [],
      },
    ],
  }),
  actions: {
    act_toggleNewGroupWindow({ commit }, toggleValue) {
      commit('MUT_TOGGLE_NEW_GROUP_WINDOW', toggleValue);
    },
    act_closeNewGroupWindow({ commit }) {
      commit('MUT_CLOSE_NEW_GROUP_WINDOW');
    },
    async act_createNewGroup({ commit }, newGroup) {
      await RestCall.rcRequest(API_PATHS.group.create, 'post', null, newGroup)
        .then(({ data }) => {
          console.log('act_updateSpecificUserInformationen() res: ', data);
          if (data !== null) {
            commit('MUT_SAVE_USER', data);
          }
        })
        .catch((error) => {
          console.log('act_updateSpecificUserInformationen(): ', error);
        });
    },
  },
  mutations: {
    MUT_TOGGLE_NEW_GROUP_WINDOW(state, tValue) {
      state.enableWindow = tValue;
    },
    MUT_CLOSE_NEW_GROUP_WINDOW(state) {
      state.enableWindow = false;
    },
    MUT_SAVE_GROUP(state, group){
      // if(state.groups && !!state.groups){

      // }
      state.groups.push(group)
    }
  },
};

export default groupModule;
