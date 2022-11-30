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
                            value-format="yyyy-MM-dd"
                            style="width: 100%;"
                            placeholder="装箱日期起">
                            </el-date-picker>
                        </el-col>
                        <el-col :span="6">
                            <el-date-picker
                            v-model="cxForm.endDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            style="width: 100%;"
                            placeholder="装箱日期止">
                            </el-date-picker>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20" style="margin-top: 10px;">
                        <el-col :span="6">
                            <el-select 
                            ref="stevedoreIDRef"
                            v-model="cxForm.stevedoreID" 
                            style="width: 100%;"
                            filterable placeholder="装卸工队">
                                <el-option
                                v-for="item in gdlist"
                                :key="item.dicKey"
                                :label="item.dicVal"
                                :value="item.dicKey">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="6">
                            <el-select v-model="cxForm.areaID" 
                            style="width: 100%;"
                            filterable placeholder="报关地区">
                                <el-option
                                v-for="item in dqlist"
                                :key="item.dicKey"
                                :label="item.dicVal"
                                :value="item.dicKey">
                                </el-option>
                            </el-select>
                        </el-col>
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
                    height="calc(100vh - 315px)"
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
                    prop="stevedoreID"
                    label="工队">
                    <template slot-scope="{row}">
                        {{ row.stevedoreID | filterType(gdlist) }}
                    </template>
                    </el-table-column>
                    <el-table-column
                    prop="containerDate"
                    label="装箱日期"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="boxTypeID"
                    label="箱子类型">
                    <template slot-scope="{row}">
                        {{ row.boxTypeID | filterType(lxlist) }}
                    </template>
                    </el-table-column>
                    <el-table-column
                    prop="billofladingNo"
                    label="提单号"
                    width="100">
                    </el-table-column>
                    <el-table-column
                    prop="containerNo"
                    label="箱号"
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
                    prop="cost"
                    label="装箱费用"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    prop="stevedoreNotes"
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
import { zxgzhtj, zxgzhtjExcel } from '@/api/oltp/cx'
import { listDic } from '@/api/basic/lbxx'
export default {
    name:'ZXGZHTJ',
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
                startDate:'',
                endDate:'',
                stevedoreID:'',
                areaID:'',
                boxTypeID:''
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
        excel(){
            let data = this.cxForm
            let stevedoreName = ''
            if(this.cxForm.stevedoreID == ''){
                stevedoreName = '全部'
            }else{
                stevedoreName = this.$refs.stevedoreIDRef.selected.label
            }
            data.stevedoreName = stevedoreName
            zxgzhtjExcel(data, "装卸工队费用一览表.xls")
        },
        loadTableData(){
            let param = Object.assign({}, this.page)
            param = Object.assign(param, this.cxForm)
            zxgzhtj(param).then(res=>{
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
                const tempArr = ['cubicNum', 'cost', 'supervisedCbm']//给需要的列加求和计算
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
        loadDqDic(){
            listDic({
                dicNo: '02'
            }).then(res=>{
                const { data } = res
                let arr = []
                arr.push({
                    dicKey:'',
                    dicVal:'报关地区-全部'
                })
                arr = arr.concat(data)
                this.dqlist = arr
            })
        },
        loadGdDic(){
            listDic({
                dicNo: '01'
            }).then(res=>{
                const { data } = res
                let arr = []
                arr.push({
                    dicKey:'',
                    dicVal:'装卸工队-全部'
                })
                arr = arr.concat(data)
                this.gdlist = arr
            })
        },
        loadLxDic(){
            listDic({
                dicNo: '00'
            }).then(res=>{
                const { data } = res
                let arr = []
                arr.push({
                    dicKey:'',
                    dicVal:'箱子类型-全部'
                })
                arr = arr.concat(data)
                this.lxlist = arr
            })
        }
    },
    created(){
        this.loadPage()
        this.loadDqDic()
        this.loadGdDic()
        this.loadLxDic()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>