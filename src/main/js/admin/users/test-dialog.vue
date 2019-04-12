<template>
    <md-dialog :md-active.sync="open">
      <md-dialog-title>Test Dialog</md-dialog-title>
      <p>This is a test dialog.</p>
     
      <md-dialog-actions>
        <md-button class="md-primary" @click="$emit('on-request-close', $event)">Close</md-button>
      </md-dialog-actions>
    </md-dialog>
</template>

<script>
import Vue from 'vue';
import { MdDialog, MdDialogTitle, MdDialogActions, MdButton, MdContent } from 'vue-material/dist/components';
import 'vue-material/dist/vue-material.min.css';
import 'vue-material/dist/theme/default.css';

Vue.use(MdDialog);
Vue.use(MdButton);

const component = {
  name: 'TestDialog',
  data: () => ({}),
  props: {
    open: Boolean,
  },
};

export default component;
</script>

<style lang="scss" scoped>
.md-dialog {
  max-width: 768px;
  width: 500px;
}
</style>
