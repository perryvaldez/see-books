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
	    
	    getCheckedRoles: function () {
	    	return this.selectedRoles.filter(item => item.checked).map(item => item.key);
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
	      for (let i = 0; i < result.data.length; i++) {
	    	let roleData = { key: result.data[i].key, value: result.data[i].value, checked: true };
	        Vue.set(this.selectedRoles, i, roleData);
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
