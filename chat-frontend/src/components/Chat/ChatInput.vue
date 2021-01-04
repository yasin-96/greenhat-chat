<template>
  <div>
    <!-- <v-footer class="mb-72" app color="transparent" inset>
    </v-footer> -->
    <v-footer app color="transparent" inset>
      <v-container fluid>
        <emoji v-show="enableEmojiDialog" @select="selectEmoji" class="ml-4 mb-2" />
        <v-text-field v-model="message" background-color="grey lighten-1" dense flat hide-details rounded solo clearable>
          <template v-slot:prepend-inner>
            <v-icon @click="openEmojiDialog">mdi-emoticon-happy-outline</v-icon>
          </template>
          <template v-slot:append-outer>
            <div>
              <v-icon @click="openEmojiDialog" color="send" :disabled="!message">mdi-send</v-icon>
            </div>
          </template>
        </v-text-field>
      </v-container>
    </v-footer>
  </div>
</template>

<script>
import { VEmojiPicker } from 'v-emoji-picker';
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
      this.enableEmojiDialog = !this.enableEmojiDialog;
    },
    openEmojiDialog() {
      this.enableEmojiDialog = !this.enableEmojiDialog;
    },
  },
};
</script>

<style></style>
