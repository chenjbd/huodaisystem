<template>
    <div class="page-container">
        <el-row>
            <el-col>
                <div class="page-panel">
                    <el-row :gutter="20">
                        <el-col :span="6">
                            <el-input v-model="cxForm.driverName" placeholder="司机姓名"></el-input>
                        </el-col>
                        <el-col :span="6">
                            <el-button type="primary" @click="loadTableData">查询</el-button>
                            <el-button type="primary" @click="handleA">新增</el-button>
                        </el-col>
                    </el-row>
                </div>
            </el-col>
        </el-row>
        <el-row style="margin-top: 15px;">
            <el-col>
                <div class="page-panel">
                    <el-table
                    height="calc(100vh - 275px)"
                    :data="tableData"
                    border
                    stripe
                    highlight-current-row
                    >
                    <el-table-column
                    type="index"
                    label="序号"
                    width="50">
                    </el-table-column>
                    <el-table-column label="司机姓名" width="200">
                        <template slot-scope="{row}">
                        <el-tag>
                            {{ row.driverName}}
                        </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                    prop="tel"
                    label="联系电话"
                    width="250">
                    </el-table-column>
                    <el-table-column
                    prop="note"
                    label="说明">
                    </el-table-column>
                    <el-table-column
                    fixed="right"
                    label="操作"
                    width="200">
                    <template slot-scope="scope">
                        <el-button @click="handleE(scope.row)" type="text" size="small">编辑</el-button>
                        <el-button @click="handleD(scope.row)" type="text" size="small">删除</el-button>
                    </template>
                    </el-table-column>
                    </el-table>
                    <el-pagination
                        @current-change="handleCurrentChange"
                        :current-page.sync="page.pageIndex"
                        :page-size="page.pageSize"
                        layout="total, prev, pager, next"
                        :total="page.total">
                    </el-pagination>
                </div>
            </el-col>
        </el-row>  

        <SjxxForm :title="dialog.title" :dialog-visible="dialog.dialogVisible" 
        :id="dialog.id"
        :f="dialog.f"
        :dialogData="cxForm"
        @closeDialog="closeDialog"
        />

    </div>
</template>
<script>
import { loadData, remove } from '@/api/basic/sjxx'
import SjxxForm from './components/sjxx-form.vue'
export default {
    name:'SJXX',
    components:{ SjxxForm },
    data(){
        return {
            cxForm:{
                driverName:'',
            },
            tableData: [],
            page:{
                total: 0,
                pageSize: 20,
                pageIndex: 1
            },
            dialog:{
                title: '表单',//弹窗标题
                dialogVisible: false,//是否显示弹窗
                f: '',//弹窗类型 查看/新增/修改
                id: '',//传给子组件的id
            },
            dialogRes:{
                title: '角色授权',//弹窗标题
                dialogVisible: false,//是否显示弹窗
                f: '',//弹窗类型
                id: '',//传给子组件的id
            }
        }
    },
    methods:{
        loadTableData(){
            let param = Object.assign({}, this.page)
            param = Object.assign(param, this.cxForm)
            loadData(param).then(res=>{
                const data = res.data
                this.tableData = data.data
                this.page.total = data.count
            }).catch(error => {
                reject(error)
            })
        },
        handleCurrentChange(val){
            this.page.pageIndex = val
            this.loadTableData()
        },
        handleA(){
            this.dialog.title = '新增'
            this.dialog.dialogVisible = true
            this.dialog.f = 'A'
            this.dialog.id = ''
        },
        handleE(row){
            this.dialog.title = '修改'
            this.dialog.dialogVisible = true
            this.dialog.f = 'E'
            this.dialog.id = row.id
        },
        handleD(row){
            const data = {
                id: row.id
            }
            this.$confirm('确定删除该机构信息?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    remove(data).then((res)=>{
                        this.loadPage()
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    });
                }).catch(() => {});
        },
        onSubmit(){
            this.loadTableData()
        },
        loadPage(){
            this.loadTableData()
        },
        closeDialog(f){
            this.dialog.dialogVisible = false
            if(f==true){
                this.loadPage()
            }
        }
        
    },
    created(){
        this.loadPage()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>