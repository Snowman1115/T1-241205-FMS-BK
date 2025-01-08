<template>
  <RouterLink to="/dashboard" class="logo">
    <img :src="MenuLogo">
    <p v-if="isShow" class="logo-title">{{ title }}</p>
  </RouterLink>
</template>

<script lang="ts" setup>
import {computed} from "vue";
import { menuStore } from '@/store/menu';
import MenuLogo from '@/assets/logo/fms-logo.png';

const title = "FMS System";
const store = menuStore();
const isShow = computed(() => {
  return !store.getCollapse
})

</script>


<style lang="scss" scoped>
.logo {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 6.5vh;
  background-color: #ccc;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  img {
    width: 25px;
    height: 25px;
    text-shadow: 2px 2px;
  }

  .logo-title {
    color: #444444;
    font-weight: 700;
    font-size: 20px;
    margin-left: 10px;
  }
}


/* Transition styles */
.fade-enter-active, .fade-leave-active {
  transition: opacity 5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}
</style>