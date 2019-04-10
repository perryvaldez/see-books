import Vue from 'vue';

const createVue = (opts = {}) => {
  opts.delimiters = ['$[', ']'];

  return new Vue(opts);
};

const createVueComponent = (name, opts = {}) => {
  opts.delimiters = ['$[', ']'];
  
  return Vue.component(name, opts);
};

export default {
	createVue,
	createVueComponent,
};
