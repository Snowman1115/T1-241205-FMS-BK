<template>
  <MenuLogo/>
  <el-menu
      default-active="/dashboard"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      router
  >
    <MenuItem/>
  </el-menu>
</template>

<script setup>
import { computed } from "vue";
import { menuStore } from '@/store/menu/index.js';
import MenuLogo from "@/layout/menu/MenuLogo.vue";
import MenuItem from "@/layout/menu/MenuItem.vue";

const store = menuStore();
const isCollapse = computed(() => {
  return store.getCollapse;
})

</script>

<style lang="scss" scoped>
.el-menu {
  transition: none !important;
}
</style>