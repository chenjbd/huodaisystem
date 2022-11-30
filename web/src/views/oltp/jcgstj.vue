<template>
    <div class="page-container">
        <el-row>
            <el-col>
                <div class="page-panel">
                    <el-row :gutter="20">
                        <el-col :span="6">
                            <el-date-picker
                            v-model="cxForm.startDate"
                            type="date"
                            style="width: 100%;"
                            value-format="yyyy-MM-dd"
                            placeholder="进仓日期起">
                            </el-date-picker>
                        </el-col>
                        <el-col :span="6">
                            <el-date-picker
                            v-model="cxForm.endDate"
                            type="date"
                            style="width: 100%;"
                            value-format="yyyy-MM-dd"
                            placeholder="进仓日期止">
                            </el-date-picker>
                        </el-col>
                        <el-col :span="6">
                            <el-select v-model="cxForm.customerID" 
                            style="width: 100%;"
                            filterable placeholder="公司名称">
                                <el-option
                                v-for="item in gslist"
                                :key="item.dicKey"
                                :label="item.dicVal"
                                :value="item.dicKey">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="6">
                            <el-button type="primary" @click="loadTableData">查询</el-button>
                            <el-button type="primary" @click="excel">Excel</el-button>
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
                    v-if="tableData.length>0"
                    border
                    stripe
                    highlight-current-row
                    show-summary
                    :summary-method="getSummaries"
                    >
                    <el-table-column
                    type="index"
                    label="序号"
                    width="50">
                    </el-table-column>
                    <el-table-column
                    prop="customerName"
                    label="公司名称">
                    </el-table-column>
                    <el-table-column
                    prop="inboundNo"
                    label="进仓编号"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    prop="pks"
                    label="件数"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    prop="cbm"
                    label="立方数"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    prop="date"
                    label="进仓日期"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="remark"
                    label="备注">
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
import { jcgstj } from '@/api/oltp/cx'
import { gsBox2 } from '@/api/basic/gsxx'
export default {
    name:'JCGSTJ',
    data(){
        return {
            cxForm:{
                startDate:'',
                endDate:'',
                customerID:''
            },
            gslist:[],
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
        excel(){
            this.$message({
                type:'warning',
                message:'请提供导出excel模板'
            })
        },
        loadTableData(){
            let param = Object.assign({}, this.page)
            param = Object.assign(param, this.cxForm)
            jcgstj(param).then(res=>{
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
        getSummaries(param) {
            const { columns, data } = param
            const sums = []  
            columns.forEach((column, index) => {
                if (index === 0) {//给哪一列加‘合计’
                    sums[index] = '合计'
                    return
                }
                const tempArr = ['pks', 'cbm']//给需要的列加求和计算
                if (tempArr.includes(column.property)) {
                    const values = data.map(item => Number(item[column.property]))
                    if (!values.every(value => isNaN(value))) {
                        sums[index] = values.reduce((prev, curr) => {
                                const value = Number(curr)
                                if (!isNaN(value)) {
                                    return prev + curr
                                } else {
                                    return prev
                                }
                            }, 0)
                    }
                } else {
                    sums[index] = '-'
                }
            })
            return sums
        },
        loadPage(){
            this.loadTableData()
        },
        loadGsDic(){
            gsBox2({
            }).then(res=>{
                const { data } = res
                let arr = []
                arr.push({
                    dicKey:'',
                    dicVal:'公司-全部'
                })
                arr = arr.concat(data)
                this.gslist = arr
            })
        }
    },
    created(){
        this.loadPage()
        this.loadGsDic()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>