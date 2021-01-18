import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import colors from 'vuetify/lib/util/colors';

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: colors.lightBlue.darken1, // #E53935
        secondary: colors.red.lighten4, // #FFCDD2
        accent: colors.indigo.base, // #3F51B5
        success: colors.green.darken1,
        greenHatGreen: colors.green.darken2,
        danger: colors.deepOrange.darken2,
        error: colors.red.darken4,
        yellow: colors.amber.accent3, //#FFEA00
        send: colors.lightGreen.accent4,
        transparent: colors.transparent,
        blueGrey: colors.blueGrey.darken4
      },
    },
  },
});
