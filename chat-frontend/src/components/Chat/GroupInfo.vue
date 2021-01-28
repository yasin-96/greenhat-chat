<template>
  <v-sheet class="pl-14" color="grey lighten-4" height="128" width="100%">
    <v-list>
      <v-list-item class="grow">
        <v-list-item-content>
          <h2 class="text-center"> {{ groupInfo.name }}</h2>
          <h4 class="text-center caption"> Groupe</h4>
        </v-list-item-content>
      </v-list-item>
      <v-list-item class="grow">
        <v-list-item-avatar color="grey darken-3">
          <v-img
            class="elevation-6"
            alt=""
            src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
          ></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ groupInfo.admin }}</v-list-item-title>
          <v-list-item-subtitle> <h6>Admin</h6></v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-sheet>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'GroupInfo',
  computed: {
    ...mapState({
      groupInfo: (state) => state.group.activeGroup,
    }),
  },
};
</script>

<style></style>
