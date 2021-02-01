<template>
  <div class="text-center">
    <v-avatar v-if="homeLogo" class="rounded mt-4" color="transparent" size="36" @click="loadUserInfo()">
      <img src="@/assets/logo_02.svg" alt="User" class="elevation-6" />
    </v-avatar>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'Home',
  methods: {
    loadUserInfo() {},
  },
  computed: {
    ...mapState({
      homeLogo: (state) => state.settings.sidebar.sidebarMini.homeLogo,
      user: (state) => state.user.user,
    }),
  },
};
</script>

<style></style>
