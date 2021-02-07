export const umix = {
  methods: {
    setNotification(message, color, icon) {
      this.$store.dispatch('notify/act_setAlterMessage', { message, color, icon });
    },
    /**
     *
     */
    async removeUserFromGroup(userId) {
      console.log(userId);
      if (userId) {
        const removeUserFromGroup = {
          groupId: this.activeGroupId,
          userId: userId,
        };
        let errorStatus = await this.$store.dispatch('group/act_removeUserFromGroup', removeUserFromGroup);
        if (errorStatus === 200) {
          this.$store.dispatch('notify/act_setAlterMessage', {
            message: 'User wurde aus der Liste entfernt',
            color: 'success',
            icon: 'mdi-information-outline',
          });
        } else if (errorStatus >= 500) {
          this.$store.dispatch('notify/act_setAlterMessage', {
            message: errorStatus,
            color: 'error',
            icon: 'mdi-information-outline',
          });
        } else {
          this.$store.dispatch('notify/act_setAlterMessage', {
            message: 'User wurde aus der Liste entfernt',
            color: 'success',
            icon: 'mdi-information-outline',
          });
        }
      }
    },
    /**
     *
     */
    async addUserToGroup() {
      const allUserIds = this.selectedUser.map((user) => user.userId);

      const newUserToGroup = {
        groupId: this.activeGroupId,
        userIds: allUserIds,
      };
      const errorStatus = await this.$store.dispatch('group/act_addUserToGroup', newUserToGroup);
      this.selectedUser = [];
      this.$store.dispatch('settings/act_toggleDialogForAddUserToGroup', false);
      if (errorStatus === 200) {
        this.$store.dispatch('notify/act_setAlterMessage', {
          message: this.$t('chatView.user.editValue.error.202'),
          color: 'success',
          icon: 'mdi-information-outline',
        });
      }
      this.$store.dispatch('notify/act_setAlterMessage', {
        message: errorStatus,
        color: 'error',
        icon: 'mdi-information-outline',
      });
    },
    openDialogForAddingUserToGroup() {
      this.$store.dispatch('settings/act_openDialogForAddUserToGroup');
    },
  },
  computed: {
    /**
     *
     */
    groupAdmin() {
      const group = this.rawGroups.find((group) => group._id == this.activeGroupId);
      if (group) {
        return group.admin;
      }
      return null;
    },
    /**
     *
     */
    isCurrentUserAdminOfGroup() {
      const foundedGroup = this.rawGroups.find((group) => group._id == this.activeGroupId);
      if (foundedGroup) {
        return this.userId == foundedGroup.admin;
      }
      return null;
    },
  },
};
