<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
        <el-row :gutter="20">
            <el-col :span="6">
                <el-input v-model="cxForm.inboundNo" placeholder="进仓编号"></el-input>
            </el-col>
            <el-col :span="6">
                <el-button type="primary" @click="loadTableData">查询</el-button>
            </el-col>
        </el-row>
        <el-row style="margin-top: 15px;">
            <el-col :span="8">
                箱号：<el-tag>{{ formData.containerNo }}</el-tag>
            </el-col>
            <el-col :span="8">
                封号：<el-tag>{{ formData.sealNum }}</el-tag>
            </el-col>
            <el-col :span="8">
                提单号：<el-tag>{{ formData.billofladingNo }}</el-tag>
            </el-col>
        </el-row>
        <el-row style="margin-top: 15px;">
            <el-col>
                <el-table
                    ref="table"
                    height="calc(100vh - 430px)"
                    :data="tableData"
                    border
                    stripe
                    highlight-current-row
                    >
                    <el-table-column
                    type="selection"
                    label="选择"
                    width="50">
                    </el-table-column>
                    <el-table-column
                    prop="customerName"
                    label="公司名称"
                    width="250">
                    </el-table-column>
                    <el-table-column
                    prop="inboundNo"
                    label="进仓编号"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    prop="cbm"
                    label="立方数"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="pks"
                    label="件数"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="shippingMark"
                    label="唛头">
                    </el-table-column>
                    </el-table>
                    <el-pagination
                        @current-change="handleCurrentChange"
                        :current-page.sync="page.pageIndex"
                        :page-size="page.pageSize"
                        layout="total, prev, pager, next"
                        :total="page.total">
                    </el-pagination>
            </el-col>
        </el-row>  

            <div slot="footer" class="dialog-footer">
                <el-button @click="closeDialog(false)">取 消</el-button>
                <el-button type="primary" @click="submitForm()" v-if="f!=='V'">装 箱</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import { loadData } from '@/api/oltp/jcgl'
import { get, zx } from '@/api/oltp/xzgl'
export default {
    name:'XzglXH',
    props:{
        dialogVisible:{
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            default: '表单'
        },
        id: {
            type: String,
            default: ''
        },
        f: {
            type: String,
            default: ''
        },
        dialogData:{
            type: Object,
            default(){
                return {
                    
                }
            }
        }
    },
    data(){
        return {
            cxForm:{
                boxStatue:'1',//未装箱
                inboundNo:''
            },
            formData:{
                id:'',
                sealNum:'',
                containerNo:'',
                boxTypeID:'',
                billofladingNo:''
            },
            tableData: [],
            page:{
                total: 0,
                pageSize: 10,
                pageIndex: 1
            },
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
            })
        },
        handleCurrentChange(val){
            this.page.pageIndex = val
            this.loadTableData()
        },
        closeDialog(f){
            this.$emit('closeDialog', f)
        },
        submitForm(){
            let arr = this.$refs.table.selection;//选中的行数据
            let inIds = []
            arr.forEach((item,index)=>{
                inIds.push(item.id)
            })
            if(inIds.length == 0){
                this.$message({
                            type: 'warning',
                            message: '请选择装箱货物!'
                        });
                return
            }
            const data = {
                inIds: inIds.toString(),
                id: this.formData.id
            }
            zx(data).then(res=>{
                this.$notify({
                    title: '成功',
                    message: '装箱成功',
                    type: 'success',
                    duration: 2000
                });
                this.resetForm();
                this.closeDialog(true)
            })
        },
        get(id){
            const param = {
                id: id
            }
            get(param).then(res=>{
                const { data } = res
                this.formData.id = data.id
                this.formData.sealNum = data.sealnum
                this.formData.containerNo = data.containerno
                this.formData.billofladingNo = data.billofladingno
            })
        },
        resetForm(){
            
        },
    },
    watch:{
        id(newVal,oldVal){
            if(newVal && newVal.length>0){
                this.get(newVal)
                this.loadTableData()
            }else{
                this.resetForm()
            }
        },
        dialogData:{
            handler:function (newObj, oldObj){
                
            },
            deep: true 
        }
    }
}
</script>