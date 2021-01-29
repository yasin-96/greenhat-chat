<template>
  <v-row justify="center">
    <v-dialog v-model="showWindow" persistent max-width="600">
      <v-card>
        <v-card-title class="headline">
          <h1 class="headline">
            {{ $t('chatView.user.editValue.title') }}
          </h1>
          <span class="ml-2"
            ><h1 class="title">{{ editOpions.title }}</h1></span
          >
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-container v-if="editOpions.infoType < 3">
              <v-text-field :value="editOpions.old" :label="`Old-${editOpions.title}`" disabled filled></v-text-field>
              <v-text-field
                v-if="editOpions.infoType == userInfo.MAIL && editOpions.suffix"
                v-model="updatedValue"
                :label="`New-${editOpions.title}`"
                outlined
                prepend-inner-icon="mdi-account-circle"
                clearable
                :rules="[rules.required, rules.email]"
                required
                :suffix="editOpions.suffix"
              ></v-text-field>

              <v-text-field
                v-else
                v-model="updatedValue"
                outlined
                :label="`New-${editOpions.title}`"
                required
                clearable
              ></v-text-field>
            </v-container>
            <v-container v-else>
              <span>
                {{ $t('chatView.user.editValue.createAvatar') }}
              </span>
              <a href="https://getavataaars.com/" target="_blank">
                <v-icon class="ml-1" large color="info"> mdi-image</v-icon>
              </a>
              <v-textarea
                :value="editOpions.old ? '<img src=\'' + editOpions.old + '\'/>' : 'Copy code here'"
                auto-grow
                rows="3"
                row-height="25"
                filled
                outlined
                v-model="rawImageToImage"
                label="Avatar Image Tag"
                hint="<img> , <img />"
              ></v-textarea>
            </v-container>
          </v-form>
          <v-container v-show="linkFromAvatar">
            <img max-height="100px" :src="linkFromAvatar" alt="User" class="elevation-2 mx-auto" />
          </v-container>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error darken-1" text @click="showWindow = false">
            {{ $t('actions.close') }}
          </v-btn>
          <v-btn color="success darken-1" text @click="updateUserInformationen">
            {{ $t('actions.save') }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'EditValue',
  data: () => ({
    rawImageToImage: '',
    generatedImage: '',
    valid: false,
    userInfo: {
      MAIL: 0,
      NICKNAME: 1,
      AVATAR_NAME: 2,
      PROFILE: 3,
    },
    updatedValue: '',
    rules: {
      required: (value) => (value && !!value) || 'Das Feld ist erforderlich.',
      email: (value) => {
        const pattern = /^([a-zA-z0-9]+((\.[a-zA-z0-9]+)*)|(".+"))$/;
        return pattern.test(value) || 'Email passt nicht zur Domaine';
      },
    },
    dialog: false,
  }),
  methods: {
    async updateUserInformationen() {
      this.showWindow = false;

      // let updateInfo = {
      //   _id: this.userId,
      //   update: {},
      // };
      console.log('updateUserInformationen', this.infoType);
      switch (this.infoType) {
        case 0:
          await this.$store.dispatch('user/act_updateSpecificUserInformationen', {
            _id: this.userId,
            update: { email: this.updatedValue + this.editOpions.suffix },
          });
          break;
        case 1:
          await this.$store.dispatch('user/act_updateSpecificUserInformationen', {
            _id: this.userId,
            update: { username: this.updatedValue },
          });
          break;
        case 3:
          await this.$store.dispatch('user/act_updateSpecificUserInformationen', {
            _id: this.userId,
            update: {
              avatarPicture: this.linkFromAvatar,
              hasAvatarPicture: true,
            },
          });
          break;
        default:
          break;
      }
      this.showWindow = false;
    },
  },
  computed: {
    ...mapState({
      userId: (state) => state.user.user.id,
      editOpions: (state) => state.user.editOptions,
      enableWindow: (state) => state.user.editOptions.displaySettingsWindow,
      infoType: (state) => state.user.editOptions.infoType,
    }),

    showWindow: {
      get() {
        return this.editOpions.displaySettingsWindow;
      },
      set() {
        this.$store.dispatch('user/act_closeEditWindowForUser');
      },
    },

    linkFromAvatar() {
      if (this.rawImageToImage) {
        if (this.rawImageToImage.indexOf("'") != -1 && this.rawImageToImage.lastIndexOf("'") != -1) {
          let fin = this.rawImageToImage
            .split("'")[1]
            .split("'")[0]
            .replace('avatarStyle=Circle', 'avatarStyle=Transparent');
          console.log('IMG,', fin);
          return fin;
        }
      }
      return '';
    },
  },
};
</script>

<style></style>
