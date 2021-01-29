<template>
  <v-container>
    <v-card class="mx-auto text-center elevation-4" max-width="500">
      <v-card-title>
        <v-container>
          <v-icon large>mdi-translate</v-icon>
          {{ $t('chatView.settings.languagesSwitcher.title') }}
        </v-container>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-list>
          <v-list-item v-for="(lang, index) in languages" :key="index">
            <v-list-item-content class="text-center">
              <v-btn elevation="0" :color="lang.active ? 'success' : 'notInUse'" @click="changeLanguage(lang.local, index)"> 
                {{ $t(lang.title) }}
              </v-btn>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        </v-container>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: 'LanguageSwitcher',
  methods:{
      changeLanguage(localLang, index){
      this.$i18n.locale = localLang 
      this.$store.dispatch("settings/act_changeLanguage", index)
    }
  },
  computed: {
      ...mapState({
          languages: (state) => state.settings.languages
      })
  }
};
</script>

<style></style>
