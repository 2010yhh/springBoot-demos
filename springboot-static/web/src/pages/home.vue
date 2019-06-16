<template>
           <div style="top:100px;width:300px">
               <span>当前用户：</span>
                  <el-input v-model="this.$route.params.userName" auto-complete="off" class="el-col-width" :disabled="true"></el-input>
                </div>
</template>
<script>
import HTTP from '@/api/index'
export default {
  data() {
    return {
    }
  },
  methods: {
}}
</script>
