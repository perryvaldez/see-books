<template>
    <md-dialog :md-active.sync="open">
      <md-dialog-title>Add Roles</md-dialog-title>

      <md-dialog-content>
        <p>Select the roles to associate with the user:</p>
        
        <div id="role-list">
          <div v-for="(role, index) in roles" :key="role.key">
              <label class="role-label">
                <input type="checkbox" 
                       :name="`roleIds[${index}]`" 
                       :value="role.key" 
                       v-model="roles[index].checked" 
                /> {{ role.value }}
              </label>
          </div>          
        </div>
      </md-dialog-content>

      <md-dialog-actions>
        <md-button class="md-primary" @click="$emit('on-request-select', $event)">Select</md-button>
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
  data: () => ({
    roles: [],
  }),
  props: {
    open: Boolean,
    selectedRoleIds: Array,
  },
  watch: {
    open: async function (newOpen, oldOpen) {
      if (newOpen && (newOpen !== oldOpen)) {
        let api = 'roles';
          
        if (this.selectedRoleIds && this.selectedRoleIds.length > 0) {
          api = `roles?exceptids=${encodeURIComponent(this.selectedRoleIds.join(','))}`;
        }
          
        const apiUrl = urlJoin(appconfig.API_BASE_URL, api);
        const result = await axios.get(apiUrl);
              
        this.roles.length = 0; // Clear the array
        for (let i = 0; i < result.data.length; i++) {
          console.log('role-dialog: data: ', result.data[i]);
          let roleData = { key: result.data[i].key, value: result.data[i].value, checked: false };
          this.roles.push(roleData);
        }    	  
      }
    }	  
  },
};

export default component;
</script>

<style lang="scss" scoped>
.md-dialog {
  width: 500px;
}

#role-list {
  margin-top: 16px;
}
</style>
