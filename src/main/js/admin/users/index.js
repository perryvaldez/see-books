import Vue from 'vue';
import TestComponent from './test-component.vue';
import TestDialog from './test-dialog.vue';

const run = (element) => {
	const v = new Vue({
	  el: element,
	  components: { TestComponent, TestDialog },
	  data: {
	      message: '',
	  },
	  methods: {
	    launchAddRole: (evt) => {
	        v.message = 'Test Vue component';	        
	    }
	  },
	  delimiters: ['$[', ']'],
	});	
};

export default {
	run,
};
