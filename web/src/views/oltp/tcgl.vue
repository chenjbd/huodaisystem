<template>
<div class="page-container">
    <div class="page-panel">
        <el-form :model="inData" ref="inForm" :rules="inRules" label-width="150px">
            <el-row>
                <el-col :span="3">
                    <el-tag>进仓信息</el-tag>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="进仓编号" prop="inboundNo">
                        <el-input v-model="inData.inboundNo" 
                        @blur="cxInData"
                        autocomplete="off" placeholder="输入进仓编号查询"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="公司名称" prop="customerName">
                        <el-input v-model="inData.customerName" autocomplete="off" readonly></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="进仓日期" prop="date">
                        <el-input v-model.number="inData.date" maxlength="10" autocomplete="off" readonly></el-input>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="仓库" prop="boundName">
                        <el-input v-model.number="inData.boundName" autocomplete="off" readonly></el-input>
                    </el-form-item>  
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="立方数（CBM）" prop="cbm">
                        <el-input v-model="inData.cbm" maxlength="10" autocomplete="off" readonly></el-input>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="件数（件）" prop="pks">
                        <el-input v-model.number="inData.pks" maxlength="10" autocomplete="off" readonly></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
    </div>
    <div class="page-panel" style="margin-top: 10px;">
        <el-form :model="formData" ref="form" :rules="rules" label-width="150px">
            <el-row>
                <el-col :span="3">
                    <el-tag type="warning">退仓登记</el-tag>
                </el-col>
            </el-row>
            
            <el-row>
                <el-col :span="12">
                    <el-form-item label="退仓立方数" prop="cbm">
                        <el-input v-model="formData.cbm" maxlength="10" autocomplete="off"></el-input>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="退仓件数" prop="pks">
                        <el-input v-model.number="formData.pks" maxlength="10" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="退仓装卸工队" prop="stevedoreID">
                        <el-select v-model="formData.stevedoreID" 
                        style="width: 100%;"
                        filterable placeholder="请选择">
                            <el-option
                            v-for="item in stlist"
                            :key="item.dicKey"
                            :label="item.dicVal"
                            :value="item.dicKey">
                            </el-option>
                        </el-select>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="退仓工队费用" prop="stevedoreMoney">
                        <el-input v-model.number="formData.stevedoreMoney" maxlength="10" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="退仓拉货司机" prop="driverID">
                        <el-select v-model="formData.driverID" 
                        style="width: 100%;"
                        filterable placeholder="请选择">
                            <el-option
                            v-for="item in sjlist"
                            :key="item.dicKey"
                            :label="item.dicVal"
                            :value="item.dicKey">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="退仓司机费用" prop="driverMoney">
                        <el-input v-model="formData.driverMoney" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="退仓日期" prop="outDate">
                        <el-date-picker
                            v-model="formData.outDate"
                            type="date"
                            style="width: 100%;"
                            placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="备注" prop="note">
                        <el-input type="textarea" :rows="3" v-model="formData.note" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>  
                </el-col>
            </el-row>
            <div align="center">
                <el-button type="primary" @click="submitForm()" v-if="f!=='V'">提 交</el-button>
            </div>
        </el-form>
    </div>
</div>
</template>
<script>
import { getInfo } from '@/api/oltp/jcgl'
import { add } from '@/api/oltp/tcgl'
import { listDic } from '@/api/basic/lbxx'
import { sjBox } from '@/api/basic/sjxx'
export default {
    name:'TCGL',
    data(){
        //自定义校验器
        var validatePks = (rule, value, callback)=>{
                if (value === '' || value === undefined || value === null) {
                    callback()//为空不处理，交给其他校验器处理
                }else{
                    let inPks = this.inData.pks
                    if (inPks === '' || inPks === undefined || inPks === null) {
                        callback(new Error('退仓件数不能大于进仓件数'))
                    }else if(parseInt(value) > parseInt(inPks)){
                        callback(new Error('退仓件数不能大于进仓件数'))
                    }else{
                        callback() 
                    }
                }
            };
            var validateCbm = (rule, value, callback)=>{
                if (value === '' || value === undefined || value === null) {
                    callback()//为空不处理，交给其他校验器处理
                }else{
                    let inCbm = this.inData.cbm
                    if (inCbm === '' || inCbm === undefined || inCbm === null) {
                        callback(new Error('退仓立方数不能大于进仓立方数'))
                    }else if(parseFloat(value) > parseFloat(inCbm)){
                        callback(new Error('退仓立方数不能大于进仓立方数'))
                    }else{
                        callback() 
                    }
                }
            };
        return {
            f: 'A',
            inData:{
                id:'',
                inBoundIndex:'',
                customerName:'',
                inboundNo:'',
                cbm:'',
                pks:'',
                shippingMark:'',
                driverName:'',
                date:'',
                remark:'',
                stevedoreName:'',
                boundName:''
            },
            formData:{
                inBoundIndex:'',
                cbm:'',
                pks:'',
                driverID:'',
                driverMoney:'',
                outDate:'',
                note:'',
                stevedoreID:'',
                stevedoreMoney:''
            },
            gslist:[],//公司
            cklist:[],//仓库
            stlist:[],//装卸工队
            sjlist:[],//司机
            inRules:{
                inboundNo: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度不超过20位', trigger: 'blur' }
                ]
            },
            rules:{
                cbm: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { pattern: /^\d+.?\d{0,2}$/, message: '数字类型，小数点后最两位', trigger: 'blur'},
                    { validator: validateCbm, message:'退仓立方数不能大于进立方数', trigger: 'blur'}
                ],
                pks: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { type: 'number', message: '必须为数字'},
                    { validator: validatePks, message:'退仓件数不能大于进仓件数', trigger: 'blur'}
                ],
                driverID: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                ],
                driverMoney: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { pattern: /^\d+.?\d{0,2}$/, message: '数字类型，小数点后最两位', trigger: 'blur'}
                ],
                stevedoreID: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                ],
                stevedoreMoney: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { pattern: /^\d+.?\d{0,2}$/, message: '数字类型，小数点后最两位', trigger: 'blur'}
                ],
                outDate: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                ],
            }
        }
    },
    methods:{
        cxInData(){
            getInfo({inboundNo:this.inData.inboundNo}).then(res=>{
                const { data } = res
                if(!data){
                    this.$message({
                        type:'warning',
                        message: '进仓信息不存在，请检查进仓编号是否正确'
                    })
                    this.$refs.inForm.resetFields()
                    return false
                }
                this.inData.customerName = data.customerName
                this.inData.date = data.date
                this.inData.pks = data.pks
                this.inData.cbm = data.cbm
                this.inData.boundName = data.boundName
                this.formData.inBoundIndex = data.id
            })
        },
        submitForm(){
            this.$refs.form.validate((valid)=>{
                if(valid){
                    this.$confirm('确定进行退仓处理吗?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        add(this.formData).then(res=>{
                            this.$notify({
                                title: '成功',
                                message: '退仓成功',
                                type: 'success',
                                duration: 2000
                            });
                            this.resetForm()
                        });   
                    }).catch(() => {});
                }else{
                    return false
                }
            })
        },
        resetForm(){
            this.$refs.form.resetFields()
        },
        loadStDic(){
            listDic({
                dicNo: '01'
            }).then(res=>{
                const { data } = res
                this.stlist = data
            })
        },
        loadCkDic(){
            listDic({
                dicNo: '03'
            }).then(res=>{
                const { data } = res
                this.cklist = data
            })
        },
        loadSjDic(){
            sjBox({}).then(res=>{
                const { data } = res
                this.sjlist = data
            })
        },
    },
    created(){
        this.loadStDic()
        this.loadCkDic()
        this.loadSjDic()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>