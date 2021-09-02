<template>
    <div>
        <div class="userinfo">
            <van-row>
                <van-col span="4">
                    <img :src="picIp+author.image" />
                </van-col>
                <van-col span="6" offset="4">
                    <p class="name">{{author.username}}</p>
                </van-col>
                <van-col span="8" offset="4">
                    <van-button type="danger" plain @click="attention">{{author.isAttention}}</van-button>
                </van-col>
            </van-row>
        </div>
        <van-pull-refresh v-model="refreshing" @refreshing="onRefresh">
            <van-list
                v-model="loading"
                :finished="finished"
                finished-text="没有更多"
                @load="onLoad"
            >
                <van-cell v-for="item in list" :key="item.newsid">
                    <van-card 
                        @click="getInfo(item.newsid)"
                        :title="item.headtitle"
                        :thumb="picIp+item.pictures"
                    />
                </van-cell>
            </van-list>
        </van-pull-refresh>
    </div>
</template>

<script>
import { Toast } from 'vant';
export default {
    name:"AuthorInfo",
    data(){
        return {
            picIp:"http://172.16.35.25:8080/pic/",
            author:this.$route.params,
            pageIndex:1,
            list:[],
            loading:false,
            finished:false,
            refreshing:false,
            user:JSON.parse(localStorage.getItem("user")),
            result:[],
        }
    },
    created(){
        console.log(this.author);
    },
    methods:{
        getInfo(newsid){
            this.$router.push("NewsInfo/"+newsid);
        },
        attention(){
            if(this.user == null){
                Toast("您未登录，请登录后关注作者！");
            }
            if(this.author.isAttention == "已关注"){
              this.axios.get("attention/del?authorid="+this.author.authorid+"&uid="+this.user.uid).then(res=>{
                  this.author.isAttention = '关注';
              })
            }else{
                this.axios.get("attention/add?authorid="+this.author.authorid+"&uid="+this.user.uid).then(res=>{
                    this.author.isAttention = '已关注';
                })
            }
        },
        onLoad(){
            if(this.refreshing){
                this.list=[];
                this.refreshing=false;
            }
            this.axios.get("news/getByUid?current="+this.pageIndex+"&size=10&uid="+this.author.authorid).then((response)=>{
                this.list=this.list.concat(response.data.records);
                this.loading=false;
                if(this.pageIndex>=response.data.pages){
                    this.finished=true;
                }
                this.pageIndex++;
            })
        },
        onRefresh(){
            this.finished=false;
            this.pageIndex=1;
            this.loading=true;
            this.onLoad();
        },
    }
}
</script>

<style scoped>
.userinfo img{
    width: 100px;
    height: 100px;
    border-radius: 50%;
}
</style>