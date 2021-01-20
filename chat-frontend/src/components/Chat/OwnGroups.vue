<template>
  <div class="ml-2">
    <v-avatar

      v-for="group in userGroups"
      :key="group._id"
      :class="activeGroupID == group._id ? 'mb-5 text-white': 'mb-5' "
      :color="activeGroupID == group._id ? 'lightGreen': 'grey lighten-1' "
      size="38"
      :rounded="activeGroupID == group._id ? false : true" 
      @click="loadGroup(group._id)"

      v-text="String(group.name).slice(0, 2).toUpperCase()"
    >
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
    loadGroup(groupId) {
      console.log(groupId);
      const groupToLoad = {
        _id: groupId
      }
      this.$store.dispatch('group/act_loadGroupInfos', groupToLoad);
    },
  },
  computed: {
    ...mapState({
      userGroups: (state) => state.group.userGroups,
      activeGroupID: (state) => state.group.activeGroupId
    }),
    ...mapGetters({
      usersGroups: 'usersGroups',
    }),
  }
};
</script>

<style></style>
