<template>
  <div>
    <div class="userinfo">
      <van-form @submit="onSubmit" class="form1">
        <van-field
          v-model="post.postcontent"
          name="postcontent"
          rows="1"
          autosize
          label="发布内容"
          type="textarea"
          placeholder="请输入发布内容"
          :rules="[{ required: true, message: '请填写发布内容' }]"
        />

        <van-field name="uploader" label="内容图片">
          <template #input>
            <van-uploader v-model="uploader" :max-count="1"/>
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
      post:{},
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
      formData.append("username",localStorage.getItem("username"));

      this.axios.post("userpost/fabu",formData,{'Content-Type':'multipart/form-data'}).then((response) => {
        if(response.data=="ok"){
          Toast("发布成功,待审核!");
          this.$router.push("fabu");
        }else{
          Toast("发布失败");
        }
      })
    },
  }
}
</script>

<style scoped>

</style>