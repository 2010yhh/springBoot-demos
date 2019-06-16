/*
*后台api接口
*/
import http from './index'

export const login = data => {
  return http.get(`/login`, {
    params: data
  })
}

export default {
  login
}
