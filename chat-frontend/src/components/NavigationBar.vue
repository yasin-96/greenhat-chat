<template>
  <v-app-bar app>
    <v-btn v-if="checkRoute" icon @click="toggleSidePanel">
      <v-icon v-if="uidrawer">mdi-menu</v-icon>
      <v-icon v-else>mdi-dots-vertical</v-icon>
    </v-btn>
    <div class="d-flex align-center">>
      <v-img
        alt="Greenhat Logo"
        class="shrink mr-2"
        contain
        src="@/assets/logo_02.svg"
        transition="scale-transition"
        width="40"
      />
    </div>

    <v-spacer></v-spacer>
    <v-responsive max-width="156">
      <v-text-field dense flat hide-details rounded solo-inverted></v-text-field>
    </v-responsive>
  </v-app-bar>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'NavigationBar',
  methods: {
    toggleSidePanel(value) {
      this.$store.dispatch('sidePanel/act_toggleSidePanel', value);
    },
  },
  computed: {
    ...mapState({
      drawer: (state) => state.sidePanel.drawer,
    }),

    uidrawer: {
      get() {
        return this.drawer;
      },
      set() {
        this.toggleSidePanel();
      },
    },
    checkRoute() {
      return this.$route.name === 'LoginView' || this.$route.name === 'RegisterView' ? false : true;
    },
  },
};
</script>

<style></style>
