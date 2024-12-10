import axios from "axios";
import message from "@/utils/message.js";

const request = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 5000
})

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=UTF-8';
    let user = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {}
    if (user) {
        config.headers['token'] = user.token;
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
    if (res.code === '401') {
        message.error(res.message);
    }
    return res;
}, error => {
    return Promise.reject(error);
})

export default request;

