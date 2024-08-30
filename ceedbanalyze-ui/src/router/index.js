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
            component: () => import('../views/school/schoolindex.vue')
      }
    ]},
      {
        path:'/analyse',
        name: 'analyse',
        component: () => import('../views/analyseview.vue'),
        children: [
          {
             path:'analyzedetail',
              name: 'analyzedetail',
              component: () => import('../views/analyse/analyze1.vue')
          }]
      },

    ]
  })
  
  export default router