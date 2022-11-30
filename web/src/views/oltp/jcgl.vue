<template>
<div class="page-container">
    <div class="page-panel">
        <el-form :model="formData" ref="form" :rules="rules" label-width="150px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="公司名称" prop="customerID">
                        <el-select v-model="formData.customerID" 
                        style="width: 100%;"
                        filterable placeholder="请选择">
                            <el-option
                            v-for="item in gslist"
                            :key="item.dicKey"
                            :label="item.dicVal"
                            :value="item.dicKey">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="进仓编号" prop="inboundNo">
                        <el-input v-model="formData.inboundNo" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="立方数（CBM）" prop="cbm">
                        <el-input v-model="formData.cbm" maxlength="10" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="件数（件）" prop="pks">
                        <el-input v-model.number="formData.pks" maxlength="10" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="装卸工队" prop="stevedoreID">
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
                    <el-form-item label="拉货司机" prop="driverID">
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
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="仓库" prop="boundID">
                        <el-select v-model="formData.boundID" 
                        style="width: 100%;"
                        filterable placeholder="请选择">
                            <el-option
                            v-for="item in cklist"
                            :key="item.dicKey"
                            :label="item.dicVal"
                            :value="item.dicKey">
                            </el-option>
                        </el-select>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="唛头" prop="shippingMark">
                        <el-input v-model="formData.shippingMark" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="进仓日期" prop="date">
                        <el-date-picker
                            v-model="formData.date"
                            type="date"
                            style="width: 100%;"
                            placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>  
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="备注" prop="remark">
                        <el-input type="textarea" :rows="5" v-model="formData.remark" autocomplete="off" :readonly="f==='V'"></el-input>
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
import { add } from '@/api/oltp/jcgl'
import { listDic } from '@/api/basic/lbxx'
import { sjBox } from '@/api/basic/sjxx'
import { gsBox } from '@/api/basic/gsxx'
export default {
    name:'JCGL',
    data(){
        return {
            f: 'A',
            formData:{
                inBoundIndex:'',
                customerID:'',
                inboundNo:'',
                cbm:'',
                pks:'',
                shippingMark:'',
                driverID:'',
                date:'',
                remark:'',
                stevedoreID:'',
                boundID:''
            },
            gslist:[],//公司
            cklist:[],//仓库
            stlist:[],//装卸工队
            sjlist:[],//司机
            rules:{
                customerID: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                ],
                inboundNo: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度不超过20位', trigger: 'blur' }
                ],
                cbm: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { pattern: /^\d+.?\d{0,2}$/, message: '数字类型，小数点后最两位', trigger: 'blur'}
                ],
                pks: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { type: 'number', message: '必须为数字'}

                ],
                driverID: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                ],
                stevedoreID: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                ],
                boundID: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                ],
                date: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                ],
            }
        }
    },
    methods:{
        submitForm(){
            this.$refs.form.validate((valid)=>{
                if(valid){
                    add(this.formData).then(res=>{
                            this.$notify({
                                title: '成功',
                                message: '进仓成功',
                                type: 'success',
                                duration: 2000
                            });
                            this.resetForm()
                        });
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
        loadGsDic(){
            gsBox({}).then(res=>{
                const { data } = res
                this.gslist = data
            })
        },
    },
    created(){
        this.loadStDic()
        this.loadCkDic()
        this.loadSjDic()
        this.loadGsDic()
    }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>