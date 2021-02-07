<template>
  <v-container>
    <v-card class="mx-auto text-center elevation-4" max-width="500">
      <v-card-title>
        <v-container>
          <v-icon large>mdi-heart-broken-outline</v-icon>
          {{ $t('chatView.sidebar.sidebarLeft.admin.editValue.reallyDeleteGroup') }}
        </v-container>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-dialog v-model="dialog" persistent transition="dialog-bottom-transition" max-width="400">
            <template v-slot:activator="{ on, attrs }">
              <v-btn v-bind="attrs" v-on="on" block large color="error">
                {{ $t('chatView.sidebar.sidebarLeft.admin.editValue.deleteGroup') }}
              </v-btn>
            </template>
            <v-card>
              <v-card-text>
                <div class="text-h2 pa-12">{{ $t('chatView.sidebar.sidebarLeft.admin.editValue.lastQuestionBeforeDeleteGroup') }}</div>
              </v-card-text>
              <v-card-actions>
                <v-btn x-large color="primary" @click="dialog = false">{{ $t('actions.abort') }}</v-btn>
                <v-spacer></v-spacer>
                <v-btn large color="error" text @click="removeGroup">
                  {{ $t('actions.close') }}
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-container>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'DeleteGroup',
  methods: {
    async removeGroup() {
      const gid = {
        _id: this.groupId,
      };
      let errorStatus = await this.$store.dispatch('group/act_deleteGroup', gid);
      if (errorStatus === 200) {
         this.$store.dispatch('notify/act_setAlterMessage', {
          message: this.$t(`errors.admin.group.deleteGroup.${errorStatus}`),
          color: 'success',
          icon: 'mdi-information-outline',
        });
        //user state is cleared after request serrver to close account
        //TODO remove group from state
        this.$store.dispatch('settings/act_toggleAdminSettingsDialogForGroup', false);


      } else if(errorStatus >= 500){
         this.$store.dispatch('notify/act_setAlterMessage', {
          message: this.$t(`errors.admin.group.deleteGroup.${errorStatus}`),
          color: 'warning',
          icon: 'mdi-information-outline',
        });
      } else{
         this.$store.dispatch('notify/act_setAlterMessage', {
          message: this.$t('chatView.user.editValue.error.404'),
          color: 'warning',
          icon: 'mdi-information-outline',
        });
      }
    },
  },
  computed: {
    ...mapState({
      localStorageKey: (state) => state.settings.localStorageKey,
      groupId: (state) => state.group.activeGroupId,
      languages: (state) => state.settings.languages,
      deleteGroupRequestWindow: (state) => state.settings.sidebar.sidebarLeft.admin.group.deleteGroup,
    }),
    /**
     * 
     */
    dialog: {
      get() {
        return this.deleteGroupRequestWindow;
      },
      set(value) {
        this.$store.dispatch('settings/act_toggleDialogForDeletingGroup', value);
      },
    },
  },
};
</script>

<style></style>
