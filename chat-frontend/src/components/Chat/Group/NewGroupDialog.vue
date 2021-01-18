<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="400">
      <v-card>
        <v-card-title>
          <v-container class="text-center">
            <h1 class="headline">
              Create New Group
            </h1>
          </v-container>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-form ref="reg_form" v-model="validOfFormular">
              <v-text-field
                v-model="newGroupName"
                :label="label.groupTitle"
                outlined
                shaped
                prepend-inner-icon="mdi-account-group"
                clearable
                required
              ></v-text-field>

              <v-combobox v-model="selectedUser" :items="items" :label="label.addUser" outlined multiple chips>
                <template v-slot:selection="data">
                  <v-chip
                    :key="JSON.stringify(data.item)"
                    v-bind="data.attrs"
                    :input-value="data.selected"
                    :disabled="data.disabled"
                    @click:close="data.parent.selectItem(data.item)"
                  >
                    <v-avatar class="accent white--text" left v-text="data.item.slice(0, 1).toUpperCase()"></v-avatar>
                    {{ data.item }}
                  </v-chip>
                </template>
              </v-combobox>
            </v-form>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error darken-1" text @click="dialog = false">
            Close
          </v-btn>
          <v-btn color="green darken-1" text @click="createNewGroup">
            Create
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
    selectedUser: ['Vuetify', 'Programming'],
    items: ['Programming', 'Design', 'Vue', 'Vuetify'],
    newGroupName: '',
    label: {
      groupTitle: 'New Group',
      addUser: 'Add Users',
    },
  }),
  computed: {
    ...mapState({
      enableDialog: (state) => state.group.enableWindow,
      userId: (state) => state.user.user.id,
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
      const newGroup = {
        name: this.newGroupName,
        admin: this.userId,
        users: this.selectedUser,
      };
      await this.$store.dispatch("group/act_createNewGroup", newGroup)
      this.$store.dispatch('group/act_CloseNewGroupWindow');
    },
  },
};
</script>

<style></style>
