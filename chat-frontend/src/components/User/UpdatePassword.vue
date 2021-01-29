<template>
  <v-container>
    <v-card class="mx-auto text-center elevation-4" max-width="500">
      <v-card-title>
        <v-container>
          <v-icon large>mdi-lock-reset</v-icon>
          {{ $t('chatView.user.editValue.changePassword') }}
        </v-container>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-form ref="reg_form" v-model="valid">
            <v-text-field
              v-model="user.oldPassword"
              :label="$t('registrieView.user.password')"
              outlined
              type="password"
              prepend-inner-icon="mdi-lock-check"
              clearable
              :rules="[rules.required]"
              required
            ></v-text-field>
            <v-text-field
              v-model="user.password"
              :label="$t('registrieView.user.password')"
              type="password"
              outlined
              prepend-inner-icon="mdi-lock"
              clearable
              :rules="[rules.required, rules.password(user.password, repeatPasswd)]"
            ></v-text-field>
            <v-text-field
              clearable
              outlined
              v-model="repeatPasswd"
              :label="$t('registrieView.user.repeatPassword')"
              type="password"
              prepend-inner-icon="mdi-lock-alert"
              :rules="[rules.required, rules.password(repeatPasswd, user.password)]"
              required
            ></v-text-field>
          </v-form>
        </v-container>
      </v-card-text>
      <v-card-actions>
          <v-btn large block color="error" :disabled="!valid && !isPasswordValid" @click="registerUser">
            {{ $t('actions.change') }}
          </v-btn>
        </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'UpdatePassword',
  data: () => ({
    valid:false,
    repeatPasswd: '',
    showPasswd: false,
    user: {
      oldPassword: '',
      email: '',
      password: '',
    },
    rules: {
      required: (value) => (value && !!value) || 'Das Feld ist erforderlich.',
      email: (value) => {
        const pattern = /^([a-zA-z0-9]+((\.[a-zA-z0-9]+)*)|(".+"))$/;
        return pattern.test(value) || 'Email passt nicht zur Domaine';
      },
      password: (value, checkWiththis) => {
        let check = false;
        if (value === checkWiththis && !!value && !!checkWiththis) {
          check = true;
        } else {
          check = false;
        }
        // const status = this.checkPassword();
        console.log('status', check);
        return check || 'Passwörter stimmen nicht überein!';
      },
    },
  }),
  methods: {
    changeLanguage(localLang, index) {
      this.$i18n.locale = localLang;
      this.$store.dispatch('settings/act_changeLanguage', index);
    },
  },
  computed: {
    ...mapState({
      languages: (state) => state.settings.languages,
    }),
  },
};
</script>

<style></style>
