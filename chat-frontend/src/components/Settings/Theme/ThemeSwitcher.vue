<template>
  <v-container>
    <v-card class="mx-auto text-center" max-width="500">
      <v-card-title>
        <v-container>
          Change Theme
        </v-container>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-btn large @click="switchToLightTheme"> Light<v-icon right>mdi-white-balance-sunny</v-icon> </v-btn>
          <v-btn class="ml-2" large @click="switchToDarkTheme"> Dark<v-icon right>mdi-weather-night</v-icon> </v-btn>
        </v-container>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
export default {
  name: 'ThemeSwitcher',
  methods: {
    switchToLightTheme() {
      this.$vuetify.theme.dark = false;
      this.$vuetify.theme.light = true;
    },
    switchToDarkTheme() {
      this.$vuetify.theme.light = false;
      this.$vuetify.theme.dark = true;
    },
  },
};
</script>

<style></style>
