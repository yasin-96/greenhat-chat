<template>
  <div>
    <!-- <v-footer class="mb-72" app color="transparent" inset>
    </v-footer> -->
    <v-footer app color="transparent" inset>
      <v-container fluid>
        <emoji v-show="enableEmojiDialog" @select="selectEmoji" class="ml-4 mb-2" />
        <v-text-field
          v-model="message"
          background-color="grey lighten-1"
          dense
          flat
          hide-details
          rounded
          solo
          clearable
        >
          <template v-slot:prepend>
            <v-icon @click="openEmojiDialog">mdi-emoticon-happy-outline</v-icon>
          </template>
          <template v-slot:append-outer>
            <div>
              <v-icon @click="sendMessage" @keyup.enter="sendMessage" color="send" :disabled="!message"
                >mdi-send</v-icon
              >
            </div>
          </template>
        </v-text-field>
      </v-container>
    </v-footer>
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
      groupId: (state) => state.user.activeGroupId,
    }),
  },
};
</script>

<style></style>
