import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BabyDetail from '../views/BabyDetail.vue'
import Member from '../views/Member.vue'
import Search from '../views/Search.vue'
import Cart from '../views/Cart.vue'
import Register from '../views/Register.vue'
import Test from '../views/Test.vue'
import Profile from '../views/Profile.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'home', component: HomeView },
    { path: '/babies/:id', name: 'detail', component: BabyDetail },
    { path: '/member', name: 'member', component: Member },
    { path: '/search', name: 'search', component: Search},
    { path: '/cart', name: 'cart', component: Cart },
    { path: '/register', name: 'register', component: Register },
    { path: '/test', name: 'test', component: Test },
    { path: '/profile', name: 'profile', component: Profile }
  ]
})

export default router
