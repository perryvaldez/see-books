import Vue from 'vue';
import axios from 'axios';
import urlJoin from 'url-join';
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
	  mounted: async function () {
	    const userId = document.forms['form-user'].id.value;
	    try {
	      const apiUrl = urlJoin(appconfig.API_BASE_URL, 'users', userId, 'roles');
	      const result = await axios.get(apiUrl);	      
	      console.log('axios: get apiUrl: ', result.data);
	      console.log('axios: selectedRoles: ', v.selectedRoles);
	      for (let i = 0; i < result.data.length; i++) {
	        Vue.set(this.selectedRoles, i, result.data[i]);
	      }
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
