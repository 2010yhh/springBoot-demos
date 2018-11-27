<template>
    <div style="top:100px;width:300px">
        <span>测试接口：</span>
        <el-form inline="">
             <el-form-item>
                <el-button type="primary" @click="handleTest">测试接口</el-button>
            </el-form-item>
            <el-form-item>
                {{this.result}}
            </el-form-item>
        </el-form>
        <a href="./static/img/1.jpg">测试静态资源</a>
    </div>
</template>
<script>
import HTTP from '@/api/test'
export default {
  data() {
    return {
         result:{},
    }
  },
  methods: {
    handleTest() {
      var params = {}
      HTTP.test(params).then(r => {
      //后台定义的状态码,登录成功后跳转
        if (r.data.code ==="200") {
          this.result=r.data.data
          this.$notify({
            title: 'success',
            message: '接口调用成功',
            type: 'success',
            position: 'bottom-right'
          })
        } else {
          this.$notify.error({
            title: 'error',
            message: '接口测试失败:'+r.data.msg,
            type: 'error',
            position: 'bottom-right'
          })
        }

      })
    }
  }
}
</script>
