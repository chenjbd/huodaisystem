<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
            <el-form :model="formData" ref="form" :rules="rules" label-width="100px">
                <el-form-item label="类别" prop="bigTypeID">
                    <el-select v-model="formData.bigTypeID" style="width:100%" placeholder="请选择" :disabled="f==='E'">
                        <el-option
                        v-for="item in lblist"
                        :key="item.value"
                        :value="item.value"
                        :label="item.name">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="名称" prop="typeName">
                    <el-input v-model="formData.typeName" autocomplete="off" :readonly="f==='V'"></el-input>
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
import { add, get, update } from '@/api/basic/lbxx'
export default {
    name:'LbxxForm',
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
            lblist:[
                {value:"00", name:"箱子类型"},
                {value:"01", name:"装卸工队"},
                {value:"02", name:"报关地区"},
                {value:"03", name:"仓库"},
            ],
            formData:{
                id:'',
                bigTypeID:'',
                litTypeID:'',
                typeName:'',
            },
            rules:{
                litTypeID: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { min: 1, max: 10, message: '长度不超过50位', trigger: 'blur' }
                ],
                typeName: [
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
                this.formData.id = data.id
                this.formData.bigTypeID = data.bigtypeid
                this.formData.litTypeID = data.littypeid
                this.formData.typeName = data.typename
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