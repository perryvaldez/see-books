<template>
    <md-dialog :md-active.sync="open">
      <md-dialog-title>Add Roles</md-dialog-title>

      <md-dialog-content>
        <p>Select the roles to associate with the user:</p>
      </md-dialog-content>

      <md-dialog-actions>
        <md-button class="md-primary" @click="$emit('on-save', $event)">Save</md-button>
        <md-button class="md-primary" @click="$emit('on-request-close', $event)">Close</md-button>
      </md-dialog-actions>
    </md-dialog>
</template>

<script>
import Vue from 'vue';
import { MdDialog, MdDialogTitle, MdDialogActions, MdButton, MdDialogContent } from 'vue-material/dist/components';
import 'vue-material/dist/vue-material.min.css';
import 'vue-material/dist/theme/default.css';

Vue.use(MdDialog);
Vue.use(MdButton);

const component = {
  name: 'RoleDialog',
  data: () => ({}),
  props: {
    open: Boolean,
  },
};

export default component;
</script>

<style lang="scss" scoped>
.md-dialog {
  width: 500px;
}
</style>
