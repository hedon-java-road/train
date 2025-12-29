import * as Icons from '@ant-design/icons-vue';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
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