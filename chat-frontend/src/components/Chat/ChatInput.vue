<template>
  <div>
    <emoji v-show="enableEmojiDialog" @select="selectEmoji" class="ml-4 mb-2" />
    <v-bottom-navigation app dense class="elevation-0" color="danger" height="60px">
      <v-container fluid>
        <v-text-field
          v-model="message"
          background-color="chatInputTextMessage"
          dense
          flat
          hide-details
          rounded
          solo
          clearable
          @keyup.enter="sendMessage"
        >
          <template v-slot:prepend>
            <v-icon @click="openEmojiDialog">mdi-emoticon-happy-outline</v-icon>
          </template>
          <template v-slot:append-outer>
            <div>
              <v-icon @click="sendMessage" @keyup.enter="sendMessage" color="chatInputSendButton" :disabled="!message"
                >mdi-send</v-icon
              >
            </div>
          </template>
        </v-text-field>
      </v-container>
    </v-bottom-navigation>
  </div>
</template>

<script>
import { VEmojiPicker } from 'v-emoji-picker';
import { mapState } from 'vuex';
export default {
  name: 'ChatInput',
  components: {
    emoji: VEmojiPicker,
  },
  data: () => ({
    message: '',
    enableEmojiDialog: false,
  }),
  methods: {
    selectEmoji({ data }) {
      console.log('emoji', data);
      this.message += data;
    },
    openEmojiDialog() {
      this.enableEmojiDialog = !this.enableEmojiDialog;
    },
    sendMessage() {
      if (this.message && !!this.message) {
        const message = {
          userId: this.userId,
          groupId: this.groupId,
          content: this.message,
        };
        console.log('message', message);
        this.$store.dispatch('chat/act_createNewMessage', message);
        this.message = '';
        this.enableEmojiDialog = false;
      }
    },
  },
  computed: {
    ...mapState({
      userId: (state) => state.user.user.id,
      groupId: (state) => state.group.activeGroupId,
    }),
  },
};
</script>

<style></style>
