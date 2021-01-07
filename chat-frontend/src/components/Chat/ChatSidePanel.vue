<template>
  <v-navigation-drawer hideOverlay app v-model="uidrawer" width="300">
    <v-navigation-drawer hideOverlay v-model="uidrawer" absolute color="grey lighten-3" mini-variant>
      <v-avatar class="d-block text-center mx-auto mt-4" color="grey darken-1" size="36"></v-avatar>
      <v-divider class="mx-3 my-5"></v-divider>
      <OwnGroups />
    </v-navigation-drawer>

    <GroupInfo/>

    <v-list class="pl-14" shaped>
      <v-list-item v-for="n in 5" :key="n">
        <v-avatar :key="n" class="d-block " color="grey lighten-1" size="28">
          <img :src="`http://i.pravatar.cc/${n * 10}`" />
        </v-avatar>
        <span class="pl-2"> Item {{ n }} </span>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
import OwnGroups from '@/components/Chat/OwnGroups';
import GroupInfo from '@/components/Chat/GroupInfo';
import { mapState } from 'vuex';
export default {
  name: 'ChatSidePanel',
  components: {
    OwnGroups,
    GroupInfo,
  },
  data: () => ({}),
  methods: {
    toggleSidePanelWithValue(value) {
      this.$store.dispatch('sidePanel/act_toggleSidePanelWithValue', value);
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
      set(value) {
        this.toggleSidePanelWithValue(value);
      },
    },
  },
};
</script>

<style></style>
