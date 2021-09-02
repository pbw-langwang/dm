<template>
  <div>
    <div class="userinfo">
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