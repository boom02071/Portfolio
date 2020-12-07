import Vue from 'vue';
import VueRouter from 'vue-router';
import HouseDeal from '@/view/housedeal.vue';
import Heart from '@/view/heart.vue';
import HouseMain from '@/view/housemain.vue';
import Mypage from '@/view/mypage.vue';
import store from '@/store/store';
import Register from '@/view/register.vue';
import Food from '@/view/food.vue';
import UpdatePage from '@/view/updatepage.vue';

const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (store.getters.getAccessToken) {
    return next();
  } else next('/login' + nextRoute);
};

Vue.use(VueRouter);
export const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'housemain',
      component: HouseMain,
    },
    {
      path: '/housedeal',
      name: 'housedeal',
      component: HouseDeal,
    },
    {
      path: '/heart',
      name: 'heart',
      component: Heart,
    },
    {
      path: '/login/:nextRoute',
      name: 'LoginNextRoute',
      component: HouseMain,
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: Mypage,
      beforeEnter: requireAuth(),
    },
    {
      path: '/register',
      name: 'register',
      component: Register,
    },
    {
      path: '/food',
      name: 'food',
      component: Food,
    },
    {
      path: '/update',
      name: 'update',
      component: UpdatePage,
      beforeEnter: requireAuth(),
    },
  ],
});

const router2 = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  router,
});

export default router2;
