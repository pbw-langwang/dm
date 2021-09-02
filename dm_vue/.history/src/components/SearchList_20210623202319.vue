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
        <van-cell v-for="item in recommend" :key="item.newsid">
            <van-card @click="gotocontent(item.contentid,item.contentvip,item.contentname)"
            :title="item.contentname"
            :thumb="pic+item.contentimg"
            :desc="item.contentintroduction"
            :num="item.contentvip == 1 ? 'vip专属':'观看'"
            />
        </van-cell>
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
        gotocontent(contentid,vip,contentname){
            const name = localStorage.getItem("username");
            const uservip = localStorage.getItem("uservip");
            console.log(uservip);
            console.log(vip);
            if(name == null){
                Toast("请先进行登录");
            }else if(uservip == 1 || vip == 0){
                this.axios.get("contenthistory/add?username="+name+"&contentname="+contentname).then(res=>{
                    console.log(res);
                    that.$router.push("Dmdetail/"+contentid);
                });
            }else{
                Toast("请先充值vip");
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