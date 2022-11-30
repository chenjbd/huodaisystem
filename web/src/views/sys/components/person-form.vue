<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
            <el-form :model="formData" ref="form" :rules="rules" label-width="100px">
                <el-form-item label="姓名" prop="customerName">
                    <el-input v-model="formData.customerName" autocomplete="off" :readonly="f==='V'"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-radio-group v-model="formData.gender">
                        <el-radio label="1">男</el-radio>
                        <el-radio label="2">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="所属机构" prop="deptName">
                    <el-input v-model="formData.deptName" autocomplete="off" readonly></el-input>
                </el-form-item>
                <el-form-item label="联系电话" prop="phoneNo">
                    <el-input v-model="formData.phoneNo" autocomplete="off" :readonly="f==='V'"></el-input>
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
import { add, get, update } from '@/api/person'
export default {
    name:'OrgForm',
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
                    orgNo: '',
                    orgName: ''
                }
            }
        }
    },
    data(){
        return {
            formData:{
                uid:'',
                customerName:'',
                gender:'1',
                deptNo:'',
                deptName:'',
                phoneNo:'',
                status:'1',
                seqno: 1
            },
            rules:{
                customerName: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 50, message: '长度不超过50位', trigger: 'blur' }
                ],
                phoneNo: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 13, message: '长度不超过13位', trigger: 'blur' }
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
                this.formData.uid = data.U_ID
                this.formData.customerName = data.CUSTOMER_NAME
                this.formData.gender = data.GENDER
                this.formData.phoneNo = data.PHONE_NO
                this.formData.deptNo = data.DEPT_NO
                this.formData.deptName = data.orgname
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
                this.formData.deptNo = newObj.orgNo
                this.formData.deptName = newObj.orgName
            },
            deep: true 
        }
    }
}
</script>