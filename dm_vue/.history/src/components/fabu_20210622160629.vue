<template>
  <div>
    <div class="top">
      <img :src="picIp+userimg" />
      <p class="name">{{username}} 的发布内容</p>
      <p>发布内容</p>
    </div>

    <van-pull-refresh v-model="refreshing" @refreshing="onRefresh">
      <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多"
        @load="onLoad"
      >
        <van-cell v-for="item in list" :key="item.postid">
          <div class="contentList" @click="gotopostdetail(item.postid)">
            <p style="margin: 5px 0">{{item.postcontent}}</p>
            <img style="width: 80%" :src="picIp+item.postimg"/>
          </div>
        </van-cell>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script>
export default {
    name:"fabu",
    data(){
        return {
            picIp:"http://localhost:8080/pic/",
            pageIndex:1,
            list:[],
            userimg:localStorage.getItem("userImg"),
            username:localStorage.getItem("username"),
            loading:false,
            finished:false,
            refreshing:false,
        }
    },
    mounted(){
      
    },
    methods:{
        gotopostdetail(postId){
          this.$router.push("Postdetail/"+postId);
        },

        onLoad(){
          if(this.refreshing){
            this.list=[];
            this.refreshing=false;
          }
          this.axios.get("userpost/getByid?current="+this.pageIndex+"&size=3&username="+localStorage.getItem("username")).then((response)=>{
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
.top .name{
  flex: 7;
  padding-left: 20px;
  font-size: 15px;
  font-weight: 700;
}
.top p:nth-child(3){
  flex: 5;
  display: inline-block;
  background-color: rgb(0, 110, 255);
  color: white;
  text-align: center;
  margin: 0;
  padding: 10px 0;
  border-radius: 25px;
}


.van-pull-refresh{
  margin-top: 60px;
}

.contentList{
  margin-bottom: 30px;
  border-bottom: #999 solid 5px;
}
</style>