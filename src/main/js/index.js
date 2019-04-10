import common from './common';
import adminUsersModule from './admin/users';

export const createVue = (opts = {}) => common.createVue(opts);
export const createVueComponent = (name, opts = {}) => common.createVueComponent(name, opts);

export const adminUsers = adminUsersModule;
