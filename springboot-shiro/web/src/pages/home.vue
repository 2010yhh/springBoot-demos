<template>
    <div style="top:100px;width:300px">
        <span>当前用户：</span>
        <el-form inline="">
            <el-form-item>
                {{this.$route.params.user}}
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handleLogout">退出</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import HTTP from '@/api/user'
export default {
  data() {
    return {}
  },
  methods: {
    handleLogout() {
      var params = {}
      HTTP.logout(params).then(r => {
        // 退出登录
          this.$router.push('login')
      })
    }
  }
}
</script>
