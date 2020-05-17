<!-- (User)页面
  @author hyhong
  @since 2020-05-17 16:14:46
  -->
<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-form ref="form" :model="form" class="head-container search-form" :class="{'noExpand': !isExpand}" inline="true">
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
                                                                 <div class="search-bar">
              <div class="form-item-control" @click='isExpand = !isExpand'>
                <span>{{isExpand ? '收起' : '展开'}}</span>
                <i :class="isExpand ? 'el-icon-caret-top' : 'el-icon-caret-bottom'"></i>
              </div>
              <el-button type="success" icon="el-icon-search"  @click="getList">搜索</el-button>
              <el-button icon="el-icon-close" @click="resetForm('form')">重置</el-button>
          </div>
          <!-- 新增 -->
        </el-form>
      </el-col>
    </el-row>

    <el-row class="tool">
      <el-col>
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-plus"
          @click="saveHandle">新增</el-button>
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-edit"
          @click="updateHandle">编辑</el-button>
        <el-button
          class="filter-item"
          type="danger"
          icon="el-icon-delete"
          @click="deleteHandle">删除</el-button>
      </el-col>
    </el-row>

    <el-row>
      <el-col>
          <!--表格渲染-->
        <el-table
          ref="table"
          :max-height="tableMaxHeight"
          :data="tableData"
          border
          stripe
          row-key="roleId"
          @selection-change="handleSelectionChange"
          @row-click="handleRowClick">
          <el-table-column  type="selection" width="40" fixed="left"/>
                    <el-table-column
            prop="userId"
            label="用户"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="userName"
            label="登录名"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="userPwd"
            label="登录密码"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="salt"
            label="加密盐"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="realName"
            label="真是姓名"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="phone"
            label="电话"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="mobile"
            label="手机"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="qq"
            label="QQ"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="wechat"
            label="微信"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="email"
            label="邮箱"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="status"
            label="是否有效"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="type"
            label="用户类型"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="userWxId"
            label="用户微信"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="createrId"
            label="创建人"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="createrName"
            label="创建人名称"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="createTime"
            label="创建时间"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="updaterId"
            label="更新人"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="updaterName"
            label="更新人名称"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="updateTime"
            label="更新时间"
            sortable
            align="center"
            width="150"/>
                  </el-table>
        <!--分页组件-->
        <el-pagination
          :current-page="pageNo"
          :page-size="pageSize"
          :total="pageCount"
          :page-sizes="pagesizes"
          layout="total, sizes, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"/>
      </el-col>
    </el-row>

    <editor ref="editor"/>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import pagination from '@/common/mixins/pagination'
import tool from '@/common/mixins/tool'
import form  from '@/common/mixins/form'
import editor from './editor.vue'
const moduleName = 'User'
export default {
 components: {
    editor
  },
  mixins: [pagination, tool,form],
  data() {
    return {
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
                                                                                                                      }
    }
  },
  created() {
    this.$nextTick(() => {
      this.getList()
    })
  },
  methods: {
    ...mapActions([`get${moduleName}List}`]),
    // 新增
    saveHandle() {
      this.$refs.editor.type = 'add'
      this.$refs.editor.detail = {}
      this.$refs.editor.visible = true
    },
    // 修改
    updateHandle() {
      if (this.selectData && this.selectData.length == 1) {
        this.$refs.editor.type = 'edit'
        this.$refs.editor.visible = true
        this.$refs.editor.detail = this.selectData[0]
      } else {
        this.$confirm('请选择一条数据进行编辑')
      }
    },
    // 删除
    deleteHandle() {
      if (this.selectData && this.selectData.length >= 1) {
          this['$store'].dispatch(`delete${moduleName}`, this.selectData.map(v => v['ColumnInfo(obj=column:user_id, name=userId, comment=用户ID, type=java.lang.Long, shortType=Long, custom=false, ext={sqlType=bigint, jdbcType=INTEGER})'])).then(res => {
                if (res.success) {
                    this.$refs['form'].resetFields()
                    this.getList()
                    this.$message({
                    message: '删除成功',
                    type: 'success'
                    })
                } else {
                    this.$message({
                    message: res.msg,
                    type: 'error'
                    })
                }
            })
      
      } else {
        this.$confirm('请至少选择一条数据进行删除')
      }
    },
    handleSelectionChange(val) {
      this.selectData = val
    },
    getList() {
      this.pageHandler(this.form, this[`get${moduleName}List}`])
    }
  },
  mounted() {
  }
}
</script>
    
<style scoped>
</style>