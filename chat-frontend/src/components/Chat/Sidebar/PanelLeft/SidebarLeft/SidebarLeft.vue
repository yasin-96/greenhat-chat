<template>
  <div v-show="userId && userGroups">
    <GroupInfo />
    <GroupUser />

    <v-spacer class="my-auto"></v-spacer>

    <UserPanelInfo />
  </div>
</template>

<script>
import { mapState } from 'vuex';
import GroupInfo from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/GroupInfo';
import GroupUser from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/GroupUser';
import UserPanelInfo from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/UserPanelInfo';

/**
 *
 */
export default {
  name: 'SidebarLeft',
  components: {
    GroupInfo,
    GroupUser,
    UserPanelInfo,
  },
  computed: {
    ...mapState({
      userGroups: (state) => state.group.userGroups,
      activeGroupId: (state) => state.group.activeGroupId,
      userId: (state) => state.user.user.id,
    }),
  },
};
</script>

<style></style>
