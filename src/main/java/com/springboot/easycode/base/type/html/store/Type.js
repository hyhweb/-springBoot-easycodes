<!-- (Type)页面
  @author hyhong
  @since 2020-05-17 16:15:06
  -->
import api from '@/api'
const module = 'Type'
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