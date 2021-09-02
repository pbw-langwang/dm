<template>
  <div>
    <div class="userinfo">
      <van-form @submit="onSubmit" class="form1">
        <van-field
          v-model="post.postcontent"
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
export default {
  name:"FabuPost",
  data(){
    return{
      post:{},
      picIp:"http://localhost:8080/pic/",
    }
  },
  methods:{
    onSubmit(){
      
    },
  }
}
</script>

<style scoped>

</style>