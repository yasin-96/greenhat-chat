<template>
  <v-sheet class="pl-14" color="sidebarGroupInfoHeader" height="150" width="100%">
    <v-list>
      <v-list-item class="grow">
        <v-list-item-content>
          <h2 class="title">{{ groupInfo.name }}</h2>
          <h4 class="caption">{{$t('chatView.sidebar.sidebarLeft.sidebarMini.groupInfo.subTitle')}}</h4>
        </v-list-item-content>
      </v-list-item>
      <v-divider class="mx-auto" width="100"></v-divider>
      <v-list-item class="grow">
        <v-list-item-avatar color="white" size="36">
            <v-img class="elevation-6" alt="Admin" src="@/assets/logo.svg"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ groupInfo.admin }}</v-list-item-title>
          <v-list-item-subtitle> <h6>{{$t('chatView.sidebar.sidebarLeft.sidebarMini.groupInfo.groupAdmin')}}</h6></v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-action>
          <v-btn fab text small @click="openGroupAdminSettingsDialog">
            <v-icon >mdi-cog</v-icon>
          </v-btn>
        </v-list-item-action>
      </v-list-item>
    </v-list>
  </v-sheet>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'GroupInfoAdmin',
  data: () => ({
    adminAvatar: "@/assets/logo.svg"
      // 'https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairFrizzle&accessoriesType=Prescription02&hairColor=Black&facialHairType=BeardMagestic&facialHairColor=Brown&clotheType=GraphicShirt&clotheColor=Blue03&graphicType=Deer&eyeType=Happy&eyebrowType=RaisedExcitedNatural&mouthType=Twinkle&skinColor=Brown',
  }),
  computed: {
    ...mapState({
      groupInfo: (state) => state.group.activeGroup,
    }),
  },
  methods:{
    openGroupAdminSettingsDialog(){
      this.$store.dispatch("settings/act_openAdminSettingsDialogForGroup")
    }
  }
};
</script>

<style></style>
