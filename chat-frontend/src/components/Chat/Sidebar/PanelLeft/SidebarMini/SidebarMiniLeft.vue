<template>
  <v-navigation-drawer hideOverlay v-model="uidrawer" absolute color="sidebarMiniBackGround" mini-variant>
    <!-- <User /> -->
    <Home />
    <AddGroup />
    <v-divider class="mx-3 my-5"></v-divider>
    <OwnGroups />
  </v-navigation-drawer>
</template>

<script>

import Home from "@/components/Chat/Sidebar/PanelLeft/SidebarMini/Home";
// import User from "@/componeknts/Chat/Sidebar/PanelLeft/SidebarMini/User";
import AddGroup from "@/components/Chat/Sidebar/PanelLeft/SidebarMini/AddGroup";
import OwnGroups from "@/components/Chat/Sidebar/PanelLeft/SidebarMini/OwnGroups";
import { mapState } from "vuex";

export default {
  name: 'SidebarMiniLeft',
  components:{
    Home,
      // User,
      AddGroup,
      OwnGroups
  },
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
