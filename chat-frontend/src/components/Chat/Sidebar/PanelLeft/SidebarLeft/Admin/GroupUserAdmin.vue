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
        <v-list-item-action>
          <v-btn color="error" icon text small>
            <v-icon small>
              mdi-trash-can
            </v-icon>
          </v-btn>
        </v-list-item-action>
      </v-list-item>
    </v-list>
    <v-divider class="mt-5 mb-3"></v-divider>
    <div class="mx-auto">
      <v-btn class="pl-14" block text small color="success"><v-icon> mdi-account-plus</v-icon></v-btn>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'GroupUserAdmin',
  computed: {
    ...mapState({
      activeGroup: (state) => state.group.activeGroupId,
      userInGroup: (state) => state.group.activeGroup.users,
    }),
  },
  methods: {
    removeUserFromGroup(userId) {
      const removeUserFromGroup = {
        groupId: this.activeGroup,
        userId: userId,
      };

      this.$store.dispatch('user/act_removeUserFromGroup', removeUserFromGroup);
    },
  },
};
</script>

<style></style>
