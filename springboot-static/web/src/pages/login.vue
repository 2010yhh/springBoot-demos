<template>
    <div style="top:100px;width:300px">
        <el-form :model="form" label-width="80px" :rules="rules">
            <el-form-item label="用户名" required>
                <el-input v-model="form.userName" auto-complete="off" class="el-col-width"></el-input>
            </el-form-item>
            <el-form-item label="密码" required>
                <el-input v-model="form.passWord" auto-complete="off" class="el-col-width"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handleSubmit">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import HTTP from '@/api/testApi'
export default {
  data() {
    return {
      form: {
        userName: '',
        passWord: ''
      },
      rules: {
        userName: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        passWord: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      }
    }
  },
  methods: {
    handleSubmit() {
        var params={}
        params.userName=this.form.userName
        params.passWord=this.form.passWord
        HTTP.login(params).then(r => {
        //后台定义的状态码
        if (r.data.code ==="200") {
           this.$router.push({name: 'home', params: {userName:params.userName,passWord:params.passWord}})
          this.$notify({
            title: 'success',
            message: '登录成功',
            type: 'success',
            position: 'bottom-right'
          })
        } else {
          this.$notify.error({
            title: 'error',
            message: '登录失败:'+r.data.msg,
            type: 'error',
            position: 'bottom-right'
          })
        }
      })
    }
  }
}
</script>
