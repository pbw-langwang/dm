<template>
  <div>
    <img
      style="width: 100%; display: block"
      src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3563343280,3778038422&fm=26&gp=0.jpg"
    />
    <div class="user">
      <div class="user_top" v-if="show" @click="gotochange()">
        <div class="user_top_L">
          <img :src="pic+photourl" />
          <p>{{username}}</p>
        </div>
        <p style="margin-right: 5vw">></p>
      </div>

      <div style="margin:5vw" v-if="!show" @click="showLogin = true">
        <p>未登录，请登录</p>
      </div>

      <!-- 弹窗 -->
      <van-overlay :show="showLogin" @click="showLogin = false">
        <!-- 作用是点击空白处可以取消输入 -->
        <div class="wrapper" @click.stop>
          <div class="block">
            <h2>登录</h2>
            <van-form @submit="onSubmit">
                <van-field
                v-model="username"
                name="用户名"
                label="用户名"
                placeholder="用户名"
                :rules="[{ required: true, message: '请填写用户名' }]"
              />
              <van-field
                v-model="password"
                type="password"
                name="密码"
                label="密码"
                placeholder="密码"
                :rules="[{ required: true, message: '请填写密码' }]"
              />
              <div style="margin: 16px;">
                <van-button round block type="info" native-type="submit">提交</van-button>
              </div>
              <div class="registbtn" @click="gotoRegist">
                新用户点击此处进行注册
              </div>
            </van-form>
          </div>
        </div>
      </van-overlay>

      <div class="user_bottom">
        <div class="user_bottom_L">
          <van-icon name="gift-o" size="30" />
          <div class="user_bottom_L_text">
            <p style="font-size: 14px; font-weight: 600">观看领奖</p>
            <p style="color: #555">共看{{num}}部</p>
          </div>
        </div>
      </div>
    </div>

    <div class="clear_position"></div>

    <div class="setting">
      <div class="vip">
        <h3><span class="vip_h3">V</span>会员</h3>
        <p>新人首月仅9元<span class="vip_p_span">立即前往</span></p>
      </div>
      <hr />
      <div class="inform_box">
        <div class="inform" @click="gotohistory()">
          <p>观看历史</p>
          <p>></p>
        </div>
      </div>

      <div class="inform_box">
        <div class="inform" @click="gotofabu()">
          <p>我的发布</p>
          <p>></p>
        </div>
      </div>

      <div class="inform_box" v-if="usertype == 1">
        <div class="inform" @click="gotoYhfabu()">
          <p>用户发帖</p>
          <p>></p>
        </div>
      </div>

      <div class="inform_box" v-if="usertype == 1">
        <div class="inform" @click="gotoDmfabu()">
          <p>发布动漫</p>
          <p>></p>
        </div>
      </div>

      <div class="inform_box" v-if="usertype == 1">
        <div class="inform" @click="gotoHdfabu()">
          <p>发布活动</p>
          <p>></p>
        </div>
      </div>

      <div class="inform_box" v-if="usertype == 1">
        <div class="inform" @click="gotoDmList()">
          <p>动漫管理</p>
          <p>></p>
        </div>
      </div>

      <div class="inform_box" v-if="usertype == 1">
        <div class="inform" @click="gotoHdList()">
          <p>活动管理</p>
          <p>></p>
        </div>
      </div>

      <div style="padding: 16px;margin-bottom:70px" @click="quit()">
        <van-button round block type="info" native-type="submit">
          <p style="font-size:20px;font-weight:600">退出登录</p>
        </van-button>
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
  name: "setting",
  data() {
    return {
      pic:"http://localhost:8080/pic/",
      show:false,
      showLogin:false,
      username:"",
      password:"",
      photourl:"",
      num:0,
      usertype:localStorage.getItem("usertype"),
    };
  },
  methods:{
    onSubmit(){
      // 这里要修改成写着对应login方法的文件
      this.axios.post("usermessage/login?username="+this.username+"&userpwd="+this.password).then((response)=>{
        console.log(response);
        var result = response.data;
        
        if(result == null || result == ""){
          Toast("用户名密码错误，请重新输入");
        }else{
          this.showLogin=false;
          this.show = true;
          Toast("用户登录成功");
          this.photourl = result.userimg;
          this.usertype = result.usertype;
          localStorage.setItem("username",this.username);
          localStorage.setItem("userImg",result.userimg);
          localStorage.setItem("uservip",result.uservip);
          localStorage.setItem("userid",result.userid);
          localStorage.setItem("usertype",result.usertype);
        }
      })
    },

    gotoRegist(){
      this.$router.push("Regist");
    },

    gotochange(){
      this.$router.push("Change");
    },

    gotohistory(){
      if(localStorage.getItem("username") != null){
        this.$router.push("History");
      }else{
        Toast("请登陆后查看!");
      }
    },

    gotofabu(){
      if(localStorage.getItem("username") != null){
        this.$router.push("fabu");
      }else{
        Toast("请登陆后查看!");
      }
    },

    gotoYhfabu(){
      if(localStorage.getItem("username") != null){
        this.$router.push("Yhfabu");
      }else{
        Toast("请登陆后查看!");
      }
    },

    gotoDmfabu(){
      if(localStorage.getItem("username") != null){
        this.$router.push("Dmfabu");
      }else{
        Toast("请登陆后查看!");
      }
    },

    gotoHdfabu(){
      if(localStorage.getItem("username") != null){
        this.$router.push("Hdfabu");
      }else{
        Toast("请登陆后查看!");
      }
    },

    gotoDmList(){
      if(localStorage.getItem("username") != null){
        this.$router.push("Dmlist");
      }else{
        Toast("请登陆后查看!");
      }
    },

    gotoHdList(){
      if(localStorage.getItem("username") != null){
        this.$router.push("Hdlist");
      }else{
        Toast("请登陆后查看!");
      }
    },

    getNum(){
      this.axios.get("contenthistory/getNum?username="+localStorage.getItem("username")).then(res=>{
        this.num = res.data;
      })
    },

    quit(){
      localStorage.removeItem("username");
      localStorage.removeItem("userImg");
      localStorage.removeItem("uservip");
      localStorage.removeItem("userid");
      localStorage.removeItem("usertype");
      this.show=false;
      this.photourl="";
      this.num = 0;
    }
  },

  mounted(){
    this.getNum();
    const name = localStorage.getItem("username");
    if(name != null){
      this.username=name;
      this.show=true;
      this.photourl=localStorage.getItem("userImg");
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
* {
  margin: 0;
}
.user {
  width: 90%;
  border-radius: 10px;
  background-color: white;
  position: absolute;
  top: 5vh;
  left: 5vw;
}
.user_top {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 1px rgb(204, 203, 203);
}
.van-overlay{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
}
.wrapper{
    width: 100%;
}
.block{
    text-align: center;
    width: 100%;
    background-color: white;
    padding: 20px 0;
}
.registbtn{
    text-align: center;
    margin: 16px;
    font-size: 18px;
    color: #dd4c4c;
}
.user_top img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  margin-right: 30px;
  position: relative;
  bottom: 15px;
  left: 5vw;
}
.user_top_L {
  display: flex;
  align-items: center;
}

.user_bottom {
  display: flex;
  margin: 20px 0;
}
.user_bottom_L {
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.user_bottom_L_text {
  display: flex;
  flex-direction: column;
  font-size: 12px;
  margin-left: 10px;
}

.clear_position {
  height: 12vh;
  width: 100%;
  background-color: rgb(237, 238, 238);
}

.setting {
  width: 100%;
}
.vip {
  width: 86%;
  margin: 0 auto;
  padding: 3px 2vw;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  background-color: black;
  color: gold;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.vip p {
  font-size: 12px;
}
.vip_h3 {
  font-style: italic;
}
.vip_p_span {
  padding: 2px 5px;
  margin-left: 10px;
  border-radius: 15px;
  font-size: 10px;
  background-color: gold;
  color: black;
}

.inform_box {
  width: 100%;
  border-bottom: solid 1px rgb(187, 187, 187);
  background-color: white;
}
.inform {
  width: 90%;
  margin: 0 auto;
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.setting_bottom {
  background-color: white;
  border-bottom: solid 5px rgb(187, 187, 187);
}
</style>
