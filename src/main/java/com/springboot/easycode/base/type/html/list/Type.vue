<!-- (Type)页面
  @author hyhong
  @since 2020-05-17 16:15:06
  -->
<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-form ref="form" :model="form" class="head-container search-form" :class="{'noExpand': !isExpand}" inline="true">
                                   <el-form-item label="类别名称"  prop="typeName">
                <el-input type="text" v-model="form.typeName" clearable placeholder="请输入类别名称" 
                          class="filter-item"/>
             </el-form-item>
                                                               <el-form-item label="类别编码"  prop="typeCode">
                <el-input type="text" v-model="form.typeCode" clearable placeholder="请输入类别编码" 
                          class="filter-item"/>
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
            prop="typeId"
            label="类别"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="typeName"
            label="类别名称"
            sortable
            align="center"
            width="150"/>
                    <el-table-column
            prop="typeCode"
            label="类别编码"
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
const moduleName = 'Type'
export default {
 components: {
    editor
  },
  mixins: [pagination, tool,form],
  data() {
    return {
      form: {
                        typeName:'',//类别名称
                                typeCode:'',//类别编码
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
          this['$store'].dispatch(`delete${moduleName}`, this.selectData.map(v => v['ColumnInfo(obj=column:type_id, name=typeId, comment=类别ID, type=java.lang.Long, shortType=Long, custom=false, ext={sqlType=bigint, jdbcType=INTEGER})'])).then(res => {
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