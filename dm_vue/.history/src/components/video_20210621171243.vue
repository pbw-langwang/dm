<template>
  <div>
    <van-tabs v-model="activeName" sticky>
      <van-tab title="关注" name="a">
        <!-- 关注 -->
        <div class="attention">
          <img style="width: 100%" src="../assets/nothing.png" />
        </div>
      </van-tab>

      <van-tab title="推荐" name="b">
        <!-- 推荐 -->
        <div class="recommend">
          <div class="recommend_div">
            <!-- 头 -->
            <van-swipe :autoplay="5000">
              <van-swipe-item v-for="(image, index) in lunbo" :key="index">
                <img v-lazy="image" />
              </van-swipe-item>
            </van-swipe>

            <!-- 中 -->
            <div class="recommend_center">
              <div class="recommend_center_title">
                <p>热门圈子</p>
                <p style="font-size: 13px; color: #555">查看更多 ></p>
              </div>
              <div class="recommend_center_content">
                <div
                  class="recommend_center_content_div"
                  v-for="(item, index) in content"
                  :key="index"
                >
                  <img
                    class="recommend_center_content_div_img"
                    :src="item.img"
                  />
                  <div class="recommend_center_content_div_txt">
                    <p>#{{ item.title }}</p>
                    <p style="color: #888">{{ item.text }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 底部 -->
        <van-pull-refresh v-model="refreshing">
          <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
          >
            <div class="recommend_bottom" v-for="item in recommend" :key="item.postid">
              <!-- 头部 -->
              <div class="recommend_bottom_top">
                <div class="recommend_bottom_top_Left">
                  <img :src="pic+item.userimg" />
                  <p style="font-size:20px">{{item.username}}</p>
                  <div class="recommend_bottom_top_Left_vip" v-if="item.uservip == 1">
                    <p>vip</p>
                  </div>
                </div>
              </div>

              <!-- 中间 -->
              <div  @click="gotopostdetail(item.postid)">
                <p style="margin: 5px 0">{{item.postcontent}}</p>
                <img style="width: 80%" :src="pic+item.postimg"/>
              </div>

              <!-- 底部 -->
              <div class="recommend_bottom_bottom">
                <div>
                  <van-icon name="good-job-o" color="#555"/>
                  <span style="font-size: 10px; color: #555">{{item.postgood}}</span>
                </div>
              </div>

            </div>
          </van-list>
        </van-pull-refresh>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
import Vue from "vue";
import { Lazyload } from "vant";
import { Toast } from 'vant';

Vue.use(Toast);
Toast.setDefaultOptions({duration:2000});
Vue.use(Lazyload);

export default {
  name: "Video",
  data() {
    return {
      pic:"http://localhost:8080/pic/",
      activeName: "b",
      label_page: ["关注", "推荐"],
      lunbo: [
        "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3336750308,2784091665&fm=26&gp=0.jpg",
        "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3055257081,3011205547&fm=26&gp=0.jpg",
        "https://uploadfile.huiyi8.com/2018/af/88/a0/a8/400208.jpg",
        "https://img.zcool.cn/community/01e9cc5a489480a801206ed39ab45b.jpg@1280w_1l_2o_100sh.jpg",
      ],
      content: [
        {
          img:
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2751908706,73237334&fm=26&gp=0.jpg",
          title: "RE0:再启航",
          text: "140万人参加",
        },
        {
          img:
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3017911798,186701776&fm=26&gp=0.jpg",
          title: "哆啦A梦",
          text: "1479人参加",
        },
        {
          img:
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=309670466,788960244&fm=26&gp=0.jpg",
          title: "阴阳师联动",
          text: "1000万人参加",
        },
        {
          img:
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1517826954,3323335173&fm=26&gp=0.jpg",
          title: "弑神之路，等你来战",
          text: "200万人参加",
        },
      ],
      pageIndex:1,
      loading:false,
      finished:false,
      refreshing:false,
      recommend:[],
      attentionList:[],
    };
  },
  created(){
    this.getattentionList();
  },
  methods: {
    goodjog(e) {

    },

    onLoad(){
      if(this.refreshing){
        this.recommend=[];
        this.refreshing=false;
      }

      this.axios.get("userpost/listAll?current="+this.pageIndex+"&size=3").then((response)=>{
        this.recommend=this.recommend.concat(response.data.records);
        console.log(this.recommend);

        this.loading=false;
        if(this.pageIndex>=response.data.pages){
            this.finished=true;
        };
        this.pageIndex++;
      });
    },

    onRefresh(){
        this.finished=false;
        this.pageIndex=1;
        this.loading=true;
        this.onLoad();
    },

    gotopostdetail(postId){
      this.$router.push("Postdetail/"+postId);
    },

    getattentionList(){
      const userid = localStorage.getItem("userid");

      this.axios.get("attention/attentionsList?userid="+userid).then(res=>{
        console.log(res);
      })
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.attention {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
}

.recommend {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}
.recommend_div {
  width: 90%;
  border-radius: 10px;
  overflow: hidden;
}

.van-swipe {
  height: 20vh;
}
.van-swipe img {
  width: 100%;
  height: 20vh;
}

.recommend_center_title {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.recommend_center_content {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.recommend_center_content_div {
  width: 48%;
  display: flex;
  margin-bottom: 10px;
}
.recommend_center_content_div_img {
  width: 50px;
  height: 50px;
  margin-right: 10px;
  border-radius: 10px;
}
.recommend_center_content_div_txt {
  width: 60%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
.recommend_center_content_div_txt p {
  margin: 0;
  font-size: 10px;
}

.recommend_bottom {
  border-top: solid 5px rgb(204, 202, 202);
  padding: 10px 5%;
  /* margin-bottom: 10vh; */
}
.recommend_bottom_top {
  display: flex;
  justify-content: space-between;
}
.recommend_bottom_top_Left {
  display: flex;
  align-items: center;
}
.recommend_bottom_top_Left img{
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: black solid 2px;
}
.recommend_bottom_top_Left p {
  margin: 0 0 0 15px;
}
.recommend_bottom_top_Left_vip{
  background-color: gold;
  color: white;
  border-radius: 10px;
  border: 1px #333 solid;
  margin-left: 10px;
}
.recommend_bottom_top_Left_vip p{
  margin: 0;
  padding: 2px 5px;
}
.recommend_bottom_top_right {
  padding: 5px 10px;
  border-radius: 15px;
  font-size: 10px;
  border: solid 1px;
  text-align: center;
}
.recommend_bottom_top_right2 {
  padding: 5px 10px;
  border-radius: 15px;
  font-size: 10px;
  border: solid 1px;
  text-align: center;
  background-color: red;
  color: white;
}

.recommend_bottom_bottom {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}
.recommend_bottom_bottom div {
  display: flex;
  align-items: center;
  margin-right: 20px;
}

.van-pull-refresh{
  padding-bottom: 50px;
}
</style>
