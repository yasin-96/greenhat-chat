<template>
  <v-container id="chatMessages" fluid class="overflow-y-auto mb-72">
    <v-timeline v-if="messagesToDisplay">
      <v-timeline-item
        v-for="(msg, index) in messagesToDisplay.messages"
        :key="index"
        large
        color="transparent"
      >
        <template v-slot:icon>
          <v-tooltip bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-avatar v-on="on" v-bind="attrs" v-if="msg.user.avatarPicture" class="tile mx-auto rounded" size="36">
                <img :src="msg.user.avatarPicture" alt="User" />
              </v-avatar>

              <span v-on="on" v-bind="attrs" v-else class="title">{{ msg.user.avatarName }}</span>
            </template>
            <span>{{ msg ? msg.user.username : '' }}</span>
          </v-tooltip>
        </template>
        <template v-slot:opposite>
          <span class="caption"> {{ msg ? msg.created : '' }}</span>
        </template>
        <v-card class="elevation-3">
          <v-card-text>
            {{ msg ? msg.content : '' }}
          </v-card-text>
        </v-card>
      </v-timeline-item>
    </v-timeline>
  </v-container>
</template>

<script>
import { mapState } from 'vuex';

/**
 *
 */
export default {
  name: 'ChatMessages',
  updated() {
    console.log("SCrolle jetzt", this.sizeOfMessages)
     this.$vuetify.goTo(this.pageHeight)
    },
  computed: {
    ...mapState({
      activeGroupId: (state) => state.group.activeGroupId,
      activeGroup: (state) => state.group.activeGroup,
      messagesFromGroup: (state) => state.chat.messagesOfGroup,
    }),
    /**
     *
     */
    messagesToDisplay() {
      if (this.messagesFromGroup && !!this.messagesFromGroup && this.messagesFromGroup.length && this.activeGroupId) {
        return this.messagesFromGroup.find((msg) => (msg.gid = this.activeGroupId));
      }
      return null;
    },

    sizeOfMessages() {
      if (this.messagesToDisplay) {
        return this.messagesToDisplay.messages.length;
      }
      return null;
    },
    pageHeight() {
      return document.getElementById("chatMessages").scrollHeight;
    },
  },
};
</script>

<style></style>
