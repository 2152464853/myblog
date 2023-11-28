import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'



// const app=createApp(App);
// app.use(ElementUI);
// app.use(store).use(router).mount('#app')
//Vue.use(ElementUI);
import './assets/css/global.css'

import axios from 'axios'
//请求携带上浏览器的cookie
axios.defaults.withCredentials=true
axios.defaults.baseURL='http://localhost:8088';

const app=createApp(App);
app.use(store).use(router).use(ElementPlus).use(mavonEditor)
app.config.globalProperties.$axios=axios
app.mount('#app')



//createApp(App).use(store).use(router).use(ElementPlus).use(mavonEditor).mount('#app')
