<template>
  <v-container>
    <div class="d-flex flex-no-wrap justify-center">
      <v-card outlined class="my-auto elevation-6" max-height="100px" max-width="180px">
        <v-container>
          <v-chip x-large label link>
            <h1 class="display-3 text-white">
              {{
                String(activeGroup.name)
                  .slice(0, 2)
                  .toUpperCase()
              }}
            </h1></v-chip
          >
        </v-container>

        <v-card-title primary-title style="position: relative">
          <div>
            <v-tooltip bottom>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  v-bind="attrs"
                  v-on="on"
                  color="warning"
                  small
                  absolute
                  top
                  right
                  fab
                  @click="setValuesForEditingSpecificGroupInformation(3)"
                >
                  <v-icon>mdi-pencil-outline</v-icon>
                </v-btn>
              </template>
              <span>
                {{ $t('chatView.user.userDetails.action.avatarPicture') }}
              </span>
            </v-tooltip>
          </div>
        </v-card-title>
      </v-card>

      <v-card outlined class="ml-14 elevation-6">
        <v-list>
          <v-list-item-group>
            <v-list-item>
              <v-list-item-icon>
                <v-tooltip left>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon v-bind="attrs" v-on="on">mdi-fingerprint</v-icon>
                  </template>
                  <span>{{ $t('chatView.user.userDetails.tooltip.id') }}</span>
                </v-tooltip>
              </v-list-item-icon>
              <v-list-item-content>
                <h4 class="body-1" ref="idToCopy">{{ activeGroup._id }}</h4>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-icon>
                <v-tooltip left>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon v-bind="attrs" v-on="on">mdi-badge-account</v-icon>
                  </template>
                  <span>{{ $t('chatView.user.userDetails.tooltip.username') }}</span>
                </v-tooltip>
              </v-list-item-icon>
              <v-list-item-content>
                <h4 class="body-1">{{ activeGroup.name }}</h4>
              </v-list-item-content>
              <v-list-item-action>
                <v-btn fab text small @click="setValuesForEditingSpecificGroupInformation(0)">
                  <v-icon small>
                    mdi-pencil-outline
                  </v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
            <v-list-item>
              <v-list-item-icon>
                <v-tooltip left>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon v-bind="attrs" v-on="on">mdi-account-tie</v-icon>
                  </template>
                  <span>{{ $t('chatView.user.userDetails.tooltip.email') }}</span>
                </v-tooltip>
              </v-list-item-icon>
              <v-list-item-content>
                <h4 class="body-1">{{ activeGroup.admin }}</h4>
                <h6 class="caption">{{ adminOfGroup }}</h6></v-list-item-content
              >
              <v-list-item-action>
                <v-btn fab text small @click="setValuesForEditingSpecificGroupInformation(1)">
                  <v-icon small>
                    mdi-pencil-outline
                  </v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </div>
    <EditValueForGroup />
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
import EditValueForGroup from '@/components/Chat/Sidebar/PanelLeft/SidebarLeft/Admin/EditValueForGroup';
export default {
  name: 'GroupDetailsAdmin',
  components: {
    EditValueForGroup,
  },
  methods: {
    setValuesForEditingSpecificGroupInformation(groupInfo) {
      console.log('userInfo', groupInfo);
      this.$store.dispatch('group/act_setGroupEditWindowSettingsBasedOnType', groupInfo);
      this.$store.dispatch('settings/act_openDialogForChangeValueForGroup');
    },
  },
  computed: {
    ...mapState({
      activeGroupId: (state) => state.group.activeGroupId,
      activeGroup: (state) => state.group.activeGroup,
      userGroups: (state) => state.group.userGroups,
      user: (state) => state.user.user,
      adminOfGroup: (state) => state.user.user.id,
    }),
  },
  watch: {
    enableOrDisbleUserAvatarPicture() {
      if (this.enableOrDisbleUserAvatarPicture) {
        this.removeAvatarPicture();
      }
    },
  },
};
</script>

<style></style>
