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
    this.pollingData();
    // const wsInfo = {"userId": this.userId, "group": this.userGroups}
    // this.$socket.send("asda")
    // console.log(JSON.stringify(wsInfo))
    // let wsString = JSON.stringify(wsInfo)
    this.$socket.send(JSON.stringify(this.allGroupIDS))
  },
  update() {
    this.trigger = this.pollingData();
     if(this.userGroups && this.userId && this.clientConnected ) {
        const wsInfo = {userId: this.userId, group: this.userGroups}
          this.$socket.send(JSON.stringify(wsInfo))
      }
  },
  beforeDestroy() {
    this.trgger = null
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
      // this.$store.dispatch('act_sendWSMessageToServer', "aaaa")
      
    },
    pollingData(){
      setTimeout(()=>{
        this.loadAllUsers()
        this.loadAllGroupsFromUser()
      }, 5000);
    }
  },
  computed:{
    ...mapState({
      allGroupIDS: (state) => state.group.allGroupIDS,
      userId: (state) => state.user.user.id,
      clientConnected: (state) => state.chat.socketInfo.clientConnected
    })
  }
};
</script>

<style></style>
