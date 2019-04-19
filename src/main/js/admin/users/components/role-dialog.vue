<template>
    <md-dialog :md-active.sync="open">
      <md-dialog-title>Add Roles</md-dialog-title>

      <md-dialog-content>
        <p>Select the roles to associate with the user:</p>
      </md-dialog-content>

      <md-dialog-actions>
        <md-button class="md-primary" @click="$emit('on-save', $event)">Save</md-button>
        <md-button class="md-primary" @click="$emit('on-request-close', $event)">Close</md-button>
      </md-dialog-actions>
    </md-dialog>
</template>

<script>
import Vue from 'vue';
import { MdDialog, MdDialogTitle, MdDialogActions, MdButton, MdDialogContent } from 'vue-material/dist/components';
import axios from 'axios';
import urlJoin from 'url-join';
import appconfig from '../../../appconfig';
import 'vue-material/dist/vue-material.min.css';
import 'vue-material/dist/theme/default.css';

Vue.use(MdDialog);
Vue.use(MdButton);

const component = {
  name: 'RoleDialog',
  data: () => ({}),
  props: {
    open: Boolean,
    selectedRoleIds: Array,
  },
  updated: async function () {
	console.log('role-dialog: updated: open: ', this.open);
	console.log('role-dialog: updated: selectedRoleIds: ', this.selectedRoleIds);
	console.log('role-dialog: updated: exceptids: ', this.selectedRoleIds.join(','));
	
	if (this.open) {
		let api = 'roles';
		
		if (this.selectedRoleIds && this.selectedRoleIds.length > 0) {
			api = `roles?exceptids=${encodeURIComponent(this.selectedRoleIds.join(','))}`;
		}
		
	    const apiUrl = urlJoin(appconfig.API_BASE_URL, api);
	    const result = await axios.get(apiUrl);
	    console.log('role-dialog: updated: axios result: ', result.data);		
	}
  },
};

export default component;
</script>

<style lang="scss" scoped>
.md-dialog {
  width: 500px;
}
</style>
