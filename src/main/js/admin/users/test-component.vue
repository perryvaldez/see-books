<template>
  <span>{{ message }}<input type="button" value="Test Click" v-on:click="message='Hi, again!.'" /></span>
</template>

<script>
export default {
  data: () => ({
    message: '',
  }),
}
</script>
