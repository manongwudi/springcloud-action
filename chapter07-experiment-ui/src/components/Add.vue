<template>
    <el-col>
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
            <el-form-item label="姓名" prop="name">
                <el-input v-model="addForm.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-radio-group v-model="addForm.sex">
                    <el-radio class="radio" :label="1">男</el-radio>
                    <el-radio class="radio" :label="0">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="年龄">
                <el-input-number v-model="addForm.age" :min="0" :max="200"></el-input-number>
            </el-form-item>
            <el-form-item label="生日">
                <el-date-picker type="date" placeholder="选择日期" v-model="addForm.birth"></el-date-picker>
                </el-form-item>
                <el-form-item label="地址">
                <el-input type="textarea" v-model="addForm.addr"></el-input>
            </el-form-item>
        </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
    </el-col>
</template>
