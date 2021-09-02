<template>
  <div>
    <img
      style="width: 100%; height: 40vh; z-index: -1; position: fixed; top: 50px"
      src="../assets/gift_bg.jpg"
    />

    <div class="img">
      <h3>腾讯动漫</h3>
    </div>

    <div class="gift">
      <div style="margin: 10px 5vw">
        <h2>今日福利</h2>
      </div>
      <div class="do_gift">
        <p style="margin-right: 10px">做任务领奖</p>
        <van-icon name="gift-o" size="18" />
      </div>
      <!-- 底部 -->
      <div class="gift_bottom">
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
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import { Toast } from 'vant';

Vue.use(Toast);
Toast.setDefaultOptions({duration:2000});

export default {
  name: "gift",
  data() {
    return {
      pic:"http://localhost:8080/pic/",
      List:[],
      pageIndex:1,
      loading:false,
      finished:false,
      refreshing:false,
    };
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
        this.List=[];
        this.refreshing=false;
      }
      this.axios.get("actmessage/actAll?current="+this.pageIndex+"&size=5").then((response)=>{
        console.log(response);
        this.List=this.List.concat(response.data.records);
        this.loading=false;
        if(this.pageIndex>=response.data.pages){
          this.finished=true;
        }
        this.pageIndex++;
      })
    },

  },
};
</script>

<style scoped>
* {
  margin: 0;
}
.img {
  width: 100%;
  height: 50px;
  position: fixed;
  /* background-color: white; */
  background-color: rgb(250,214,126);
  z-index: 10;
}
.img h3 {
  position: absolute;
  right: 10px;
  top: 20px;
  width: 15px;
  height: 15px;
}
.gift {
  position: absolute;
  top: 20vh;
  margin-bottom: 10vh;
  background-image: linear-gradient(
    rgba(255, 255, 255, 0),
    rgba(255, 255, 255, 0.95),
    rgba(255, 255, 255, 0.95),
    rgba(255, 255, 255, 0.95),
    rgba(255, 255, 255, 0.95)
  );
}
.do_gift {
  width: 35%;
  position: sticky;
  top: 10px;
  z-index: 15;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  border: solid 1px;
  border-radius: 20px;
  font-size: 14px;
  padding: 5px 10px;
  margin: 0 0 5vh 5vw;
}

.gift_bottom {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
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