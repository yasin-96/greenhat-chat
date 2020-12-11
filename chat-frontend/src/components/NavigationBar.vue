<template>
  <v-app-bar
      app
      color="greenHatGreen"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="@/assets/logo_02.svg"
          transition="scale-transition"
          width="40"
        />

      </div>

      <v-spacer></v-spacer>
          <v-responsive max-width="156">
            <v-text-field
              dense
              flat
              hide-details
              rounded
              solo-inverted
            ></v-text-field>
          </v-responsive>
    </v-app-bar>
</template>

<script>
export default {
    name: "NavigationBar"
}
</script>

<style>

</style>