<template>
  <div>
    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top header-inner-pages">
      <div class="container d-flex align-items-center">
        <h1 class="logo mr-auto"><a href="/">놀러와마이룸</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html" class="logo mr-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

        <nav class="nav-menu d-none d-lg-block">
          <ul>
            <li>
              <a><router-link to="/">Home</router-link></a>
            </li>
            <li v-if="!getAccessToken">
              <a href="#" @click="showModal">Login</a>
            </li>
            <li v-if="!getAccessToken">
              <a><router-link to="/register">Register</router-link></a>
            </li>
            <li v-if="getAccessToken">
              <a href="#" @click="onClickLogout">Logout</a>
            </li>
            <li v-if="getAccessToken">
              <a><router-link to="/mypage">MyPage</router-link></a>
            </li>
            <li>
              <a><router-link to="/housedeal">방찾기</router-link></a>
            </li>
            <li>
              <a><router-link to="/heart">관심지역</router-link></a>
            </li>
            <li>
              <a><router-link to="food">먹거리</router-link></a>
            </li>
          </ul>
        </nav>
        <!-- .nav-menu -->
      </div>
    </header>
    <!-- End Header -->

    <!-- Modal -->
    <b-modal ref="my-modal" hide-footer title="HappyHouse 로그인">
      <h2>로그인</h2>
      <h6 v-if="test" id="loginerror">로그인 정보가 틀렸습니다</h6>
      <b-form>
        <div class="form-group">
          <label for="userid">ID:</label>
          <input
            type="id"
            class="form-control"
            id="userid"
            v-model="user.userid"
            placeholder="ID를 입력하세요"
          />
        </div>
        <div class="form-group">
          <label for="pwd">Password:</label>
          <input
            type="password"
            class="form-control"
            id="userpasswd"
            v-model="user.userpasswd"
            placeholder="Password를 입력하세요"
            @keypress.enter="login"
          />
        </div>
        <router-link id="erase" to="/register">
          <b-button type="button" variant="info" class="m-1" @click="hideModal">
            회원가입
          </b-button>
        </router-link>
        &nbsp;
        <b-button type="button" variant="primary" class="m-1" @click="login"
          >로그인
        </b-button>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import Vue from 'vue';
import VueRouter from 'vue-router';

import { mapGetters } from 'vuex';

Vue.use(VueRouter);

export default {
  name: 'vue-header',
  on() {
    console.log('하하');
  },
  data: function() {
    return {
      user: {
        userid: '',
        userpasswd: '',
      },
      message: '서버 이상현상으로 로그인이 불가능 합니다.',
      test: false,
    };
  },

  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName']),
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
    },
  },
  methods: {
    login: function() {
      this.$store
        .dispatch('LOGIN', this.user)
        .then(() => {
          this.$router.replace(`/mypage`);
          //console.log('일단여기까지');
          //console.log(this.$router.currentRoute.path);
          if (this.$router.currentRoute.path === '/mypage') {
            this.test = false;
            this.$refs['my-modal'].hide();
          } else {
            this.test = true;
          }
        })
        .catch(({ message }) => (this.msg = message));
    },
    onClickLogout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => this.$router.replace('/').catch(() => {}));
      alert('성공적으로 로그아웃을 하였습니다.');
    },
    showModal() {
      this.$refs['my-modal'].show();
    },
    hideModal() {
      this.$refs['my-modal'].hide();
    },
  },
};
</script>

<style>
#loginerror {
  color: red;
}
</style>
