import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import colors from 'vuetify/lib/util/colors';

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    // dark: true,   //only dark theme
    // light: true   //onlt light theme 
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
        blueGrey: colors.blueGrey.darken4,
        lightGreen: colors.green.lighten1,

        //Navbar
        navbarBackground: colors.grey.lighten3,
        navbarSearchbar: colors.lightGreen.accent4, 

        //SIDEBAR MINI
        sidebarMiniBackGround: colors.grey.lighten3,
        sidebarMiniGroupUsersAvatarBackGround: colors.blueGrey,
        sidebarMiniAddGroupButton: colors.blueGrey,
        sidebarMiniAddGroupButtonIcon: colors.white,

        sidebarMiniActiveGroup: colors.lightGreen,
        sidebarMiniNotActiveGroups: colors.grey.lighten1,
        
        //SIDEBAR GROUP
        sidebarGroupInfoHeader: colors.grey.lighten3,

        //CHAT INPUT
        chatInputSendButton: colors.lightGreen.accent4,
        chatInputTextMessage: colors.grey.lighten1
      },
      dark: {
        //Navbar
        navbarBackground: colors.grey.darken4,
        navbarSearchbar: colors.lightGreen.accent4,

        //SIDEBAR MINI
        sidebarMiniBackGround: colors.grey.darken4,
        sidebarMiniGroupUsersAvatarBackGround: colors.grey,
        sidebarMiniAddGroupButton: colors.blueGrey.lighten1,
        sidebarMiniAddGroupButtonIcon: colors.black,

        sidebarMiniActiveGroup: colors.lightGreen,
        sidebarMiniNotActiveGroups: colors.grey.darken3,

        //SIDEBAR GROUP
        sidebarGroupInfoHeader: colors.grey.darken4,

        //CHAT INPUT
        chatInputSendButton: colors.lightGreen.accent4,
        chatInputTextMessage: colors.grey.darken3
      }
    },
  },
});
