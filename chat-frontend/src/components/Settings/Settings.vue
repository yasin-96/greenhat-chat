<template>
  <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
    <!-- <SettingsSidePanel /> -->
    <v-card class="pl-14">
      <v-toolbar flat dark color="dark">
        <v-btn icon dark @click="closeDialog(false)">
          <v-icon color="">mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>
          <h1 class="headline">
            Mein Konto
          </h1></v-toolbar-title
        >
        <v-spacer></v-spacer>

        <!-- <v-toolbar-items>
              <v-btn dark text @click="dialog = false">
                Speichern
              </v-btn>
            </v-toolbar-items> -->
      </v-toolbar>
      <v-card-title>
        <v-container>
          <UserInfo />
        </v-container>
        <div class="mx-auto text-center">
          <v-btn depressed rounded text>
            Edit Account
          </v-btn>
        </div>
        <v-sheet class="pl-14" color="grey lighten-4" height="" width="100%"> </v-sheet>
      </v-card-title>
      <v-card-text> </v-card-text>

      <div style="flex: 1 1 auto;"></div>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapState } from 'vuex';
// import SettingsSidePanel from '@/components/Settings/SettingsSidePanel';
import UserInfo from '@/components/User/UserInfo';
export default {
  name: 'Settings',
  components: {
    // SettingsSidePanel,
    UserInfo,
  },
  methods: {
    closeDialog(value) {
      this.$store.dispatch('settings/act_openSettingsDialogWithValue', value);
    },
  },
  computed: {
    ...mapState({
      settingsDialog: (state) => state.settings.enableDialog,
      user: (state) => state.user.user,
    }),
    dialog: {
      get() {
        return this.settingsDialog;
      },
      set(value) {
        this.closeDialog(value);
      },
    },
  },
};
</script>

<style></style>
