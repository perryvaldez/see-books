<template>
    <md-dialog :md-active.sync="open">
      <md-dialog-title>Test Dialog</md-dialog-title>
      <p>This is a test dialog.</p>
      
      <md-dialog-actions>
        <md-button class="md-primary" @click="showDialog = false">Close</md-button>
      </md-dialog-actions>
    </md-dialog>
</template>

<script>
import Vue from 'vue';
import { MdDialog, MdDialogTitle, MdDialogActions, MdButton } from 'vue-material/dist/components';
import 'vue-material/dist/vue-material.min.css';

Vue.use(MdDialog);
Vue.use(MdButton);

export default {
  name: 'TestDialog',
  data: function () {
    return {};
  },
  props: {
    open: Boolean,
  },
}
</script>

<style lang="scss" scoped>
.md-dialog {
  max-width: 768px;
}
</style>
