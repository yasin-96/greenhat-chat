import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import store from "./store/store";
import i18n from './plugins/i18n';

Vue.config.productionTip = false

new Vue({
  i18n,
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
