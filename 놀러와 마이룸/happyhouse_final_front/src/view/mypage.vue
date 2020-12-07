<template>
  <main id="main">
    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <ol>
          <li>
            <a><router-link to="/">Home</router-link></a>
          </li>
          <li>내 정보보기</li>
        </ol>
        <h2>HappyHouse는 당신을 환영합니다.</h2>
      </div>
    </section>
    <!-- End Breadcrumbs -->

    <section class="inner-page">
      <div class="container">
        <b-container class="mt-4" v-if="user">
          <b-row>
            <b-col></b-col>
            <b-col cols="8">
              <b-jumbotron id="center-jumbo">
                <template #header>My Page</template>

                <template #lead>
                  내 정보 확인페이지입니다.
                </template>

                <hr class="my-4" />

                <b-container class="mt-4">
                  <b-row>
                    <b-col cols="2"></b-col>
                    <b-col cols="2" align-self="end">아이디</b-col
                    ><b-col cols="4" align-self="start">{{
                      user.userid
                    }}</b-col>
                    <b-col cols="2"></b-col>
                  </b-row>
                  <b-row>
                    <b-col cols="2"></b-col>
                    <b-col cols="2" align-self="end">이름</b-col
                    ><b-col cols="4" align-self="start">{{
                      user.username
                    }}</b-col>
                    <b-col cols="2"></b-col>
                  </b-row>
                  <b-row>
                    <b-col cols="2"></b-col>
                    <b-col cols="2" align-self="end">이메일</b-col
                    ><b-col cols="4" align-self="start">{{ user.email }}</b-col>
                    <b-col cols="2"></b-col>
                  </b-row>
                </b-container>
                <hr class="my-4" />

                <b-button
                  variant="primary"
                  href="#"
                  class="mr-1"
                  @click="GoUpdate"
                >
                  정보수정</b-button
                >
                <b-button variant="danger" href="#" @click="onClickDelete"
                  >회원탈퇴</b-button
                >
              </b-jumbotron>
            </b-col>
            <b-col></b-col>
          </b-row>
        </b-container>
        <!-- <h2>내정보보기</h2>
    이름 : {{ user.username }}<br />
    아이디 : {{ user.userid }}<br />
    이메일 : {{ user.email }}<br />
    주소 : {{ user.address }}<br />
    가입일 : {{ user.joindate }}<br />
  </div> -->
      </div>
    </section>
  </main>
  <!-- End #main -->
</template>

<script>
import http from '@/utils/http-common.js';
import { mapGetters } from 'vuex';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'mypage',
  components: {},
  created() {
    http
      .get(`${SERVER_URL}/user/info`)
      .then((response) => {
        this.user = response.data.user;
      })
      .catch(() => {
        this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/'));
      });
  },
  data() {
    return {
      isLogin: false,
      user: null,
    };
  },
  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName']),
  },
  methods: {
    onClickLogout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => this.$router.replace('/').catch(() => {}));
    },
    onClickDelete() {
      http
        .delete(`${SERVER_URL}/user/delete/${this.user.userid}`)
        .then(({ data }) => {
          let msg = '계정정보를 삭제하는 데 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '계정삭제 완료되었습니다.';
          }
          alert(msg);
          this.onClickLogout();
        });
    },
    GoUpdate() {
      this.$router.replace('/update');
    },
  },
};
</script>
<style>
#center-jumbo {
  text-align: center;
}
</style>
