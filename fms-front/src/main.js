import './assets/main.sass'

import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router";

import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import { createPinia } from 'pinia'

const pinia = createPinia()
const app = createApp(App);
app.use(ElementPlus, { size: 'medium' })
app.use(router)
app.use(pinia)
app.mount("#app")

