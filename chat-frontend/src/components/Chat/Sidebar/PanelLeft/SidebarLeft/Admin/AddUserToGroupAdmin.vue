<template>
  <v-dialog v-model="dialog" transition="dialog-top-transition" max-width="500">
    <v-card>
      <v-toolbar flat class="mb-5" color="primary"
        >{{ $t('chatView.sidebar.sidebarLeft.admin.addUserToGroup') }}
        <v-spacer></v-spacer>
        <v-btn text @click="closeDialog">
          <v-icon>mdi-close</v-icon>
        </v-btn>
      </v-toolbar>
      <v-card-text>
        <v-combobox
          v-model="selectedUser"
          :items="filteredListWithoutAdminAndUsersInGroup"
          item-text="userName"
          :label="$t('chatView.sidebar.sidebarLeft.sidebarMini.groupDialog.addUsers')"
          outlined
          multiple
          chips
        >
          <template v-slot:selection="data">
            <v-chip
              :key="JSON.stringify(data.item)"
              v-bind="data.attrs"
              :input-value="data.selected"
              :disabled="data.disabled"
              @click:close="data.parent.selectItem(data.item)"
            >
              <v-avatar
                class="accent white--text"
                left
                v-text="data.item.userName.slice(0, 1).toUpperCase()"
              ></v-avatar>
              {{ data.item.userName }}
            </v-chip>
          </template>
          <template v-slot:append-outer>
            <v-icon large color="success" @click="addUserToGroup">mdi-content-save-move</v-icon>
          </template>
        </v-combobox>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapState } from 'vuex';
import { umix } from '@/mixins/umix';
export default {
  name: 'AddUserToGroupAdmin',
  mixins: [umix],
  data: () => ({
    selectedUser: [],
  }),
  computed: {
    ...mapState({
      activeGroup: (state) => state.group.activeGroup,
      activeGroupId: (state) => state.group.activeGroupId,
      userId: (state) => state.user.user.id,
      userList: (state) => state.user.allRegisterdUsers,
      addUserToGroupDialog: (state) => state.settings.sidebar.sidebarLeft.admin.group.addUserToGroupDialog,
    }),

    /**
     *
     */
    dialog: {
      get() {
        return this.addUserToGroupDialog;
      },
      set(value) {
        this.$store.dispatch('settings/act_toggleDialogForAddUserToGroup', value);
      },
    },
    /**
     *
     */
    allUserIdsInGroup() {
      if (this.activeGroup && !!this.activeGroup && this.activeGroup.length) {
        return this.activeGroup.users.map((user) => user.userId);
      }
      return null;
    },
    /**
     *
     */
    filteredListWithoutAdminAndUsersInGroup() {
      if (this.userList && !!this.userList && this.userList.length) {
        return this.userList.filter(
          (user) => !this.allUserIdsInGroup.includes(user.userId) && user.userId !== this.userId
        );
      }
      return null;
    },
  },
  methods: {
    /**
     *
     */
    closeDialog() {
      this.dialog = false;
      this.selectedUser = [];
    },
  },
};
</script>

<style></style>
