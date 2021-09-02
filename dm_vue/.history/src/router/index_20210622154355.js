import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import video from '@/components/video'
import setting from '@/components/setting'
import Gift from '@/components/gift'
import Regist from '@/components/Regist'
import History from "@/components/History"
import Search from "@/components/Search"
import SearchList from "@/components/SearchList"
import Postdetail from "@/components/Postdetail"
import Authord from "@/components/Authord"
import Fabu from "@/components/fabu"

Vue.use(Router)

export default new Router({
    routes: [{
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld
        },
        {
            path: '/video',
            name: 'Video',
            component: video
        },
        {
            path: '/setting',
            name: 'setting',
            component: setting
        },
        {
            path: "/gift",
            name: "gift",
            component: Gift
        },
        {
            path: "/Regist",
            name: "Regist",
            component: Regist
        }, {
            path: "/History",
            name: "History",
            component: History
        }, {
            path: "/Search",
            name: "Search",
            component: Search
        }, {
            path: "/SearchList/:value",
            name: "SearchList",
            component: SearchList
        }, {
            path: "/Postdetail/:value",
            name: "Postdetail",
            component: Postdetail
        },
        {
            path: "/Authord/:value",
            name: "Authord",
            component: Authord
        },
        {
            path: "/Fabu",
            name: "Fabu",
            component: Fabu
        }
    ]
})