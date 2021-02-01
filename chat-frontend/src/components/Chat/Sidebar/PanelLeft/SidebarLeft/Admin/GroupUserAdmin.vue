<template>
  <div>
    <v-list class="pl-14" shaped>
      <v-list-item v-for="userg in userInGroup" :key="userg._id">
        <v-list-item-avatar class="d-block " color="sidebarMiniGroupUsersAvatarBackGround" size="32">
          <img v-if="userg.avatarPicture" :src="userg.avatarPicture" />
          <span class="title" v-else> {{ userg.avatarName }} </span>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title>
            {{ userg.username }}
          </v-list-item-title>
        </v-list-item-content>
        <v-list-item-action v-if="userg.userId !== groupAdmin">
          <v-btn color="error" icon text small @click="removeUserFromGroup(userg.userId)">
            <v-icon small>
              mdi-trash-can
            </v-icon>
          </v-btn>
        </v-list-item-action>
      </v-list-item>
    </v-list>
    <v-divider class="mt-5 mb-3"></v-divider>
    <div class="mx-auto">
      <v-btn class="pl-14" block text small color="success" @click="openDialogForAddingUserToGroup"><v-icon> mdi-account-plus</v-icon></v-btn>
    </div>
    <AddUserToGroupAdmin />
  </div>
</template>

<script>

import AddUserToGroupAdmin from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/AddUserToGroupAdmin';

import { mapState } from 'vuex';
export default {
  name: 'GroupUserAdmin',
  components:{
    AddUserToGroupAdmin
  },
  computed: {
    ...mapState({
      activeGroup: (state) => state.group.activeGroupId,
      userInGroup: (state) => state.group.activeGroup.users,
      groups: (state) => state.group.userGroups,
    }),

    groupAdmin(){
      const group = this.groups.find((group) => group._id == this.activeGroup)
      return group.admin
    }
  },
  methods: {
    async removeUserFromGroup(userId) {
      if (userId) {
        const removeUserFromGroup = {
          groupId: this.activeGroup,
          userId: userId,
        };
        let error = await this.$store.dispatch('group/act_removeUserFromGroup', removeUserFromGroup);
        console.log("removeUserFromGroup",error)
        if (error.status === 200) {
          this.$store.dispatch('notify/act_setAlterMessage', {
            message: "User wurde aus der Liste entfernt",
            color: 'success',
            icon: 'mdi-information-outline',
          });
        } else {
          this.$store.dispatch('notify/act_setAlterMessage', {
            message: error.message,
            color: 'error',
            icon: 'mdi-information-outline',
          });
        }
      }
    },
    openDialogForAddingUserToGroup(){
      this.$store.dispatch("settings/act_openDialogForAddUserToGroup")
    }
  },
};
</script>

<style></style>
