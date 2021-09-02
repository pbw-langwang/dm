<template>
  <div id="app">
    <router-view />

    <van-tabbar v-model="active" route>
      <van-tabbar-item name="home" to="/" icon="star-o">推荐</van-tabbar-item>
      <van-tabbar-item name="search" to="/video" icon="chat-o"
        >圈子</van-tabbar-item
      >
      <van-tabbar-item name="search" to="/gift" icon="point-gift-o"
        >福利</van-tabbar-item
      >
      <van-tabbar-item name="setting" to="/setting" icon="smile-o"
        >我的</van-tabbar-item
      >
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      active: "home",
    };
  },
};
</script>
