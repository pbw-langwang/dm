<template>
  <div>
    <div class="top">
      <p class="name">管理动漫</p>
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
            <van-cell v-for="(item,index) in list" :key="item.contentid" @click="toggle(index)">
                <template #right-icon>
                    <van-checkbox :name="item" ref="checkboxes"/>
                </template>
                <van-swipe-cell>
                    <van-card 
                        @click="gotopostdetail(item.contentid)"
                        :title="item.contentname"
                        :thumb="picIp+item.contentimg"
                    />
                    <template #right>
                        <van-button square text="删除" type="danger" class="delete-button" @click="del(item.contentid)"></van-button>
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
          result:[],
      }
  },
  methods:{
    gotopostdetail(contentid){
      this.$router.push("Dmdetail/"+contentid);
    },

    toggle(index){
      this.$refs.checkboxes[index].toggle();
    },

    onLoad(){
      if(this.refreshing){
        this.list=[];
        this.refreshing=false;
      }
      this.axios.get("contentmessage/All?current="+this.pageIndex+"&size=10").then((response)=>{
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

    del(contentid){
      this.axios.get("contentmessage/del?contentid="+contentid).then(res=>{
        console.log(res);
        this.refreshing=true;
        this.onRefresh();
      })
    },

    batchDel(){
      let delIds = [];
      this.result.forEach(item=>{
        delIds.push(item.contentid);
      });
      this.axios.post("contentmessage/batchDel",{"delIds":delIds}).then(res=>{
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
  display: flex;
  align-items: center;
  padding: 10px;
  position: fixed;
  top: 0;
  background-color: rgb(250,214,126);
}
.top .name{
  text-align: center;
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
  border-radius: 15px;
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