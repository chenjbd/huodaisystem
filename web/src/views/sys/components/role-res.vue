<template>
    <div>
        <el-dialog :title="title" :visible.sync="dialogVisible"
        :before-close="closeDialog"
        >
            <el-form :model="formData" ref="form" :rules="rules" label-width="100px">
                <el-form-item label="角色授权" prop="roleNo">
                    <el-tree
                    class="filter-tree"
                    :data="treeData"
                    :props="defaultProps"
                    node-key="id"
                    :default-checked-keys="checkedData"
                    show-checkbox
                    default-expand-all
                    @node-click="nodeClick"
                    ref="tree">
                    </el-tree>
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
import { loadTree, addRes } from '@/api/sys/role'
export default {
    name:'RoleRes',
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
                rId:'',
                roleNo:''
            },
            rules:{
            },
            treeData:[],
            defaultProps:{
                children: 'children',
                label: 'title'
            }
        }
    },
    computed:{
        checkedData(){
            let arr = [];
            this.filterCheckedData(this.treeData, arr)
            return arr
        }
    },
    methods:{
        filterCheckedData(children, arr){
            children.forEach((item, index)=>{
                if(item.children && item.children.length > 0){
                    this.filterCheckedData(item.children, arr)
                }else{
                    if(item.checked == 1){
                        arr.push(item.id)
                    }
                }
            })
        },
        closeDialog(f){
            this.$emit('closeDialog', f)
        },
        nodeClick(data, node, elem){
            let id = data.id
        },
        submitForm(){
            let keys = this.$refs.tree.getCheckedKeys();
            if(keys.length == 0){
                this.$message({
                    type: 'warning',
                    message: '请选择权限!'
                });
                return false;  
            }
            const data = {
                roleId: this.id,
                resIds: keys.toString()
            }
            addRes(data).then(res=>{
                this.$notify({
                    title: '成功',
                    message: '提交成功',
                    type: 'success',
                    duration: 2000
                });
                this.closeDialog(false)
            });
        },
        loadTree(id){
            this.formData.rId = id
            const param = {
                id: id
            }
            loadTree(param).then(res=>{
                const { data } = res
                this.treeData = data
            })
        },
        resetForm(){
            this.$refs.form.resetFields()
        }
    },
    watch:{
        id(newVal,oldVal){
            if(newVal && newVal.length>0){
                this.loadTree(newVal)
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