<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
            <el-form :model="formData" ref="form" :rules="rules" label-width="150px">
                <el-form-item label="客户公司名称" prop="customerName">
                    <el-input v-model="formData.customerName" autocomplete="off" :readonly="f==='V'"></el-input>
                </el-form-item>
                <el-form-item label="公司拼音索引" prop="customerIndex">
                    <el-input v-model="formData.customerIndex" autocomplete="off" :readonly="f==='V'"></el-input>
                </el-form-item>
                <el-form-item label="说明" prop="note">
                    <el-input type="textarea" :row="3" v-model="formData.note" autocomplete="off" :readonly="f==='V'"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="closeDialog(false)">取 消</el-button>
                <el-button type="primary" @click="submitForm()" v-if="f!=='V'">提 交</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import { add, get, update } from '@/api/basic/gsxx'
export default {
    name:'GsxxForm',
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
            formData:{
                id:'',
                customerID:'',
                customerName:'',
                customerIndex:'',
                note:''
            },
            rules:{
                customerName: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 10, message: '长度不超过50位', trigger: 'blur' }
                ],
                customerIndex: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 50, message: '长度不超过10位', trigger: 'blur' }
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
                                message: '提交成功',
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
                this.formData.customerID = data.customerid
                this.formData.customerName = data.customername
                this.formData.customerIndex = data.customerindex
                this.formData.note = data.note
            })
        },
        resetForm(){
            this.$refs.form.resetFields()
        }
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
    }
}
</script>