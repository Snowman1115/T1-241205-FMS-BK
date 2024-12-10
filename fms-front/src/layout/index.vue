<template>
  <el-container class="layout">
    <el-aside :width="sideBarWidth" class="aside"><MenuBar/></el-aside>
    <el-container>
      <el-header class="header"><Header/></el-header>
      <el-main class="main">
        <RouterView/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import MenuBar from "@/layout/menu/MenuBar.vue";
import Header from "@/layout/header/Header.vue";

import {computed} from "vue";
import { menuStore } from '@/store/menu';


const store = menuStore();

const sideBarWidth = computed(() => {
  if (store.getCollapse == true) {
    return "auto";
  } else {
    return "250px"
  }
})
</script>

<style lang="scss" scoped>
.layout {
  height: 100%;
  .aside {
    background-color: #FAFAFA;
    border-right: #eee 1px solid;
  }
  .header {
    background: #fff;
    display: flex;
    align-items: center;
  }
  .main {
    background-color: #eee;
    padding: 20px 0 0 0;
    margin: 0px;
    height: 100%;
    width: 100%;
    max-width: 100%;
    box-sizing: border-box;
  }
}
</style>