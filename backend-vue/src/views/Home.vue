<template>
  <div class="home">
    <HelloWorld msg="home页面"/>
  </div>
</template>

<script>
  // @ 是 /src 的一个别名
  import HelloWorld from '@/components/HelloWorld.vue'

  export default {
    name: 'home',
    components: {
      HelloWorld
    }
  }
</script>
