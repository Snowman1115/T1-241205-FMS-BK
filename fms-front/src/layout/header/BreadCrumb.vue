<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item v-for="(crumb, index) in breadcrumbs" :key="index">
      <router-link v-if="index < breadcrumbs.length - 1" :to="crumb.path">
        {{ crumb.breadcrumb }}
      </router-link>
      <span v-else>{{ crumb.breadcrumb }}</span>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import { useRoute } from 'vue-router';
import {computed} from "vue";

const route = useRoute();

// Filter breadcrumbs to only include routes with the "breadcrumb" meta key
const breadcrumbs = computed(() => {
  return route.matched
      .filter((match) => match.meta.breadcrumb) // Exclude routes without breadcrumb meta
      .map((match) => ({
        path: match.path,
        breadcrumb: match.meta.breadcrumb,
      }));
});
</script>
