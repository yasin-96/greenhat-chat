<template>
  <div v-if="userInGroup && !!userInGroup && userInGroup.length">
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
        <div v-show="isCurrentUserAdminOfGroup">
          <v-list-item-action v-if="userg.userId !== groupAdmin">
            <v-btn color="error" icon text small @click="removeUserFromGroup(userg.userId)">
              <v-icon small>
                mdi-trash-can
              </v-icon>
            </v-btn>
          </v-list-item-action>
        </div>
      </v-list-item>
    </v-list>
    <div v-show="isCurrentUserAdminOfGroup">
      <v-divider class="mt-5 mb-3"></v-divider>
      <div class="mx-auto">
        <v-btn class="pl-14" block text small color="success" @click="openDialogForAddingUserToGroup"
          ><v-icon> mdi-account-plus</v-icon></v-btn
        >
      </div>
      <!-- Dialog to add user to group if current user is admin -->
      <AddUserToGroupAdmin />
    </div>
  </div>
</template>

<script>
import AddUserToGroupAdmin from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/AddUserToGroupAdmin';

import { mapState } from 'vuex';
import { umix } from '@/mixins/umix';
export default {
  name: 'GroupUser',
  mixins: [umix],
  components: {
    AddUserToGroupAdmin,
  },
  computed: {
    ...mapState({
      userInGroup: (state) => state.group.activeGroup.users,
      rawGroups: (state) => state.group.rawGroups,
      userGroups: (state) => state.group.userGroups,
      activeGroupId: (state) => state.group.activeGroupId,
      userId: (state) => state.user.user.id,
    }),
  },
};
</script>

<style></style>
