<template>
    <div class="page-container">
        <el-row>
            <el-col>
                <div class="page-panel">
                    <el-row :gutter="20">
                        <el-col :span="6">
                            <el-input v-model="cxForm.customerName" placeholder="公司名称"></el-input>
                        </el-col>
                        <el-col :span="6">
                            <el-input v-model="cxForm.inboundNo" placeholder="进仓编号"></el-input>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20" style="margin-top: 10px;">
                        <el-col :span="6">
                            <el-input v-model="cxForm.containerNo" placeholder="箱号"></el-input>
                        </el-col>
                        <el-col :span="6">
                            <el-input v-model="cxForm.billofladingNo" placeholder="提单号"></el-input>
                        </el-col>
                        <el-col :span="6">
                            <el-button type="primary" @click="loadTableData">查询</el-button>
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
                    prop="customerName"
                    label="公司名称"
                    width="150">
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
                    <el-table-column
                    prop="driverName"
                    label="拉货司机"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="stevedoreID"
                    label="装卸工队"
                    width="120">
                    <template slot-scope="{row}">
                        {{ row.stevedoreID | filterType(gdlist) }}
                    </template>
                    </el-table-column>
                    <el-table-column
                    prop="date"
                    label="进仓日期"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="remark"
                    label="进仓备注">
                    </el-table-column>
                    <el-table-column
                    prop="boxStatue"
                    label="状态名"
                    width="100">
                    <template slot-scope="{row}">
                        {{ row.boxStatue | filterType(ztlist) }}
                    </template>
                    </el-table-column>
                    <el-table-column
                    prop="containerNo"
                    label="箱号"
                    width="100">
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
    </div>
</template>
<script>
import { ckxxcx } from '@/api/oltp/cx'
import { listDic } from '@/api/basic/lbxx'
export default {
    name:'CKXXCX',
    data(){
        return {
            lxlist:[],
            dqlist:[],
            gdlist:[],
            ztlist:[
                {
                    dicKey:"1",
                    dicVal:"未装箱"
                },
                {
                    dicKey:"2",
                    dicVal:"已装箱"
                }
            ],
            cxForm:{
                customerName:'',
                inboundNo:'',
                containerNo:'',
                billofladingNo:'',
            },
            tableData: [],
            page:{
                total: 0,
                pageSize: 20,
                pageIndex: 1
            },
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
        loadTableData(){
            let param = Object.assign({}, this.page)
            param = Object.assign(param, this.cxForm)
            ckxxcx(param).then(res=>{
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
        onSubmit(){
            this.loadTableData()
        },
        loadPage(){
            this.loadTableData()
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
        this.loadDqDic()
        this.loadGdDic()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>