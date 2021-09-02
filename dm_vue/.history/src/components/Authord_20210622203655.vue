<template>
  <div>
    <div class="top">
      <img :src="picIp+author.userimg" />
      <p class="name">{{author.username}}</p>
      <p :class='tip2 == "关注" ? "tip" : "tip2"' @click="attention">{{tip2}}</p>
    </div>

    <van-pull-refresh v-model="refreshing" @refreshing="onRefresh">
      <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多"
        @load="onLoad"
      >
        <van-cell v-for="item in list" :key="item.postid">
          <div class="contentList" @click="gotopostdetail(item.postid)" v-if="item.postgood == 1">
            <p style="margin: 5px 0">{{item.postcontent}}</p>
            <img style="width: 80%" :src="picIp+item.postimg"/>
          </div>
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
    mounted(){
      console.log(this.author);
      this.isAttention();
    },
    methods:{
        gotopostdetail(postId){
          this.$router.push("Postdetail/"+postId);
        },

        isAttention(){
          this.axios.get("attention/exist?authorid="+this.author.userid+"&userid="+localStorage.getItem("userid")).then(res=>{
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
            this.axios.get("attention/del?authorid="+this.author.userid+"&userid="+localStorage.getItem("userid")).then(res=>{
              this.tip2 = '关注';
            })
          }else{
            this.axios.get("attention/add?authorid="+this.author.userid+"&userid="+localStorage.getItem("userid")).then(res=>{
              this.tip2 = '已关注';
            })
          }
        },

        onLoad(){
          if(this.refreshing){
            this.list=[];
            this.refreshing=false;
          }
          this.axios.get("userpost/getByid?current="+this.pageIndex+"&size=3&username="+this.author.username).then((response)=>{
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
.top{
  z-index: 10000;
  width: 100%;
  display: flex;
  align-items: center;
  padding: 10px;
  position: fixed;
  top: 0;
  background-color: rgb(250,214,126);
}
.top img{
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: #333 solid 2px;
}
.top p:nth-child(2){
  flex: 1;
  margin-left: 20px;
  font-size: 15px;
  font-weight: 700;
}
.top p:nth-child(3){
  margin-right: 20px;
}

.tip{
  padding: 3px 8px;
  border: solid 1px #333;
  border-radius: 15px;
  display: inline-block;
  margin: 0;
}
.tip2{
  padding: 3px 8px;
  border: solid 1px #333;
  border-radius: 15px;
  background-color: red;
  color: white;
  display: inline-block;
  margin: 0;
}

.van-pull-refresh{
  margin-top: 60px;
}

.van-pull-refresh{
  padding-bottom: 50px;
}

.contentList{
  border-bottom: #999 solid 5px;
}
</style>