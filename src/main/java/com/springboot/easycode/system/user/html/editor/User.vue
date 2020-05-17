<!-- (User)页面
  @author hyhong
  @since 2020-05-17 16:14:46
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
                            <el-form-item label="登录名"  prop="userName">
                <el-input type="text" v-model="form.userName" clearable placeholder="请输入登录名" 
                          class="filter-item"/>
             </el-form-item>
                                                               <el-form-item label="登录密码"  prop="userPwd">
                <el-input type="text" v-model="form.userPwd" clearable placeholder="请输入登录密码" 
                          class="filter-item"/>
             </el-form-item>
                                                               <el-form-item label="加密盐"  prop="salt">
                <el-input type="text" v-model="form.salt" clearable placeholder="请输入加密盐" 
                          class="filter-item"/>
             </el-form-item>
                                                               <el-form-item label="真是姓名"  prop="realName">
                <el-input type="text" v-model="form.realName" clearable placeholder="请输入真是姓名" 
                          class="filter-item"/>
             </el-form-item>
                                                               <el-form-item label="电话"  prop="phone">
                <el-input type="text" v-model="form.phone" clearable placeholder="请输入电话" 
                          class="filter-item"/>
             </el-form-item>
                                                               <el-form-item label="手机"  prop="mobile">
                <el-input type="text" v-model="form.mobile" clearable placeholder="请输入手机" 
                          class="filter-item"/>
             </el-form-item>
                                                               <el-form-item label="QQ"  prop="qq">
                <el-input type="text" v-model="form.qq" clearable placeholder="请输入QQ" 
                          class="filter-item"/>
             </el-form-item>
                                                               <el-form-item label="微信"  prop="wechat">
                <el-input type="text" v-model="form.wechat" clearable placeholder="请输入微信" 
                          class="filter-item"/>
             </el-form-item>
                                                               <el-form-item label="邮箱"  prop="email">
                <el-input type="text" v-model="form.email" clearable placeholder="请输入邮箱" 
                          class="filter-item"/>
             </el-form-item>
                                                                                       <el-form-item label="是否有效" prop="status">
                <el-select  v-model="form.status" clearable  filterable 
                class="filter-item"  placeholder="请选择是否有效"  >
                  <el-option label="有效" :value="1"/>
                  <el-option label="无效" :value="0"/>
                </el-select>
            </el-form-item>
                                                <el-form-item label="用户类型" prop="type">
               <el-select v-model="form.type" clearable filterable placeholder="请选择用户类型" 
                          class="filter-item"></el-select>
              </el-form-item>
                                                               <el-form-item label="用户微信" prop="userWxId">
               <el-select v-model="form.userWxId" clearable filterable placeholder="请选择用户微信" 
                          class="filter-item"></el-select>
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
  const moduleName = 'User'
  export default {
    name: '',
    mixins: [form,tool,download,pagination],
    data () {
      return {
        type: 'edit',
        visible: false,
        detail: {},
        form: {
                    userName:'',//登录名
                userPwd:'',//登录密码
                salt:'',//加密盐
                realName:'',//真是姓名
                phone:'',//电话
                mobile:'',//手机
                qq:'',//QQ
                wechat:'',//微信
                email:'',//邮箱
                status:'',//是否有效(0无效,1有效)
                type:'',//用户类型(0管理员,1后台用户,2微信用户)
                userWxId:'',//用户微信ID
                                    },
        addFormRules: {
                    userName:[{required: true, message: '请输入登录名'}],//登录名
                userPwd:[{required: true, message: '请输入登录密码'}],//登录密码
                salt:[{required: true, message: '请输入加密盐'}],//加密盐
                realName:[{required: true, message: '请输入真是姓名'}],//真是姓名
                phone:[{required: true, message: '请输入电话'}],//电话
                mobile:[{required: true, message: '请输入手机'}],//手机
                qq:[{required: true, message: '请输入QQ'}],//QQ
                wechat:[{required: true, message: '请输入微信'}],//微信
                email:[{required: true, message: '请输入邮箱'}],//邮箱
                status:[{required: true, message: '请输入是否有效'}],//是否有效(0无效,1有效)
                type:[{required: true, message: '请输入用户类型'}],//用户类型(0管理员,1后台用户,2微信用户)
                userWxId:[{required: true, message: '请输入用户微信'}],//用户微信ID
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