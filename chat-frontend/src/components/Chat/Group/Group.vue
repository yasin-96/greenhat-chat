<template>
  <div>
    <GroupInfo />
    <GroupUser />
  </div>
</template>

<script>

import GroupInfo from '@/components/Chat/GroupInfo';
import GroupUser from '@/components/Chat/Group/GroupUser';

export default {
  name: 'Group',
  components: {
    GroupInfo,
    GroupUser
  },
};
</script>

<style></style>
