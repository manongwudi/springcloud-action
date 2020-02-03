<template>
  <div id="charge">
  <el-card class="box-card">
     <ul class="msg-box">
      <li>
        <h4>钱包充值</h4>
      </li>
      <li>
          <h4 style="margin-bottom: 15px;">充值金额</h4>
            <el-radio-group v-model="amountVal" @change="amountChange">
                <el-radio border :label="''+ 20">¥20</el-radio>
                <el-radio border :label="''+ 50">¥50</el-radio>
                <el-radio border :label="''+ 100">¥100</el-radio>
                <el-radio border :label="''+ 200">¥200</el-radio>
                <el-radio border :label="''">自定义</el-radio>
            </el-radio-group>
      </li>
      <li>
          <h4 style="margin-bottom: 15px;">支付方式</h4>
            <el-radio-group v-model="rechargeParams.paymentType" @change="paymentTypeChange">
                <el-radio border :label="''+ 0">微信</el-radio>
                <el-radio border :label="''+ 1">支付宝</el-radio>
            </el-radio-group>
      </li>
      <li>
          <h4 style="margin-bottom: 15px;">充值金额</h4>
          <el-input :disabled="disabled" clearable v-model="rechargeParams.totalAmt" placeholder="请输入金额" style="width: 150px;"></el-input>
      </li>
    </ul>
    <div style="text-align: center; margin-top: 30px;">
      <el-button type="primary" @click="surePay">确认支付</el-button>
    </div>
  </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      amountVal: '',
      disabled: false,
      // 充值参数
      rechargeParams: {
        'totalAmt': '', // 金额
        'paymentType': '0', // 支付方式[0:微信,1:支付宝,2:余额,3:活动]
        'transType': '0' // 交易类型[0:充值,1:消费]
      }
    }
  },
  methods: {
    // 充值金额
    amountChange (val) {
      this.rechargeParams.totalAmt = val
      // eslint-disable-next-line eqeqeq
      if (val == '') {
        this.disabled = false
      } else {
        this.disabled = true
      }
    },
    // 支付方式
    paymentTypeChange (val) {
      this.rechargeParams.paymentType = val
    },
    // 确认支付
    async surePay () {
      // eslint-disable-next-line eqeqeq
      if (this.rechargeParams.totalAmt == '') {
        this.$message.warning('请输入金额')
        return
      }
      const res = await this.$http.post('orderInfo/createOrderInfo', this.rechargeParams)
      const {
        code,
        msg,
        result
      } = res.data
      if (code === '200') {
        // 支付方式跳转
        // eslint-disable-next-line eqeqeq
        if (this.rechargeParams.paymentType == '0') {
          this.$message.success('微信支付')
          this.wechatPay(result)
        // eslint-disable-next-line eqeqeq
        } else if (this.rechargeParams.paymentType == '1') {
          this.$message.success('支付宝支付')
          const payDiv = document.getElementById('payDiv')
          if (payDiv) {
            document.body.removeChild(payDiv)
          }
          const div = document.createElement('div')
          div.id = 'payDiv'
          div.innerHTML = result
          document.body.appendChild(div)
          document.getElementById('payDiv').getElementsByTagName('form')[0].submit()
        // eslint-disable-next-line eqeqeq
        } else if (this.rechargeParams.paymentType == '2') {
          this.$message.success('余额支付成功')
          this.$router.push({
            name: 'order'
          })
        } else {
          this.$message.success('活动支付')
        }
      } else if (code === 401) {
        this.$message.error(msg)
        this.$router.push({
          name: 'login'
        })
      } else {
        this.$message.error(msg)
      }
    },
    // 微信支付
    wechatPay (result) {
      if (result) {
        const orderParams = JSON.parse(result)
        sessionStorage.qrurl = orderParams.qrurl
        sessionStorage.amt = orderParams.amt
        sessionStorage.returnUrl = orderParams.returnUrl
        sessionStorage.order_id = orderParams.order_id
        this.$router.push({
          name: 'wechatPay'
        })
      }
    }
  }
}
</script>

<style scoped>
#charge {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
  margin-top: 0px;
}
/* 信息列表样式 */
.msg-box > li {
  list-style: none;
    border-bottom: 1px solid #c5c5c5;
    padding: 20px 10px;
}
</style>
