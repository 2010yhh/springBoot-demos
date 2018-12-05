/*
*后台api接口
*/
import http from './index'

export const login = data => {
  return http.get(`/login`, {
    params: data
  })
}
export const logout = data => {
  return http.get(`/logout`)
}
export const createImageCode = data => {
  return http.get(`/createImageCode`, {
    params: data
  })
}
export const checkImageCode = data => {
  return http.get(`/checkImageCode`, {
    params: data
  })
}
export default {
  login,
  logout,
  createImageCode,
  checkImageCode
}
