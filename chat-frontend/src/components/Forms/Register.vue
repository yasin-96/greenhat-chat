<template>
  <div>
    <v-container class="ma-10">
      <Info />
    </v-container>
    <v-container class="justify-center justify-sm-center justify-md-center justify-lg-center justify-xl-center">
      <v-card elevation="2" class="mx-auto my-auto" max-width="400">
        <v-card-title>
          <v-container>
            <h2 class="text-center display-1">{{ $t('registrieView.title') }}</h2>
          </v-container>
        </v-card-title>

        <v-card-text>
          <v-container>
            <v-form ref="reg_form" v-model="valid">
              <v-text-field
                v-model="user.username"
                :label="$t('registrieView.user.username')"
                outlined
                prepend-inner-icon="mdi-account-circle"
                clearable
                :rules="[rules.required]"
                required
              ></v-text-field>
              <v-text-field
                v-model="user.email"
                :label="$t('registrieView.user.email')"
                outlined
                prepend-inner-icon="mdi-account-circle"
                clearable
                :rules="[rules.required, rules.email]"
                required
                :suffix="domainName"
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
          <v-btn dense small color="primary" text :link="true" to="/login">
            {{ $t('registrieView.signIn') }}
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="success" :disabled="!valid && !isPasswordValid" @click="registerUser">
            {{ $t('actions.create') }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import Info from '@/components/Info';
import { umix } from '@/mixins/umix';

/**
 * 
 */
export default {
  name: 'Register',
  mixins: [umix],
  components: {
    Info,
  },
  data: () => ({
    valid: false,
    /**
     * 
     */
    isPasswordValid: false,
    /**
     * 
     */
    domainName: '@greenhat.de',
    /**
     * 
     */
    user: {
      username: '',
      email: '',
      password: '',
    },
    repeatPasswd: '',
    /**
     * Option to show or hide password
     */
    showPasswd: false,
    /**
     * 
     */
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
    /**
     * 
     */
    async registerUser() {
      let copyOfUser = Object.assign({}, this.user);
      copyOfUser.email = copyOfUser.email + this.domainName;

      const error = await this.$store.dispatch('user/act_registerUser', copyOfUser);

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
          message: this.$t(`errors.registryView.${error.status}`),
          color: 'warning',
          icon: 'mdi-information-outline',
        });
      }
    },
  }
};
</script>

<style></style>
