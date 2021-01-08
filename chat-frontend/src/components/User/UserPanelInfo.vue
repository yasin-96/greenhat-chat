<template>
  <v-bottom-navigation dense class="pl-14" color="danger" height="40px">
    <v-btn disabled
      ><v-avatar>
        <img
          src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
          alt="User"
          class="elevation-6"
        />
      </v-avatar>
    </v-btn>

    <v-tooltip top>
      <template v-slot:activator="{ on, attrs }">
        <v-btn v-bind="attrs" v-on="on">
          <v-list>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  <h3>{{ username }}</h3>
                </v-list-item-title>
                <v-list-item-subtitle>
                  <h6 class="">{{ shortUserId }}</h6>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-btn>
      </template>
      <span>{{ userId }}</span>
    </v-tooltip>

    <v-btn>
      <v-icon @click="openSettingsDialog" color="danger">mdi-cog-outline</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'UserPanelInfo',
  methods: {
    openSettingsDialog() {
      console.log('asjadh');
      this.$store.dispatch('settings/act_openSettingsDialog');
    },
  },
  computed: {
    ...mapState({
      username: (state) => state.user.user.username || '',
      userId: (state) => state.user.user.id || '',
      shortUserId: (state) => `#${state.user.user.id.substring(0, 9)} . . .`,
    }),
  },
};
</script>

<style></style>
