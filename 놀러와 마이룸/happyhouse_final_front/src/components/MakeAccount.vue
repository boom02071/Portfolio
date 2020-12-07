<template>
  <section class="inner-page">
    <div class="container">
      <b-container class="bv-example-row">
        <b-row>
          <b-col></b-col>
          <b-col cols="8">
            <b-form>
              <b-form-group label="아이디:" label-for="userid">
                <b-form-input
                  id="userid"
                  v-model="user.userid"
                  required
                  placeholder="사용하실 아이디를 입력해주세요"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="비밀번호:" label-for="userpasswd">
                <b-form-input
                  type="password"
                  id="userpasswd"
                  v-model="user.userpasswd"
                  required
                  placeholder="사용하실 비밀번호를 입력해주세요"
                >
                </b-form-input>
              </b-form-group>
              <b-form-group label="닉네임:" label-for="username">
                <b-form-input
                  id="username"
                  v-model="user.username"
                  required
                  placeholder="사용하실 닉네임을 입력해주세요"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이메일:" label-for="email">
                <b-form-input
                  type="email"
                  id="email"
                  v-model="user.email"
                  required
                  placeholder="연락가능한 이메일을 입력해주세요"
                ></b-form-input>
              </b-form-group>
              <b-button
                type="button"
                variant="success"
                class="m-1"
                @click="Regist"
              >
                회원가입
              </b-button>
            </b-form>
          </b-col>
          <b-col></b-col>
        </b-row>
      </b-container>
    </div>
  </section>
</template>

<script>
import http from '@/utils/http-common.js';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  name: 'MakeAccount',
  components: {},
  data: function() {
    return {
      user: {
        userid: '',
        userpasswd: '',
        username: '',
        email: '',
      },
      message: '',
    };
  },
  methods: {
    Regist: function() {
      //계정 추가하기
      console.log('여긴 들어왔어!');
      console.log(this.user.userid);
      http
        .post(`${SERVER_URL}/user/regist`, {
          userid: this.user.userid,
          userpasswd: this.user.userpasswd,
          username: this.user.username,
          email: this.user.email,
        })
        .then(({ data }) => {
          let msg = '새로운 계정을 추가하는 데 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '회원가입 완료되었습니다.';
          }
          alert(msg);
          this.login();
        });
    },
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
  },
};
</script>
