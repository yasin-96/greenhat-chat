<template>
  <div>
    <GroupInfo />
    <div>
      <v-list class="pl-14" shaped>
        <v-list-item v-for="n in 5" :key="n">
          <v-avatar :key="n" class="d-block " color="grey lighten-1" size="28">
            <img :src="`http://i.pravatar.cc/${n * 10}`" />
          </v-avatar>
          <span class="pl-4"> Item {{ n }} </span>
        </v-list-item>
      </v-list>
    </div>
  </div>
</template>

<script>
import GroupInfo from '@/components/Chat/GroupInfo';

export default {
  name: 'Group',
  components: {
    GroupInfo,
  },
};
</script>

<style></style>
