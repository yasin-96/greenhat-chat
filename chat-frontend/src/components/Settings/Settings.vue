<template>
  <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
    <v-card class="pl-14">
      <v-toolbar flat dark color="dark">
        <v-btn icon dark @click="dialog = false">
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
          <UserDetails />
          <v-divider class="mt-10 mb-10 mx-auto"></v-divider>
          <ThemeSwitcher />
          <v-divider width="250px" class="mt-10 mb-10 mx-auto"></v-divider>
          <LanguageSwitcher />
          <v-divider width="250px" class="mt-10 mb-10 mx-auto"></v-divider>
          <UpdatePassword />
          <v-divider width="250px" class="mt-10 mb-10 mx-auto"></v-divider>
          <DeleteAccount />
        </v-container>
        <v-sheet class="pl-14" color="grey lighten-4" height="" width="100%"> </v-sheet>
      </v-card-title>
      <v-card-text> </v-card-text>

      <div style="flex: 1 1 auto;"></div>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapState } from 'vuex';
import UserDetails from '@/components/User/UserDetails';
import ThemeSwitcher from '@/components/Settings/Theme/ThemeSwitcher';
import LanguageSwitcher from '@/components/Settings/LanguageSwitcher';
import UpdatePassword from '@/components/User/UpdatePassword';
import DeleteAccount from '@/components/User/DeleteAccount';

/**
 *
 */
export default {
  name: 'Settings',
  components: {
    UserDetails,
    ThemeSwitcher,
    LanguageSwitcher,
    UpdatePassword,
    DeleteAccount,
  },
  computed: {
    ...mapState({
      settingsDialog: (state) => state.settings.enableDialog,
      user: (state) => state.user.user,
    }),
    /**
     * 
     */
    dialog: {
      get() {
        return this.settingsDialog;
      },
      set(value) {
        this.$store.dispatch('settings/act_openSettingsDialogWithValue', value);
      },
    },
  },
};
</script>
