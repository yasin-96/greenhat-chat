<template>
  <v-bottom-navigation app dense class="ml-14 elevation-0" color="danger" height="60px" width="245px">
    <v-btn disabled
      ><v-avatar v-if="user.hasAvatarPicture">
        <img :src="avatarPic" alt="User" class="elevation-6" />
      </v-avatar>
      <v-avatar v-else>
        {{ user.avatarName }}
      </v-avatar>
    </v-btn>

    <v-tooltip top>
      <template v-slot:activator="{ on, attrs }">
        <v-btn v-bind="attrs" v-on="on">
          <v-list>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  <h3>{{ shortUserName }}</h3>
                </v-list-item-title>
                <v-list-item-subtitle>
                  <h6 class="">{{ shortUserId }}</h6>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-btn>
      </template>
      <span>
        <p>
          <v-icon>
            mdi-badge-account
          </v-icon>
          {{ username }}
        </p>
        <p>
          <v-icon>
            mdi-fingerprint
          </v-icon>
          #{{ userId }}
        </p>
        <p></p>
      </span>
    </v-tooltip>

    <v-btn @click="openSettingsDialog">
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
      user: (state) => state.user.user,
      avatarName: (state) => state.user.user.avatarName,
      avatarPic: (state) => state.user.user.avatarPicture,
      username: (state) => state.user.user.username || '',
      userId: (state) => state.user.user.id || '',
    }),
    shortUserId() {
      if (this.user && this.user.id) {
        return `#${this.user.id.substring(0, 9)} . . .`;
      }
      return '';
    },
    shortUserName() {
      if (this.user && this.user.username) {
        return `${this.user.username.substring(0, 4)} ...`;
      }
      return '';
    },
  },
};
</script>

<style></style>
