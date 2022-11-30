<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
        <el-form :model="formData" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="箱号" prop="containerNo">
                        <el-input v-model="formData.containerNo" maxLength="20" autocomplete="off"></el-input>
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
                        <el-input v-model="formData.sealNum" maxlength="20" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="提单号" prop="billofladingNo">
                        <el-input v-model="formData.billofladingNo" maxlength="20" autocomplete="off" :readonly="f==='V'"></el-input>
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
                            value-format="yyyy-MM-dd"
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
                        <el-input v-model="formData.cubicNum" maxlength="10" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="装箱费用" prop="cost">
                        <el-input v-model.number="formData.cost" maxlength="10" autocomplete="off" :readonly="f==='V'"></el-input>
                    </el-form-item>  
                </el-col>
                <el-col :span="12">
                    <el-form-item label="监管仓立方" prop="supervisedCbm">
                        <el-input v-model="formData.supervisedCbm" maxlength="10" autocomplete="off" :readonly="f==='V'"></el-input>
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
        </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="closeDialog(false)">取 消</el-button>
                <el-button type="primary" @click="submitForm()" v-if="f!=='V'">提 交</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import { add, get, update } from '@/api/oltp/xzgl'
import { listDic } from '@/api/basic/lbxx'
export default {
    name:'XzglForm',
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
            lxlist:[],
            dqlist:[],
            gdlist:[],
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
                notes:'',
                stevedoreNotes:'',
                supervisedCbm:''
            },
            rules:{
                containerNo: [
                    { required: true, message: '不能为空', trigger: 'blur' }
                ],
                boxTypeID: [
                    { required: true, message: '不能为空', trigger: 'blur' }
                ],
                sealNum: [
                    { required: true, message: '不能为空', trigger: 'blur' }
                ],
                billofladingNo:[
                    { required: true, message: '不能为空', trigger: 'blur' }
                ],
                areaID:[
                    { required: true, message: '不能为空', trigger: 'blur' }
                ],
                containerDate:[
                    { required: true, message: '不能为空', trigger: 'blur' }
                ],
                stevedoreID:[
                    { required: true, message: '不能为空', trigger: 'blur' }
                ],
                cubicNum:[
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { pattern: /^\d+.?\d{0,2}$/, message: '数字类型，小数点后最两位', trigger: 'blur'}
                ],
                cost:[
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { type:'number', message: '必须为数字'}
                ],
                supervisedCbm:[
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { pattern: /^\d+.?\d{0,2}$/, message: '数字类型，小数点后最两位', trigger: 'blur'}
                ]
            }
        }
    },
    methods:{
        closeDialog(f){
            this.$emit('closeDialog', f)
        },
        submitForm(){
            this.$refs.form.validate((valid)=>{
                if(valid){
                    if(this.f === 'A'){
                        add(this.formData).then(res=>{
                            this.$notify({
                                title: '成功',
                                message: '提交成功',
                                type: 'success',
                                duration: 2000
                            });
                            this.resetForm();
                            this.closeDialog(true)
                        });
                    }else if(this.f === 'E'){
                        update(this.formData).then(res=>{
                            this.$notify({
                                title: '成功',
                                message: '保存成功',
                                type: 'success',
                                duration: 2000
                            });
                            this.closeDialog(true)
                        });
                    }
                    
                }else{
                    return false
                }
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
                this.formData.boxTypeID = data.boxtypeid
                this.formData.billofladingNo = data.billofladingno
                this.formData.areaID = data.areaid
                this.formData.containerDate = data.containerdate
                this.formData.stevedoreID = data.stevedoreid
                this.formData.cost = data.cost
                this.formData.cubicNum = data.cubicnum
                this.formData.notes = data.notes
                this.formData.stevedoreNotes = data.stevedorenotes
                this.formData.supervisedCbm = data.supervisedcbm
            })
        },
        resetForm(){
            this.$refs.form.resetFields()
        },
        loadLxDic(){
            listDic({
                dicNo: '00'
            }).then(res=>{
                const { data } = res
                this.lxlist = data
            })
        },
        loadGdDic(){
            listDic({
                dicNo: '01'
            }).then(res=>{
                const { data } = res
                this.gdlist = data
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
    },
    watch:{
        id(newVal,oldVal){
            if(newVal && newVal.length>0){
                this.get(newVal)
            }else{
                this.resetForm()
            }
        },
        dialogData:{
            handler:function (newObj, oldObj){
                
            },
            deep: true 
        }
    },
    created(){
        this.loadLxDic()
        this.loadDqDic()
        this.loadGdDic()
    }
}
</script>