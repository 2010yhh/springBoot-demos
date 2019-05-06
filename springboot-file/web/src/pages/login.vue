<template>
    <div style="top:100px;width:300px">
        <el-form :model="form" label-width="120px">
            <el-form-item label="请输入文件名" required>
                <el-input v-model="form.fileName" auto-complete="off" class="el-col-width" required></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="small" type="primary" @click="handleDownLoad">下载</el-button>
            </el-form-item>
            <el-form-item>
                <el-upload class="upload-demo" :action="uploadUrl" :before-upload="handleBeforeUpload"  :on-error="handleUploadError" :before-remove="beforeRemove" multiple :limit="5" :on-exceed="handleExceed" :file-list="fileList">
                    <el-button size="small" type="primary">点击上传</el-button>
                    <div slot="tip" class="el-upload__tip">不超过5Mb</div>
                </el-upload>
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
        fileName: '',
      },
       uploadUrl: '/file/upload',
       fileList: []
    }
  },

  methods: {
    //测试下载文件(注意web的上下文)
    handleDownLoad() {
      window.location.href = `/file/download?fileName=` + this.form.fileName
    },

      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 5 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      handleUploadError(error, file) {
    
       this.$notify.error({
                title: 'error',
                message: '上传出错:' +  error,
                type: 'error',
                position: 'bottom-right'
              })
    },
    //测试上传文件(注意web的上下文)
    handleBeforeUpload(file){
         this.uploadUrl =`/file/upload`
    }
    //,
    // handleSubmit() {
    //   var params = {}
    //   params.userName = this.form.userName
    //   params.passWord = this.form.passWord
    //   params.rememberMe = this.form.rememberMe
    //   params.inputImageCode = this.form.inputImageCode
    //   HTTP.checkImageCode(params).then(r => {
    //     //后台定义的状态码,登录成功后跳转
    //     if (r.data.code === '200') {
    //       HTTP.login(params).then(r => {
    //         //后台定义的状态码,登录成功后跳转
    //         if (r.data.code === '200') {
    //           this.$router.push({ name: 'home', params: { user: r.data.user } })
    //           this.$notify({
    //             title: 'success',
    //             message: '登录成功',
    //             type: 'success',
    //             position: 'bottom-right'
    //           })
    //         } else {
    //             document.getElementById("img").src="/createImageCode?d='+new Date()*1"; //这里的图片是更换后的图片
    //           this.$notify.error({
    //             title: 'error',
    //             message: '登录失败:' + r.data.msg,
    //             type: 'error',
    //             position: 'bottom-right'
    //           })
    //         }
    //       })
    //     } else {
    //         document.getElementById("img").src="/createImageCode?d='+new Date()*1"; //这里的图片是更换后的图片
    //       this.$notify.error({
    //         title: 'error',
    //         message: '验证码错误',
    //         type: 'error',
    //         position: 'bottom-right'
    //       })
    //     }
    //   })
    // }
  }
}
</script>
