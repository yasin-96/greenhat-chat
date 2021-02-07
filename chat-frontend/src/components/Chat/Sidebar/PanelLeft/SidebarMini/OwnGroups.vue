<template>
  <div class="text-center">
    <v-tooltip right v-for="(group,i) in userGroups" :key="i">
      <template v-slot:activator="{ on, attrs }">
        <v-avatar
          id="ownGroups"
          v-bind="attrs"
          v-on="on"
          :key="group._id"
          :class="activeGroupID == group._id ? 'mb-5 text-white' : 'mb-5'"
          :color="activeGroupID == group._id ? 'sidebarMiniActiveGroup' : group.groupColor || 'sidebarMiniNotActiveGroups'"
          size="40"
          :rounded="activeGroupID != group._id ? false : true"
          @click="loadGroup(group._id)"
        >
        <span>
          {{String(group.name)
              .slice(0, 2)
              .toUpperCase()}}
        </span>
        </v-avatar>
      </template>
      <span>{{group.name}}</span>
    </v-tooltip>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'OwnGroups',
  methods: {
    /**
     * 
     */
    loadGroup(groupId) {
      console.log(groupId);
      const groupToLoad = {
        _id: groupId,
      };
      this.$store.dispatch('group/act_loadGroupInfos', groupToLoad);
      this.$store.dispatch('chat/act_loadAllMessagesFromGroup', groupToLoad);
    },
  },
  computed: {
    ...mapState({
      userGroups: (state) => state.group.userGroups,
      activeGroupID: (state) => state.group.activeGroupId,
    }),
  },
};
</script>