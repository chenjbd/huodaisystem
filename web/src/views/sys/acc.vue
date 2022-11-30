<template>
    <div class="page-container">
        <el-row :gutter="20">
            <el-col :span="6">
                <div class="page-panel" style="height:calc(100vh - 110px)">
                    <el-input
                    placeholder="输入关键字进行过滤"
                    v-model="filterOrgName">
                    </el-input>
                    <el-tree
                    class="filter-tree"
                    :data="treeData"
                    :props="defaultProps"
                    default-expand-all
                    :filter-node-method="filterNode"
                    @node-click="nodeClick"
                    ref="tree">
                    </el-tree>
                </div>
            </el-col>
            <el-col :span="18" >
                <el-row>
                    <el-col>
                        <div class="page-panel">
                            <el-row :gutter="20">
                                <el-col :span="6">
                                    <el-input v-model="cxForm.name" placeholder="人员姓名"></el-input>
                                </el-col>
                                <el-col :span="6">
                                    <el-button type="primary" @click="onSubmit">查询</el-button>
                                    <el-button type="primary" @click="handleNew">新建用户</el-button>
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
                            highlight-current-row
                            stripe>
                            <el-table-column
                            type="index"
                            label="序号"
                            width="50">
                            </el-table-column>
                            <el-table-column
                            prop="acc_name"
                            label="账号"
                            width="200">
                            </el-table-column>
                            <el-table-column
                            prop="name"
                            label="姓名">
                            </el-table-column>
                            <el-table-column
                            prop="phone"
                            label="联系电话"
                            width="150">
                            </el-table-column>
                            <el-table-column
                            fixed="right"
                            label="操作"
                            width="150">
                            <template slot-scope="scope">
                                <el-button @click="handleRole(scope.row)" type="text" size="small">设置角色</el-button>
                                <el-button @click="handlePwd(scope.row)" type="text" size="small">重设密码</el-button>
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
            </el-col>
        </el-row>
        <AccPwd :title="dialog.title" :dialog-visible="dialog.dialogVisible" 
        :id="dialog.id"
        :f="dialog.f"
        :dialogData="cxForm"
        @closeDialog="closeDialog"
        />

        <AccRole :title="dialogRole.title" :dialog-visible="dialogRole.dialogVisible" 
        :id="dialogRole.id"
        :f="dialogRole.f"
        @closeDialog="closeDialogRole"
        />
    </div>
</template>
<script>
import { loadData } from '@/api/acc'
import { loadTree } from '@/api/org'
import AccPwd from './components/acc-pwd'
import AccRole from './components/acc-role'
export default {
    name:'Acc',
    components:{ AccPwd, AccRole },
    data(){
        return {
            cxForm:{
                name:'',
                unitNo:'',
                unitName:''
            },
            filterOrgName: '',
            treeData: [],
            defaultProps:{
                children: 'children',
                label: 'title'
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
            dialogRole:{
                title: '设置角色',
                dialogVisible: false,
                f: '',
                id: '',
            }
        }
    },
    filters:{
        filter_xb(val){
            if(val=='1'){
                return '男'
            }else{
                return '女'
            }
        }
    },
    watch:{
        filterOrgName(val){
            this.$refs.tree.filter(val)
        }
    },
    methods:{
        loadTreeData(){
            loadTree({}).then(res=>{
                const data = res.data
                this.treeData = data
            }).catch(error => {
                reject(error)
            })
        },
        filterNode(value, data){
            if(!value){
                return true;
            }
            return data.title.indexOf(value) != -1
        },
        nodeClick(data, node, elem){
            this.cxForm.unitNo = data.id
            this.cxForm.unitName = data.title
            this.loadTableData()
        },
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
        handleNew(){
            this.dialog.title = '创建用户'
            this.dialog.dialogVisible = true
            this.dialog.f = 'A'
            this.dialog.id = ''
        },
        handlePwd(row){
            this.dialog.title = '重设密码'
            this.dialog.dialogVisible = true
            this.dialog.f = 'E'
            this.dialog.id = row.acc_id
        },
        handleRole(row){
            this.dialogRole.dialogVisible = true
            this.dialogRole.f = 'R'
            this.dialogRole.id = row.acc_id
        },
        onSubmit(){
            this.loadTableData()
        },
        loadPage(){
            this.loadTreeData()
            this.loadTableData()
        },
        closeDialog(f){
            this.dialog.dialogVisible = false
            this.dialog.id = ''
            if(f==true){
                this.loadPage()
            }
        },
        closeDialogRole(f){
            this.dialogRole.dialogVisible = false
        }
    },
    created(){
        this.loadPage()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>