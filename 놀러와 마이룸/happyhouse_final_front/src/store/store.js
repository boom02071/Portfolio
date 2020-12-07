import Vue from 'vue';
import Vuex from 'vuex';
import http from '@/utils/http-common.js';

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  state: {
    accessToken: null, // 토큰 값 주어짐
    userId: '', // 아이디와
    userName: '', //이름
  },
  getters: {
    getAccessToken(state) {
      //토큰 받아오기
      return state.accessToken;
    },
    getUserId(state) {
      // 아이디 가져오기
      return state.userId;
    },
    getUserName(state) {
      // 이름 가져오기
      return state.userName;
    },
  },
  mutations: {
    LOGIN(state, payload) {
      // 로그인 시 변화
      state.accessToken = payload['auth-token'];
      state.userId = payload['user-id'];
      state.userName = payload['user-name'];
    },
    LOGOUT(state) {
      // 로그아웃시 초기화
      state.accessToken = null;
      state.userId = '';
      state.userName = '';
    },
  },
  actions: {
    LOGIN(context, user) {
      //로그인 후 행동 변화
      return http
        .post(`${SERVER_URL}/user/confirm/login`, user) // 서버에서 받아옴
        .then((response) => {
          context.commit('LOGIN', response.data);
          http.defaults.headers.common[
            'auth-token'
          ] = `${response.data['auth-token']}`;
        });
    },
    LOGOUT(context) {
      //로그아웃 시 행동 변화
      context.commit('LOGOUT');
      http.defaults.headers.common['auth-token'] = undefined;
    },
  },
  modules: {},
});
