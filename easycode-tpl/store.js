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
#save("/html/store", ".js")
<!-- $!{tableInfo.comment}($!{tableInfo.name})页面
 @author $!author
 @since $!time.currTime()
 -->
import api from '@/api'
const module = '$!{tableInfo.name}'
export default {
state: {

},
getters: {

},
actions: {
[`get${module}List`]({ commit }, param) {
return api[`${module.toLowerCase()}`].getList(param).then((res) => {
return new Promise((resolve, reject) => {
resolve(res)
})
})
},
[`add${module}`]({ commit }, param) {
return api[`${module.toLowerCase()}`].save(param).then((res) => {
return new Promise((resolve, reject) => {
resolve(res)
})
})
},
[`update${module}`]({ commit }, param) {
return api[`${module.toLowerCase()}`].update(param).then((res) => {
return new Promise((resolve, reject) => {
resolve(res)
})
})
},
[`delete${module}`]({ commit }, param) {
return api[`${module.toLowerCase()}`].del(param).then((res) => {
return new Promise((resolve, reject) => {
resolve(res)
})
})
},
[`get${module}Choose`]({ commit }, param) {
return api[`${module.toLowerCase()}`].choose(param).then((res) => {
return new Promise((resolve, reject) => {
resolve(res)
})
})
}
},
mutations: {

}

}