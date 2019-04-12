import Vue from 'vue';
import TestComponent from './test-component.vue';
import TestDialog from './test-dialog.vue';

const run = (element) => {
	const v = new Vue({
	  el: element,
	  components: { TestComponent, TestDialog },
	  data: {
	      message: '',
	      theString: 'Init from root app',
	      showDialog: false,
	  },
	  methods: {
	    launchAddRole: (evt) => {
	        v.message = 'Test Vue component';
	        v.theString = 'From launchAddRole';
	        v.showDialog = true;
	    },
	    
	    handleRequestClose: (e) => {
	    	v.showDialog = false;
	    },
	  },
	  delimiters: ['$[', ']'],
	});	
};

export default {
	run,
};
