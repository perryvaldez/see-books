import Vue from 'vue';
import axios from 'axios';
import path from 'path';
import appconfig from '../../appconfig';
import RoleDialog from './components/role-dialog.vue';

const run = (element) => {
	const v = new Vue({
	  el: element,
	  components: { RoleDialog },
	  data: {
	      showRoleDialog: false,
	      selectedRoles: [],
	  },
	  methods: {
	    launchAddRole: (evt) => {
	        v.showRoleDialog = true;
	    },
	    
	    handleRequestClose: (e) => {
	    	v.showRoleDialog = false;
	    },
	    
	    handleSave: (e) => {
	    	console.log('handleSave() triggered...', v.selectedRoles);
	    },
	  },
	  created: function () {
        const el = document.forms['form-user'].elements;
        let index = 0;
	    for (let i = 0; i < el.length; i += 1) {
	      if (el[i].name.startsWith('roleIds[')) {
	        Vue.set(this.selectedRoles, index, el[i].value);
	        index += 1;
	      }
	    }
	  },
	  mounted: async function () {
	    const userId = document.forms['form-user'].id.value;
	    try {
	      const apiUrl = path(appconfig.API_BASE_URL, 'users', userId, 'roles');
	      const result = await axios.get(apiUrl);
	      console.log('axios: get apiUrl: ', result);
	    } catch (ex) {
	      console.error('Error while retrieving user roles: ', ex);
	    }
	  },
	  delimiters: ['$[', ']'],
	});	
};

export default {
	run,
};
