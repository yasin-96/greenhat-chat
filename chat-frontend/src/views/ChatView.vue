<template>
  <div>
    <ChatSidePanel />

    <ChatMessage />

    <ChatInput />

    <Settings />
  </div>
</template>

<script>
import {mapState} from 'vuex';
import ChatSidePanel from '@/components/Chat/ChatSidePanel';
import ChatInput from '@/components/Chat/ChatInput';
import ChatMessage from '@/components/Chat/ChatMessage';
import Settings from '@/components/Settings/Settings';
export default {
  name: 'ChatView',
  components: {
    ChatSidePanel,
    ChatInput,
    ChatMessage,
    Settings,
  },
  mounted() {
    this.loadAllUsers();
    this.loadAllGroupsFromUser()
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
    loadAllUsers(){
      console.log("trigger user data")
      this.$store.dispatch("user/act_getAllUserWithIdAndUserName");
    },
    loadAllGroupsFromUser(){
      console.log("trigger user groups")
      this.$store.dispatch("group/act_getAllGroupsFromUser");
      this.$store.dispatch("group/act_getAllGroupIdsFromUser");
      if(this.clientConnected && this.userId && this.allGroupIDS) {
        this.$socket.send(JSON.stringify(this.allGroupIDS))
      }
    },
    pollingData(){
      return setTimeout(()=>{
        console.info("Load All Users, Groups from User")
        this.loadAllUsers()
        this.loadAllGroupsFromUser()
      }, 15000);
    }
  },
  computed:{
    ...mapState({
      allGroupIDS: (state) => state.group.allGroupIDS,
      activeGroupId: (state) => state.group.activeGroupId,
      userId: (state) => state.user.user.id,
      clientConnected: (state) => state.chat.socketInfo.clientConnected,
      wsMessage: (state) => state.chat.socketInfo.recievedMessages
    })
  },
  watch: {
    wsMessage(){
      console.log("Incomming WS Message", this.wsMessage)
      this.$store.dispatch("chat/act_addNewMessagesFromWS", this.activeGroupId)
    }
  }
};
</script>

<style></style>
