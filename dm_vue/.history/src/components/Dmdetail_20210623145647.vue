<template>
   <div>
       <div class="info">
           <h2>{{news.headtitle}}</h2>
           <p class="time">{{news.newstime|dateFormat}}</p>

           <div class="userlist">
               <van-image :src="picIp+news.image" round width="50" height="50" @click="getAuthor"></van-image>
               <div class="name">
                   {{news.username}}
               </div>
               <div class="right">
                   <van-button type="danger" plain @click="attention">{{tip2}}</van-button>
               </div>
           </div>

           <van-image
              width="100%"
              :src="picIp+news.pictures"
           />
           <p class="content" v-html="news.content"></p>
           <van-row v-for="item in commentList" :key="item.commentId">
               <van-col span="6">
                   <van-image
                      round
                      width="5rem"
                      height="5rem"
                      :src="picIp+item.image"
                   />
               </van-col>
               <van-col span="18">
                   <p class="user">
                       {{item.username}}---{{item.commenttime|dateFormat}}
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
   </div>
</template>

<script>
import Vue from 'vue';
import { Toast } from 'vant';
Vue.use(Toast);
Toast.setDefaultOptions({ duration: 2000 });
export default {
  name: 'NewsInfo',
  data() {
    return {
        newsId:this.$route.params.newsId,
        content:"",
        clearable:true,
        news:{},
        picIp:"http://172.16.35.25:8080/pic/",
        commentList:[],
        isLike:"like-o",
        user:JSON.parse(localStorage.getItem("user")),
        tip:"未收藏",
        tip2:"关注",
    }
  },
  created(){
      this.getNewsInfo();
      this.getComments();
    //   if(this.user!=null){
    //       this.isCollect();
    //   }
  },
  methods:{
      onSubmit(values){
          var user=JSON.parse(localStorage.getItem("user"));
          if(user==null){
              Toast('您未登陆，请登录后发表评论');
              return;
          }
          this.axios.post("newscomment/add?newsid="+this.newsId
          +"&content="+this.content+"&uid="+user.uid).then((response) => {
              this.getComments();
              this.content="";
          })
      },

      getNewsInfo(){
          this.axios.get("news/getById?newsid="+this.newsId).then((response) => {
              this.news=response.data;
              if(this.user != null){
                  this.isCollect();
                  this.isAttention();
              }
          })
      },

      getComments(){
          this.axios.get("newscomment/getByNewsid?newsid="+this.newsId).then((response) => {
              this.commentList=response.data;
          })
      },
      isCollect(){
          this.axios.get("newscollect/exist?newsid="+this.newsId+"&uid="+this.user.uid).then(res=>{
              if(res.data=="exist"){
                  this.isLike="like";
                  this.tip="已收藏";
              }
          })
      },
      collect(){
          if(this.user==null){
              Toast("您未登录，请登录后收藏新闻！");
              return ;
          }
          if(this.isLike == "like"){
              this.axios.get("newscollect/del?newsid="+this.newsId+"&uid="+this.user.uid).then(res=>{
                  this.isLike="like-o";
                  this.tip="未收藏";
              })
          }else{
              this.axios.get("newscollect/add?newsid="+this.newsId+"&uid="+this.user.uid).then(res=>{
                  this.isLike="like";
                  this.tip="已收藏";
              })
          }
      },

      getAuthor(){
          this.$router.push({
              name:"AuthorInfo",
              params:{
                  authorid:this.news.uid,
                  username:this.news.username,
                  image:this.news.image,
                  isAttention:this.tip2
              }
          })
      },
      isAttention(){
          this.axios.get("attention/exist?authorid="+this.news.uid+"&uid="+this.user.uid).then(res=>{
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
    margin: 0 10px;
}
.info h2{
    margin: 60px 0px 20px 0px;
    text-align: center;
    font-weight: 500;
    font-size: 20px;
}
.info h4{
    text-align: center;
    font-weight: 500;
    font-size: 16px;
}
p.time{
    text-align: center;
}
p.content{
    line-height: 1.5;
    text-indent: 15px;
}
p.user{
    font-size: 14px;
    margin: 5px;
}

.userlist{
    display: flex;
    height: 50px;
    line-height: 50px;
    font-size: 16px;
}
.userlist .van-image{
    flex: 1;
}
.userlist .name{
    flex: 5;
    margin-left: 10px;
}
.userlist .right{
    flex: 2;
    text-align: right;
}
.tip{
    font-size: 14px;
    color: #999;
}
</style>