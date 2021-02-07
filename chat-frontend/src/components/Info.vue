<template>
  <v-scroll-x-transition>
    <v-snackbar
      v-if="notify.toggle"
      top
      :timeout="notify.timeout"
      :value="true"
      :color="`${notify.color} accent-10`"
      :multi-line="true"
      elevation="12"
      transition="expand-transition"
    >
      <v-icon>
        {{ notify.icon }}
      </v-icon>
      {{ notify.message }}
    </v-snackbar>
  </v-scroll-x-transition>
</template>

<script>
import { mapState } from "vuex";

/**
 * This component represents a notification window. It is used to notify whether errors have occurred or have been successfully completed.
 * - login error
 * - error during registration
 * - error deleting users/groups
 */
export default {
  name: 'Info',
  computed:{
    ...mapState({
      notify: (state) => state.notify.notfication
    })
  },
};
</script>

<style></style>
