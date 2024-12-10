<template>
  <div class="file">
    <div class="queryForm">
      <el-input type="text" v-model="fileName" suffix-icon="List" style="width: 200px" placeholder="File Name"></el-input>
      <el-input type="text" v-model="fileType" suffix-icon="Iphone" style="width: 200px" placeholder="File Type"></el-input>
      <el-button type="primary" @click="handleQuery">Query <el-icon style="margin-left: 5px"><Search/></el-icon></el-button>
      <el-button type="warning" @click="handleClear">Clear <el-icon style="margin-left: 5px"><Delete/></el-icon></el-button>
    </div>
    <div class="actionButton">
      <el-upload
        :show-file-list="false"
        :before-upload="handleFileUpload"
        style="display: inline-block; margin-right: 5px"
    >
      <el-button type="primary">Import<el-icon style="margin-left: 5px"><Upload/></el-icon></el-button>
    </el-upload>
      <el-button type="danger" @click="handleBatchDelete">Batch Del <el-icon style="margin-left: 5px"><Delete/></el-icon></el-button>
    </div>
    <el-main>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection" width="55" />
        <el-table-column property="id" label="ID" width="55" />
        <el-table-column property="folderId" label="Folder ID" />
        <el-table-column property="name" label="Name" />
        <el-table-column property="type" label="Type" />
        <el-table-column property="size" label="Size" />
        <el-table-column property="lastAccess" label="Last Access" />
        <el-table-column property="createTime" label="Create Date" />
        <el-table-column width="200px" label="Action">
          <el-button type="primary" size="small" @click="handleView">View <el-icon style="margin-left: 5px"><EditPen/></el-icon></el-button>
          <el-button type="danger" size="small" @click="handleDelete">Delete <el-icon style="margin-left: 5px"><Delete/></el-icon></el-button>
        </el-table-column>
      </el-table>

      <el-pagination style="margin-top: 10px"
                     v-model:current-page="pageNum"
                     v-model:page-size="pageSize"
                     :page-sizes="[10, 20, 30, 40]"
                     size="small"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total"
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
      />
    </el-main>
  </div>
</template>

<script setup>
import {ref, reactive, onMounted} from "vue";
import { Upload, Download, Search, Plus, Delete, EditPen } from '@element-plus/icons-vue';

import request from "@/utils/request.js";
import message from "@/utils/message.js";
import router from "@/router/index.js";

let pageNum = ref(1);
let pageSize = ref(10);
let total = ref(0);
let fileName = ref("");
let fileType = ref("");
let tableData = reactive([]);

const load = async () => {
  await request.get('http://localhost:9090/files', {
    params: {
      page: pageNum.value,
      pageSize: pageSize.value,
      fileName: fileName.value,
      fileType: fileType.value
    }
  }).then(res => {
    tableData.length = 0;
    Object.assign(tableData, res.data.rows);
    total.value = res.data.total;
  }).catch(error => {
    message.error(error.message);
  });
}

onMounted(() => {
  let user = localStorage.getItem("fms_user") ? JSON.parse(localStorage.getItem("fms_user")) : null;
  if (user == null) {
    message.error("Please Login.");
    router.push("/login");
  }
  load();
})

const handleQuery = async () => {
  load();
}

const handleClear = () => {
  fileName.value = "";
  fileType.value = "";
  load();
};

const handleFileUpload = async (file) => {
  const formData = new FormData();
  formData.append('file', file);

  await request.post('http://localhost:9090/files/upload', formData).then(res => {
    if (res.code != '200') {
      message.error(res.message);
    } else {
      message.success(res.message);
      load();
    }
  }).catch(error => {
    message.error(error.message);
  });
}

const handleSizeChange = (n) => {
  pageSize.value = n;
  load();
}

const handleCurrentChange = (n) => {
  pageNum.value = n;
  load();
}

</script>

<style lang="scss" scoped>
.file {
  .queryForm {
    display: flex;
    margin-left: 20px;
    margin-right: 20px;
    .el-input {
      margin-right: 8px;
    }
  }
  .actionButton {
    display: flex;
    margin-top: 10px;
    margin-left: 20px;
  }
  .pagination {
    margin-top: 10px;
  }
}
</style>