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
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'GroupUser',
  computed: {
    ...mapState({
      userInGroup: (state) => state.group.activeGroup.users,
    }),
  },
};
</script>

<style></style>
