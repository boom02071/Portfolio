<template>
  <div class="App"></div>
</template>

<script>
import gmapsInit from '../utils/gmaps.js';

export default {
  name: 'App',
  data() {
    return {
      center: {
        lat: 37.49821,
        lng: 126.890134,
      },
      google: '',
      geocoder: '',
      map: '',
    };
  },

  props: {
    aptlist: Array,
    gugunName: String,
  },

  async mounted() {
    this.$markers = []; //
    try {
      const google = await gmapsInit();
      const geocoder = new google.maps.Geocoder();
      const map = new google.maps.Map(this.$el);

      geocoder.geocode({ address: '서울시' }, (results, status) => {
        if (status !== 'OK' || !results[0]) throw new Error(status);
        map.setCenter(results[0].geometry.location);
        //map.fitBounds(results[0].geometry.viewport);
        map.setZoom(14);
      });

      this.google = google;
      this.geocoder = geocoder;
      this.map = map;
    } catch (error) {
      console.error(error);
    }
  },

  watch: {
    aptlist: function() {
      this.clearMarkers();
      const google = this.google;
      const map = this.map;
      const geocoder = new google.maps.Geocoder();

      geocoder.geocode({ address: this.gugunName }, (results, status) => {
        if (status !== 'OK' || !results[0]) throw new Error(status);
        map.setCenter(results[0].geometry.location);
        //map.fitBounds(results[0].geometry.viewport);
        map.setZoom(14);
      });

      for (let i = 0; i < 10; i++) {
        this.getLatLng(i, this.aptlist[i].법정동 + ' ' + this.aptlist[i].지번);
      }
      setTimeout(() => {
        for (let i = 10; i < 20; i++) {
          this.getLatLng(
            i,
            this.aptlist[i].법정동 + ' ' + this.aptlist[i].지번
          );
        }
      }, 11000);

      // setTimeout(() => {
      //   console.log('현재 num = ' + num);
      //   for (let i = num; i < this.aptlist.length || i < num + 5; i++) {
      //     this.getLatLng(
      //       i,
      //       this.aptlist[i].법정동 + ' ' + this.aptlist[i].지번
      //     );
      //   }

      //   setTimeout(() => {
      //     num = num + 5;
      //   }, 1000);
      // }, 1500);
    },
  },
  methods: {
    addMarkder: function(map, google, location, aptName) {
      var center = {
        lat: location.lat(),
        lng: location.lng(),
      };
      var marker = new google.maps.Marker({
        position: center,
        label: aptName,
        map: map,
      });
      var setThis = this;
      marker.addListener('click', function() {
        //map.setZoom(17);
        map.setCenter(marker.getPosition());
        setThis.selectApt(aptName);
      });

      this.$markers.push(marker); //
    },
    selectApt: function(aptName) {
      this.$emit('select-apt', aptName);
    },
    getLatLng: function(index, address) {
      const google = this.google;
      const map = this.map;
      const geocoder = new google.maps.Geocoder();

      geocoder.geocode({ address: address }, (results, status) => {
        if (status !== 'OK' || !results[0]) throw new Error(status);
        //console.log(results[0].geometry.location.lat());
        this.addMarkder(
          map,
          google,
          results[0].geometry.location,
          this.aptlist[index].아파트
        );
      });
    },
    clearMarkers() {
      for (let i = 0; i < this.$markers.length; i++) {
        this.$markers[i].setMap(null);
      }
    },
  },
};

//function selectApt(AptName) {}
</script>

<style>
.App {
  height: 700px;
  margin-top: 20px;
  width: 100%;
}
</style>
