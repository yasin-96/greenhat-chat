<template>
  <v-container fluid class="overflow-y-auto mb-72">
    <v-timeline>
      <v-timeline-item v-for="(msg) in messages" :key="msg.id" large>
        <template v-slot:icon>
          <v-avatar>
            <!-- <img :src="`http://i.pravatar.cc/${n}`" /> -->
          </v-avatar>
        </template>
        <template v-slot:opposite>
          <span>timestamp</span>
        </template>
        <v-card class="elevation-3">
          <v-card-text>
            {{ msg.content }}
          </v-card-text>
        </v-card>
      </v-timeline-item>
    </v-timeline>
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'ChatMessage',
  data: () => ({
    date: new Date().toLocaleDateString('de'),
    tmpMsg: [{ _id: '', created: '', groupId: '', content: [] }],
  }),
  computed: {
    ...mapState({
      messages: (state) => state.chat.messages,
    }),
  },
};
</script>

<style></style>
