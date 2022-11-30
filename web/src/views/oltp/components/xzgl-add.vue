<template>
<div class="page-container">
    <div class="page-panel">
        <el-form :model="formData" ref="form" :rules="rules" label-width="150px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="箱号" prop="containerNo">
                        <el-input v-model="formData.containerNo" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="箱子类型" prop="boxTypeID">
                        <el-select v-model="formData.boxTypeID" 
                        style="width: 100%;"
                        filterable placeholder="请选择">
                            <el-option
                            v-for="item in lxlist"
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
                    <el-form-item label="封号" prop="sealNum">
                        <el-input v-model="formData.sealNum" maxlength="10" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="提单号" prop="billofladingNo">
                        <el-input v-model="formData.billofladingNo" maxlength="10" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="报关地区" prop="areaID">
                        <el-select v-model="formData.areaID" 
                        style="width: 100%;"
                        filterable placeholder="请选择">
                            <el-option
                            v-for="item in dqlist"
                            :key="item.dicKey"
                            :label="item.dicVal"
                            :value="item.dicKey">
                            </el-option>
                        </el-select>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="装箱日期" prop="containerDate">
                        <el-date-picker
                            v-model="formData.containerDate"
                            type="date"
                            style="width: 100%;"
                            placeholder="选择日期">
                        </el-date-picker>
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
                            v-for="item in gdlist"
                            :key="item.dicKey"
                            :label="item.dicVal"
                            :value="item.dicKey">
                            </el-option>
                        </el-select>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="装箱立方" prop="cubicNum">
                        <el-input v-model.number="formData.cubicNum" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="装箱费用" prop="cost">
                        <el-input v-model="formData.cost" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="监管仓立方" prop="supervisedCbm">
                        <el-input v-model="formData.supervisedCbm" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>  
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="装箱费用说明" prop="stevedoreNotes">
                        <el-input type="textarea" :rows="5" v-model="formData.stevedoreNotes" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>  
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="箱子说明" prop="notes">
                        <el-input type="textarea" :rows="5" v-model="formData.notes" autocomplete="off" :readonly="f==='V'"></el-input>
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
                id:'',
                sealNum:'',
                containerNo:'',
                boxTypeID:'',
                billofladingNo:'',
                areaID:'',
                containerDate:'',
                stevedoreID:'',
                cost:'',
                cubicNum:'',
                statue:'',
                notes:'',
                stevedoreNotes:'',
                supervisedCbm:''
            },
            xzlxlist:[],//箱子类型
            dqlist:[],//地区
            gdlist:[],//装卸工队
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
                            this.resetForm();
                            this.closeDialog(true)
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