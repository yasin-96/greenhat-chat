<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="400">
      <v-card>
        <v-card-title>
          <v-container class="text-center">
            <h1 class="headline">
              {{ $t('chatView.sidebar.panelLeft.sidebarLeft.sidebarMini.groupDialog.title') }}
            </h1>
          </v-container>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-form ref="reg_form" v-model="validOfFormular">
              <v-text-field
                v-model="newGroupName"
                :label="$t('chatView.sidebar.panelLeft.sidebarLeft.sidebarMini.groupDialog.newTitle')"
                outlined
                shaped
                prepend-inner-icon="mdi-account-group"
                clearable
                required
              ></v-text-field>

              <v-combobox
                v-model="selectedUser"
                :items="userList"
                item-text="userName"
                :label="$t('chatView.sidebar.panelLeft.sidebarLeft.sidebarMini.groupDialog.addUsers')"
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
              </v-combobox>
            </v-form>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error darken-1" text @click="dialog = false">
            {{ $t('actions.close') }}
          </v-btn>
          <v-btn color="green darken-1" text @click="createNewGroup">
            {{ $t('actions.create') }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'NewGroupDialog',
  data: () => ({
    validOfFormular: false,
    selectedUser: [],
    newGroupName: '',
  }),
  computed: {
    ...mapState({
      enableDialog: (state) => state.group.enableWindow,
      userId: (state) => state.user.user.id,
      userList: (state) => state.user.allRegisterdUsers,
    }),
    dialog: {
      get() {
        return this.enableDialog;
      },
      set(tValue) {
        this.$store.dispatch('group/act_toggleNewGroupWindow', tValue);
      },
    },
  },
  methods: {
    async createNewGroup() {
      const userInGroup = this.selectedUser.map((item) => item.userId);
      const newGroup = {
        name: this.newGroupName,
        admin: this.userId,
        users: userInGroup,
      };
      await this.$store.dispatch('group/act_createNewGroup', newGroup);
      this.$store.dispatch('group/act_toggleNewGroupWindow', false);
    },
  },
};
</script>

<style></style>
