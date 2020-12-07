<template>
  <div>
    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <ol>
          <li><router-link to="/">Home</router-link></li>
          <li>방찾기</li>
        </ol>
        <h2>Find My House</h2>
      </div>
    </section>
    <!-- <section id="hero2"></section> -->
    <main id="main">
      <!-- End Breadcrumbs -->

      <section class="inner-page">
        <search @select-dong="sendDongCode"></search>
        <div class="row">
          <div class="col-1"></div>
          <div class="col-10">
            <div class="row">
              <div class="col">
                <apt-list :aptName="aptName"></apt-list>
              </div>
              <div class="col-10">
                <apt-map
                  :aptlist="apts"
                  :gugunName="dongCode.gugunName"
                  @select-apt="selectApt"
                ></apt-map>
              </div>
            </div>
          </div>
          <div class="col-1"></div>
        </div>
      </section>
    </main>
    <!-- End #main -->
  </div>
</template>

<script>
import axios from 'axios';
import AptList from '@/components/AptList.vue';
import AptMap from '@/components/map.vue';
import Search from '@/components/search.vue';

//const API_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
const API_KEY =
  'XBFGtQAfKlsyQV5Dq7Q7vXVtjNovP%2BDxVTc%2BoDQRyuxSWf79MrQpmaa3HFkTzba3cboHMNYV%2BtujO6PNTHLyLg%3D%3D';
const API_URL =
  'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';
const ymd = ['202011', '202010', '202009'];
export default {
  name: 'housedeal',
  components: {
    AptList,
    AptMap,
    Search,
  },

  data() {
    return {
      dongCode: '',
      apts: [],
      aptName: '',
    };
  },
  methods: {
    sendDongCode: function(dongCode) {
      this.dongCode = dongCode;
      var temp2 = [];
      for (var i = 0; i < 3; i++) {
        const params = {
          LAWD_CD: this.dongCode.code,
          DEAL_YMD: ymd[i],
          serviceKey: decodeURIComponent(API_KEY),
        };
        axios
          .get(API_URL, {
            params,
          })
          .then((response) => {
            //console.log(response);
            //console.log(response.data.response.body)
            //console.log(response.data.response.body.items.item);
            var temp = response.data.response.body.items.item;
            for (var j = 0; j < temp.length; j++) temp2.push(temp[j]);
            //console.log(this.apts);
          });
      }

      setTimeout(() => {
        this.apts = temp2;
        //console.log(this.apts);
      }, 1000);
    },
    selectApt: function(aptName) {
      this.aptName = aptName;
    },
  },
};
</script>
