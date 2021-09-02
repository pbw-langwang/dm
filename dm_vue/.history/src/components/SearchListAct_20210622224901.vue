<template>
<div>
    <van-divider>搜索：{{val}}</van-divider>
    <van-pull-refresh v-model="refreshing">
      <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="onLoad"
      >
        <div class="gift_content" v-for="item in recommend" :key="item.actid" @click="gotoact(item.acturl,item.actvip)">
          <div class="gift_content_top">
            <p>{{item.actname}}</p>
            <span v-if="item.actvip == 1" class="bottom_centent_vip">vip专享</span>
            <p class="gift_content_top_p2" v-if="item.type == 0">去下载<span>></span></p>
            <p class="gift_content_top_p2" v-if="item.type == 1">去玩玩<span>></span></p>
            <p class="gift_content_top_p2" v-if="item.type == 2">去逛逛<span>></span></p>
          </div>
          <div class="gift_content_text">
            <img
              :src=pic+item.actimg
            />
            <p>{{item.actintroduction}}</p>
          </div>
        </div>
      </van-list>
    </van-pull-refresh>
</div>
</template>

<script>
import Vue from "vue";
import { Lazyload,Toast } from "vant";

Vue.use(Lazyload);
Vue.use(Toast);
Toast.setDefaultOptions({duration:2000});

export default {
    name:'SearchList',
    data(){
        return{
            val:decodeURIComponent(this.$route.params.value),
            pic:"http://localhost:8080/pic/",
            pageIndex:1,
            recommend:[],
            loading:false,
            finished:false,
            refreshing:false,
        }
    },
    created(){
        console.log(this.val);
    },
    methods:{
        gotoact(url,vip){
          console.log(url);
          const name = localStorage.getItem("username");
          const uservip = localStorage.getItem("uservip");
          if(name == null){
            Toast("请先进行登录");
          }else{
            if(uservip == 1 || vip == 0){
              window.open(url); 
            }else{
              Toast("请先充值vip");
            }
          }
        },

        onLoad(){
            if(this.refreshing){
                this.recommend=[];
                this.refreshing=false;
            }
            this.axios.get("actmessage/Search?current="+this.pageIndex+"&size=10&val="+this.val).then((response)=>{
                this.recommend=this.recommend.concat(response.data.records);
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
.gift_content {
  width: 90%;
  border-radius: 20px;
  /* margin-bottom: 10px; */
  margin: 0 auto 10px; 
  border: solid 1px rgb(216, 216, 216);
  background-color: white;
}
.gift_content_top {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  padding-left: 10px;
  margin-top: 15px;
}
.gift_content_top_p2 {
  padding: 3px 6px;
  font-size: 10px;
  border-radius: 15px;
  background-color: rgb(12, 154, 219);
  color: white;
}
.bottom_centent_vip{
  font-size: 12px;
  font-weight: 300;
  color: white;
  background-color: gold;
  padding:3px 6px;
  border-radius: 10px;
  margin: 0 0 0 5px;
}
.gift_content_top_p2 span {
  line-height: 10px;
  text-align: center;
  display: inline-block;
  width: 10px;
  height: 10px;
  background-color: white;
  color: rgb(12, 154, 219);
  border-radius: 50%;
  margin: 2px;
}
.gift_content_text {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
}
.gift_content_text img {
  width: 60%;
  margin: 10px;
  border-radius: 10px;
}
</style>