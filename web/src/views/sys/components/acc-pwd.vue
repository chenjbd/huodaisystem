<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
            <el-form :model="formData" ref="form" :rules="rules" label-width="100px">
                <el-form-item label="所属单位" prop="unitName">
                    <el-input v-model="formData.unitName" autocomplete="off" readonly></el-input>
                </el-form-item>
                <el-form-item label="用户姓名" prop="name">
                    <el-input v-model="formData.name" maxlength="10" autocomplete="off" :readonly="f==='E'"></el-input>
                </el-form-item>
                <el-form-item label="登录账号" prop="accName">
                    <el-input v-model="formData.accName" maxlength="10" autocomplete="off" :readonly="f==='E'"></el-input>
                </el-form-item>
                <el-form-item label="登录密码" prop="accPwd">
                    <el-input v-model="formData.accPwd" maxlength="10" autocomplete="off" show-password></el-input>
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
import { get, add, update } from '@/api/acc'
import md5 from 'js-md5';
export default {
    name:'AccPwd',
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
                    unitNo: '',
                    unitName: ''
                }
            }
        }
    },
    data(){
        return {
            formData:{
                accId: '',
                accName:'',
                name:'',
                accPwd:'',
                phone:'',
                unitNo:'',
                unitName:''
            },
            rules:{
                name: [
                    { required: true, message: '不能为空', trigger: 'blur' }
                ],
                accName: [
                    { required: true, message: '不能为空', trigger: 'blur' }
                ],
                accPwd: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 6, max: 8, message: '长度6-8位', trigger: 'blur' }
                ],
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
                    this.formData.accPwd = md5(this.formData.accPwd)
                    if(this.f === 'A'){
                        add(this.formData).then(res=>{
                            this.$notify({
                                title: '成功',
                                message: '创建用户成功',
                                type: 'success',
                                duration: 2000
                            });
                            this.closeDialog(true)
                        });
                    }else if(this.f === 'E'){
                        update(this.formData).then(res=>{
                            this.$notify({
                                title: '成功',
                                message: '密码修改成功',
                                type: 'success',
                                duration: 2000
                            });
                            this.closeDialog(false)
                        });
                    }else{
                        return false
                    }
                }
            });
        },
        get(id){
            const param = {
                id: id
            }
            get(param).then(res=>{
                const { data } = res
                this.formData.accId = data.accId
                this.formData.accName = data.accName
                this.formData.name = data.name
                this.formData.phone = data.phone
                this.formData.unitNo = data.unitNo
                this.formData.unitName = data.unitName
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
                this.formData.unitNo = newObj.unitNo
                this.formData.unitName = newObj.unitName
            },
            deep: true 
        }
    }
}
</script>