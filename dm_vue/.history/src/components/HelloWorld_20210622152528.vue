<template>
  <div>
    <van-tabs animated sticky @click="getindex">
      <!-- 标签页 -->
      <van-tab v-for="(item, index) in label_page" :title="item" :key="index">
        <!-- 搜索 -->
        <div v-if="index > 0 && index < 5" class="search" @click="gotoSearch()">
          <van-search
            v-model="value"
            shape="round"
            placeholder="请输入关键词"
          />
        </div>

        <!-- 轮播 -->
        <van-swipe v-if="index >= 0 && index < 5" :autoplay="5000">
          <van-swipe-item v-for="image in lunbo" :key="image.slideshowimg">
            <img :src=pic+image.slideshowimg />
          </van-swipe-item>
        </van-swipe>

        <!-- 简单的总体 -->
        <div v-if="index == 5" style="margin-bottom: 18vw">
          <img
            style="width: 100%; display: block"
            src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2310663588,4133875723&fm=26&gp=0.jpg"
          />
          <video
            class="video"
            controls="controls"
            style="width: 100%; display: block"
            src="https://xiaoyu226.oss-cn-beijing.aliyuncs.com/witApp/unit2/unit2-1-myface/black%20sheep.mp4"
          />
          <img
            style="width: 100%; display: block"
            src="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1244422798,2793521029&fm=26&gp=0.jpg"
          />
        </div>
        <div class="page6" v-if="index == 6">
          <img src="../assets/1.png" />
          <img src="../assets/2.png" />
          <img src="../assets/3.png" />
          <img src="../assets/4.png" />
        </div>

        <!-- 中间 -->
        <!-- 1~4的中间 -->
        <div v-if="index > 0 && index < 5" class="center1">
          <img class="center1_img" :src="center[index - 1]" />
        </div>
        
        <!-- 0的中间 -->
        <div v-if="index == 0" class="cneter2">
          <div class="cneter2_div">
            <div class="cneter2_top">
              <div class="cneter2_top_text">
                <h3 style="margin: 0">萌新专属见面礼</h3>
                <p style="margin: 0; font-size: 14px">95%萌新已领取</p>
              </div>
            </div>
            <div class="cneter2_bottom">
              <div
                class="cneter2_bottom_content"
                v-for="item in center_story"
                :key="item.text"
              >
                <div class="cneter2_bottom_content_img">
                  <img :src="item.img" />
                </div>
                <p style="margin: 0; font-size: 14px">{{ item.text }}</p>
              </div>
            </div>
          </div>
        </div>

        <!-- 底部 -->
        <!-- 0~5的底部 -->
        <div v-if="index >= 0 && index < 5" class="bottom">
          <h3 style="margin: 0 0 10px 0">{{ h_content[index] }}</h3>
          <van-pull-refresh v-model="refreshing">
            <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
            >
              <van-cell v-for="item in recommend" :key="item.newsid">
                  <van-card @click="gotocontent(item.contenturl,item.contentvip)"
                  :title="item.contentname"
                  :thumb="pic+item.contentimg"
                  :desc="item.contentintroduction"
                  :num="item.contentvip == 1 ? 'vip专属':'观看'"
                  />
              </van-cell>
            </van-list>
          </van-pull-refresh>
        </div>

      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
import Vue from "vue";
import { Lazyload,Toast } from "vant";

Vue.use(Lazyload);
Vue.use(Toast);
Toast.setDefaultOptions({duration:2000});

export default {
  name: "HelloWorld",
  data() {
    return {
      pic:"http://localhost:8080/pic/",
      value: "",
      video: null,
      pageIndex:1,
      index:0,
      loading:false,
      finished:false,
      refreshing:false,
      label_page: [],
      lunbo: [],
      center: [
        "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3336750308,2784091665&fm=26&gp=0.jpg",
        "https://img.zcool.cn/community/01aefd5a489473a80121974198b17e.jpg@1280w_1l_2o_100sh.jpg",
        "https://uploadfile.huiyi8.com/2018/af/88/a0/a8/400208.jpg",
        "https://img.zcool.cn/community/01e9cc5a489480a801206ed39ab45b.jpg@1280w_1l_2o_100sh.jpg",
      ],
      center_story: [
        {
          img:
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=998413785,663905502&fm=26&gp=0.jpg",
          text: "王牌御史",
        },
        {
          img:
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3435774291,478944199&fm=26&gp=0.jpg",
          text: "血小板",
        },
        {
          img:
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=542420415,863750099&fm=26&gp=0.jpg",
          text: "妖神记",
        },
      ],
      h_content: [
        "精品.企鹅娘打滚推荐",
        "今日高热新作【new】",
        "全新CP上线！速看！",
        "NEW！新作赏味大全！",
        "人气TOP",
      ],
      recommend: [],
    };
  },

  created() {
    this.firstGo(0);
    this.onLoad();

    this.axios.get("contentclass/getcontent").then((result) => {
      console.log(result);
      for(let i = 0;i < result.data.length;i++){
        this.label_page.push(result.data[i].content);
      }
    });
  },

  methods: {
    getindex(index) {
      this.recommend = [];
      this.pageIndex = 1;
      this.loading=true;
      this.finished=false;
      this.index = index;
      this.onLoad();

      if (index == 5) {
        this.video = document.querySelector(".video");
        this.$nextTick(function () {
          this.video = document.querySelector(".video");
        });
      };

      if (index != 5 && this.video != null) {
        if (!this.video.pause()) {
          this.video.pause();
        }
      };

      this.firstGo(index);
    },

    firstGo(index){
      this.axios.get("slideshow/getslide?index="+index).then((response)=>{
        console.log(response);
        this.lunbo = response.data;
      });
    },

    onLoad(){
      if(this.refreshing){
        this.recommend=[];
        this.refreshing=false;
      }
      this.axios.get("contentmessage/listAll?current="+this.pageIndex+"&size=10&contenttype="+this.index).then((response)=>{
        console.log(response);
        this.recommend=this.recommend.concat(response.data.records);
        this.loading=false;
        if(this.pageIndex>=response.data.pages){
          this.finished=true;
        }
        this.pageIndex++;
      })
    },

    gotocontent(url,vip){
      const name = localStorage.getItem("username");
      const uservip = localStorage.getItem("uservip");
      if(name == null){
        Toast("请先进行登录");
      }else if(uservip == 1 || vip == 0){
        window.open(url);

        this.axios.get("contenthistory/add?username")
      }else{
        Toast("请先充值vip");
      }
    },

    gotoSearch(){
      this.$router.push("Search");
    }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.search {
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.van-search{
  width: 100%;
}
.van-swipe {
  height: 40vh;
}
.van-swipe img {
  width: 100%;
  height: 40vh;
}

.page6 {
  margin-bottom: 18vw;
}
.page6 img {
  width: 100%;
}
.center1 {
  width: 100%;
  height: 15vh;
  margin: 20px 0;
  text-align: center;
}
.center1_img {
  width: 90%;
  height: 100%;
}

.cneter2 {
  width: 100%;
  height: 35vh;
  background-color: white;
  position: relative;
  bottom: 10px;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.cneter2_div {
  width: 90%;
  height: 90%;
  box-shadow: 0px 0px 5px #555;
  border-radius: 10px;
}
.cneter2_top {
  display: flex;
  height: 30%;
  justify-content: space-between;
  align-items: center;
}
.cneter2_top_text {
  display: flex;
  flex-direction: column;
  margin-left: 10px;
}
.cneter2_bottom {
  display: flex;
  height: 70%;
  justify-content: space-around;
  align-items: center;
}
.cneter2_bottom_content {
  display: flex;
  flex-direction: column;
  width: 30%;
  height: 100%;
}
.cneter2_bottom_content_img {
  width: 100%;
  height: 83%;
}
.cneter2_bottom_content img {
  width: 100%;
  height: 100%;
  border-radius: 5px;
}

.bottom {
  margin: 0 4vw 18vw 4vw;
}
.van-card{
  background-color: rgb(236, 236, 236);
  padding: 0;
  border-radius: 5px;
}
.van-card__title{
  font-size: 20px;
  line-height: 50px;
  max-height:50px
}
.van-card__desc{
  font-size: 12px;
}
.van-card__num{
  font-size: 12px;
  background-color: goldenrod;
  color: white;
  font-weight: 600;
  border-radius: 10px;
  padding: 0px 5px;
  margin-right: 10px;
}
</style>
