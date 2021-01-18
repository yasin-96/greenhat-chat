<template>
  <div>
    <v-avatar v-if="user.hasAvatarPicture" class="d-block tile mx-auto rounded mt-4" color="dark" size="36" @click="loadUserInfo()">
        <img
          :src="user.avatarPicture"
          alt="User"
          class="elevation-6"
        />
      </v-avatar>
     <v-avatar v-else class="d-block tile mx-auto rounded mt-4" color="green darken-1" size="36" @click="loadUserInfo()">
      <span class="white--text title">{{ user.avatarName }}</span>
    </v-avatar>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'User',
  methods: {
    loadUserInfo() {},
  },
  computed: {
    ...mapState({
      user: (state) => state.user.user
    }),
  },
};
</script>

<style></style>
