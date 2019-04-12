import Vue from 'vue';
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
	  delimiters: ['$[', ']'],
	});	
};

export default {
	run,
};
