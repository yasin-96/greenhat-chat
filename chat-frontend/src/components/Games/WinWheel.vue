<template>
  <v-row justify="center">
    <v-dialog v-model="openDialog" fullscreen>
      <v-card>
        <v-card-title class="headline">
          {{$t('chatView.games.title')}}
        </v-card-title>
        <v-card-text>
          <winwheel id="wwheel" :segments="gamePrices"/>
        </v-card-text>
        <v-card-actions>
          <v-btn block large color="danger darken-1" text @click="diasbleDialog">
            <v-icon large>mdi-close</v-icon>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
import VueWinwheel from 'vue-winwheel/vue-winwheel';
import { mapState } from 'vuex';





  // winText.innerHtml = "Yay your price is:";
  // winText.innerHtml = "Dein Preis ist:";
export default {
  name: 'GameWinWheel',
  data:() => ({
    winWheelHeader: null,
    winWheelHTML: null,
    winWheelBTn: ""
  }),
  destroyed(){
    console.log("Kein spiel mehr");
  },
  updated(){
    this.winWheelHTML = document.getElementById("wwheel");
    let changedHeader = this.winWheelHTML.firstElementChild.firstElementChild.innerHTML = this.$t('chatView.games.winWheel')
    console.log("changedHeader",changedHeader);
  },
  components: {
    winwheel: VueWinwheel,
  },
  methods: {
    diasbleDialog() {
      
      this.$store.dispatch('game/act_gameWasPlayed');
    },
  },
  computed: {
    ...mapState({
      openDialog: (state) => state.game.openDialog,
      gamePrices: (state) => state.game.gamePrices,
    }),
  },
};
</script>