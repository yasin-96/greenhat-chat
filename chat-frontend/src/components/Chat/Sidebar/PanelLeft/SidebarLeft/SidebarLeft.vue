<template>
  <div v-show="userId && userGroups ">
    <div v-if="isCurrentUserAdminOfGroup">
      <GroupInfoAdmin />
      <GroupUserAdmin />
    </div>
    <div v-else>
      <GroupInfo />
      <GroupUser />
    </div>
    
    <v-spacer class="my-auto"></v-spacer>

    <UserPanelInfo />
  </div>
</template>

<script>
import { mapState } from "vuex";
import GroupInfo from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/GroupInfo';
import GroupUser from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/GroupUser';
import UserPanelInfo from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/UserPanelInfo';

import GroupInfoAdmin from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/GroupInfoAdmin';
import GroupUserAdmin from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/GroupUserAdmin';


export default {
  name: 'SidebarLeft',
  components: {
    GroupInfo,
    GroupUser,
    UserPanelInfo,
    GroupInfoAdmin,
    GroupUserAdmin,
  },
  computed:{
    ...mapState({
      userGroups: (state) => state.group.userGroups,
      activeGroupId: (state) => state.group.activeGroupId,
      userId: (state) => state.user.user.id
    }),
    isCurrentUserAdminOfGroup(){
      const foundedGroup = this.userGroups.find((group) => group._id == this.activeGroupId)
      return this.userId == foundedGroup.admin
    }
  }
};
</script>

<style></style>
