<template>
  <div>
    <v-avatar class="d-block tile mx-auto rounded mt-4" color="sidebarMiniAddGroupButton" size="36">
      <v-icon color="sidebarMiniAddGroupButtonIcon" @click="openDialog">mdi-plus</v-icon>
    </v-avatar>
    <NewGroupDialog />
  </div>
</template>

<script>
import NewGroupDialog from '@/components/Chat/Sidebar/PanelLeft/SidebarMini/NewGroupDialog';

/**
 *
 */
export default {
  name: 'AddGroup',
  components: {
    NewGroupDialog,
  },
  methods: {
    /**
     *
     */
    openDialog() {
      this.$store.dispatch('group/act_toggleNewGroupWindow', true);
    },
  },
};
</script>

<style></style>
