<template>
  <div style="margin-top: 20px;">
    <p class="title">{{ aptName }}</p>
    <div
      style="overflow: auto; height: 700px; margin-top: 20px;"
      class="scrollspy-example"
    >
      <ul
        class="content list-group"
        v-for="(apt, index) in selectapts"
        :key="index"
      >
        <li class="list-group-item">
          <img :src="img[index % 5]" alt="" /><br />
          보증금액 : {{ apt.보증금액 }}만원<br />
          월세금액 : {{ apt.월세금액 }}만원<br />
          날짜 : {{ apt.월 }}/{{ apt.일 }}<br />
          전용면적 : {{ apt.전용면적 }}<br />
          층 : {{ apt.층 }}<br />
          <button @click="addHeartList(apt)" class="btn btn-outline-danger">
            <svg
              width="1em"
              height="1em"
              viewBox="0 0 16 16"
              class="bi bi-heart-fill"
              fill="currentColor"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fill-rule="evenodd"
                d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
              /></svg
            >관심주기
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import Axios from 'axios';
import http from '@/utils/http-common.js';
const API_KEY =
  'XBFGtQAfKlsyQV5Dq7Q7vXVtjNovP%2BDxVTc%2BoDQRyuxSWf79MrQpmaa3HFkTzba3cboHMNYV%2BtujO6PNTHLyLg%3D%3D';
const API_URL =
  'http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent';
const ymd = ['202011', '202010', '202009'];
export default {
  name: 'AptList',
  data() {
    return {
      apts: [],
      selectapts: [],
      img: [
        require('@/assets/room1.jpg'),
        require('@/assets/room2.jpg'),
        require('@/assets/room3.jpg'),
        require('@/assets/room4.jpg'),
        require('@/assets/room5.jpg'),
      ],
    };
  },
  props: {
    aptName: String,
  },
  watch: {
    aptName: function() {
      this.selectapts = [];
      this.getDealList();
    },
  },
  methods: {
    getDealList: function() {
      for (var i = 0; i < 3; i++) {
        const params = {
          LAWD_CD: '11110', //this.dongCode,
          DEAL_YMD: ymd[i],
          serviceKey: decodeURIComponent(API_KEY),
        };
        Axios.get(API_URL, {
          params,
        }).then((response) => {
          this.apts = response.data.response.body.items.item;
          for (let i = 0; i < this.apts.length; i++) {
            if (this.aptName === this.apts[i].아파트)
              this.selectapts.push(this.apts[i]);
          }
          //console.log(this.selectapts);
        });
      }
    },
    addHeartList: function(apt) {
      //아파트 리스트에 추가하기
      http
        .post('/house', {
          userid: 'ssafy',
          aptname: apt.아파트,
          guarantee: apt.보증금액,
          rent: apt.월세금액,
          date: apt.월 + '/' + apt.일,
          fsize: apt.전용면적,
          floor: apt.층,
        })
        .then(({ data }) => {
          let msg = '관심 목록 등록에 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '관심목록에 등록되었습니다.';
          }
          alert(msg);
        });
    },
  },
};
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Single+Day&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap');
.title {
  font-family: 'Single Day', cursive;
  font-size: 40px;
  text-align: center;
}
.content {
  text-align: left;
  font-family: 'Nanum Myeongjo', serif;
}
img {
  width: 200px;
}
button {
  margin-top: 10px;
  margin-left: 10%;
  font-family: 'Nanum Myeongjo', serif;
}
</style>
