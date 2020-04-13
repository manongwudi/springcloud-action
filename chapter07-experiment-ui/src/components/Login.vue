<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-position="left" label-width="0px" class="demo-ruleForm login-container">
    <h3 class="title">实验系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="ruleForm.account" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit" :loading="logining">登录</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
// 引入请求方法
import { requestLogin } from '../api/api'
export default {
  data () {
    return {
      logining: false,
      ruleForm: {
        account: 'admin',
        checkPass: '123456'
      },
      rules: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        checkPass: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
      checked: true
    }
  },
  methods: {
    // 定义登录处理函数
    handleSubmit (ev) {
      // eslint-disable-next-line no-unused-vars
      var _this = this
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.logining = true
          var loginParams = { username: this.ruleForm.account, password: this.ruleForm.checkPass }
          requestLogin(loginParams).then(resData => {
            this.logining = false
            let { code, message, data } = resData
            if (code !== 0) {
              this.$message({
                message: message,
                type: 'error'
              })
            } else {
              // 日志打印，便于调试
              console.log(JSON.stringify(data))
              // 将用户信息进行Session存储
              sessionStorage.setItem('user', JSON.stringify(data))
              // 页面路由跳转至/list
              this.$router.push({ path: '/list' })
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 35px 0px;
    }
  }
</style>
