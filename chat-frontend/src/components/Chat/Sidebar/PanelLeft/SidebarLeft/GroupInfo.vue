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
          <v-img v-if="!isCurrentUserAdminOfGroup" class="elevation-6" alt="Admin" src="@/assets/logo_02.svg"></v-img>
          <v-img v-else class="elevation-6" alt="Admin" src="@/assets/logo.svg"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ groupInfo.admin }}</v-list-item-title>
          <v-list-item-subtitle>
            <h6>
              {{ $t('chatView.sidebar.sidebarLeft.sidebarMini.groupInfo.groupAdmin') }}
            </h6></v-list-item-subtitle
          >
        </v-list-item-content>
        <div v-show="isCurrentUserAdminOfGroup">
          <v-list-item-action>
            <v-btn fab text small @click="openGroupAdminSettingsDialog">
              <v-icon>mdi-cog</v-icon>
            </v-btn>
          </v-list-item-action>
        </div>
      </v-list-item>
    </v-list>
  </v-sheet>
</template>

<script>
import { mapState } from 'vuex';
import { umix } from '@/mixins/umix';

/**
 *
 */
export default {
  name: 'GroupInfo',
  mixins: [umix],
  computed: {
    ...mapState({
      groupInfo: (state) => state.group.activeGroup,
      rawGroups: (state) => state.group.rawGroups,
      activeGroupId: (state) => state.group.activeGroupId,
      userId: (state) => state.user.user.id,
    }),
  },
  methods: {
    /**
     *
     */
    openGroupAdminSettingsDialog() {
      this.$store.dispatch('settings/act_openAdminSettingsDialogForGroup');
    },
  },
};
</script>

<style></style>
