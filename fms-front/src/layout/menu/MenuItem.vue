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
    <div class="logout">
      <el-menu-item @click="dialogVisible = true">
        <el-icon><CloseBold/></el-icon>
        <template #title>Logout</template>
      </el-menu-item>
      <el-dialog
          v-model="dialogVisible"
          title="Logout Confirmation"
          width="500"
          :before-close="handleClose"
      >
        <span>Are you sure you want to logout ?</span>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogVisible = false">Cancel</el-button>
            <el-button type="warning" @click="handleLogout">
              Confirm
            </el-button>
          </div>
        </template>
      </el-dialog>
    </div>
</template>

<script setup>
import { Files, HomeFilled, Folder, Document, CloseBold } from '@element-plus/icons-vue';
import { ref } from 'vue';
import router from "@/router/index.js";
import request from '@/utils/request.js'
import message from '@/utils/message.js';

let dialogVisible = ref(false);

const handleLogout = async () => {
  dialogVisible.value = false;

  try {
    await request.post('http://localhost:9090/auth/logout').then(res => {
      if (res.code !== 200) {
        message.error('Failed to Logout!');
      } else {
        localStorage.removeItem("fms_user");
        message.success('Logout Successful!');
        router.push('/login');
      }

    })
  } catch (e) {
    message.warning(e.message);
  }
}

function handleClose() {
  dialogVisible.value = false;
}
</script>

<style lange="scss" scoped>

</style>