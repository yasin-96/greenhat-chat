export const umix = {
  methods: {
    setNotification(message, color, icon) {
      this.notfication.message = message;
      this.notfication.icon = icon;
      this.notfication.color = color;

      this.notfication.toggle = true;

      setTimeout(() => {
        this.notfication.toggle = false;
      }, this.notfication.timeOut);
    },
  },
};
