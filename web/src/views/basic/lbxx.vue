<template>
    <div class="page-container">
        <el-row>
            <el-col>
                <div class="page-panel">
                    <el-row :gutter="20">
                        <el-col :span="6">
                            <el-select v-model="cxForm.bigTypeID" style="width:100%" placeholder="全部">
                                <el-option
                                v-for="item in lblist"
                                :key="item.value"
                                :value="item.value"
                                :label="item.name">
                                </el-option>
                            </el-select>
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
                    <el-table-column label="类别" width="300">
                        <template slot-scope="{row}">
                        <el-tag>
                            {{ row.bigTypeID | bigTypeFilter }}
                        </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                    prop="typeName"
                    label="类别名称">
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

        <LbxxForm :title="dialog.title" :dialog-visible="dialog.dialogVisible" 
        :id="dialog.id"
        :f="dialog.f"
        :dialogData="cxForm"
        @closeDialog="closeDialog"
        />

    </div>
</template>
<script>
import { loadData, remove } from '@/api/basic/lbxx'
import LbxxForm from './components/lbxx-form.vue'
export default {
    name:'GSXX',
    components:{ LbxxForm },
    data(){
        return {
            lblist:[
                {value:"", name:"全部"},
                {value:"00", name:"箱子类型"},
                {value:"01", name:"装卸工队"},
                {value:"02", name:"报关地区"},
                {value:"03", name:"仓库"},
            ],
            cxForm:{
                bigTypeID:'',
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
    filters:{
        bigTypeFilter(type){
            if(type=='00'){
                return '箱子类型'
            }else if(type=="01"){
                return '装卸工队'
            }else if(type=="02"){
                return '报关地区'
            }else if(type=="03"){
                return '仓库'
            }else{
                return type
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
        },
    },
    created(){
        this.loadPage()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>