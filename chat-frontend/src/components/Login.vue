<template>
  <div>
    <v-container
      class="d-flex justify-center justify-sm-center justify-md-center justify-lg-center justify-xl-center"
    >
      <v-card elevation="2" shaped class="mx-auto my-auto" max-width="400">
        <v-card-title>
          <h2 class="text-center">Login</h2>
        </v-card-title>
        <v-card-text>
          <v-row>
            <v-col>
              <v-text-field
                v-model="email"
                label="Email"
                outlined
                hide-details
                prepend-inner-icon="mdi-account-circle"
                clearable
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                v-model="passwd"
                label="Password"
                :type="showPasswd ? 'text' : 'password'"
                prepend-inner-icon="mdi-lock"
                :append-icon="
                  showPasswd ? 'mdi-eye-outline' : 'mdi-eye-off-outline'
                "
                @click:append="showPasswd = !showPasswd"
                hide-details
                outlined
                clearable
              ></v-text-field>
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-container>
            <v-btn block large color="success">
              Anmelden
            </v-btn>
          </v-container>
        </v-card-actions>
      </v-card>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "Login",
  data: () => ({
    email: "",
    passwd: "",
    showPasswd: false,
  }),
  methods: {
    login() {
      alert("asdja");
    },
  },
  computed: {},
};
</script>

<style></style>
