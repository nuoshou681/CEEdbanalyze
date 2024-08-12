import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
      {
        path: '/',
        name: 'home',
        component: () => import('../views/homeview.vue') 
      },
      {
        path:'/major',
        name: 'major',
        component: () => import('../views/majorview.vue')
      },
      {
        path:'/school',
        name: 'school',
        component: () => import('../views/schoolview.vue'),
        children: [
          {
            path:'detail',
            name: 'detail',
            component: () => import('../views/school/schooldetail/basicinformation.vue')
      },
      {
        path:'schoolmajor',
        name: 'schoolmajor',
        component: () => import('../views/school/schooldetail/majorinformation.vue')
      },
      {
        path:'scoreplan',
        name: 'scoreplan',
        component: () => import('../views/school/schooldetail/scoreandplan.vue')
      }
    ]},
      {
        path:'/analyse',
        name: 'analyse',
        component: () => import('../views/analyseview.vue')
      },

    ]
  })
  
  export default router