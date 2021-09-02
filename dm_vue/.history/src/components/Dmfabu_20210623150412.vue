<template>
  <div>
    <div class="userinfo">
      <van-form @submit="onSubmit" class="form1">
        <van-field
          v-model="dm.contentname"
          name="contentname"
          label="动漫名"
          placeholder="动漫名"
          clearable
          @blur="checkName"
          :rules="[{ required: true, message: '请填写动漫名' }]"
        />

        <van-field
          v-model="dm.useremail"
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
  name:"FabuPost",
  data(){
    return{
      dm:{},
      uploader:[],
      picIp:"http://localhost:8080/pic/",
    }
  },
  methods:{
    onSubmit(){
      const form1 = document.querySelector(".form1");
      let formData=new FormData(form1);

      formData.append("imgFile",this.uploader[0].file);
      formData.append("username",localStorage.getItem("username"));

      this.axios.post("userpost/fabu",formData,{'Content-Type':'multipart/form-data'}).then((response) => {
        if(response.data=="ok"){
          Toast("发布成功!");
          this.$router.push("fabu");
        }else{
          Toast("发布失败");
        }
      })
    },
  }
}
</script>
