import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import video from '@/components/video'
import setting from '@/components/setting'
import Gift from '@/components/gift'
import Regist from '@/components/Regist'
import History from "@/components/history"
import Search from "@/components/Search"
import SearchList from "@/components/SearchList"
import Postdetail from "@/components/Postdetail"
import Authord from "@/components/Authord"
import fabu from "@/components/fabu"
import FabuPost from "@/components/FabuPost"
import Yhfabu from "@/components/Yhfabu"
import SearchAct from "@/components/SearchAct"
import SearchListAct from "@/components/SearchListAct"
import Change from "@/components/Change"

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
            path: "/FabuPost",
            name: "FabuPost",
            component: FabuPost
        },
        {
            path: "/fabu",
            name: "fabu",
            component: fabu
        },
        {
            path: "/Yhfabu",
            name: "Yhfabu",
            component: Yhfabu
        },
        {
            path: "/SearchAct",
            name: "SearchAct",
            component: SearchAct
        },
        {
            path: "/SearchListAct/:value",
            name: "SearchListAct",
            component: SearchListAct
        },
        {
            path: "/Change",
            name: "Change",
            component: Change
        }
    ]
})