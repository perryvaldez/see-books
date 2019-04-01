import Vue from 'vue';

export const sample = () => 'hello, world!';

export const createVue = (opts = {}) => {
  opts.delimiters = ['$[', ']'];

  return new Vue(opts);
};
