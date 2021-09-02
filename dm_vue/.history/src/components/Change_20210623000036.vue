<template>
  <div>
    <div class="userinfo">
          <h2>修改</h2>
          <van-form @submit="onSubmit" class="form1">
            <van-field
              v-model="user.username"
              name="username"
              label="用户名"
              placeholder="用户名"
              clearable
              @blur="checkName"
              :rules="[{ required: true, message: '请填写用户名' }]"
            />

            <van-field
            v-model="user.useremail"
            name="useremail"
            label="邮箱"
            placeholder="邮箱"
            :rules="[{required:true,message:'请填写邮箱'}]"
            />
            <van-field
            v-model="user.vericode"
            name="vericode"
            label="验证码"
            placeholder="验证码"
            :rules="[{required:true,message:'请填写验证码'}]"
            >
              <template #button>
                <van-button size="small" type="primary" @click="sendMail">发送验证码</van-button>
              </template>
            </van-field>

            <van-field
              v-model="user.userpwd"
              type="password"
              name="userpwd"
              label="密码"
              placeholder="密码"
              :rules="[{ required: true, message: '请填写密码' }]"
            />
            <van-field
              v-model="user.userpwd1"
              type="password"
              name="确认密码"
              label="确认密码"
              placeholder="确认密码"
              :rules="[{ required: true, message: '请填写确认密码' }]"
            />

            <van-field name="uploader" label="用户头像">
              <template #input>
                <van-uploader v-model="uploader" :max-count="1" :before-read="beforeRead"/>
              </template>
            </van-field>

            <div>
              <van-button round block type="info" native-type="submit">提交</van-button>
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
    name: 'Regist',
    data() {
      return { 
        user:{},
        uploader:[],
        picIp:"http://localhost:8080/pic/",
      }
    },
    methods:{
      checkName(){
        if(this.user.username !== undefined){
          if(this.user.username.length > 0 && this.user.username != localStorage.getItem("username")){
            this.axios.post("usermessage/checkName",{"username":this.user.username}).then(res=>{
              if(res.data=="exist"){
                Toast("该用户已存在，请使用其他的用户名");
                this.user.username="";
              }else{
                Toast("该用户名可以使用");
              }
            })
          }
        }
      },

      beforeRead(file){
        if(file.type !== 'image/jpeg'){
          Toast('请上传jpg格式图片');
          return false;
        }
        return true;
      },

      onSubmit(){
        if(this.user.userpwd != this.user.userpwd1){
          Toast('密码和确认密码必须保持一致');
          return;
        }
        if(this.uploader.length == 0){
          Toast("请上传图片!");
          return;
        }
        const form1 = document.querySelector(".form1");
        let formData=new FormData(form1);
        formData.append("imgFile",this.uploader[0].file);
        this.axios.post("usermessage/update",formData,{"username":localStorage.getItem('username')},{'Content-Type':'multipart/form-data'}).then((response) => {
          if(response.data=="ok"){
            Toast("修改成功");
            this.$router.push("Setting");
          }else{
            Toast("修改失败");
          }
        })
      },

      sendMail(){
        if(this.user.useremail.length>0){
          this.axios.post("usermessage/sendMail?useremail="+this.user.useremail).then(response=>{})
        }
      }
    }
  }
</script>

<style scoped>
  .userinfo {
    margin: 8px;
  }
  .userinfo h2{
    text-align: center;
  }
  .userinfo img {
    width: 80px;
    height: 80px;
    border-radius: 50%;
  }
  p.name{
      margin: 30px 0px;
  }
  .wrapper {
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100%;
    }

  .block {
      width: 100%;
      height: 40%;
      background-color: #fff;
    }
  .block h2{
      text-align: center;
  }
</style>
