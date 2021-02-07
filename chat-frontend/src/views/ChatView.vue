<template>
  <div>
    <PanelLeft />

    <ChatMessages />
    <ChatInput />

    <Settings />
    <GroupSettingsAdmin />

    <Info />
  </div>
</template>

<script>
import { mapState } from 'vuex';
import PanelLeft from '@/components/Chat/Sidebar/PanelLeft/PanelLeft';
import ChatInput from '@/components/Chat/MainContent/ChatInput';
import ChatMessages from '@/components/Chat/MainContent/ChatMessages';
import Settings from '@/components/Settings/Settings';
import GroupSettingsAdmin from "@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/GroupSettingsAdmin"
import Info from '@/components/Info';

/**
 * 
 */
export default {
  name: 'ChatView',
  components: {
    PanelLeft,
    ChatInput,
    ChatMessages,
    Settings,
    GroupSettingsAdmin,
    Info
  },
  mounted() {
    this.loadAllUsers();
    this.loadAllGroupsFromUser();
    this.trigger = this.pollingData();
  },
  updated() {
    this.trigger = this.pollingData();
  },
  beforeDestroy() {
    clearInterval(this.trigger);
  },
  data: () => ({ trigger: null }),
  methods: {
    loadAllUsers() {
      console.log('trigger user data');
      this.$store.dispatch('user/act_getAllUserWithIdAndUserName');
    },
    loadAllGroupsFromUser() {
      console.log('trigger user groups');
      this.$store.dispatch('group/act_getAllGroupsFromUser');
      this.$store.dispatch('group/act_getAllGroupIdsFromUser');
      if (this.clientConnected && this.userId && this.allGroupIDS) {
        this.$socket.send(JSON.stringify(this.allGroupIDS));
      }
    },
    pollingData() {
      return setTimeout(() => {
        console.info('Load All Users, Groups from User');
        this.loadAllUsers();
        this.loadAllGroupsFromUser();
      }, 15000);
    },
  },
  computed: {
    ...mapState({
      allGroupIDS: (state) => state.group.allGroupIDS,
      activeGroupId: (state) => state.group.activeGroupId,
      userId: (state) => state.user.user.id,
      clientConnected: (state) => state.chat.socketInfo.clientConnected,
      wsMessage: (state) => state.chat.socketInfo.recievedMessages,
    }),
  },
  watch: {
    wsMessage() {
      console.log('Incomming WS Message', this.wsMessage);
      this.$store.dispatch('chat/act_addNewMessagesFromWS', this.activeGroupId);
    },
  },
};
</script>

<style></style>
