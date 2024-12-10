<template>
  <el-icon color="#bbb" class="no-inherit" size="25px" style="cursor: pointer" @click="setCollapse">
    <component :is="status ? Fold : Expand"></component>
  </el-icon>
</template>

<script setup>
import { computed } from "vue";
import { menuStore } from "@/store/menu";
import { Fold, Expand } from "@element-plus/icons-vue";

const store = menuStore();

const status = computed(() => {
  return !store.getCollapse
});

const setCollapse = () => {
  store.setCollapse(!store.getCollapse);
}
</script>

<style lang="scss">

</style>