<template>
  <el-menu-item index="/dashboard">
    <el-icon><HomeFilled/></el-icon>
    <template #title>Dashboard</template>
  </el-menu-item>
  <el-sub-menu index="1">
    <template #title>
      <el-icon><Files/></el-icon>
      <span>File Management</span>
    </template>
    <el-menu-item index="/file"><el-icon><Document/></el-icon>Files</el-menu-item>
    <el-menu-item index="/folder"><el-icon><Folder/></el-icon>Folder</el-menu-item>
  </el-sub-menu>

</template>

<script setup>
import { Files, HomeFilled, Folder, Document } from '@element-plus/icons-vue';
</script>

<style lange="scss" scoped>

</style>