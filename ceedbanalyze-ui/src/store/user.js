// 初始化一个用户pinia
import { defineStore } from 'pinia'
import { userlogin } from '@/api/login'
import { ref } from 'vue'
export const useUserStore = defineStore('course',()=>{
//登录函数，vue3写法
const logintags = ref(false)
  function login(username, password) {
    userlogin(username, password).then((res) => {
      logintags.value = true
    })
  }
  function clearUser(){
    usertags.value=false
    logintags.value=false
    
  }
  const usertags= ref(false)
   return{
    logintags,
    login,   
    usertags
    ,clearUser
   }

},{
  presist:true
})//presist:true表示持久化存储