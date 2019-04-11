import Vue from 'vue';
import TestComponent from './test-component.vue';

const run = (element) => {
	const v = new Vue({
	  el: element,
	  components: { TestComponent },
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
