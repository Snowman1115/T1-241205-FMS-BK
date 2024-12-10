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
      <el-popconfirm
          confirm-button-text="Yes"
          cancel-button-text="No"
          icon="el-icon-info"
          icon-color="red"
          title="Confirm Batch Delete?"
          @confirm="handleBatchDelete"
      >
        <template v-slot:reference>
          <el-button type="danger" style="margin-left: 5px; margin-right: 5px">Batch Delete<el-icon style="margin-left: 5px"><Delete/></el-icon></el-button>
        </template>
      </el-popconfirm>
    </div>
    <el-main>
      <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column property="id" label="ID" width="55" />
        <el-table-column property="folderId" label="Folder ID" width="100"/>
        <el-table-column property="name" label="Name"/>
        <el-table-column property="type" label="Type" width="55" />
        <el-table-column property="size" label="Size" width="55" />
        <el-table-column property="createTime" label="Create Date" width="180" />
        <el-table-column width="320" label="Action">
          <template v-slot="scope">
            <el-button type="primary" size="small" @click="handleDownload(scope.row.url)">Download <el-icon style="margin-left: 5px"><Download/></el-icon></el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope.row.id)">Delete <el-icon style="margin-left: 5px"><Delete/></el-icon></el-button>
          </template>
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
import { Upload, Download, Search, Delete } from '@element-plus/icons-vue';

import request from "@/utils/request.js";
import message from "@/utils/message.js";
import router from "@/router/index.js";

let pageNum = ref(1);
let pageSize = ref(10);
let total = ref(0);
let fileName = ref("");
let fileType = ref("");
let tableData = reactive([]);
let multipleSelection = reactive([]);

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
    // message.error(error.message);
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

const handleDelete = async (id) => {
  await request.delete('http://localhost:9090/files/' + id).then(res => {
    if (res.code != '200') {
      message.error(res.message);
    } else {
      message.success(res.message);
      load();
    }
  }).catch(error => {
    message.error(error.message);
  });
};

const handleDownload = (url) => {
  window.open('http://localhost:9090/files/download/' + url, '_blank');
};

const handleSelectionChange = (value) => {
  multipleSelection = value;
};

const handleBatchDelete = async () => {
  let ids = multipleSelection.map(item => item.id);
  try {
    const res = await request.delete('http://localhost:9090/files/del/batch/' + ids);
    if (res.code != '200') {
      message.error(res.message);
    } else {
      message.success(res.message);
      load();
    }
  } catch (error) {
    message.error(error.message);
  }
};

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