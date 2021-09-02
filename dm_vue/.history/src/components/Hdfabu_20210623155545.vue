<template>
  <div>
    <div class="userinfo">
      <van-form @submit="onSubmit" class="form1">
        <van-field
          v-model="hd.actname"
          name="actname"
          label="活动名"
          placeholder="活动名"
          clearable
          @blur="checkName"
          :rules="[{ required: true, message: '请填写活动名' }]"
        />

        <van-field
          v-model="hd.acturl"
          name="acturl"
          label="活动地址"
          placeholder="活动地址"
          :rules="[{required:true,message:'请填写活动地址'}]"
        />

        <van-field
          v-model="hd.actintroduction"
          name="actintroduction"
          label="活动简介"
          placeholder="活动简介"
          :rules="[{ required: true, message: '请填写活动简介' }]"
        />

        <van-field
          v-model="hd.type"
          name="type"
          label="活动类型（0-2）"
          placeholder="活动类型（0-2）"
          :rules="[{ required: true, message: '请填写活动类型（0-2）' }]"
        />

        <van-field
          v-model="hd.actvip"
          name="actvip"
          label="活动是否需要vip（是：1；否：0）"
          placeholder="活动是否需要vip（是：1；否：0）"
          :rules="[{ required: true, message: '请填写活动是否需要vip（是：1；否：0）' }]"
        />

        <van-field name="uploader" label="活动图片">
          <template #input>
            <van-uploader v-model="uploader" :max-count="1" :before-read="beforeRead"/>
          </template>
        </van-field>

        <div class="btn">
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
  name:"Hdfabu",
  data(){
    return{
      hd:{},
      uploader:[],
      picIp:"http://localhost:8080/pic/",
    }
  },
  methods:{
    onSubmit(){
      if(this.uploader.length == 0){
        Toast("请上传图片!");
        return;
      }

      const form1 = document.querySelector(".form1");
      let formData=new FormData(form1);

      formData.append("imgFile",this.uploader[0].file);

      this.axios.post("actmessage/fabu",formData,{'Content-Type':'multipart/form-data'}).then((response) => {
        if(response.data=="ok"){
          Toast("发布成功!");
          this.$router.push("setting");
        }else{
          Toast("发布失败");
        }
      })
    },
  }
}
</script>
<style scoped>
.btn{
  margin-bottom: 70px;
}
</style>
