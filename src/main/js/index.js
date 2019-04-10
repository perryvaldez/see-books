import Vue from 'vue';

export const createVue = (opts = {}) => {
  opts.delimiters = ['$[', ']'];

  return new Vue(opts);
};

export const createVueComponent = (name, opts = {}) => {
  opts.delimiters = ['$[', ']'];
  
  return Vue.component(name, opts);
};
