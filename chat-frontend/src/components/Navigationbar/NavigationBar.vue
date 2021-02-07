<template>
  <v-app-bar app color="navbarBackground">
    <div v-if="checkRoute">
      <v-btn v-if="drawer" icon @click="uidrawer = true">
        <v-icon>mdi-menu</v-icon>
      </v-btn>
      <v-btn v-else icon @click="uidrawer = false">
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
    </div>

    <v-spacer></v-spacer>
    <!-- <v-responsive max-width="156">
      <v-text-field color="navbarSearchbar" dense flat hide-details rounded solo-inverted></v-text-field>
    </v-responsive> -->
    <v-divider class="ml-2 mr-2 pt-3" vertical></v-divider>

      <v-menu v-if="checkRoute" v-model="menu" :close-on-content-click="true" :nudge-width="200" offset-x>
        <template v-slot:activator="{ on, attrs }">
          <v-btn class="mx-2" large icon @click="openGameWindow" v-bind="attrs" v-on="on">
            <!-- <v-badge :color="gameBell" icon="mdi-gamepad-square" overlap
            >
          </v-badge> -->
            <v-icon large :color="gameBell">{{ bellIcon }}</v-icon>
          </v-btn>
        </template>

        <v-card>
          <winwheel />
        </v-card>
      </v-menu>
      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn v-bind="attrs" v-on="on" class="mx-2" icon large @click="openSettingsDialog">
            <v-icon dark>
              mdi-cog
            </v-icon>
          </v-btn>
        </template>
        <span>{{ $t('chatView.navigationbar.settings') }}</span>
      </v-tooltip>

      <div class="text-center">
        <v-menu open-on-hover button offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-bind="attrs" v-on="on" class="mx-2" icon large>
              <v-icon>
                mdi-translate
              </v-icon>
            </v-btn>
          </template>

          <v-list>
            <v-list-item v-for="(lang, index) in languages" :key="index">
              <v-list-item-content class="text-center">
                <v-btn text small :color="lang.active ? 'success' : ''" @click="changeLanguage(lang.local, index)">
                  {{ $t(lang.title) }}
                </v-btn>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
  </v-app-bar>
</template>

<script>
import { mapState } from 'vuex';
import GameWinWheel from '@/components/Games/WinWheel';

export default {
  name: 'NavigationBar',
  components: {
    winwheel: GameWinWheel,
  },
  data: () => ({
    menu: '',
    gameBell: '#424242',
    bellIcon: 'mdi-bell',
    icons: {
      notRing: 'mdi-bell',
      ring: 'mdi-bell-ring',
    },
    
    count: 0,
  }),
  methods: {
    toggleSidePanel() {
      this.$store.dispatch('settings/act_toggleSidePanel');
    },
    openGameWindow() {
      this.$store.dispatch('game/act_toggleGameWindowWithValue', true);
    },
    changeLanguage(localLang, index) {
      this.$i18n.locale = localLang;
      this.$store.dispatch('settings/act_changeLanguage', index);
    },
    openSettingsDialog() {
      console.log('asjadh');
      this.$store.dispatch('settings/act_openSettingsDialog');
    },
  },
  computed: {
    ...mapState({
      drawer: (state) => state.settings.sidebar.sidebarLeft.sidebarLeftDrawer,
      gameWindow: (state) => state.game.openDialog,
      gameStatus: (state) => state.game.gameWasPlayed,
      languages: (state) => state.settings.languages,
    }),

    uidrawer: {
      get() {
        return this.drawer;
      },
      set() {
        this.toggleSidePanel();
      },
    },
    checkRoute() {
      return this.$route.name === 'LoginView' || this.$route.name === 'RegisterView' ? false : true;
    },
    checkGames() {
      return true;
    },
    gameIsActive() {
      return this.gameStatus;
    },
  },
};
</script>

<style></style>
