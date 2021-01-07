export const umix = {
  methods: {
    setNotification(message, color, icon) {
      this.$store.dispatch("notify/act_setAlterMessage", {message, color, icon});
    },
  },
};
