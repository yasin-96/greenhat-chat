<template>
  <v-container>
    <v-timeline>
      <v-timeline-item v-for="n in 10" :key="n" large>
        <template v-slot:icon>
          <v-avatar>
            <img :src="`http://i.pravatar.cc/${n}`" />
          </v-avatar>
        </template>
        <template v-slot:opposite>
          <span>{{ date }}</span>
        </template>
        <v-card class="elevation-3">
          <v-card-text>
            Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire
            principes at. Est et nobis iisque percipit, an vim zril disputando voluptatibus, vix an salutandi
            sententiae.
          </v-card-text>
        </v-card>
      </v-timeline-item>
    </v-timeline>
  </v-container>
</template>

<script>
export default {
  name: 'ChatMessage',
  data: () => ({
    date: new Date().toLocaleDateString('de'),
    tmpMsg: [{ _id: '', created: '', groupId: '', content: [] }],
  }),
};
</script>

<style></style>
