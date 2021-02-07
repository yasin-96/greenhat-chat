<template>
  <div>
    <v-container class="ma-10">
      <Info />
    </v-container>

    <v-container fluid class="justify-center justify-sm-center justify-md-center justify-lg-center justify-xl-center">
      <v-card elevation="2" outlined class="mx-auto my-auto" max-width="400">
        <v-card-title class="justify-center justify-sm-center justify-md-center justify-lg-center justify-xl-center">
          <v-container>
            <h2 class="text-center display-1">{{ $t('loginView.title') }}</h2>
          </v-container>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-form ref="reg_login" v-model="valid">
              <v-text-field
                v-model="user.username"
                :label="$t('loginView.user.username')"
                outlined
                prepend-inner-icon="mdi-account-circle"
                clearable
                :rules="[rules.required, rules.notEmpty]"
                required
              ></v-text-field>
              <v-text-field
                v-model="user.password"
                :label="$t('loginView.user.password')"
                :type="showPasswd ? 'text' : 'password'"
                prepend-inner-icon="mdi-lock"
                :append-icon="showPasswd ? 'mdi-eye-outline' : 'mdi-eye-off-outline'"
                @click:append="showPasswd = !showPasswd"
                outlined
                clearable
                :rules="[rules.required, rules.notEmpty]"
                required
                @keyup.enter="login"
              ></v-text-field>
            </v-form>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-btn dense small color="primary" text :link="true" to="/register">
            {{ $t('loginView.createAccount') }}
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="success" :disabled="!valid" @click="login">
            {{ $t('actions.login') }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import Info from '@/components/Info';
import { umix } from '@/mixins/umix';

export default {
  name: 'Login',
  mixins: [umix],
  components: {
    Info,
  },
  data: () => ({
    /**
     * 
     */
    user: {
      username: '',
      password: '',
    },
    /**
     * 
     */
    showPasswd: false,
    /**
     * 
     */
    valid: false,
    /**
     * 
     */
    rules: {
      required: (value) => (value && !!value) || 'Das Feld ist erforderlich.',
      notEmpty: (value) => value !== null || value !== undefined || 'Feld darf nicht leer bleiben',
    },
  }),
  methods: {
    /**
     * 
     */
    async login() {
      const error = await this.$store.dispatch('user/act_logUserIn', this.user);

      if (error.status == 200) {
        this.$router.push({ path: '/chat' });
      } else if (error.status >= 500) {
        this.$store.dispatch('notify/act_setAlterMessage', {
          message: this.$t(`errors.${error.status}`),
          color: 'error',
          icon: 'mdi-information-outline',
        });
      } else {
        this.$store.dispatch('notify/act_setAlterMessage', {
          message: this.$t(`errors.loginView.${error.status}`),
          color: 'warning',
          icon: 'mdi-information-outline',
        });
      }
    },
  },
};
</script>

<style></style>
