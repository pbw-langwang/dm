<template>
  <div class="info">
    <div class="recommend_bottom_top">
      <div class="recommend_bottom_top_Left">
        <p style="font-size:20px;font-weight:700">{{news.actname}}</p>
        <div class="recommend_bottom_top_Left_vip" v-if="news.actvip == 1">
          <p>vip</p>
        </div>
      </div>
    </div>

    <p>{{news.actintroduction}}</p>
    <van-image
      width="100%"
      :src="picIp+news.actimg"
    />
    
    <div style="margin:16px;">
        <van-button round block type="info" @click="seeDm(news.acturl)">观看</van-button>
    </div>

  </div>
</template>

<script>
import Vue from 'vue';
import { Toast } from 'vant';
Vue.use(Toast);
Toast.setDefaultOptions({ duration: 2000 });

export default {
  name: 'Dmdetail',
  data() {
    return {
        actid:this.$route.params.value,
        content:"",
        clearable:true,
        news:{},
        picIp:"http://localhost:8080/pic/",
        commentList:[],
    }
  },
  mounted(){
      this.getNewsInfo();
  },
  methods:{
    getNewsInfo(){
      this.axios.get("actmessage/getByid?actid="+this.actid).then((response) => {
        console.log(response);
        this.news=response.data;
      })
    },
    seeDm(url){
      window.open(url);
    },
  }
}

</script>


<style scoped>
.info{
    margin:0 10px 70px;
}
p.user{
    font-size: 14px;
    margin: 5px;
}



.recommend_bottom_top_Left {
  display: flex;
  align-items: center;
}
.recommend_bottom_top_Left_vip{
  background-color: gold;
  color: white;
  border-radius: 10px;
  border: 1px #333 solid;
  margin-left: 10px;
}
.recommend_bottom_top_Left_vip p{
  margin: 0;
  padding: 2px 5px;
}
</style>