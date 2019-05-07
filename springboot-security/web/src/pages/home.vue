<template>
    <div style="top:100px;width:300px">
        <span>当前用户：前端可以先获取当前用户的所有角色及所有权限:进而决定前端显示效果
            这里以角色而不是权限进行简单演示
        </span>
        <el-form inline="" >
            <el-form-item>
                {{'当前登录用户信息：'+this.userInfo}}
            </el-form-item>
             <el-form-item>
                <el-button type="primary" :disabled="queryAll1" @click="getUserList">查询所有用户</el-button>
            </el-form-item>
            <el-form-item label="结果" >
                <el-input v-model="this.userList"  type="textarea" :rows="5" size="large"></el-input>
            </el-form-item>
             <el-form-item>
                <el-button type="primary"  :disabled="queryAll2"  @click="getUserList2">查询所有用户2</el-button>
            </el-form-item>
              <el-form-item label="结果2" >
                <el-input v-model="this.userList2"  type="textarea" :rows="5" size="large"></el-input>
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
    return {
        userInfo:'',
        userList:'',
        userList2:'',
        queryAll1:false,
        queryAll2:false,
        userRoles:[]
        }
  },
  mounted(){
      this.getUserInfo() 
       this.getUserRoles()
  }
  ,
  methods: {
    handleLogout() {
      var params = {}
      HTTP.logout(params).then(r => {
        // 退出登录
          this.$router.push('login')
      })
    },
    getUserInfo(){
          HTTP.getUserInfo().then(r => {
          //后台定义的状态码,登录成功后跳转
            if (r.data.code === 200) {
              this.userInfo=r.data.result
              console.log('this.userInfo:'+JSON.stringify(this.userInfo))
              this.$notify({
                title: 'success',
                message: '成功',
                type: 'success',
                position: 'bottom-right'
              })
            } else {
              this.$notify.error({
                title: 'error',
                message: '失败:' + r.data.msg,
                type: 'error',
                position: 'bottom-right'
              })
            }
      })
    },
        getUserRoles(){
          HTTP.getUserRoles().then(r => {
          //后台定义的状态码,登录成功后跳转
            if (r.data.code === 200) {
              this.userRoles=r.data.result
              if(this.userRoles.indexOf('ROLE_admin')==-1){
                  this.queryAll1=true
              }
              if(this.userRoles.indexOf('ROLE_manager')==-1){
                  this.queryAll2=true
              }
              console.log('this.userRoles:'+JSON.stringify(this.userRoles))
         this.$notify({
                title: 'success',
                message: '成功',
                type: 'success',
                position: 'bottom-right'
              })
            } else {
              this.$notify.error({
                title: 'error',
                message: '失败:' + r.data.msg,
                type: 'error',
                position: 'bottom-right'
              })
            }
      })
    },
     getUserList(){
          HTTP.getUserList().then(r => {
               //后台定义的状态码,登录成功后跳转
            if (r.data.code === 200) {
              this.userList=JSON.stringify(r.data.result)
              this.$notify({
                title: 'success',
                message: '成功',
                type: 'success',
                position: 'bottom-right'
              })
            } else {
               this.$notify.error({
                title: 'error',
                message: '失败:' + r.data.msg,
                type: 'error',
                position: 'bottom-right'
              })
            }
       
      })
    },
     getUserList2(){
          HTTP.getUserList2().then(r => {
               //后台定义的状态码,登录成功后跳转
            if (r.data.code === 200) {
              this.userList2=JSON.stringify(r.data.result)
              this.$notify({
                title: 'success',
                message: '成功',
                type: 'success',
                position: 'bottom-right'
              })
            } else {
              this.$notify.error({
                title: 'error',
                message: '失败:' + r.data.msg,
                type: 'error',
                position: 'bottom-right'
              })
            }
       
      })
    }
  }
}
</script>
