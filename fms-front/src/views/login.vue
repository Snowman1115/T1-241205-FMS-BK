<template>
  <div class="main-container">
    <div class="left-container">
      <img src="@/assets/login/login-banner.png"/>
    </div>
    <div class="right-container">
      <div class="login-form-container">
        <div class="login-form-container-title">
          <div class="logo"><img src="@/assets/logo/fms-logo.png"/></div>
          <div class="title"><h2>File Management System</h2></div>
        </div>
        <div class="form-container">
          <el-form
              class="login-form"
              :model="formData"
              :rules="rules"
              ref="formDataRef"
              @submit.prevent
          >
            <el-form-item prop="username">
              <el-input clearable placeholder="Enter Username/Email" v-model.trim="formData.username">
                <template #prefix><el-icon><UserFilled/></el-icon></template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input clearable placeholder="Enter Password" v-model.trim="formData.password">
                <template #prefix><el-icon><Lock/></el-icon></template>
              </el-input>
            </el-form-item>
            <el-form-item prop="verifyCode">
              <div class="check-code-panel">
                <el-input clearable placeholder="Enter Verification Code " v-model.trim="formData.verifyCode">
                  <template #prefix><el-icon><Ticket/></el-icon></template>
                </el-input>
                <img :src="kaptchaImg" @click="getCaptcha" class="check-code"/>
              </div>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="op-btn" @click="handleLogin">Login</el-button>
            </el-form-item>
          </el-form>
        </div>
        <h4>Not A Member Yet? <span @click="router.push('/register')">Register Now</span></h4>
        <h3>Developed By Sn0w_15 @MIT Licenses</h3>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, onBeforeUnmount, reactive, ref} from "vue";
import { v4 as genUUID} from 'uuid';
import {UserFilled, Lock, Ticket} from '@element-plus/icons-vue';
import message from "@/utils/message.js";
import request from "@/utils/request.js";
import router from "@/router/index.js";

let formDataRef = ref(null);
let kaptchaImg = ref("");
let refreshCaptchaInterval = null;

const formData = reactive({
  username: "",
  password: "",
  verifyCode: "",
  kaptchaUUID: ""
})
const rules = {
  username: [{ required: true, message: "Please Enter Username/Email", trigger: "blur" }],
  password: [{ required: true, message: "Please Enter Password", trigger: "blur" }],
  verifyCode: [{ required: true, message: "Please Enter Verification Code", trigger: "blur" }]
}

const handleLogin = () => {
  formDataRef.value.validate(async (valid) => {
    if (valid) {
      const params = `username=${formData.username}&password=${formData.password}&verifyCode=${formData.verifyCode}&kaptchaUUID=${formData.kaptchaUUID}`;
      await request.post('/auth/login?' + params).then(res => {
        if (res.code != '200') {
          message.error(res.message);
          getCaptcha();
        } else {
          message.success(res.message);
          const jwt = res.data;
          localStorage.setItem("fms_user", JSON.stringify(jwt))
          router.push("/")
        }
      }).catch(error => {
        message.error(error.message || 'An error occurred');
      });
    } else {
      message.error("Login Failed. Please Fill In All The Blank.")
      return false;
    }
  });
};

const getCaptcha = async () => {
  let newUUID = genUUID();
  await request.get('/verifyCode/image', {
    params: {
      uuid: newUUID,
    }
  }).then(res => {
    formData.kaptchaUUID = newUUID;
    kaptchaImg.value = 'http://localhost:9090/verifyCode/image?uuid=' + newUUID;
  }).catch(error => {
    message.error(error);
  })
};

onMounted(() => {
  let user = localStorage.getItem("fms_user") ? JSON.parse(localStorage.getItem("fms_user")) : null;
  if (user != null) {
    message.error("Please Log Out To Login.");
    router.push("/");
  }
  getCaptcha();
  refreshCaptchaInterval = setInterval(() => {
    getCaptcha();
  }, 600000); // 10 minutes in milliseconds
})

onBeforeUnmount(() => {
  if (refreshCaptchaInterval) {
    clearInterval(refreshCaptchaInterval);
  }
});


</script>

<style lang="scss" scoped>
.main-container {
  height: 100vh;
  width: 100vw;
  background: url("@/assets/login/login-background.png") no-repeat center center;
  background-size: cover;
  display: flex;
  overflow: hidden;
  margin: 0;
  padding: 0;

  .left-container {
    flex: 1;
    display: flex;
    justify-content: center; /* Center the banner horizontally */
    align-items: center; /* Center the banner vertically */

    img {
      max-width: 75%; /* Adjust the banner size */
      height: auto;
      object-fit: contain;
    }
  }

  .right-container {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;

    .login-form-container {
      width: 400px;
      padding: 20px;
      background: rgba(255, 255, 255, 0.1);
      backdrop-filter: blur(10px);
      border-radius: 15px;
      box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
      border: 1px solid rgba(255, 255, 255, 0.2);
      display: flex;
      flex-direction: column;
      align-items: center;

      .login-form-container-title {
        display: flex;
        align-items: center;
        gap: 20px;
        margin-bottom: 20px;
        text-align: center;

        .logo img {
          width: 25px;
          margin-top: 10px;
          object-fit: contain;
        }

        .title h2 {
          color: #2d2d2d;
          font-weight: 600;
          font-size: 24px;
          margin: 0;
        }
      }

      .form-container {
        width: 100%;

        .el-form {
          .el-form-item {
            margin-bottom: 20px;

            .el-input {
              color: #a1a1a1;
              border-radius: 8px;
            }

            .el-button {
              width: 100%;
              border-radius: 8px;
              font-weight: bold;
            }
          }

          .check-code-panel {
            display: flex;
            align-items: center;
            gap: 10px;

            .check-code {
              width: 120px;
              height: 30px;
              cursor: pointer;
              border-radius: 5px;
            }
          }
        }
      }
    }
  }

  h3 {
    font-size: 12px;
    color: #a1a1a1;
    text-align: center;
    margin-top: 20px;
  }

  h4 {
    font-size: 11px;
    color: #a1a1a1;
    text-align: center;

    span {
      text-decoration: underline;
      cursor: pointer;
      &:hover {
        color: #007BFF;
      }
      &:active {
        color: #0056b3;
      }
    }
  }
}



</style>