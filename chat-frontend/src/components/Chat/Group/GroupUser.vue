<template>
  <div>
      <v-list class="pl-14" shaped>
        <v-list-item v-for="userg in userInGroup" :key="userg._id">
          <v-avatar :key="userg._id" class="d-block " color="blue-grey lighten-3" size="32">
            <img v-if="userg.avatarPicture" :src="userg.avatarPicture" />
            <span class="title" v-else> {{ userg.avatarName }} </span>
          </v-avatar>
           <span class="pl-4">{{userg.username}}</span>
        </v-list-item>
      </v-list>
    </div>
</template>

<script>
import { mapState } from "vuex";
export default {
    name:"GroupUser",
    computed: {
        ...mapState({
            userInGroup: (state) => state.group.activeGroup.users
        })
    }
}
</script>

<style>

</style>