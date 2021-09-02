<template>
<div>
    <div class="userinfo">
      <van-row>
        <van-col span="4">
          <img :src="picIp+userimg" />
        </van-col>
        <van-col span="16" offset="4">
          <p class="name">{{username}}的阅读历史</p>
        </van-col>
      </van-row>
    </div>

    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="onLoad"
      >
        <div class="List" v-for="item in list" :key="item.contentname">
          <p>动漫名：{{item.contentname}}</p>
          <p>阅读时间：{{item.contentseetime}}</p>
        </div>

        <template>
          <div id="time-line">
            <div class="item" v-for="item in items" :key="item.index">
              <div class="item-index">{{ item.index }}</div>
              <div class="item-content">
                <van-card
                  :desc="item.movieinfo"
                  :title="item.moviename"
                  :thumb="item.movieimage"
                >
                  <template #footer>
                    <van-button size="mini">评论</van-button>
                    <van-button size="mini">删除</van-button>
                  </template>
                </van-card>
              </div>
            </div>
          </div>
        </template>
      </van-list>
    </van-pull-refresh>
</div>

</template>
<script>
import Vue from 'vue';
import { Lazyload } from 'vant';
Vue.use(Lazyload);
export default {
    name:'History',
    data(){
        return{
            picIp:"http://localhost:8080/pic/",
            pageIndex:1,
            list:[],
            loading:false,
            finished:false,
            refreshing:false,
            userimg:localStorage.getItem("userImg"),
            username:localStorage.getItem("username"),
        }
    },
    created(){
        
    },
    methods:{
        onLoad(){
            if(this.refreshing){
                this.list=[];
                this.refreshing=false;
            }
            this.axios.get("contenthistory/getByUsername?current="+this.pageIndex+"&size=10&username="+this.username).then((response)=>{
                console.log(response);
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
      padding: 28px 0px 20px 20px;
      background: url("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3563343280,3778038422&fm=26&gp=0.jpg");
      background-size: 100%;
    }
    .userinfo img{
      width: 80px;
      height: 80px;
      border-radius: 50%;
      border: solid black 2px;
    }
    .name{
      margin-left: 20px;
      font-size: 20px;
      font-weight: 600;
      margin-top: 30px;
    }

    .List{
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: rgb(236, 236, 236);
      margin: 10px;
    }
    .List p{
      flex: 1;
      padding: 0 10px;
    }
</style>