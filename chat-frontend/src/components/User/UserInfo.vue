<template>
  <v-container>
    <div class="d-flex flex-no-wrap justify-center">
      <v-card max-height="200px" max-width="180px" v-if="user.hasAvatarPicture">
        <v-img max-height="180px" max-width="180px" alt="" :src="user.avatarPicture"> </v-img>
        <v-card-title primary-title style="position: relative">
          <div>
            <v-btn
              color="warning"
              small
              absolute
              top
              right
              fab
              @click="setValuesForEditingSpecificUserInformation(3)"
            >
              <v-icon>mdi-pencil-outline</v-icon>
            </v-btn>
          </div>
        </v-card-title>
      </v-card>

      <v-card class="my-auto" max-height="100px" max-width="180px" v-else>
        <v-container>
          <v-chip x-large label link>
            <h1 class="display-3 text-white">{{ user.avatarName }}</h1></v-chip
          >
        </v-container>

        <v-card-title primary-title style="position: relative">
          <div>
            <v-btn
              color="warning"
              small
              absolute
              top
              right
              fab
              @click="setValuesForEditingSpecificUserInformation(3)"
            >
              <v-icon>mdi-pencil-outline</v-icon>
            </v-btn>
          </div>
        </v-card-title>
      </v-card>

      <v-card class="ml-14">
        <v-list>
          <v-list-item-group>
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-fingerprint</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <h4 class="body-1">{{ user.id }}</h4>
              </v-list-item-content>
              <v-list-item-action>
                <v-btn small>
                  <v-icon small>
                    mdi-content-copy
                  </v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-email</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <h4 class="body-1">{{ user.email }}</h4></v-list-item-content
              >
              <v-list-item-action>
                <v-btn small @click="setValuesForEditingSpecificUserInformation(0)">
                  <v-icon small>
                    mdi-pencil-outline
                  </v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-badge-account</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <h4 class="body-1">{{ user.username }}</h4>
              </v-list-item-content>
              <v-list-item-action>
                <v-btn small @click="setValuesForEditingSpecificUserInformation(1)">
                  <v-icon small>
                    mdi-pencil-outline
                  </v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-drama-masks</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <h4 class="body-1">{{ user.avatarName }}</h4>
              </v-list-item-content>
              <v-list-item-action>
                <v-switch
                  :disabled="!user.hasAvatarPicture"
                  v-model="enableOrDisbleUserAvatarPicture"
                  flat
                  :label="user.hasAvatarPicture ? 'enable' : 'disabled'"
                ></v-switch>
              </v-list-item-action>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </div>
    <v-divider class="mt-5"></v-divider>
    <v-container>
       <v-switch
        v-model="$vuetify.theme.dark"
        hint="Switch to Dark Theme"
        inset
        label="Change Theme from light to dark"
        persistent-hint
      ></v-switch>
    </v-container>
    <EditValue />
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
import EditValue from '@/components/User/EditValue';
export default {
  name: 'UserInfo',
  components: {
    EditValue,
  },
  data: () => ({
    enableOrDisbleUserAvatarPicture: '',
    domainName: '@greenhat.de',
  }),
  methods: {
    setValuesForEditingSpecificUserInformation(userInfo) {
      console.log('userInfo', userInfo);
      this.$store.dispatch('user/act_setEditWindowSettingsBasedOnType', userInfo);
      this.$store.dispatch('user/act_toggleEditWindowForUserWithValue', true);
    },
    async removeAvatarPicture() {
      await this.$store.dispatch('user/act_updateSpecificUserInformationen', {
        _id: this.user.id,
        update: {
          avatarPicture: '',
          hasAvatarPicture: false,
        },
      });
      this.enableOrDisbleUserAvatarPicture = ''
    },
  },
  computed: {
    ...mapState({
      user: (state) => state.user.user,
    }),
  },
  watch: {
    enableOrDisbleUserAvatarPicture() {
      if (this.enableOrDisbleUserAvatarPicture) {
        this.removeAvatarPicture();
      }
    },
  },
};
</script>

<style></style>
