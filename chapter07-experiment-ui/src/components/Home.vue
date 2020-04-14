<template>
    <el-container style="height: 500px; border: 1px solid #eee">
        <!--通过Element-UI容器布局方式对系统主页进行布局-->
        <el-header style="text-align: right; font-size: 12px">
            <!--系统名称展示区域控制-->
            <el-col :span="10" class="logo">
                {{sysName}}
            </el-col>
            <!--根据登录会话动态显示用户信息-->
            <el-col :span="4" class="userinfo">
                <el-dropdown>
                    <span class="userinfo-inner"><img :src="this.sysUserAvatar"/>{{sysUserName}}</span>
                    <!--用户操作下拉菜单-->
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>我的消息</el-dropdown-item>
                        <el-dropdown-item>设置</el-dropdown-item>
                        <!--退出登录点击触发事件-->
                        <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-col>
        </el-header>
        <!--左侧导航于主区域位于同一容器-->
        <el-container class="main">
            <!--定义左侧导航栏-->
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <!--根据路由列表定义动态迭代菜单，避免写死-->
                <el-menu :default-active="$route.path">
                    <!--定义内层模版，通过v-for标签动态遍历菜单选项-->
                    <template  v-for="(item,index) in $router.options.routes" v-if="!item.hidden">
                        <!--定义子菜单-->
                        <el-submenu :index="index+''" v-if="!item.leaf" :key="index">
                            <!--定义插槽-->
                            <template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>
                            <!--用于导航选中后的页面刷新-->
                            <el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.hidden">{{child.name}}</el-menu-item>
                        </el-submenu>
                        <el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path" :key="item.path"><i :class="item.iconCls">{{item.children[0].name}}</i></el-menu-item>
                    </template>
                </el-menu>
            </el-aside>
            <!--HTML5标签定义具体内容区段-->
            <section class="content-container">
                <div class="grid-content bg-purple-light">
                    <el-col :span="24" class="breadcrumb-container">
                        <strong class="title">{{$route.name}}</strong>
                        <!--Element-UI面包屑标签-->
                        <el-breadcrumb separator="/" class="breadcrumb-inner">
                        <el-breadcrumb-item v-for="item in $route.mathched" :key="item.path">
                            {{item.name}}
                        </el-breadcrumb-item>
                        </el-breadcrumb>
                    </el-col>
                    <el-col :span="24" class="content-wrapper">
                        <!--动画元素包裹-->
                        <transition name="fade" mode="out-in">
                            <router-view>/</router-view>
                        </transition>
                    </el-col>
                </div>
            </section>
        </el-container>
        <el-footer>休闲鞋</el-footer>
    </el-container>
</template>
<!--脚本区域-->
<script>
export default {
  data () {
    return {
      sysName: 'A/B实验管理系统',
      sysUserName: 'wudimanong',
      sysUserAvatar: ''
    }
  },
  // 定义JavaScript处理函数
  methods: {
    // 退出登录
    logout: function () {
      var _this = this
      this.$confirm('确认退出?', '提示', {
        // type : 'warning'
      }).then(() => {
        sessionStorage.removeItem('user')
        _this.$router.push('/login')
      }).catch(() => {
      })
    }
  },
  // 添加Vue钩子，在页面渲染后设置用户登录信息
  mounted () {
    var user = sessionStorage.getItem('user')
    if (user) {
      user = JSON.parse(user)
      this.sysUserName = user.name || ''
      this.sysUserAvatar = user.avatar || ''
    }
  }
}
</script>
<!--样式控制，采用SCSS语法-->
<style scoped lang="scss">
.el-header {
    background-color: rgb(106, 160, 231);
    color: rgb(250, 245, 245);
    line-height: 60px;
    .userinfo{
        text-align: right;
        padding-right: 35px;
        float: right;
        .userinfo-inner{
            cursor: pointer;
            color: #fff;
            img{
                width: 40px;
                height: 40px;
                border-radius: 20px;
                margin:10px 0px 10px 10px;
                float: right;
            }
        }
    }
    .logo{
        text-align: left;
        height: 60px;
        font-size: 20px;
        padding-left:2px;
        padding-right: 2px;
        border-color:rgba(238,241,146,0.3);
        border-right-width: 0px;
        border-right-style: solid;
    }
}
.main{
    display: flex;
    position: absolute;
    top: 60px;
    bottom: 0px;
    overflow: hidden;
    .el-aside{
        flex: 0 0 230px;
        width: 230px;
    }
    .el-menu{
        height: 100%;
        .submenu{
            position: absolute;
            top:0px;
            left: 60px;
            z-index: 99999;
            height:auto;
            display: none;
        }
    }
    .content-container {
        flex:1;
        overflow-y: scroll;
        padding: 20px;
        .breadcrumb-container{
            .title{
                width: 200px;
                float: left;
                color: #475669;
            }
            .breadcrumb-inner{
                float: right;
            }
        }
    }
}
</style>
