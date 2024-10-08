// 初始化一个用户pinia
import { defineStore } from 'pinia'
import { ref } from 'vue'
export const useUserStore = defineStore('course',()=>{
//登录函数，vue3写法
const logintags = ref(false)
const userID = ref('')
const  score=ref('')
const  rank=ref('') 
const  major1=ref('')
const  major2=ref('')
const  major3=ref('')
const analyzetags = ref(false)
const usertags= ref(false)
  function clearUser(){
    usertags.value=false
    logintags.value=false
  }
  function userinformation(score_1,rank_1,major_1,major_2,major_3){
    score.value=score_1
    rank.value=rank_1
    major1.value=major_1
    major2.value=major_2
    major3.value=major_3
  }
   return{
    logintags, 
    usertags
    ,clearUser
    ,userID
    ,analyzetags
    ,major1
    ,major2
    ,major3
    ,score
    ,rank
   }

},{
    persist: {
      enabled: true,
      strategies: [
        {
          key: 'user',
          storage: localStorage,
        },
      ],
    },
})