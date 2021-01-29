<template>
  <div>
    <div>
      <GroupInfo />
      <GroupUser />
    </div>
    <v-spacer class="my-auto"></v-spacer>

    <UserPanelInfo />
  </div>
</template>

<script>
import GroupInfo from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/GroupInfo';
import GroupUser from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/GroupUser';
import UserPanelInfo from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/UserPanelInfo';

export default {
  name: 'SidebarLeft',
  components: {
    GroupInfo,
    GroupUser,
    UserPanelInfo,
  },
};
</script>

<style></style>
