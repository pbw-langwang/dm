<template>
  <div>
    <div class="userinfo">
      <van-form @submit="onSubmit" class="form1">
        <van-field
          v-model="post.postcontent"
          name="postcontent"
          placeholder="发布内容"
          clearable
          @blur="checkName"
          :rules="[{ required: true, message: '请填写发布内容' }]"
        />

        <van-field name="uploader" label="内容图片">
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
export default {
  name:"FabuPost",
  data(){
    return{
      
    }
  }
}
</script>

<style scoped>

</style>