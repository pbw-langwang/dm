// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vant from 'vant'
import 'vant/lib/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)
Vue.use(Vant);

axios.defaults.baseURL = 'http://localhost:8080/dm/';
Vue.config.productionTip = false

Vue.filter("dateFormat", function (dateStr, pattern = "") {
    var dt = new Date(dateStr);
    var y = dt.getFullYear();
    var m = dt.getMonth() + 1;
    var d = dt.getDate();
    if (pattern.toLowerCase() == "yyyy-mm-dd") {
        return `${y}年${m}月${d}日`
    } else {
        var hh = dt.getHours();
        var mm = dt.getMinutes();
        var ss = dt.getSeconds();
        return `${y}年${m}月${d}日 ${hh}:${mm}:${ss}`
    }
})

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    components: { App },
    template: '<App/>'
})