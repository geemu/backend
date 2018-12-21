<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    这是哪里啊？
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: {
      type: String,
      required: true,
      default: ''
    }
  }
}
</script>

<style scoped lang="scss">

</style>
