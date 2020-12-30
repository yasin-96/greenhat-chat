<template>
  <div>
    <v-avatar
      v-for="group in ownGroups"
      :key="group._id"
      class="d-block text-center mx-auto mb-9"
      color="grey lighten-1"
      size="28"
      rounded
      @click="loadGroup(group._id)"
    >
      <img :src="`http://i.pravatar.cc/${group + 10}`" />
    </v-avatar>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex';
export default {
  name: 'OwnGroups',
  props: {
    groupList: {
      type: Object,
      //   required: true,
    },
  },
  methods: {
    async loadGroup(groupId) {
      console.log(groupId);
      await this.$store.dispatch('user/act_loadGroupInfos', groupId);
    },
  },
  computed: {
    ...mapState({
      ownGroups: (state) => state.user.ownGroups,
    }),
    ...mapGetters({
      usersGroups: 'usersGroups',
    }),
    ownGroups() {
      return null;//this.usersGroups(),
    },
  }
};
</script>

<style></style>
