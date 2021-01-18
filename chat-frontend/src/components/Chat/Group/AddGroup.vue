<template>
  <div>
    <v-avatar class="d-block tile mx-auto rounded mt-4" color="blueGrey" size="36">
      <v-icon color="white" @click="openDialog">mdi-plus</v-icon>
    </v-avatar>
    <NewGroupDialog />
  </div>
</template>

<script>
import NewGroupDialog from '@/components/Chat/Group/NewGroupDialog.vue';
export default {
  name: 'AddGroup',
  components: {
    NewGroupDialog,
  },
  methods: {
    openDialog() {
      this.$store.dispatch('group/act_toggleNewGroupWindow', true);
    },
  },
};
</script>

<style></style>
