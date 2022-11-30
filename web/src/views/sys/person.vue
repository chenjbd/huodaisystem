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
                            <el-table-column
                            prop="CUSTOMER_NAME"
                            label="姓名"
                            width="120">
                            </el-table-column>
                            <el-table-column
                            label="性别"
                            width="100">
                            <template slot-scope="scope">
                                <el-tag size="medium">{{ scope.row.GENDER | filter_xb }}</el-tag>
                            </template>
                            </el-table-column>
                            <el-table-column
                            prop="orgname"
                            label="所属机构">
                            </el-table-column>
                            <el-table-column
                            prop="PHONE_NO"
                            label="联系电话"
                            width="150">
                            </el-table-column>
                            <el-table-column
                            fixed="right"
                            label="操作"
                            width="120">
                            <template slot-scope="scope">
                                <el-button @click="handleV(scope.row)" type="text" size="small">查看</el-button>
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
            </el-col>
        </el-row>
        <PersonForm :title="dialog.title" :dialog-visible="dialog.dialogVisible" 
        :id="dialog.id"
        :f="dialog.f"
        :dialogData="cxForm"
        @closeDialog="closeDialog"
        />
    </div>
</template>
<script>
import { loadData, remove } from '@/api/person'
import { loadTree } from '@/api/org'
import PersonForm from './components/person-form'
export default {
    name:'Person',
    components:{ PersonForm },
    data(){
        return {
            cxForm:{
                name:'',
                orgNo:'',
                orgName:''
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
            this.$refs.tree.filter(val);
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
            return data.title.indexOf(value) != -1;
        },
        nodeClick(data, node, elem){
            this.cxForm.orgNo = data.id
            this.cxForm.orgName = data.title
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
        handleV(row){
            this.dialog.title = '查看'
            this.dialog.dialogVisible = true
            this.dialog.f = 'V'
            this.dialog.id = row.U_ID
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
            this.dialog.id = row.U_ID
        },
        handleD(row){
            const data = {
                id: row.U_ID
            }
            this.$confirm('确定删除该人员信息?', '提示', {
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
            this.loadTreeData()
            this.loadTableData()
        },
        closeDialog(f){
            this.dialog.dialogVisible = false
            if(f==true){
                this.loadPage()
            }
        },
        
    },
    created(){
        this.loadPage()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>