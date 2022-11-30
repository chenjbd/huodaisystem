<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
            <el-form :model="formData" ref="form" label-width="100px">
                <el-form-item label="设置角色" prop="">
                    <el-checkbox-group v-model="formData.rIds">
                        <el-checkbox v-for="(item, index) in roles" :label="item.roleId" border >{{item.roleName}}</el-checkbox>
                    </el-checkbox-group>
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
import { addAccRole, getAccRole } from '@/api/acc'
export default {
    name:'AccRole',
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
        }
    },
    data(){
        return {
            formData:{
                accId:'',
                rIds:[]
            },
            roles:[],
        }
    },
    methods:{
        closeDialog(f){
            this.$emit('closeDialog', f)
        },
        submitForm(){
            const data = {
                accId: this.formData.accId,
                rIds: this.formData.rIds.toString()
            }
            addAccRole(data).then(res=>{
                this.$notify({
                    title: '成功',
                    message: '提交成功',
                    type: 'success',
                    duration: 2000
                });
                this.closeDialog(false)
            });
        },
        loadRoles(id){
            this.formData.accId = id
            const params = {
                accId: id
            }
            getAccRole(params).then(res=>{
                const { data } = res
                this.roles = data
                //计算选中的复选框
                let arr = []
                this.roles.forEach((item,index)=>{
                    if(item.checked == 1){
                        arr.push(item.roleId)
                    }
                })
                this.formData.rIds = arr
            });
        },
        resetForm(){
            this.$refs.form.resetFields()
        }
    },
    watch:{
        id(newVal,oldVal){
            if(newVal && newVal.length>0){
                this.loadRoles(newVal)
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