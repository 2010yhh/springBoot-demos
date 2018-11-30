/*
*后台api接口测试
*/
import http from './index'
export const test = data => {
  return http.get(`/test`, {
    params: data
  })
}
export const setSession = data => {
  return http.get(`/setSession`, {
    params: data
  })
}
export const getSession = data => {
  return http.get(`/getSession`, {
    params: data
  })
}
export default {
  test,
  setSession,
  getSession
}
