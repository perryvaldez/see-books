import Vue from 'vue';
import RoleDialog from './components/role-dialog.vue';

const run = (element) => {
	const v = new Vue({
	  el: element,
	  components: { RoleDialog },
	  data: {
	      showRoleDialog: false,
	  },
	  methods: {
	    launchAddRole: (evt) => {
	        v.showRoleDialog = true;
	    },
	    
	    handleRequestClose: (e) => {
	    	v.showRoleDialog = false;
	    },
	    
	    handleSave: (e) => {
	    	console.log('handleSave() triggered...');
	    },
	  },
	  delimiters: ['$[', ']'],
	});	
};

export default {
	run,
};
