<template>
  <div id="app">
    <!-- 由于Element-UI官方支持的ICON图标比较少，这里我们自定义一个货币图标-->
    <i class="el-icon-cny"/><br/>
    <div>
      <span>账户余额</span>
    </div>
    <!--调用后端余额查询接口进行数据渲染-->
    <div>
      {{balance}}
    </div>
    <!--使用Element-UI组件添加充值按钮-->
    <br/>
    <el-row>
        <el-button type="info" @click="getBalance('10001')">充值</el-button>
    </el-row>
    <router-view/>
  </div>
</template>

<script>
// 引入axios
// eslint-disable-next-line no-unused-vars
import axios from 'axios'

export default {
  name: 'App',
  // 页面数据定义
  data () {
    return {
      balance: ''
    }
  },
  methods: {
    // 获取用户余额方法
    getBalance () {
      axios.get('/api/account/queryAcc?userId=10001&accType=0').then(response => {
        // 通过接口返回数据为显示变量赋值
        this.balance = '¥' + response.data.data[0].balance / 100 + '元'
        console.log(response.data)
      }, response => {
        console.log('error')
      })
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.el-icon-cny{
    background: url(../src/assets/cny.png) center no-repeat;
    background-size: cover;
}
.el-icon-cny:before{
    content: "替";
    font-size: 35px;
    visibility: hidden;
}
</style>
