import {createRouter, createWebHistory} from "vue-router";
import Layout from '@/layout/index.vue';

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: ()=> import('@/views/login.vue'),
        meta: { breadcrumb: 'Login' }
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('@/views/register.vue'),
        meta: { breadcrumb: 'Register' }
    },
    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        children: [
            {
                path: '/dashboard',
                name: 'Dashboard',
                component: ()=> import("@/views/dashboard.vue"),
                meta: { breadcrumb: 'Dashboard' }
            },
            {
                path: '/file',
                name: 'File',
                component: () => import('@/views/file.vue'),
                meta: { breadcrumb: 'File' }
            },
            {
                path: '/folder',
                name: 'Folder',
                component: () => import('@/views/folder.vue'),
                meta: { breadcrumb: 'Folder' }
            },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;