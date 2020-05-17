<!-- (Type)页面
  @author hyhong
  @since 2020-05-17 16:15:06
  -->
<template>
  <el-dialog :close-on-click-modal="false"
    :title="type == 'edit' ? '编辑' : '新增'"
    :visible.sync="visible"
    :before-close="closeDialog"
    width="450px"
    >
  <div class="mar10 pad30 bg1">
    <el-form ref="form" :rules="addFormRules" :model="form" label-width="80px">
                                  <el-form-item label="类别名称"  prop="typeName">
                <el-input type="text" v-model="form.typeName" clearable placeholder="请输入类别名称" 
                          class="filter-item"/>
             </el-form-item>
                                                                                     <el-form-item label="类别编码"  prop="typeCode">
                <el-input type="text" v-model="form.typeCode" clearable placeholder="请输入类别编码" 
                          class="filter-item"/>
             </el-form-item>
                                                                                                                               <el-form-item>
        <el-button type="primary" @click="submitHandle">确定</el-button>
        <el-button type="" @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
  </el-dialog>
</template>
<script>
  import {mapActions, mapState} from 'vuex'
  import pagination from '@/common/mixins/pagination'
  import download  from '@/common/mixins/download'
  import form  from '@/common/mixins/form'
  import tool from '@/common/mixins/tool'
  const moduleName = 'Type'
  export default {
    name: 'Type',
    mixins: [form,tool,download,pagination],
    data () {
      return {
        type: 'edit',
        visible: false,
        detail: {},
        form: {
                        typeName:'',//类别名称
                                typeCode:'',//类别编码
                                                                                                                        },
        addFormRules: {
                        typeName:[{required: true, message: '请输入类别名称'}],//类别名称
                                typeCode:[{required: true, message: '请输入类别编码'}],//类别编码
                                                                                                                        },
      }
    },
    computed: {},
    methods: {
         submitHandle(){
             this['$refs'].form.validate((valid)=> {
                    if(valid){
                        var action = this.type == 'edit' ? `update${moduleName}` : `add${moduleName}`
                        this['$store'].dispatch(action,this.form).then((res)=>{
                                if (res.success) {
                                      this.$refs['form'].resetFields()
                                      this.visible = false
                                      this.$message({
                                        message: res.msg,
                                        type: 'success'
                                      })
                                      this.$parent.getList();
                                    } else {
                                      this.$message({
                                        message: res.msg,
                                        type: 'error'
                                      })
                                    }
                         })
                    }
             });
        }
        
    },
    watch: {
        visible(val) {
            if (val) {
                this.form = Object.assign({}, this.detail)
                this.$nextTick(() => {
                    this.$refs.form.clearValidate()
                })
            }
        }
    },
    created () {
    },
    mounted () {
    }
  }
</script>