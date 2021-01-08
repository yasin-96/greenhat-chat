<template>
  <div>
    <v-avatar class="d-block tile mx-auto rounded mt-4" color="green darken-1" size="36" @click="loadUserInfo()">
      <span class="white--text title">{{ userAvatar }}</span>
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
      userAvatar: (state) => state.user.avatar,
    }),
  },
};
</script>

<style></style>
