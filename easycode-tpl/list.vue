##去掉表的t_前缀
##$!init
#if($tableInfo.obj.name.startsWith("t_"))
$!tableInfo.setName($tool.getClassName($tableInfo.obj.name.substring(6)))
#end
##引入宏定义
##$!define
####定义初始变量
###set($tableName = $tableInfo.name)
####设置回调
##$!callback.setFileName($tool.append($tableName, ".vue"))
##$!callback.setSavePath($tool.append($tableInfo.savePath, "/add"))

##使用宏定义设置回调（保存位置与文件后缀）
#save("/html/list", ".vue")
##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
#set($pk = $tableInfo.pkColumn.get(0))
#end
##使用宏定义设置包后缀
###setPackageSuffix("entity")
<!-- $!{tableInfo.comment}($!{tableInfo.name})页面
 @author $!author
 @since $!time.currTime()
 -->
<template>
    <div class="app-container">
        <el-row>
            <el-col>
                <el-form ref="form" :model="form" class="head-container search-form" :class="{'noExpand': !isExpand}" inline="true">
                    #foreach($column in $tableInfo.otherColumn)
                    #if(!$column.comment.matches('.*创建.*|.*更新.*'))
                    #if(${column.type}=='java.lang.String')
                    <el-form-item label="$!{column.comment}"  prop="$!{column.name}">
                        <el-input type="text" v-model="form.$!{column.name}" clearable placeholder="请输入$!{column.comment}"
                                  class="filter-item"/>
                    </el-form-item>
                    #end
                    #if(${column.type}=='java.lang.Long'|| ${column.type}=='java.lang.Integer')
                    <el-form-item label="#if($column.comment.endsWith('ID'))$!{column.comment.replaceAll('ID','')}#elseif($column.comment.contains('('))$!{column.comment.substring(0,$column.comment.indexOf('('))}#else$!{column.comment}#end" prop="$!{column.name}">
                        <el-select v-model="form.$!{column.name}" clearable filterable placeholder="请选择#if($column.comment.endsWith('ID'))$!{column.comment.replaceAll('ID','')}#elseif($column.comment.contains('('))$!{column.comment.substring(0,$column.comment.indexOf('('))}#else$!{column.comment}#end"
                                   class="filter-item"></el-select>
                    </el-form-item>
                    #end
                    #if(${column.type}=='java.util.Date')
                    <el-form-item label="$!{column.comment}" prop="$!{column.name}">
                        <el-date-picker v-model="form.$!{column.name}" clearable type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="请选择$!{column.comment}"
                                        class="filter-item"></el-date-picker>
                    </el-form-item>
                    #end
                    #if($column.comment.contains("是否有效"))
                    <el-form-item label="是否有效" prop="$!{column.name}">
                        <el-select  v-model="form.$!{column.name}" clearable  filterable
                                    class="filter-item"  placeholder="请选择是否有效"  >
                            <el-option label="有效" :value="1"/>
                            <el-option label="无效" :value="0"/>
                        </el-select>
                    </el-form-item>
                    #end
                    #end
                    #end
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
                    #foreach($column in $tableInfo.fullColumn)
                    <el-table-column
                            prop="$!{column.name}"
                            label="#if($column.comment.endsWith('ID'))$!{column.comment.replaceAll('ID','')}#elseif($column.comment.contains('('))$!{column.comment.substring(0,$column.comment.indexOf('('))}#else$!{column.comment}#end"
                            sortable
                            align="center"
                            width="150"/>
                    #end
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
    const moduleName = '$!{tableInfo.name}'
    export default {
        components: {
            editor
        },
        mixins: [pagination, tool,form],
        data() {
            return {
                form: {
        #foreach($column in $tableInfo.otherColumn)
        #if(!$column.comment.matches('.*创建.*|.*更新.*'))
                $!{column.name}:'',//$!{column.comment}
        #end
        #end
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
                    this['$store'].dispatch(`delete${moduleName}`, this.selectData.map(v => v['$!{pk}'])).then(res => {
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