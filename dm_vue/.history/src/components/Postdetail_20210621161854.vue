<template>
  <div class="info">
    <div class="recommend_bottom_top">
      <div class="recommend_bottom_top_Left">
        <img :src="picIp+news.userimg" />
        <p style="font-size:20px">{{news.username}}</p>
        <div class="recommend_bottom_top_Left_vip" v-if="news.uservip == 1">
          <p>vip</p>
        </div>
        <p class="tip" @click="attention">{{tip2}}</p>
      </div>
    </div>


    <p>{{news.postcontent}}</p>
    <van-image
      width="100%"
      :src="picIp+news.postimg"
    />

    <van-row v-for="item in commentList" :key="item.commentId">
        <van-col span="6">
            <van-image
              round
              width="50px"
              height="50px"
              :src="picIp+item.userimg"
            />
        </van-col>
        <van-col span="12">
            <p class="user">
                {{item.username}}---{{item.time|dateFormat}}
            </p>
            <p class="user">
                {{item.content}}
            </p>
        </van-col>
    </van-row>

    <van-form @submit="onSubmit">
        <van-field
            v-model="content"
            :clearable="clearable"
            placeholder="请输入评论内容"
            :rules="[{ required: true, message: '请输入评论内容' }]"
        />
        <div style="margin:16px;">
            <van-button round block type="info" native-type="submit">
                发表
            </van-button>
        </div>
    </van-form>
  </div>
</template>

<script>
import Vue from 'vue';
import { Toast } from 'vant';
Vue.use(Toast);
Toast.setDefaultOptions({ duration: 2000 });

export default {
  name: 'Postdetail',
  data() {
    return {
        postId:this.$route.params.value,
        content:"",
        clearable:true,
        news:{},
        picIp:"http://localhost:8080/pic/",
        commentList:[],
        tip2:"关注"
    }
  },
  created(){
      this.getNewsInfo();
      this.getComments();
  },
  methods:{
    onSubmit(){
        const username=localStorage.getItem("username");
        if(username==null){
            Toast('您未登陆，请登录后发表评论');
            return;
        }

        this.axios.post("postcomment/add?postid="+this.postId+"&content="+this.content+"&username="+username).then((response) => {
          console.log(response);
          this.getComments();
          this.content="";
        })
    },

    getNewsInfo(){
      this.axios.get("userpost/getInfo?postid="+this.postId).then((response) => {
        console.log("85:");
        console.log(response);
        this.news=response.data;

        const username=localStorage.getItem("username");
        if(username != null){
          this.isAttention();
        }
      })
    },

    getComments(){
      this.axios.get("postcomment/getCommentByid?postid="+this.postId).then((response) => {
        console.log(response);
        this.commentList=response.data;
      })
    },

    isAttention(){
      this.axios.get("attention/exist?authorid="+this.news.userid+"&userid="+localStorage.getItem("userid")).then(res=>{
        if(res.data=="exist"){
          this.tip2 = "已关注";
        }
      })
    },

    attention(){
      if(this.user == null){
        Toast('您未登录，请登录后关注作者！');
        return;
      }
      if(this.tip2 == "已关注"){
        this.axios.get("attention/del?authorid="+this.news.uid+"&uid="+this.user.uid).then(res=>{
          this.tip2 = '关注';
        })
      }else{
        this.axios.get("attention/add?authorid="+this.news.uid+"&uid="+this.user.uid).then(res=>{
          this.tip2 = '已关注';
        })
      }
    }
  }
}

</script>


<style scoped>
.info{
    margin:0 10px 70px;
}
p.user{
    font-size: 14px;
    margin: 5px;
}

.tip{
  float: right;
  padding: 2px 5px;
  border: solid 2px black;
  border-radius: 15px;
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
  border: 1px black solid;
  margin-left: 10px;
}
.recommend_bottom_top_Left_vip p{
  margin: 0;
  padding: 2px 5px;
}
</style>