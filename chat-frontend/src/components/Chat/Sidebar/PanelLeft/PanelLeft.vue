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

export default {
  name: 'PanelLeft',
  components: {
    SidebarMiniLeft,
    SidebarLeft
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
