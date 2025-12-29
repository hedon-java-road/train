import * as Icons from '@ant-design/icons-vue';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import axios from 'axios';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app')

// 全局使用图片
const icons = Icons;
for (const i in icons) {
    app.component(i, icons[i]);
}

// axios 拦截器
axios.interceptors.request.use(config => {
    console.log('请求参数: ', config);
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(response => {
  console.log('响应参数: ', response);
  return response;
}, error => {
  console.log('响应错误: ', error);
  return Promise.reject(error);
});

// 输出配置信息
console.log('process.env.NODE_ENV: ', process.env.NODE_ENV);
console.log('process.env.VUE_APP_SERVER: ', process.env.VUE_APP_SERVER);

// 配置 axios 默认服务器请求前缀
axios.defaults.baseURL = process.env.VUE_APP_SERVER;