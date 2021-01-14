<template>
    <div>
    <v-container class="ma-10">
      <Info />
    </v-container>
    <v-container class="justify-center justify-sm-center justify-md-center justify-lg-center justify-xl-center">
      <v-card elevation="2" class="mx-auto my-auto" max-width="400">
        <v-card-title>
          <v-container>
            <h2 class="text-center display-1">{{ title }}</h2>
          </v-container>
        </v-card-title>

        <v-card-text>
          <v-container>
            <v-form ref="reg_form" v-model="valid">
              <v-text-field
                v-model="user.username"
                :label="label.username"
                outlined
                prepend-inner-icon="mdi-account-circle"
                clearable
                :rules="[rules.required]"
                required
              ></v-text-field>
              <v-text-field
                v-model="user.email"
                :label="label.email"
                outlined
                prepend-inner-icon="mdi-account-circle"
                clearable
                :rules="[rules.required, rules.email]"
                required
                :suffix="domainName"
              ></v-text-field>
              <v-text-field
                v-model="user.password"
                :label="label.password"
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
                label="Password Wiederholen"
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
            {{ label.logIn }}
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="success" :disabled="!valid && !isPasswordValid" @click="registerUser">
            {{ label.createAccount }}
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
  name: 'Register',
  mixins: [umix],
  components: {
    Info,
  },
  data: () => ({
    valid: false,
    isPasswordValid: false,
    title: 'Konto erstellen',
    domainName:"@greenhat.de",
    label: {
      logIn: 'Stattdessen Anmelden',
      username: 'Username',
      email: 'Email',
      password: 'Passwort',
      createAccount: 'Erstellen',
    },
    user: {
      username: '',
      email: '',
      password: '',
    },
    repeatPasswd: '',
    showPasswd: false,
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
    async registerUser() {
      this.user.email = this.user.email + this.domainName
      const { message, status } = await this.$store.dispatch('user/act_registerUser', this.user);
      if (status !== 200) {
        this.setNotification(message, 'error', 'mdi-alert-circle');
      } else {
        this.setNotification(message, 'success', 'mdi-alert-circle');
        setTimeout(() => {
          this.$router.push({ path: '/chat' });
        }, 3500);
      }
    },
    checkPassword() {
      if (this.repeatPasswd === this.user.password && !!this.repeatPasswd && !!this.user.password) {
        this.isPasswordValid = true;
      } else {
        this.isPasswordValid = false;
      }
    },
  },
  computed: {
    hideDetailsPassword() {
      return this.user.password === this.repeatPasswd ? true : false;
    },
    hideDetailsUsername() {
      return this.user.username && !!this.user.username ? true : false;
    },
    hideDetailsEmail() {
      return this.user.email && !!this.user.email ? true : false;
    },
    formState() {
      return this.$refs.reg_form.value;
    },
  },
};
</script>

<style></style>
