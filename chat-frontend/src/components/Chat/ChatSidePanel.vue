<template>
  <div>
    <v-navigation-drawer v-if="true" hideOverlay app v-model="uidrawer" width="300">
      <v-navigation-drawer hideOverlay v-model="uidrawer" absolute color="sidebarMiniBackGround" mini-variant>
        <User />
        <AddGroup />
        <v-divider class="mx-3 my-5"></v-divider>
        <OwnGroups />
      </v-navigation-drawer>
      
      <Group />
      <v-spacer class="my-auto"></v-spacer>

      <UserPanelInfo />
    </v-navigation-drawer> 
  </div>
</template>

<script>
import User from '@/components/User/User';
import OwnGroups from '@/components/Chat/OwnGroups';
import UserPanelInfo from '@/components/User/UserPanelInfo.vue';
import Group from '@/components/Chat/Group/Group.vue';
import AddGroup from '@/components/Chat/Group/AddGroup.vue';

import { mapState } from 'vuex';
export default {
  name: 'ChatSidePanel',
  components: {
    User,
    OwnGroups,
    Group,
    AddGroup,
    UserPanelInfo
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
