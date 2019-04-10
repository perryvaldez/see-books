import common from '../../common';

const run = (element) => {
	common.createVueComponent('test-component', {
	  data: () => ({
	      message: '',
	    }),
	  template: '<span>$[ message ]<input type="button" value="Test Click" v-on:click="message=\'Hi, there! From component.\'" /></span>',
	});

	const v = common.createVue({
	  el: element,
	  data: {
	      message: '',
	  },
	  methods: {
	    launchAddRole: (evt) => {
	        v.message = 'Test only from Vue as component';
	    }
	  },
	});	
};

export default {
	run,
};
