// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from "axios";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import {Message} from "element-ui";

Vue.config.productionTip = false
Vue.use(ElementUI);

/*
  定义请求体参数格式
*/
axios.defaults.headers.post['Content-Type'] = 'application/json';
/*
  设置 url 前缀，配合路由转发到后端
 */
axios.defaults.baseURL = '/back';


Vue.prototype.$http = axios;



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})



// 配置请求过滤器
axios.interceptors.request.use( (re) =>  {
  // 获取 token
  const token = sessionStorage.getItem("token");
  // 如果有值，则设置请求头
  if(token){
    re.headers.Authorization = token;
  }
  return re;
});

// 配置响应拦截器
// axios.interceptors.response.use((rs) => {
//   if (rs.data.code !== 200){
//     Message.error(rs.data.msg);
//   }
//   return rs;
// });


