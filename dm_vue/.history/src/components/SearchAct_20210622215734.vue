<template>
<div class="search">
    <van-search
        v-model="value"
        shape="round"
        placeholder="请输入搜索关键词"
        @search="onSearch"
    />
    <van-row>
        <van-col span="10"><span>历史记录</span></van-col>
        <van-col span="2" offset="12">
            <van-icon name="delete" @click="clearHistory" />
        </van-col>
    </van-row>
    <div class="history">
        <van-row gutter="20">
            <van-col span="12" v-for="item in historyList" :key="item" @click="gotoSearchList(item)">
                {{item}}
            </van-col>
        </van-row>
    </div>
</div>
</template>
<script>
export default {
    name:'Search',
    data(){
        return{
            value:"",
            historyList:[]
        }
    },
    created(){
        var history=localStorage.getItem("acthistory");
        if(history!=null){
            this.historyList=history.split(",");
        }
    },
    methods:{
        clearHistory(){
            this.historyList=[];
            localStorage.setItem("acthistory","");
        },
        onSearch(val){
            for(var i=0;i<this.historyList.length;i++){
                if(this.historyList[i]==val){
                    this.historyList.splice(i,1);
                    break;
                }
            }
            this.historyList.splice(0,0,val);
            localStorage.setItem("acthistory",this.historyList.toString());
            
            this.$router.push("SearchList/"+val);
        },
        gotoSearchList(val){
          this.$router.push("SearchList/"+val);
        }
    }
}
</script>

<style scoped>
.search{
    margin: 8px;
}
.history{
    margin-top: 10px;
}
.history .van-col{
    margin: 5px 0px;
}
.search span{
    font-size: 16px;
    color: #555;
}
</style>