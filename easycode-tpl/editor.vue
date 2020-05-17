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
#save("/html/editor", ".vue")

##使用宏定义设置包后缀
###setPackageSuffix("entity")
<!-- $!{tableInfo.comment}($!{tableInfo.name})页面
 @author $!author
 @since $!time.currTime()
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
    const moduleName = '$!{tableInfo.name}'
    export default {
        name: '$!{tableName}',
        mixins: [form,tool,download,pagination],
        data () {
            return {
                type: 'edit',
                visible: false,
                detail: {},
                form: {
        #foreach($column in $tableInfo.otherColumn)
    #if(!$column.comment.matches('.*创建.*|.*更新.*'))
                $!{column.name}:'',//$!{column.comment}
        #end
#end
        },
            addFormRules: {
            #foreach($column in $tableInfo.otherColumn)
    #if(!$column.comment.matches('.*创建.*|.*更新.*'))
                    $!{column.name}:[{required: true, message: '请输入#if($column.comment.endsWith('ID'))$!{column.comment.replaceAll('ID','')}#elseif($column.comment.contains('('))$!{column.comment.substring(0,$column.comment.indexOf('('))}#else$!{column.comment}#end'}],//$!{column.comment}
            #end
#end
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
