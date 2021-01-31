<template>
  <v-row justify="center">
    <v-dialog v-model="showWindow" persistent max-width="600">
      <v-card>
        <v-card-title class="headline">
          <h1 class="headline">
            {{ $t('chatView.user.editValue.title') }}
          </h1>
          <span class="ml-2"
            ><h1 class="title">{{ editOpions.title }}</h1></span
          >
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-container>
              <v-text-field :value="editOpions.old" :label="`Old-${editOpions.title}`" disabled filled></v-text-field>

              <v-select
                v-if="editOpions.infoType == groupInfo.GROUP_ADMIN"
                v-model="newSelectedAdmin"
                :items="filteredListWithoutCurrentAdmin"
                item-text="userName"
                :label="`New-${editOpions.title}`"
                outlined
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
              </v-select>

              <v-text-field
                v-else
                v-model="updatedValue"
                outlined
                :label="`New-${editOpions.title}`"
                required
                clearable
              ></v-text-field>
            </v-container>
          </v-form>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error darken-1" text @click="showWindow = false">
            {{ $t('actions.close') }}
          </v-btn>
          <v-btn color="success darken-1" text @click="updateGroupInformationen">
            {{ $t('actions.save') }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'EditValueForGroup',
  data: () => ({
    valid: false,
    groupInfo: {
      GROUP_NAME: 0,
      GROUP_ADMIN: 1,
    },
    updatedValue: '',
    newSelectedAdmin: '',
    dialog: false,
  }),
  methods: {
    async updateGroupInformationen() {
      this.showWindow = false;
      console.log('updateUserInformationen', this.infoType);
      switch (this.infoType) {
        case 0:
          //   await this.$store.dispatch('user/act_updateSpecificUserInformationen', {
          //     _id: this.userId,
          //     update: { email: this.updatedValue + this.editOpions.suffix },
          //   });
          break;
        case 1:
          //   await this.$store.dispatch('user/act_updateSpecificUserInformationen', {
          //     _id: this.userId,
          //     update: { username: this.updatedValue },
          //   });
          break;
        default:
          break;
      }
      this.showWindow = false;
    },
  },
  computed: {
    ...mapState({
      userId: (state) => state.user.user.id,
      allRegisterdUsers: (state) => state.user.allRegisterdUsers,
      editOpions: (state) => state.group.editOptions,
      enableWindow: (state) => state.settings.sidebar.sidebarLeft.admin.group.editValue,
      infoType: (state) => state.group.editOptions.infoType,
    }),

    showWindow: {
      get() {
        return this.enableWindow;
      },
      set(toggleValue) {
        this.$store.dispatch('settings/act_toggleDialogForChangeValueForGroup', toggleValue);
      },
    },
    filteredListWithoutCurrentAdmin() {
      return this.allRegisterdUsers.filter((user) => user.userId !== this.userId);
    },
  },
};
</script>

<style></style>
