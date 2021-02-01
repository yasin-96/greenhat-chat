<template>
  <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
    <v-card class="pl-14">
      <v-toolbar flat dark color="dark">
        <v-btn icon dark @click="closeDialog(false)">
          <v-icon color="">mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>
          <h1 class="headline">
            {{ $t('chatView.settings.title') }}
          </h1></v-toolbar-title
        >
        <v-spacer></v-spacer>
      </v-toolbar>
      <v-card-title>
        <v-container>
          <GroupDetailsAdmin />
          <v-divider class="mt-10 mb-10 mx-auto"></v-divider>
          <GroupUserListAdmin />
          <!--
            <UpdatePassword />
            <v-divider width="250px" class="mt-10 mb-10 mx-auto"></v-divider>
            <DeleteAccount /> -->
        </v-container>
        <v-sheet class="pl-14" color="grey lighten-4" height="" width="100%"> </v-sheet>
      </v-card-title>
      <v-card-text> </v-card-text>

      <div style="flex: 1 1 auto;"></div>
    </v-card>
    <AddUserToGroupAdmin />
  </v-dialog>
</template>

<script>
import { mapState } from 'vuex';
import GroupDetailsAdmin from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/GroupDetailsAdmin';
import GroupUserListAdmin from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/GroupUserListAdmin';
import AddUserToGroupAdmin from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/AddUserToGroupAdmin';

export default {
  name: 'GroupSettingsAdmin',
  components: {
    GroupDetailsAdmin,
    GroupUserListAdmin,
    AddUserToGroupAdmin
  },
  methods: {
    closeDialog(value) {
      this.$store.dispatch('settings/act_toggleAdminSettingsDialogForGroup', value);
    },
  },
  computed: {
    ...mapState({
      adminDialog: (state) => state.settings.sidebar.sidebarLeft.admin.adminDialog,
      user: (state) => state.user.user,
    }),
    dialog: {
      get() {
        return this.adminDialog;
      },
      set(value) {
        this.closeDialog(value);
      },
    },
  },
};
</script>

<style></style>
