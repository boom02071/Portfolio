<template>
  <div>
    <div class="container">
      <div class="form-row" id="serchbar">
        <div class="form-group col-md-3">
          <select v-model="selectsido" class="form-control form-control-lg">
            <option disabled value="">시/도/광역시</option>
            <option :value="sido" v-for="(sido, index) in sido" :key="index">
              {{ sido.sidoname }}</option
            >
          </select>
        </div>

        <div class="form-group col-md-3">
          <select v-model="selectgugun" class="form-control form-control-lg">
            <option disabled value="">시/구/군</option>
            <option :value="gugun" v-for="(gugun, index) in gugun" :key="index">
              {{ gugun.gugunName }}</option
            >
          </select>
        </div>

        <!-- <div class="form-group col-md-3">
          <select v-model="selectdong" class="form-control form-control-lg">
            <option disabled value="">동</option>
            <option :value="dong" v-for="(dong, index) in dong" :key="index">
              {{ dong.dongName }}</option
            >
          </select>
        </div> -->
        <div>
          <button class="btn btn-danger" @click="selectDong(selectgugun)">
            검색
          </button>
        </div>
        <div>
          <h3 class="msg">{{ msg }}</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/utils/http-common.js';
export default {
  name: 'SearchBar',
  data() {
    return {
      sido: [],
      selectsido: '',
      gugun: [],
      selectgugun: '',
      msg: '',
      // dong: [],
      // selectdong: '',
    };
  },
  created() {
    http.get('/house').then(({ data }) => {
      this.sido = data;
    });
  },
  watch: {
    selectsido: function() {
      http.get('/house/gugun/' + this.selectsido.code).then(({ data }) => {
        this.gugun = data;
      });
    },
    // selectgugun: function() {
    //   http.get('/house/dong/' + this.selectgugun.code).then(({ data }) => {
    //     this.dong = data;
    //   });
    // },
  },
  methods: {
    selectDong: function(selectDong) {
      this.msg = '검색중';
      this.$emit('select-dong', selectDong);
      setTimeout(() => {
        this.msg = '';
      }, 14000);
    },
  },
};
</script>

<style>
.btn {
  width: 150px;
  height: 60px;
  margin-top: -3px;
}
#serchbar {
  margin-left: 25%;
}
.msg {
  margin-left: 10px;
}
</style>
