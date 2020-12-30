<template>
  <div>
    <ChatSidePanel />

    <ChatMessage />

    <ChatInput />
  </div>
</template>

<script>
import ChatSidePanel from '@/components/Chat/ChatSidePanel';
import ChatInput from '@/components/Chat/ChatInput';
import ChatMessage from '@/components/Chat/ChatMessage';
export default {
  name: 'ChatView',
  components: {
    ChatSidePanel,
    ChatInput,
    ChatMessage,
  },
  data: () => ({ drawer: null }),
};
</script>

<style></style>
