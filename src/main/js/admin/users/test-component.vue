<template>
  <span>{{ message }}<input type="button" value="Test Click" v-on:click="message='Hi, again!.'" /></span>
</template>

<script>
module.exports = {
  data: () => ({
    message: '',
  }),
}
</script>
