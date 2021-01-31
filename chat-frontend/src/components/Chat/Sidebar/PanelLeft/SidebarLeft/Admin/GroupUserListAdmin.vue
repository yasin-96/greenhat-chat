<template>
  <v-container>
    <v-card outlined class="mx-auto elevation-4" max-width="500">
      <v-data-table :search="searchInTable" :headers="headers" :items="activeGroup.users" :items-per-page="5">
        <template v-slot:top>
          <v-container class="pt-10">
            <v-toolbar flat color="transparent">
              <v-text-field v-model="searchInTable" placeholder="Suche nach User- und Avatarnamen"></v-text-field>
              <v-spacer></v-spacer>
              <v-btn fab small @click="openDialogForAddingUserToGroup"><v-icon>mdi-account-plus</v-icon></v-btn>
            </v-toolbar>
          </v-container>
        </template>
        <template v-slot:item="{ item }">
          <tr :class="adminOfGroup !== item.userId ? 'groupMember text-center' : 'groupAdmin text-center'">
            <td v-if="item.avatarPicture">
              <v-avatar color="grey" size="36">
                <v-img class="elevation-6" alt="" :src="item.avatarPicture"></v-img>
              </v-avatar>
            </td>
            <td v-else>{{ item.avatarName }}</td>
            <td class="">{{ item.username }}</td>
            <td class="" v-if="adminOfGroup !== item.userId">
              <v-btn fab small text color="error">
                <v-icon @click="deleteItem(item.userId)">
                  mdi-delete
                </v-icon>
              </v-btn>
            </td>
            <td class="text-center" v-else>
              <v-divider class="mx-auto" width="50"></v-divider>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-card>
    <AddUserToGroupAdmin />
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
import AddUserToGroupAdmin from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/AddUserToGroupAdmin';
export default {
  name: 'GroupUserListAdmin',
  components: {
    AddUserToGroupAdmin,
  },
  data: () => ({
    searchInTable: '',
    headers: [
      { text: 'Avatar', value: 'avatarName', align: 'center' },
      { text: 'Username', value: 'username', align: 'center' },
      { text: 'Option', value: 'userId', align: 'center' },
    ],
  }),
  computed: {
    ...mapState({
      adminOfGroup: (state) => state.user.user.id,
      activeGroup: (state) => state.group.activeGroup,
    }),
    userlist() {
      if (this.activeGroup) {
        return this.activeGroup.users;
      }

      return null;
    },
  },
  methods: {
    openDialogForAddingUserToGroup() {
      this.$store.dispatch('settings/act_openDialogForAddUserToGroup');
    },
  },
};
</script>

<style></style>
