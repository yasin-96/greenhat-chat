<template>
  <div>
    <v-navigation-drawer v-if="true" hideOverlay app v-model="uidrawer" width="300">
      <SidebarMiniLeft />
      <SidebarLeft />
    </v-navigation-drawer>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import SidebarMiniLeft from '@/components/Chat/Sidebar/PanelLeft/SidebarMini/SidebarMiniLeft';
import SidebarLeft from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/SidebarLeft';

/**
 *
 */
export default {
  name: 'PanelLeft',
  components: {
    SidebarMiniLeft,
    SidebarLeft,
  },
  computed: {
    ...mapState({
      drawer: (state) => state.settings.sidebar.sidebarLeft.sidebarLeftDrawer,
    }),

    /**
     *
     */
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
