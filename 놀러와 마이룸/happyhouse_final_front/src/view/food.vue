<template>
  <div>
    <main id="main">
      <!-- ======= Breadcrumbs ======= -->
      <section id="breadcrumbs" class="breadcrumbs">
        <div class="container">
          <ol>
            <li>
              <a><router-link to="/">Home</router-link></a>
            </li>
            <li>Food Map</li>
          </ol>
          <h2>My Food Map</h2>
        </div>
      </section>
      <!-- End Breadcrumbs -->

      <section class="inner-page">
        <div class="container">
          <search @select-dong="sendShopCode"></search>
          <food-map-source
            :shoplist="shops"
            :gugunName="dongCode.gugunName"
            @select-shop="selectShop"
          ></food-map-source>
          <food-board :shopName="shopName" :flag="flag"></food-board>
          <write-view
            :shopName="shopName"
            @enroll-review="enrollReview"
          ></write-view>
        </div>
      </section>
    </main>
    <!-- End #main -->
  </div>
</template>

<script>
import Search from '@/components/search.vue';
import FoodMapSource from '../components/FoodMap.vue';
import FoodBoard from '@/components/FoodBoard.vue';
import WriteView from '@/components/WriteView.vue';
import axios from 'axios';

const API_KEY =
  'XBFGtQAfKlsyQV5Dq7Q7vXVtjNovP%2BDxVTc%2BoDQRyuxSWf79MrQpmaa3HFkTzba3cboHMNYV%2BtujO6PNTHLyLg%3D%3D';
// const API_URL =
//   'http://apis.data.go.kr/B553077/api/open/sdsc/storeListInUpjong';

const API_URL = 'http://apis.data.go.kr/B553077/api/open/sdsc/storeListInDong';

export default {
  name: 'food',
  components: {
    FoodMapSource,
    Search,
    FoodBoard,
    WriteView,
  },
  data() {
    return {
      shops: [],
      dongCode: '',
      shopName: '',
      flag: false,
    };
  },
  methods: {
    sendShopCode: function(dongCode) {
      this.dongCode = dongCode;
      const params = {
        divId: 'signguCd',
        key: dongCode.code,
        indsLclsCd: 'Q',
        type: 'json',
        serviceKey: decodeURIComponent(API_KEY),
      };
      axios
        .get(API_URL, {
          params,
        })
        .then((Response) => {
          //console.log(Response.data.body.items);
          this.shops = Response.data.body.items;
        });
    },
    selectShop: function(shopName) {
      this.shopName = shopName;
    },
    enrollReview: function(flag) {
      this.flag = flag;
    },
  },
};
</script>
