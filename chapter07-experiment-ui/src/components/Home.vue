<template>
  <!--采用Element-UI，Layout布局方式,通过row和col组件，并结合col组件的span属性进行自由地组合布局-->
  <el-row>
      <!--定义主界面头-->
      <el-col :span="24" class="header">
         <el-col :span="10" class="logo" :class="collapsed?'logo-collapse-width':'logo-width'">
            {{collapsed?'':sysName}}
         </el-col>
         <el-col :span="10">
            <div class="tools" @click.prevent="collapse">
                <i class="fa" fa-align-justify></i>
            </div>
         </el-col>
         <el-col :span="4" class="userinfo">
             <el-dropdown trigger="hover">
                 <span class="el-dropdown-link userinfo-inner"><img :src="this.sysUserAvatar"/>{{sysUserName}}</span>
                 <el-dropdown-item>我的消息</el-dropdown-item>
                 <el-dropdown-item>设置</el-dropdown-item>
                 <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
             </el-dropdown>
         </el-col>
      </el-col>
      <!--定义主菜单部分-->
      <el-col :span="24" class="main">
        <!--定义侧边栏菜单-->
        <aside :class="collapsed?'menu-collapsed':'menu-expanded'">
            <!--定义导航菜单-->
            <!--设置菜单栏展开-->
            <el-menu :default-active="$route.path" class="el-menu-vertical-demo" @open="handleopen" @close="handleclose" @select="handleselect" unique-opened router v-show="!collapsed">
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
            <!--定义导航菜单折叠后效果-->
            <ul class="el-menu el-menu-vertical-demo collapsed" v-show="collapsed" ref="menuCollapsed">
                <li v-for="(item,index) in $router.options.routes" v-if="!item.hidden" class="el-submenu item" :key="index">
                    <template v-if="!item.leaf">
                        <div class="el-submenu__tilte" style="padding-left:20px" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,true)"><i :class="item.iconCls"></i></div>
                        <ul class="el-menu submenu" :class="'submenu-hook-'+index" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)">
                            <li v-for="child in item.children" v-if="!child.hidden" :key="child.path" class="el-menu-item" style="padding-left: 40px;" :class="$route.path==child.path?'is-active':''" @click="$router.push(child.path)">{{child.name}}</li>
                        </ul>
                    </template>
                    <template v-else>
                        <li class="el-submenu">
                            <div class="el-submenu__title el-menu-item" style="padding-left: 20px;height: 56px;line-height: 56px;padding: 0 20px;" :class="$route.path==item.children[0].path?'is-active':''" @click="$router.push(item.children[0].path)"><i :class="item.iconCls"></i></div>
                        </li>
                    </template>
                </li>
            </ul>
        </aside>
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
      </el-col>
  </el-row>
</template>

<!--脚本方法-->
<script>
export default {
  data () {
    return {
      sysName: 'A/B实验管理系统',
      collapsed: false,
      sysUserName: 'jiangqiao',
      sysUserAvatar: '',
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit () {
      console.log('submit!')
    },
    handleopen () {
      console.log('handleopen')
    },
    handleclose () {
      console.log('handleclose')
    },
    handleselect: function (a, b) {
    },
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
  // 折叠导航栏
  collapsed: function () {
    this.collapsed = !this.collapsed
  },
  // 显示菜单
  showMenu (i, status) {
    this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none'
  },
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

<!--样式 scss技术-->
<style scoped lang="scss">
@import '../styles/vars.scss';
.container{
    position: absolute;
    top: 0px;
    bottom: 0px;
    width: 100%;
    .header{
        height: 60px;
        line-height: 60px;
        background: $color-primary;
        color:#fff;
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
            height: 60px;
            font-size: 22px;
            padding-left:20px;
            padding-right: 20px;
            border-color:rgba(238,241,146,0.3);
            border-right-width: 1px;
            border-right-style: solid;
            img{
                width: 40px;
                float: left;
                margin: 10px 10px 10px 18px;
            }
            .txt{
                color:#fff;
            }
        }
        .logo-width{
            width: 230px;
        }
        .logo-collapse-width{
            width:60px;
        }
        .tools{
            padding:0px 23px;
            width: 14px;
            height: 60px;
            line-height: 60px;
            cursor: pointer;
        }
    }
    .main {
        display: flex;
        position: absolute;
        top: 60px;
        bottom: 0px;
        overflow: hidden;
        aside{
            flex: 0 0 230px;
            width: 230px;
            .el-menu{
                height: 100%;
            }
            .collapsed{
                width: 60px;
                .item{
                    position: relative;
                }
                .submenu{
                    position: absolute;
                    top:0px;
                    left: 60px;
                    z-index: 99999;
                    height:auto;
                    display: none;
                }
            }
        }
        .menu-collapsed{
            flex: 0 0 60px;
            width: 60px;
        }
        .menu-expanded{
            flex: 0 0 230px;
            width: 230px;
        }
        .content-container{
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
            .content-wrapper{
                background-color: #fff;
                box-sizing: border-box;
            }
        }
    }
}
</style>
