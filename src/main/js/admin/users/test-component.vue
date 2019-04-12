<template>
  <span>
    {{someString}}: {{ message }}
    <input type="button" value="Test Click" @click="message='Hi, again!.'; theString='From test component'" />
  </span>
</template>

<script>
const component = {
  data: () => ({
    message: '',
  }),
  props: {
    someString: String,
  },		
};

export default component;
</script>
