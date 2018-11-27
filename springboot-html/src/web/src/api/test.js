/*
*后台api接口测试
*/
import http from './index'

export const test = data => {
  return http.get(`/test`, {
    params: data
  })
}
export default {
  test
}
