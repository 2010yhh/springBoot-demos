/*
*后台api接口
*/
import http from './index'

export const upload = data => {
  return http.post(`/file/upload`, {
    params: data
  })
}
export const download = data => {
  return http.get(`/file/download`)
}

export default {
  upload,
  download
}
