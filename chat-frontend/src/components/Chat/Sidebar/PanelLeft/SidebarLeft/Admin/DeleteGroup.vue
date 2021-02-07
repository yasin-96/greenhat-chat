<template>
  <v-container>
    <v-card class="mx-auto text-center elevation-4" max-width="500">
      <v-card-title>
        <v-container>
          <v-icon large>mdi-heart-broken-outline</v-icon>
          {{ $t('chatView.user.editValue.reallyDeleteAccount') }}
        </v-container>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-dialog v-model="dialog" persistent transition="dialog-bottom-transition" max-width="400">
            <template v-slot:activator="{ on, attrs }">
              <v-btn v-bind="attrs" v-on="on" block large color="error">
                {{ $t('chatView.user.editValue.deleteAccount') }}
              </v-btn>
            </template>
            <v-card>
              <v-card-text>
                <div class="text-h2 pa-12">{{ $t('chatView.user.editValue.lastQuestionBeforeDeleteAccount') }}</div>
              </v-card-text>
              <v-card-actions>
                <v-btn x-large color="primary" @click="dialog = false">{{ $t('actions.abort') }}</v-btn>
                <v-spacer></v-spacer>
                <v-btn large color="error" text @click="removeAccount">
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
        _id: this.userId,
      };
      let resp = await this.$store.dispatch('group/act_deleteGroup', gid);
      console.log('In Comp', resp);
      if (resp === 200) {
        //user state is cleared after request serrver to close account
        this.$store.dispatch('chat/act_clearChatState');
        this.$store.dispatch('group/act_clearGroupState');
        this.$store.dispatch('game/act_clearGameState');
        this.$store.dispatch('notify/act_clearNotificationState');
        window.localStorage.removeItem(this.localStorageKey);
        
        this.$store.dispatch('notify/act_setAlterMessage', {
          message: this.$t('chatView.user.editValue.deleteInfo'),
          color: 'success',
          icon: 'mdi-information-outline',
        });
      }

      if (resp === 404) {
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
      deleteRequestWindow: (state) => state.user.editOptions.displayDeleteWindow,
    }),
    dialog: {
      get() {
        return this.deleteRequestWindow;
      },
      set(value) {
        this.$store.dispatch('group/act_toggleCloseGroupWindow', value);
      },
    },
  },
};
</script>

<style></style>
