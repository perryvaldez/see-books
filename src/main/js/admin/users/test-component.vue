<template>
  <span>{{someString}}: {{ message }}<input type="button" value="Test Click" v-on:click="message='Hi, again!.'; theString='From test component'" /></span>
</template>

<script>
export default {
  data: () => ({
    message: '',
  }),
  props: {
    someString: String,
  },
}
</script>
