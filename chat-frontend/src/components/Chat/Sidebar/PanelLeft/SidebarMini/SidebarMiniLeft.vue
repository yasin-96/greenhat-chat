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
import Home from '@/components/Chat/Sidebar/PanelLeft/SidebarMini/Home';
import AddGroup from '@/components/Chat/Sidebar/PanelLeft/SidebarMini/AddGroup';
import OwnGroups from '@/components/Chat/Sidebar/PanelLeft/SidebarMini/OwnGroups';
import { mapState } from 'vuex';

/**
 *
 */
export default {
  name: 'SidebarMiniLeft',
  components: {
    Home,
    AddGroup,
    OwnGroups,
  },

  computed: {
    ...mapState({
      drawer: (state) => state.settings.sidebar.sidebarLeft.sidebarLeftDrawer,
    }),

    uidrawer: {
      get() {
        return this.drawer;
      },
      set(value) {
        this.$store.dispatch('settings/act_toggleSidePanelWithValue', value);
      },
    },
  },
};
</script>

<style></style>
