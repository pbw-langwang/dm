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
            <div class="item" v-for="item in list" :key="item.historyid">
              <div class="item-content">
                <p>动漫名：{{item.contentname}}</p>
                <p>阅读时间：{{item.contentseetime}}</p>
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



  .session{
    margin-top: 5px;
  }
  .item {
    margin-left: 30px; /*用左边margin显示时间线*/
    width: calc(100% - 30px); /*因为左边部分用于显示时间线，所以右边部分减去30px*/
    height: auto; /*高度由内容决定*/
    position: relative;
    margin-bottom: 10px;
    cursor: pointer;
  }
  .item::before {
    content: "";
    width: 11px;
    height: 11px;
    border-radius: 100%;
    background-color: #91c2fc;
    position: absolute;
    left: -15px;
  }
  .item::after {
      content: "";
      width: 3px;
      height: calc(100% + 10px); /*加上10px是item底部的margin*/
      background-color: #91c2fc;
      position: absolute;
      top: 0;
      left: -11px;
  }
  .item-index {
    line-height: 16px;
    font-size: 16px;
    position: relative;
    color: #656565;
  }
  .item:hover::before {
    transform: scale3d(1.2, 1.2, 1);
    background-color: #569ffb;
  }

  .item:hover::after {
    transform: scale3d(1.1, 1, 1);
    background-color: #569ffb;
  }

  .item:hover .item-index{
    transform: scale3d(1.01, 1.01, 1);
    color: #343434;
  }
</style>