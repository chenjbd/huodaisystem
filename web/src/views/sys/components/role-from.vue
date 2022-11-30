<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
            <el-form :model="formData" ref="form" :rules="rules" label-width="100px">
                <el-form-item label="角色编号" prop="roleNo">
                    <el-input v-model="formData.roleNo" autocomplete="off" :readonly="f==='V'"></el-input>
                </el-form-item>
                <el-form-item label="角色名称" prop="roleName">
                    <el-input v-model="formData.roleName" autocomplete="off" :readonly="f==='V'"></el-input>
                </el-form-item>
                <el-form-item label="角色说明" prop="roleDesc">
                    <el-input type="textarea" :row="3" v-model="formData.roleDesc" autocomplete="off" :readonly="f==='V'"></el-input>
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
import { add, get, update } from '@/api/sys/role'
export default {
    name:'RoleForm',
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
                rId:'',
                roleNo:'',
                roleName:'',
                roleDesc:''
            },
            rules:{
                roleNo: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 10, message: '长度不超过10位', trigger: 'blur' }
                ],
                roleName: [
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
                this.formData.rId = data.rId
                this.formData.roleNo = data.roleNo
                this.formData.roleName = data.roleName
                this.formData.roleDesc = data.roleDesc
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