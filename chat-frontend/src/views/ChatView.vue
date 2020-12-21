<template>
  <div>
        <ChatSidePanel />
    
    
        <v-main>
          <!--  -->
        </v-main>
    
    <ChatInput/>
  </div>
</template>

<script>
import ChatSidePanel from "@/components/Chat/ChatSidePanel";
import ChatInput from "@/components/Chat/ChatInput";
export default {
    name: "ChatView",
    components: {
        ChatSidePanel,
        ChatInput,
    },
     data: () => ({ drawer: null }),
}
</script>

<style>

</style>