<template>
    <div class="page-container">
        <el-row>
            <el-col>
                <div class="page-panel">
                    <el-row :gutter="20">
                        <el-col :span="6">
                            <el-input v-model="cxForm.containerNo" placeholder="箱号"></el-input>
                        </el-col>
                        <el-col :span="6">
                            <el-input v-model="cxForm.billofladingNo" placeholder="提单号"></el-input>
                        </el-col>
                        <el-col :span="6">
                            <el-date-picker
                            v-model="cxForm.containerDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            style="width: 100%;"
                            placeholder="装箱日期">
                            </el-date-picker>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20" style="margin-top: 10px;">
                        <el-col :span="6">
                            <el-select v-model="cxForm.boxTypeID" 
                            style="width: 100%;"
                            filterable placeholder="箱子类型">
                                <el-option
                                v-for="item in lxlist"
                                :key="item.dicKey"
                                :label="item.dicVal"
                                :value="item.dicKey">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="6">
                            <el-checkbox v-model="cxForm.statue3" border @change="zt">显示已出</el-checkbox>
                        </el-col>
                        <el-col :span="6">
                            <el-button type="primary" @click="loadTableData">查询</el-button>
                            <el-button type="primary" @click="handleA">新建箱子</el-button>
                        </el-col>
                    </el-row>
                </div>
            </el-col>
        </el-row>
        <el-row style="margin-top: 15px;">
            <el-col>
                <div class="page-panel">
                    <el-table
                    height="calc(100vh - 315px)"
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
                    prop="containerNo"
                    label="箱号"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    prop="boxTypeID"
                    label="箱子类型"
                    width="100">
                    <template slot-scope="{row}">
                        {{ row.boxTypeID | filterType(lxlist) }}
                    </template>
                    </el-table-column>
                    <el-table-column
                    prop="sealNum"
                    label="封号"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="billofladingNo"
                    label="提单号"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    label="报关地区"
                    width="100">
                    <template slot-scope="{row}">
                        {{ row.areaID | filterType(dqlist) }}
                    </template>
                    </el-table-column>
                    <el-table-column
                    prop="containerDate"
                    label="装箱日期"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    label="装卸工队"
                    width="100">
                    <template slot-scope="{row}">
                        {{ row.stevedoreID | filterType(gdlist) }}
                    </template>
                    </el-table-column>
                    <el-table-column
                    prop="cost"
                    label="装箱费用"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="cubicNum"
                    label="装箱里立方"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="supervisedCbm"
                    label="监管仓立方"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="statue"
                    label="箱子状态"
                    width="100">
                    <template slot-scope="{row}">
                        <el-tag>
                            {{ row.statue | filterType(ztlist) }}
                        </el-tag>
                    </template>
                    </el-table-column>
                    <el-table-column
                    prop="stevedoreNotes"
                    label="工队说明">
                    </el-table-column>
                    <el-table-column
                    prop="notes"
                    label="箱子说明">
                    </el-table-column>
                    <el-table-column
                    fixed="right"
                    label="操作"
                    width="250">
                    <template slot-scope="scope">
                        <el-button @click="handleE(scope.row)" type="text" size="small">编辑</el-button>
                        <el-button @click="handleD(scope.row)" type="text" size="small">删除</el-button>
                        <el-button @click="handleS(scope.row)" type="text" size="small">选货</el-button>
                        <el-button @click="handleV(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="handleR(scope.row)" type="text" size="small">报表</el-button>
                        <el-button @click="handleT(scope.row)" type="text" size="small">运走</el-button>
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

        <XzglForm :title="dialog.title" :dialog-visible="dialog.dialogVisible" 
        :id="dialog.id"
        :f="dialog.f"
        :dialogData="cxForm"
        @closeDialog="closeDialog"
        />

        <XzglXH :title="dialogXH.title" :dialog-visible="dialogXH.dialogVisible" 
        :id="dialogXH.id"
        :f="dialogXH.f"
        :dialogData="cxForm"
        @closeDialog="closeDialogXH"
        />

        <XzglView :title="dialogV.title" :dialog-visible="dialogV.dialogVisible" 
        :id="dialogV.id"
        :f="dialogV.f"
        :dialogData="cxForm"
        @closeDialog="closeDialogV"
        />

    </div>
</template>
<script>
import { loadData, remove, take } from '@/api/oltp/xzgl'
import { listDic } from '@/api/basic/lbxx'
import { zxqdExcel } from '@/api/oltp/cx'
import XzglForm from './components/xzgl-form.vue'
import XzglXH from './components/xzgl-xh.vue'
import XzglView from './components/xzgl-view.vue'
export default {
    name:'XZGL',
    components:{ XzglForm, XzglXH, XzglView },
    data(){
        return {
            lxlist:[],
            dqlist:[],
            gdlist:[],
            ztlist:[
                {
                    dicKey:"001",
                    dicVal:"空箱"
                },
                {
                    dicKey:"002",
                    dicVal:"已装货"
                },
                {
                    dicKey:"003",
                    dicVal:"已出"
                }
            ],
            cxForm:{
                containerNo:'',
                boxTypeID:'',
                billofladingNo:'',
                containerDate:'',
                statue: '',
                statue3: false
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
            dialogXH:{
                title: '装箱选货',//弹窗标题
                dialogVisible: false,//是否显示弹窗
                f: '',//弹窗类型
                id: '',//传给子组件的id
            },
            dialogV:{
                title: '装箱货物',//弹窗标题
                dialogVisible: false,//是否显示弹窗
                f: '',//弹窗类型
                id: '',//传给子组件的id
            }
        }
    },
    filters:{
        filterType(key, list){
            let val = key
            list.forEach((item,index)=>{
                if(item.dicKey == key){
                    val = item.dicVal
                }
            })
            return val
        }
    },
    methods:{
        zt(){
            if(this.cxForm.statue3){
                this.cxForm.statue = "003"
            }else{
                this.cxForm.statue = ""
            }
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
        handleA(){
            this.dialog.title = '新建箱子'
            this.dialog.dialogVisible = true
            this.dialog.f = 'A'
            this.dialog.id = ''
        },
        handleE(row){
            this.dialog.title = '编辑箱子'
            this.dialog.dialogVisible = true
            this.dialog.f = 'E'
            this.dialog.id = row.id
        },
        handleD(row){
            const data = {
                id: row.id
            }
            if(row.statue!='001'){
                this.$message({
                    type: 'warning',
                    message: '只有空箱状态的箱子能删除!'
                });
                return false
            }
            this.$confirm('确定删除箱子吗?', '提示', {
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
        handleS(row){
            if(row.statue=='003'){
                this.$message({
                    type: 'warning',
                    message: '箱子已被运走，不能装货!'
                });
                return false
            }
            this.dialogXH.title = '装箱选货'
            this.dialogXH.dialogVisible = true
            this.dialogXH.f = 'S'
            this.dialogXH.id = row.id
        },
        handleV(row){
            this.dialogV.title = '装箱货物'
            this.dialogV.dialogVisible = true
            this.dialogV.f = 'V'
            this.dialogV.id = row.id
        },
        handleR(row){
            const data = {
                boxId: row.id
            }
            let fileName = '装箱清单('+ row.billofladingNo +').xls'
            zxqdExcel(data, fileName)
        },
        handleT(row){
            const data = {
                id: row.id
            }
            if(row.statue=='001'){
                this.$message({
                    type: 'warning',
                    message: '箱子未装货，不能运走!'
                });
                return false
            }
            if(row.statue=='003'){
                this.$message({
                    type: 'warning',
                    message: '箱子已被运走!'
                });
                return false
            }
            this.$confirm('确定箱子运走吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    take(data).then((res)=>{
                        this.loadPage()
                        this.$message({
                            type: 'success',
                            message: '提交成功!'
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
        closeDialogXH(f){
            this.dialogXH.dialogVisible = false
            this.dialogXH.id=""
            if(f==true){
                this.loadPage()
            }
        },
        closeDialogV(f){
            this.dialogV.dialogVisible = false
        }
        ,loadLxDic(){
            listDic({
                dicNo: '00'
            }).then(res=>{
                const { data } = res
                this.lxlist = data
            })
        },
        loadDqDic(){
            listDic({
                dicNo: '02'
            }).then(res=>{
                const { data } = res
                this.dqlist = data
            })
        },
        loadGdDic(){
            listDic({
                dicNo: '01'
            }).then(res=>{
                const { data } = res
                this.gdlist = data
            })
        }
    },
    created(){
        this.loadPage()
        this.loadLxDic()
        this.loadDqDic()
        this.loadGdDic()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>