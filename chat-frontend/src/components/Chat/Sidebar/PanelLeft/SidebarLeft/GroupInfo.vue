<template>
  <v-sheet class="pl-14 mb-2" color="sidebarGroupInfoHeader" height="128" width="100%">
    <v-list>
      <v-list-item class="grow">
        <v-list-item-content>
          <h2 class="text-center">{{ groupInfo.name }}</h2>
          <h4 class="text-center caption">Groupe</h4>
        </v-list-item-content>
      </v-list-item>
      <v-list-item class="grow">
        <v-list-item-avatar color="">
          <v-avatar color="grey" size="36">
            <v-img class="elevation-6" alt="" :src="adminAvatar"></v-img>
          </v-avatar>
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
  data: () => ({
    adminAvatar:
      'https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairDreads02&accessoriesType=Prescription01&hairColor=Black&facialHairType=BeardLight&facialHairColor=Blonde&clotheType=GraphicShirt&clotheColor=PastelYellow&graphicType=Deer&eyeType=Close&eyebrowType=Angry&mouthType=ScreamOpen&skinColor=Light',
  }),
  computed: {
    ...mapState({
      groupInfo: (state) => state.group.activeGroup,
    }),
  },
};
</script>

<style></style>
