import axios from "axios";
import message from "@/utils/message.js";
import router from "@/router/index.js";

const request = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 5000
})

request.interceptors.request.use(config => {
    if (config.data instanceof FormData) {
        // Remove Content-Type to let the browser set it for FormData
        delete config.headers['Content-Type'];
    } else {
        // Set default Content-Type for non-FormData requests
        config.headers['Content-Type'] = 'application/json;charset=UTF-8';
    }

    let token = localStorage.getItem('fms_user') ? JSON.parse(localStorage.getItem('fms_user')) : {}
    if (token) {
        config.headers['token'] = token;
    }
    return config;
}), error => {
    return Promise.reject(error);
}

request.interceptors.response.use(response => {
    let res = response.data;
    if (response.config.responseType === "blob") {
        return res;
    }
    if (typeof res === "String") {
        res = res ? JSON.parse(res) : res;
    }
    if (res.code == '401') {
        message.error(res.message);
        localStorage.removeItem("fms_user");
        router.push('/login');
    }
    return res;
}, error => {
    return Promise.reject(error);
})

export default request;

