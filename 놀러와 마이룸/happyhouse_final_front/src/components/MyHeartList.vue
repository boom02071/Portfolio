<template>
  <div class="wrap">
    <div class="card-deck" v-if="list.length">
      <div class="card" v-for="(apt, index) in list" :key="index">
        <img :src="img[index % 5]" class="card-img-top" alt="" />
        <div class="card-body">
          <h5 class="card-title">{{ apt.aptname }}</h5>
          <p class="card-text">
            보증금액 : {{ apt.guarantee }}만원<br />
            월세금액 : {{ apt.rent }}만원<br />
            날짜 : {{ apt.date }}<br />
            전용면적 : {{ apt.fsize }}<br />
            층 : {{ apt.floor }}<br />
          </p>
          <p class="card-text">
            <!-- <small class="text-muted">Last updated 3 mins ago</small> -->
            <button
              @click="deleteList(apt.heartid)"
              class="btn btn-outline-danger"
            >
              <svg
                width="1em"
                height="1em"
                viewBox="0 0 16 16"
                class="bi bi-trash"
                fill="currentColor"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
                />
                <path
                  fill-rule="evenodd"
                  d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
                /></svg
              >삭제하기
            </button>
          </p>
        </div>
      </div>
    </div>
    <div v-else>
      저장된 관심 목록이 없습니다.
    </div>
  </div>
</template>

<script>
import http from '@/utils/http-common.js';
export default {
  name: 'MyHeartList',
  data() {
    return {
      img: [
        require('@/assets/room1.jpg'),
        require('@/assets/room2.jpg'),
        require('@/assets/room3.jpg'),
        require('@/assets/room4.jpg'),
        require('@/assets/room5.jpg'),
      ],
      userid: 'ssafy',
      list: '',
      change: 0,
    };
  },
  created() {
    //사용자 id 가져오기
    http.get('/house/heart/' + this.userid).then(({ data }) => {
      this.list = data;
    });
  },
  watch: {
    change: function() {
      http.get('/house/heart/' + this.userid).then(({ data }) => {
        this.list = data;
      });
    },
  },
  methods: {
    deleteList: function(heartid) {
      //관심목록 리스트 삭제하기
      http.delete('/house/heart/' + heartid).then(({ data }) => {
        let msg = '삭제 처리시 문제가 발생했습니다.';
        if (data === 'success') {
          msg = '삭제가 완료되었습니다.';
        }
        alert(msg);
        //this.$router.push(this.$router.currentRoute).catch(() => {});
        //this.$router.go(this.$router.currentRoute);
        this.change += 1;
      });
    },
  },
};
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
.card-title {
  font-family: 'Stylish', sans-serif;
}
.card {
  max-width: 300px;
  min-width: 300px;
}
img {
  height: 200px;
}
</style>
