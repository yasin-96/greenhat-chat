<template>
  <v-container>
    <div class="d-flex flex-no-wrap justify-center">
      <v-card max-height="200px" max-width="180px">
        <v-img
          max-height="180px"
          max-width="180px"
          alt=""
          src="https://avataaars.io/?avatarStyle=Transparent&topType=NoHair&accessoriesType=Blank&facialHairType=Blank&clotheType=Hoodie&clotheColor=Gray01&eyeType=Default&eyebrowType=Default&mouthType=Default&skinColor=Light"
        >
        </v-img>
        <v-card-title primary-title style="position: relative">
          <div>
            <v-btn v-show="!hidden" color="warning" dark small absolute top right fab>
              <v-icon>mdi-pencil-outline</v-icon>
            </v-btn>
          </div>
        </v-card-title>
      </v-card>

      <v-card class="ml-14">
        <v-list>
          <v-list-item-group v-model="model">
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-fingerprint</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <h4 class="">{{ user.id }}</h4>
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
                <h4>{{ user.email }}</h4></v-list-item-content
              >
              <v-list-item-action>
                <v-btn small>
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
                <h4>{{ user.username }}</h4>
              </v-list-item-content>
              <v-list-item-action>
                <v-btn small>
                  <v-icon small>
                    mdi-pencil-outline
                  </v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </div>
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'UserInfo',
  data: () => ({}),
  computed: {
    ...mapState({
      user: (state) => state.user.user,
    }),
  },
};
</script>

<style></style>
