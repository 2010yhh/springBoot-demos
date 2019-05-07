<template>
    <div style="top:100px;width:300px">
        <el-form :model="form" label-width="120px" :rules="rules">
            <el-form-item label="用户名" required>
                <el-input v-model="form.userName" auto-complete="off" class="el-col-width" required></el-input>
            </el-form-item>
            <el-form-item label="密码" required>
                <el-input type="password" v-model="form.passWord" auto-complete="off" class="el-col-width" required></el-input>
            </el-form-item>
            <el-form-item>
              <el-checkbox v-model="form.rememberMe">下次自动登录</el-checkbox>
            </el-form-item>
            <el-form-item label="输入验证码" required>
                <el-input v-model="form.imageCode" auto-complete="off" class="el-col-width"></el-input>
            </el-form-item>
            <el-form-item required>
                <img id="img" alt="验证码" onclick="this.src='/createImageCode?d='+new Date()*1" src="/createImageCode" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handleSubmit">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import HTTP from '@/api/user'
export default {
  data() {
    return {
      form: {
        userName: '',
        passWord: '',
        rememberMe: false,
      },
      rules: {
        userName: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        passWord: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        imageCode: [
          { required: true, message: '验证码不能为空', trigger: 'blur' }
        ]
      }
    }
  },

  methods: {
    handleSubmit() {
      var params = {}
      params.userName = this.form.userName
      params.passWord = this.form.passWord
      params.rememberMe = this.form.rememberMe
      params.imageCode = this.form.imageCode
          HTTP.login( params).then(r => {
            //后台定义的状态码,登录成功后跳转
            if (r.data.code === 200) {
              this.$router.push({ name: 'home', params: { user: r.data } })
              this.$notify({
                title: 'success',
                message: '登录成功',
                type: 'success',
                position: 'bottom-right'
              })
            } else {
                document.getElementById("img").src="/createImageCode?d='+new Date()*1"; //这里的图片是更换后的图片
              this.$notify.error({
                title: 'error',
                message: '登录失败:' + r.data.msg,
                type: 'error',
                position: 'bottom-right'
              })
            }
          })

  },
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
