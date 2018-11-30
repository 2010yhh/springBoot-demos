<template>
    <div style="top:100px;width:400px">
        <span>测试接口及跨域：</span>
        <el-form inline="">
            <el-form-item>
                <el-button type="primary" @click="handleTest">测试接口</el-button>
                <el-button type="primary" @click="handleTest2">测试跨域1</el-button>
                <el-button type="primary" @click="handleTest3">测试跨域2</el-button>
            </el-form-item>
            <el-form-item label-width="400px">
                <span> 测试接口结果：{{this.result}} </span>
        
            </el-form-item>
              <el-form-item>
      
                <span>前端调试测试跨域结果：{{this.crosResult}} </span>
   
            </el-form-item>
              <el-form-item>
            
                <span> 测试跨域结果：{{this.crosResult2}}</span>
            </el-form-item>
        </el-form>
            <span>测试利用cookie实现session内容共享：</span>
        <el-form inline="">
             <el-form-item label="用户名" required>
                <el-input v-model="form.userName" auto-complete="off" class="el-col-width"></el-input>
            </el-form-item>
            <el-form-item label="密码" required>
                <el-input type="password" v-model="form.passWord" auto-complete="off" class="el-col-width"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="setSession">设置cookie</el-button>
                <el-button type="primary" @click="getSession">获取cookie</el-button>
                <el-button type="primary" @click="getSession2">跨域cookie</el-button>
            </el-form-item>
            <el-form-item>
                <span> 获取session结果：{{this.session1}}</span>
            </el-form-item>
            <el-form-item>
                <span> 跨域获取session结果：{{this.crosSession}} </span>
            </el-form-item>
        </el-form>
        <!-- <a href="./static/img/1.jpg">测试静态资源</a>-->
        <!--<img :src="this.imgUrl" />-->
    </div>
</template>
<script>
import TEST from '@/api/test'
import httpaxios from '../api/index.js'
httpaxios.defaults.withCredentials = true //  允许携带cookie信息
export default {
  data() {
    return {
      result: '',
      result2: '',
      result3: '',
      session1: {},
      session2: {},
      imgUrl: 'http://localhost:8090/webapp2/static/img/1.jpg',
      form: {
        userName: '',
        passWord: '',
        rememberMe: false
      },
      rules: {
        userName: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        passWord: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      }
    }
  },
  computed: {
    crosResult() {
      return this.result2
    },
    crosResult2() {
      return this.result3
    },
    crosSession() {
      console.log('getSession2:' + JSON.stringify(this.session2))
      return this.session2
    }
  },
  methods: {
    handleTest() {
      var params = {}
      TEST.test(params).then(r => {
        //后台定义的状态码,登录成功后跳转
        if (r.data.code === 200) {
          this.result = r.data.data
          this.$notify({
            title: 'success',
            message: '接口调用成功',
            type: 'success',
            position: 'bottom-right'
          })
        } else {
          this.$notify.error({
            title: 'error',
            message: '接口调用失败:' + r.data.msg,
            type: 'error',
            position: 'bottom-right'
          })
        }
      })
    },
    handleTest2() {
      const self = this
      httpaxios
        .get('http://localhost:8080/webapp1/test', {
          params: {}
        })
        .then(function(res) {
          // console.log(JSON.stringify(res))
          self.result2 = res.data.data
          //alert('跨域接口调用成功'+self.result2)
          self.$notify({
            title: 'success',
            message: '接口调用成功!',
            type: 'success',
            position: 'bottom-right'
          })
        })
        .catch(function(res) {
          console.log(JSON.stringify(res.data))
          //alert( '跨域接口调用失败:' + JSON.stringify(res))
          self.$notify.error({
            title: 'error',
            message: '接口调用失败,error:' + JSON.stringify(res),
            type: 'error',
            position: 'bottom-right'
          })
        })
    },
    handleTest3() {
      const self = this
      httpaxios
        .get('http://localhost:8090/webapp2/test', {
          params: {}
        })
        .then(function(res) {
          // console.log(JSON.stringify(res))
          self.result3 = res.data.data
          //alert('跨域接口调用成功'+self.result2)
          self.$notify({
            title: 'success',
            message: '接口调用成功!',
            type: 'success',
            position: 'bottom-right'
          })
        })
        .catch(function(res) {
          console.log(JSON.stringify(res.data))
          //alert( '跨域接口调用失败:' + JSON.stringify(res))
          self.$notify.error({
            title: 'error',
            message: '接口调用失败,error:' + JSON.stringify(res),
            type: 'error',
            position: 'bottom-right'
          })
        })
    },
    setSession() {
      var params = {}
      params.key = this.form.userName
      params.value = this.form.passWord

      TEST.setSession(params).then(r => {
        //后台定义的状态码
        if (r.data.code === 200) {
          //this.session=r.data.result
          console.log('setSession:' + JSON.stringify(r.data))
          this.$notify({
            title: 'success',
            message: '设置成功',
            type: 'success',
            position: 'bottom-right'
          })
        } else {
          this.$notify.error({
            title: 'error',
            message: '设置失败:' + r.data.msg,
            type: 'error',
            position: 'bottom-right'
          })
        }
      })
    },
    getSession() {
      var params = {}
      params.key = this.form.userName
      TEST.getSession(params).then(r => {
        //后台定义的状态码
        if (r.data.code === 200) {
          this.session1 = r.data.data
          console.log('getSession:' + JSON.stringify(r.data))
          this.$notify({
            title: 'success',
            message: '获取成功',
            type: 'success',
            position: 'bottom-right'
          })
        } else {
          this.$notify.error({
            title: 'error',
            message: '获取失败:' + r.data,
            type: 'error',
            position: 'bottom-right'
          })
        }
      })
    },
    getSession2() {
      const self = this
      var url =
        'http://localhost:8090/webapp2/getSession?key=' + this.form.userName

      httpaxios
        .get(url, {
          params: {}
        })
        .then(function(res) {
          // console.log(JSON.stringify(res))
          self.session2 = res.data.data
          //alert('跨域接口调用成功'+self.result2)

          self.$notify({
            title: 'success',
            message: '跨域获取session调用成功!',
            type: 'success',
            position: 'bottom-right'
          })
        })
        .catch(function(res) {
          console.log(JSON.stringify(res.data))
          //alert( '跨域接口调用失败:' + JSON.stringify(res))
          self.$notify.error({
            title: 'error',
            message: '跨域获取session调用失败,error:' + JSON.stringify(res),
            type: 'error',
            position: 'bottom-right'
          })
        })
    }
  }
}
</script>
