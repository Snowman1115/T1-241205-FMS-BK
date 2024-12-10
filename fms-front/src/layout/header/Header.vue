<template>
  <div class="header">
    <Collapse class="collapse"/>
    <BreadCrumb/>
  </div>
</template>

<script setup lang="ts">
import Collapse from "@/layout/header/Collapse.vue";
import BreadCrumb from "@/layout/header/BreadCrumb.vue";

</script>

<style lang="scss" scoped>
.header {
  display: flex;
  align-items: center;
  .collapse {
    margin-right: 10px;
  }
  .userAvatar {
    position: absolute;
    right: 15px;
  }
}
</style>