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
          :rules="[{ required: true, message: '请填写动漫名' }]"
        />

        <van-field
          v-model="dm.contenturl"
          name="contenturl"
          label="动漫播放地址"
          placeholder="动漫播放地址"
          :rules="[{required:true,message:'请填写动漫播放地址'}]"
        />

        <van-field
          v-model="dm.contentintroduction"
          name="contentintroduction"
          label="动漫简介"
          placeholder="动漫简介"
          :rules="[{ required: true, message: '请填写动漫简介' }]"
        />

        <van-field
          v-model="dm.contenttype"
          name="contenttype"
          label="动漫类型（0-4）"
          placeholder="动漫类型（0-4）"
          :rules="[{ required: true, message: '请填写动漫类型（0-4）' }]"
        />

        <van-field
          v-model="dm.contentvip"
          name="contentvip"
          label="动漫是否需要vip（是：1；否：0）"
          placeholder="动漫是否需要vip（是：1；否：0）"
          :rules="[{ required: true, message: '请填写动漫是否需要vip（是：1；否：0）' }]"
        />

        <van-field name="uploader" label="动漫图片">
          <template #input>
            <van-uploader v-model="uploader" :max-count="1"/>
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
  name:"Dmfabu",
  data(){
    return{
      dm:{},
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

      this.axios.post("contentmessage/fabu",formData,{'Content-Type':'multipart/form-data'}).then((response) => {
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
