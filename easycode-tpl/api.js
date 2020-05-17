##去掉表的t_前缀
##$!init
#if($tableInfo.obj.name.startsWith("t_"))
#set($severName=$tool.getClassName($tableInfo.obj.name.substring(2,5)))
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
#save("/html/api", ".js")
<!-- $!{tableInfo.comment}($!{tableInfo.name})页面
 @author $!author
 @since $!time.currTime()
 -->
#set($temp = $tool.newHashMap())
$tool.call($temp.put("Bas","base"))
$tool.call($temp.put("Sys","system"))
import instance from '../../config'
const service = 'yunl-$temp.get($!{severName})-service'
const module = `${service}/$temp.get($!{severName})/$tool.firstLowerCase($tableInfo.name)`
// 分页列表
export const getList = param => instance.post(`${module}/pageList`, param)
// 保存
export const save = param => instance.post(`${module}/save`, param)
// 更新
export const update = param => instance.post(`${module}/update`, param)
// 删除
export const del = param => instance.post(`${module}/delByIds`, param)
// 下拉
export const choose = param => instance.post(`${module}/get$!{tableInfo.name}Choose`, param)