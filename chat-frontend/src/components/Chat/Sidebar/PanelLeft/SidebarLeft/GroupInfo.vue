<template>
  <v-sheet class="pl-14 mb-5" color="sidebarGroupInfoHeader" height="128" width="100%">
    <v-list>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>
            <h2 class="title">{{ groupInfo.name }}</h2>
            <h4 class="caption">
              {{ $t('chatView.sidebar.sidebarLeft.sidebarMini.groupInfo.subTitle') }}
            </h4>
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-divider class="mx-auto" width="100"></v-divider>
      <v-list-item class="grow">
        <v-list-item-avatar color="notInUse" size="36">
          <v-img class="elevation-15" alt="Admin" :src="adminAvatar"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ groupInfo.admin }}</v-list-item-title>
          <v-list-item-subtitle>
            <h6>
              {{ $t('chatView.sidebar.sidebarLeft.sidebarMini.groupInfo.groupAdmin') }}
            </h6></v-list-item-subtitle
          >
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
      'https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairFrizzle&accessoriesType=Prescription02&hairColor=Black&facialHairType=BeardMagestic&facialHairColor=Brown&clotheType=GraphicShirt&clotheColor=Blue03&graphicType=Deer&eyeType=Happy&eyebrowType=RaisedExcitedNatural&mouthType=Twinkle&skinColor=Brown',
  }),
  computed: {
    ...mapState({
      groupInfo: (state) => state.group.activeGroup,
    }),
  },
};
</script>

<style></style>
