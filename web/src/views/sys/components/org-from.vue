<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
            <el-form :model="formData" ref="form" :rules="rules" label-width="100px">
                <el-form-item label="机构号" prop="orgno">
                    <el-input v-model="formData.orgno" autocomplete="off" :readonly="f==='V'"></el-input>
                </el-form-item>
                <el-form-item label="机构名称" prop="orgname">
                    <el-input v-model="formData.orgname" autocomplete="off" :readonly="f==='V'"></el-input>
                </el-form-item>
                <el-form-item label="上级机构" prop="superOrgName">
                    <el-input v-model="formData.superOrgName" autocomplete="off" readonly></el-input>
                </el-form-item>
                <el-form-item label="联系人" prop="contactName">
                    <el-input v-model="formData.contactName" autocomplete="off" :readonly="f==='V'"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" prop="contactPhone" >
                    <el-input v-model="formData.contactPhone" autocomplete="off" :readonly="f==='V'"></el-input>
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
import { add, get, update } from '@/api/org'
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
                    superorg: '0',
                    superorgname: ''
                }
            }
        }
    },
    data(){
        return {
            formData:{
                orgid:'',
                orgno:'',
                orgname:'',
                superOrg:'0',
                superOrgName:'',
                contactName:'',
                contactPhone:'',
                status:'1',
                seqno: 1
            },
            rules:{
                orgno: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 10, message: '长度不超过10位', trigger: 'blur' }
                ],
                orgname: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 50, message: '长度不超过50位', trigger: 'blur' }
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
                this.formData.orgid = data.orgid
                this.formData.orgno = data.orgno
                this.formData.orgname = data.orgname
                this.formData.contactName = data.contact_name
                this.formData.contactPhone = data.contact_phone
                this.formData.superOrg = data.super_org
                this.formData.superOrgName = data.SUPER_ORGNAME
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
                this.formData.superOrg = newObj.superorg
                this.formData.superOrgName = newObj.superorgname
            },
            deep: true 
        }
    }
}
</script>