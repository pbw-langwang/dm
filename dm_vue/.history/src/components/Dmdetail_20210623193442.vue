<template>
  <div class="info">
    <div class="recommend_bottom_top">
      <div class="recommend_bottom_top_Left">
        <p style="font-size:20px;font-weight:700">{{news.contentname}}</p>
        <div class="recommend_bottom_top_Left_vip" v-if="news.contentvip == 1">
          <p>vip</p>
        </div>
      </div>
    </div>

    <p>{{news.contentintroduction}}</p>
    <van-image
      width="100%"
      :src="picIp+news.contentimg"
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
  name: 'Dmdetail',
  data() {
    return {
        contentid:this.$route.params.value,
        content:"",
        clearable:true,
        news:{},
        picIp:"http://localhost:8080/pic/",
        commentList:[],
    }
  },
  mounted(){
      this.getNewsInfo();
      this.getComments();
      console.log(this.cotentid);
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
      this.axios.get("contentmessage/getByid?contentid="+this.contentid).then((response) => {
        console.log(response);
        this.news=response.data;
        const username=localStorage.getItem("username");
      })
    },

    getComments(){
      this.axios.get("postcomment/getCommentByid?postid="+this.postId).then((response) => {
        console.log(response);
        this.commentList=response.data;
      })
    },

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
  padding: 3px 8px;
  border: solid 1px #333;
  border-radius: 15px;
}
.tip2{
  float: right;
  padding: 3px 8px;
  border: solid 1px #333;
  border-radius: 15px;
  background-color: red;
  color: white;
}

.recommend_bottom_top {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}
.recommend_bottom_top_Left {
  display: flex;
  align-items: center;
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
</style>