<template>
  <div>
    <ChatSidePanel />

    <ChatMessage />

    <ChatInput />

    <Settings />
  </div>
</template>

<script>
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
    this.pollingData();
    this.loadAllUsers();
  },
  update() {
    this.trigger = this.pollingData();
  },
  beforeDestroy() {
    this.trgger = null
  },
  data: () => ({ trigger: null, drawer: null }),
  methods: {
    loadAllUsers(){
      console.log("trigger user data")
      this.$store.dispatch("user/act_getAllUserWithIdAndUserName");
      // this.$store.dispatch("user/act_getAllGroupsFromUser");
    },
    pollingData(){
      setTimeout(async()=>{
        this.loadAllUsers()
      }, 60000);
    }
  },
};
</script>

<style></style>
