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
        path:'/analyze',
        name: 'analyze',
        component: () => import('../views/analyseview.vue'),
        children: [
          {
             path:'analyzedetail6',
              name: 'analyzedetail6',
              component: () => import('../views/analyze/analyze6.vue')
          },
          {
            path: 'analyzedetail2', 
            name: 'analyzedetail2',
            component: () => import('../views/analyze/analyze2.vue')
          },
          {
            path: 'analyzedetail3', 
            name: 'analyzedetail3',
            component: () => import('../views/analyze/analyze3.vue')
          },
          {
            path: 'analyzedetail4', 
            name: 'analyzedetail4',
            component: () => import('../views/analyze/analyze4.vue')
          },
          {
            path: 'analyzedetail5', 
            name: 'analyzedetail5',
            component: () => import('../views/analyze/analyze5.vue')
          },
          {
            path: 'analyzedetail1',
            name: 'analyzedetail1',
            component: () => import('../views/analyze/analyze1.vue')
          }
        ]
      },

    ]
  })
  
  export default router