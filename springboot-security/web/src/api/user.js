/*
*后台api接口
*/
import http from './index'

export const login = data => {
  return http.post(`/myloginForm?userName=${data.userName}&passWord=${data.passWord}&rememberMe=${data.rememberMe}&imageCode=${data.imageCode}`)
}
export const logout = data => {
  return http.post(`/mylogout`)
}
export const createImageCode = data => {
  return http.get(`/createImageCode`, {
    params: data
  })
}
export const getUserInfo = data => {
  return http.get(`/user/info`, {
    params: data
  })
}
export const getUserRoles = data => {
  return http.get(`/user/roles`, {
    params: data
  })
}
export const getUserList = data => {
  return http.get(`/user/list`, {
    params: data
  })
}
export const getUserList2 = data => {
  return http.get(`/user/list2`, {
    params: data
  })
}
export const test = data => {
  return http.get(`/test/test`, {
    params: data
  })
}
export default {
  login,
  logout,
  createImageCode,
  test,
  getUserInfo,
  getUserList,
  getUserList2,
  getUserRoles
}
