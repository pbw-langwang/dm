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
        <div class="gift_content" v-for="item in List" :key="item.actid" @click="gotoact(item.acturl,item.actvip)">
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
            if(vip == uservip){
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
            this.axios.get("contentmessage/Search?current="+this.pageIndex+"&size=10&val="+this.val).then((response)=>{
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
.van-multi-ellipsis--l2{
    overflow:visible;
}
.van-divider{
    margin: 10px 0px 10px 20px;
    font-size: 18px;
}
.van-card{
  background-color: rgb(236, 236, 236);
}
.van-card__title{
  font-size: 25px;
  line-height: 50px;
  max-height:50px
}
.van-card__desc{
  font-size: 16px;
}
.van-card__num{
  font-size: 12px;
  background-color: goldenrod;
  color: white;
  font-weight: 600;
  border-radius: 10px;
  padding: 2px 5px;
}
</style>