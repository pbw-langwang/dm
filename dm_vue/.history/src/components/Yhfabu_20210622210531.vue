<template>
  <div>
    <div class="top">
      <h4 style="margin:0;text-align:center">审核用户发布内容</h4>
    </div>

    <div class="btn">
        <van-button type="danger" round plain @click="batchDel">批量删除</van-button>
    </div>

    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
        <van-list
          v-model="loading"
          :finished="finished"
          finished-text="没有更多了"
          @load="onLoad"
        >
        <van-checkbox-group v-model="result">
            <van-cell v-for="(item,index) in list" :key="item.postid" @click="toggle(index)">
                <template #right-icon>
                    <van-checkbox :name="item" ref="checkboxes"/>
                </template>
                <van-swipe-cell>
                    <van-card 
                        @click="gotopostdetail(item.postid)"
                        :title="item.postcontent"
                        :thumb="picIp+item.postimg"
                        :tag="item.postgood == 1 ? '':'待审核'"
                    />
                    <template #right>
                        <van-button square text="删除" type="danger" class="delete-button" @click="del(item.postid)"></van-button>
                        <van-button square type="primary" text="过审" class="delete-button" @click="pass(item.postid)"/>
                    </template>
                </van-swipe-cell>
            </van-cell>
        </van-checkbox-group>
        </van-list>
    </van-pull-refresh>
  </div>
</template>

<script>
export default {
  name:"Yhfabu",
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
          result:[],
      }
  },
  methods:{
    gotopostdetail(postId){
      this.$router.push("Postdetail/"+postId);
    },

    gotofabupost(){
      this.$router.push("FabuPost");
    },

    toggle(index){
      this.$refs.checkboxes[index].toggle();
    },

    onLoad(){
      if(this.refreshing){
        this.list=[];
        this.refreshing=false;
      }
      this.axios.get("userpost/getBygood?current="+this.pageIndex+"&size=3").then((response)=>{
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

    del(postid){
      this.axios.get("userpost/del?postid="+postid).then(res=>{
        console.log(res);
        this.refreshing=true;
        this.onRefresh();
      })
    },

    batchDel(){
      let delIds = [];
      this.result.forEach(item=>{
        delIds.push(item.postid);
      });
      this.axios.post("userpost/batchDel",{"delIds":delIds}).then(res=>{
        console.log(res);
        this.refreshing = true;
        this.onRefresh();
      });
    },
  }
}
</script>

<style scoped>
.top{
  z-index: 10000;
  width: 100%;
  position: fixed;
  padding: 10px;
  top: 0;
  background-color: rgb(250,214,126);
}


.btn{
  text-align: center;
  margin-top: 80px;
}
.delete-button{
  height: 100%;
}

.contentList{
  border-bottom: #999 solid 5px;
}

.van-pull-refresh{
  padding-bottom: 50px;
}
.van-card__thumb{
  height: 50px;
}
.van-card__content{
  min-height: 50px;
}
</style>