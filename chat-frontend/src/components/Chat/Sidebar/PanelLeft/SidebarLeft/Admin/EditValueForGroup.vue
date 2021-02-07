<template>
  <v-row justify="center">
    <v-dialog v-model="showWindow" persistent max-width="600">
      <v-card>
        <v-card-title class="headline">
          <h1 class="headline">
            {{ $t('chatView.sidebar.sidebarLeft.admin.editValue.title') }}
          </h1>
          <span class="ml-2"
            ><h1 class="title">{{ editOpions.title }}</h1></span
          >
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation v-if="infoType < 2">
            <v-container>
              <v-text-field :value="editOpions.old" :label="`Old-${editOpions.title}`" disabled filled></v-text-field>

              <v-select
                v-if="editOpions.infoType == groupInfo.GROUP_ADMIN"
                v-model="updatedValue"
                :items="filteredListWithoutCurrentAdmin"
                item-text="userName"
                item-value="userId"
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
          <v-container v-else>
            <v-text-field :value="editOpions.old" :label="`Old-${editOpions.title}`" disabled filled>
              <template v-slot:append-outer class="my-auto">
                <v-avatar :color="editOpions.old" class="ma-0 pa-0"></v-avatar>
              </template>
            </v-text-field>

            <v-card outlined>
              <v-card-text>
                <v-color-picker
                  width="500"
                  canvas-height="80"
                  canvas-widht="100"
                  v-model="updatedValue"
                  class="ma-2"
                  show-swatches
                  swatches-max-height="50px"
                ></v-color-picker> </v-card-text
              ><v-card-actions>
                <v-container>
                  <v-text-field
                    v-model="updatedValue"
                    outlined
                    :label="`New-${editOpions.title}`"
                    disabled
                    required
                    :color="updatedValue"
                  >
                  </v-text-field>
                </v-container>
              </v-card-actions>
            </v-card>
          </v-container>
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
  }),
  methods: {
    /**
     *
     */
    async updateGroupInformationen() {
      let errorStatus = null;
      switch (this.infoType) {
        case 0:
          errorStatus = await this.$store.dispatch('group/act_updateSpecificGroupInformationen', {
            _id: this.activeGroupId,
            update: {
              name: this.updatedValue,
            },
          });
          break;
        case 1:
          errorStatus = await this.$store.dispatch('group/act_updateSpecificGroupInformationen', {
            _id: this.activeGroupId,
            update: {
              admin: this.updatedValue,
            },
          });
          break;
        case 2:
          errorStatus = await this.$store.dispatch('group/act_updateSpecificGroupInformationen', {
            _id: this.activeGroupId,
            update: {
              groupColor: this.updatedValue,
            },
          });
          break;
        default:
          break;
      }
      this.updatedValue = '';
      if (errorStatus === 200) {
        this.$store.dispatch('notify/act_setAlterMessage', {
          message: this.$t('chatView.user.editValue.error.202'),
          color: 'success',
          icon: 'mdi-information-outline',
        });
        this.$store.dispatch('settings/act_toggleAdminSettingsDialogForGroup', false);
      } else {
        this.$store.dispatch('notify/act_setAlterMessage', {
          message: errorStatus,
          color: 'error',
          icon: 'mdi-information-outline',
        });
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
      activeGroupId: (state) => state.group.activeGroupId,
    }),

    /**
     *
     */
    showWindow: {
      get() {
        return this.enableWindow;
      },
      set(toggleValue) {
        this.$store.dispatch('settings/act_toggleDialogForChangeValueForGroup', toggleValue);
      },
    },
    /**
     *
     */
    filteredListWithoutCurrentAdmin() {
      if (this.allRegisterdUsers && !!this.allRegisterdUsers && this.allRegisterdUsers.length) {
        return this.allRegisterdUsers.filter((user) => user.userId !== this.userId);
      }
      return null;
    },
  },
};
</script>

<style></style>
