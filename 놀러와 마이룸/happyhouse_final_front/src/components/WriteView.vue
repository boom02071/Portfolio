<template>
  <div>
    <svg
      width="1em"
      height="1em"
      viewBox="0 0 16 16"
      class="bi bi-pencil-square"
      fill="currentColor"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"
      />
      <path
        fill-rule="evenodd"
        d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"
      />
    </svg>
    <h1>리뷰 작성하기</h1>
    <form class="">
      <div class="form-group">
        <label for="exampleFormControlInput1">제목</label>
        <input
          type="text"
          class="form-control"
          placeholder="제목"
          v-model="title"
        />
      </div>
      <div class="form-group">
        <label for="exampleFormControlSelect1">별점</label>
        <select class="form-control" v-model="star">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5" selected> 5</option>
        </select>
      </div>
      <div class="form-group">
        <label for="exampleFormControlTextarea1">내용</label>
        <textarea
          class="form-control"
          rows="3"
          placeholder="내용을 입력해주세요."
          v-model="content"
        >
        </textarea>
      </div>
    </form>
    <div class="form-group">
      <button class="btn btn-danger" @click="sendReview">등록</button>
    </div>
  </div>
</template>
<script>
import http from '@/utils/http-common.js';
import { mapGetters } from 'vuex';
export default {
  name: 'WriteView',
  data() {
    return {
      writer: '',
      title: '',
      content: '',
      star: '',
      flag: false,
    };
  },
  props: {
    shopName: String,
  },
  computed: {
    ...mapGetters(['getUserName']),
  },
  methods: {
    sendReview: function() {
      //리뷰 추가하기

      http
        .post('/house/food', {
          writer: this.getUserName === '' ? 'ssafy' : this.getUserName,
          title: this.title,
          content: this.content,
          star: this.star,
          shop: this.shopName,
        })
        .then(({ data }) => {
          let msg = '후기 등록에 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '후기에 등록되었습니다.';
          }
          alert(msg);
        });
      if (this.flag == true) this.flag = false;
      else this.flag = true;
      this.$emit('enroll-review', this.flag);
    },
  },
};
</script>
<style>
button {
  margin-left: 0px;
}
</style>
