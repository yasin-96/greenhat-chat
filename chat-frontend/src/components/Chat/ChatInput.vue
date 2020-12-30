<template>
  <v-footer app color="transparent" height="72" inset>
    <v-text-field
      background-color="grey lighten-1"
      dense
      flat
      hide-details
      rounded
      solo
      append-outer-icon="mdi-send"
      :disabled="!message"
    ></v-text-field>
  </v-footer>
</template>

<script>
export default {
  name: 'ChatInput',
  props: {
    message: {
      type: String,
    },
  },
};
</script>

<style></style>
