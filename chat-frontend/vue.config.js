module.exports = {
  transpileDependencies: ['vuetify','vuex-persist'],
  devServer: {
    port: 4444,
  },
  pluginOptions: {
    i18n: {
      locale: 'de',
      fallbackLocale: 'en',
      enableInSFC: true
    }
  }
};
