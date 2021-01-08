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
    Settings
  },
  data: () => ({ drawer: null }),
};
</script>

<style></style>
