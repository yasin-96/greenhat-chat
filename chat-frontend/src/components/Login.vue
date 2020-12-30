<template>
  <div>
    
    <v-container class="ma-10">
      <Info/>
    </v-container>
    
    <v-container class="d-flex justify-center justify-sm-center justify-md-center justify-lg-center justify-xl-center">
      <v-card elevation="2" shaped class="mx-auto my-auto" max-width="400">
        <v-card-title>
          <h2 class="text-center">Login</h2>
        </v-card-title>
        <v-card-text>
          <v-form ref="reg_login" v-model="valid">
            <v-text-field
              dense
              v-model="user.email"
              label="Email"
              outlined
              prepend-inner-icon="mdi-account-circle"
              clearable
              :rules="[rules.required, rules.notEmpty]"
              required
            ></v-text-field>
            <v-text-field
              dense
              v-model="user.passwd"
              label="Password"
              :type="showPasswd ? 'text' : 'password'"
              prepend-inner-icon="mdi-lock"
              :append-icon="showPasswd ? 'mdi-eye-outline' : 'mdi-eye-off-outline'"
              @click:append="showPasswd = !showPasswd"
              outlined
              clearable
              :rules="[rules.required, rules.notEmpty]"
              required
            ></v-text-field>
            <v-divider></v-divider>
            <v-btn block color="success" :disabled="!valid" @click="login">
              Anmelden
            </v-btn>
            <p>
              <a href="/register">Registrieren</a>
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
  name: 'Login',
  mixins: [umix],
  components: {
    Info,
  },
  data: () => ({
    user: {
      email: '',
      passwd: '',
    },
    showPasswd: false,
    notfication: {
      timeOut: 5000,
      message: '',
      toggle: false,
      icon: '',
      color: ''
    },
    valid: false,
    rules: {
      required: (value) => (value && !!value) || 'Das Feld ist erforderlich.',
      notEmpty: (value) => value !== null || value !== undefined || 'Feld darf nicht leer bleiben',
    },
  }),
  methods: {
    async login() {

      const response = await this.$store.dispatch("user/act_logUserIn", this.user);
      //TOOD: hier muss geprüft werden, welche art von Fehler zurück kommt.
      this.setNotification(response.message, "error", "mdi-alert-circle")
    },
  },
  computed: {},
};
</script>

<style></style>
