const groupModule = {
  namespaced: true,
  state: () => ({
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
  actions: {},
  mutations: {},
};

export default groupModule;
