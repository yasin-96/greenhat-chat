<template>
  <div>
    <v-container class="ma-10">
      <Info />
    </v-container>
    <v-container class="d-flex justify-center justify-sm-center justify-md-center justify-lg-center justify-xl-center">
      <v-card elevation="2" shaped class="mx-auto my-auto" max-width="400">
        <v-card-title>
          <h2 class="text-center">Registieren</h2>
        </v-card-title>
        <v-card-text>
          <v-form ref="reg_form" v-model="valid" >
            <v-text-field dense v-model="user.userName" label="Username" outlined prepend-inner-icon="mdi-account-circle" clearable :rules="[rules.required]" required></v-text-field>
            <v-text-field
              dense
              v-model="user.userEmail"
              label="Email"
              outlined
              prepend-inner-icon="mdi-account-circle"
              clearable
              :rules="[rules.required, rules.email]"
              required
            ></v-text-field>
            <v-text-field
              dense
              v-model="user.passwd"
              label="Passwort"
              outlined
              prepend-inner-icon="mdi-lock"
              clearable
              :error-messages="isPasswordValid ? '' : 'Passwörter stimmen nicht überein!'"
            ></v-text-field>
            <v-text-field
              dense
              clearable
              outlined
              v-model="repeatPasswd"
              label="Password Wiederholen"
              :type="showPasswd ? 'text' : 'password'"
              prepend-inner-icon="mdi-lock-alert"
              :append-icon="showPasswd ? 'mdi-eye-outline' : 'mdi-eye-off-outline'"
              @click:append="showPasswd = !showPasswd"
              :rules="[rules.required]"
              :error-messages="isPasswordValid ? '' : 'Passwörter stimmen nicht überein!'"
              required
            ></v-text-field>
            <v-divider></v-divider>
            <v-btn block color="success" :disabled="!valid && !isPasswordValid" @click="registerUser">
              Konto erstellen
            </v-btn>
            <p>
              <a href="/login">Anmelden</a>
            </p>
          </v-form>
        </v-card-text>
      </v-card>
      
    </v-container>
  </div>
</template>

<script>
import Info from '@/components/Info';
import {umix} from '@/mixins/umix';

export default {
  name: 'Register',
  mixins: [umix],
  components:{
    Info
  },
  data: () => ({
    valid: false,
    isPasswordValid: false,
    user: {
      userName: '',
      userEmail: '',
      passwd: '',
    },
    repeatPasswd: '',
    showPasswd: false,
    rules: {
      required: (value) => (value && !!value) || 'Das Feld ist erforderlich.',
      email: (value) => {
        const pattern = /^([a-zA-z0-9]+((\.[a-zA-z0-9]+)*)|(".+"))@(greenhat.de)$/;
        return pattern.test(value) || 'Email passt nicht zur Domaine';
      },
      passwd: function(value) {
        this.checkPassword(value);
      },
    },
  }),
  methods: {
    async registerUser() {
      const response = await this.$store.dispatch("user/act_registerUser", this.user);
      //TODO response richtig handle und dann weiterleiten
      this.setNotification(response.message, "error", "mdi-alert-circle")
    },
    checkPassword() {
      if (this.repeatPasswd === this.passwd) {
        this.isPasswordValid = true;
      } else {
        this.isPasswordValid = false;
      }
    },
  },
  computed: {
    hideDetailsPassword() {
      return this.user.passwd === this.repeatPasswd ? true : false;
    },
    hideDetailsUsername() {
      return this.user.userName && !!this.user.userName ? true : false;
    },
    hideDetailsEmail() {
      return this.user.userEmail && !!this.user.userEmail ? true : false;
    },
    formState(){
      return this.$refs.reg_form.value;
    }
  },
  watch: {
    repeatPasswd() {
      this.checkPassword();
    },
    passwd(){
      this.checkPassword();
    }
  },
};
</script>

<style></style>
