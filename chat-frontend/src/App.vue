<template>
  <v-app>
    <NavigationBar />
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import NavigationBar from '@/components/Navigationbar/NavigationBar';
export default {
  name: 'App',
  components: {
    NavigationBar,
  },
  mounted () {
    console.log("current language: ", this.$i18n.locale)
    this.setDefaultLanguage()
  },
  methods:{
    setDefaultLanguage(){
      this.$store.dispatch("settings/act_setDefaultLanguage", this.$i18n.locale)
    }
  }
};
</script>

<style>
/* Firefox */
* {
  scrollbar-width: 0px;
  scrollbar-color: #21212100 #21212100;
}

/* Chrome Based Browser and Safari */
*::-webkit-scrollbar {
  width: 0px;
}

*::-webkit-scrollbar-track {
  background: #21212100;
}

*::-webkit-scrollbar-thumb {
  background-color: #21212100;
  border-radius: 0px;
  border: none;
}
</style>
