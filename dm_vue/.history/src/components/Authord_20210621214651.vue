<template>
  <div>
    <div class="userinfo">
      <van-row>
        <van-col span="6">
          <img :src="picIp+author.userimg" />
        </van-col>
        <van-col span="6">
          <p class="name">{{author.username}}</p>
        </van-col>
        <van-col span="6">
          <van-button type="danger" plain @click="attention">{{tip2}}</van-button>
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
    name:"Authord",
    data(){
        return {
            picIp:"http://localhost:8080/pic/",
            author:this.$route.params,
            pageIndex:1,
            list:[],
            loading:false,
            finished:false,
            refreshing:false,
            tip2:"关注",
        }
    },
    created(){
      console.log(this.author);
    },
    methods:{
        getInfo(newsid){
            this.$router.push("NewsInfo/"+newsid);
        },
        isAttention(){
          this.axios.get("attention/exist?authorid="+this.news.userid+"&userid="+localStorage.getItem("userid")).then(res=>{
            if(res.data=="exist"){
              this.tip2 = "已关注";
            }
          })
        },
        attention(){
          const username=localStorage.getItem("username");
          if(username == null){
            Toast('您未登录，请登录后关注作者！');
            return;
          }
          if(this.tip2 == "已关注"){
            this.axios.get("attention/del?authorid="+this.news.userid+"&userid="+localStorage.getItem("userid")).then(res=>{
              this.tip2 = '关注';
            })
          }else{
            this.axios.get("attention/add?authorid="+this.news.userid+"&userid="+localStorage.getItem("userid")).then(res=>{
              this.tip2 = '已关注';
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
.userinfo{
  padding: 10px;
}
.userinfo img{
    width: 40px;
    height: 40px;
    border-radius: 50%;
    border: solid #333 black;
}
</style>