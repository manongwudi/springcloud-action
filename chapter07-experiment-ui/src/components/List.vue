<template>
    <section>
        <!--查询工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <!--定义查询表单-->
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="实验名称"></el-input>
                    <el-select v-model="filters.status" placeholder="请选择状态" @change="styleChange">
                           <el-option v-for="item in optionStatus" :key="item.value" :label="item.label" :value="item.value"/>
                    </el-select>
                </el-form-item>
            </el-form>
        </el-col>
        <!--具体的数据列表-->
        <el-table :data="expInfos" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width :100%;">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column type="index" width="60"></el-table-column>
          <el-table-column prop="id" label="实验ID" width="120" sortable></el-table-column>
          <el-table-column prop="factorTag" label="实验Key" width="100" sortable></el-table-column>
          <el-table-column prop="name" label="实验描述" width="100" sortable></el-table-column>
          <el-table-column prop="status" label="实验状态" width="100" :formatter="formatStatus" sortable></el-table-column>
          <el-table-column prop="owner" label="创建人" width="100" sortable></el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="100" sortable></el-table-column>
          <!--定义列表行后的操作编辑按钮-->
          <el-table-column label="操作" width="150">
            <template slot-scope="scope">
              <el-button size="small" @click="handleEdit(scope.$index,scope.row)">编辑</el-button>
              <el-button size="danger" @click="handleDel(scope.$index,scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
    </section>
</template>
<script>
// eslint-disable-next-line no-unused-vars
import {getExpInfosListPage} from '../api/api'
export default {
  data () {
    return {
      filters: {
        name: ''
      },
      expInfos: [],
      totoal: 0,
      page: 1,
      listLoading: false,
      sels: [], // 列表中选中的列
      editFormVisible: false, // 编辑界面是否显示
      editFormRules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ]
      },
      // 定义状态下拉选
      optionStatus: [
        {
          value: '0',
          label: '全部状态'
        },
        {
          value: '1',
          label: '正常运行'
        },
        {
          value: '2',
          label: '离线或故障'
        },
        {
          value: '3',
          label: '超标报警'
        }
      ]
    }
  },
  methods: {
    styleChange (val) {
      if (val === 0) {
        this.isShow1 = true
        this.isShow2 = false
      } else {
        this.isShow1 = false
        this.isShow2 = true
      }
      var data = {}
      this.outfallType = val
      data.outfallType = this.outfallType
      // eslint-disable-next-line no-undef
      getRealDataV2(data).then(val => {
        if (val.code === 200) {
          this.newData(val)
        }
      })
    },
    selsChange: function (sels) {
      this.sels = sels
    },
    // 状态显示转换
    formatStatus: function (row, column) {
      if (row.status === 0) {
        return '新建'
      }
      if (row.status === 1) {
        return '已发布'
      }
    },

    // 获取实验信息列表
    getExpInfos () {
      let para = {
        page: this.page,
        name: this.filters.name
      }
      this.listLoading = true
      getExpInfosListPage(para).then((res) => {
        this.total = res.data.total
        this.users = res.data.expInfos
        this.listLoading = false
      })
    }
  }
}
</script>
<style scoped>

</style>
