import Vue from 'vue';

const run = (element) => {
	Vue.component('test-component', {
	  data: () => ({
	      message: '',
	    }),
	  template: '<span>{{ message }}<input type="button" value="Test Click" v-on:click="message=\'Hi, there!.\'" /></span>',
	});

	const v = new Vue({
	  el: element,
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
