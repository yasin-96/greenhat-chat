<template>
  <v-scroll-x-transition>
    <v-snackbar
      v-if="showSnackbar"
      top
      :timeout="timeout"
      :value="true"
      :color="`${color} accent-10`"
      :multi-line="true"
      elevation="12"
      transition="expand-transition"
    >
      <v-icon>
        {{ icon }}
      </v-icon>
      {{ message }}
    </v-snackbar>
  </v-scroll-x-transition>
</template>

<script>
export default {
  name: 'Info',
  beforeDestroy() {
    this.showSnackbar = false;
  },
  data: () => ({
    showSnackbar: false,
  }),
  props: {
    color: {
      type: String,
      default: 'error',
    },
    timeout: {
      type: Number,
      default: 2000,
    },
    message: {
      type: String,
    },
    toggle: {
      type: Boolean,
      default: false,
    },
    alertTyp: {
      type: String,
      default: 'error',
    },
    icon: {
      type: String,
    },
  },
  watch: {
    toggle() {
      this.showSnackbar = this.toggle;
    },
  },
};
</script>

<style></style>
