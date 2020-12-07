<template>
  <div class="App"></div>
</template>
<script>
import gmapsInit from '../utils/gmaps.js';
export default {
  name: 'App',
  data() {
    return {
      google: '',
      geocoder: '',
      map: '',
    };
  },
  props: {
    shoplist: Array,
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
        map.setZoom(12);
      });

      this.google = google;
      this.geocoder = geocoder;
      this.map = map;
    } catch (error) {
      console.error(error);
    }
  },
  watch: {
    shoplist: function() {
      this.clearMarkers();
      const google = this.google;
      const map = this.map;
      const geocoder = new google.maps.Geocoder();

      geocoder.geocode({ address: this.gugunName }, (results, status) => {
        if (status !== 'OK' || !results[0]) throw new Error(status);
        map.setCenter(results[0].geometry.location);
        map.setZoom(13.5);
      });

      for (let i = 0; i < 10; i++) {
        this.getLatLng(i, this.shoplist[i].rdnmAdr);
      }
    },
  },
  methods: {
    addMarkder: function(map, google, location, shopName) {
      var center = {
        lat: location.lat(),
        lng: location.lng(),
      };
      var marker = new google.maps.Marker({
        position: center,
        label: shopName,
        map: map,
        icon: require('@/assets/foodmap.png'),
      });
      var setThis = this;
      marker.addListener('click', function() {
        map.setCenter(marker.getPosition());
        setThis.selectApt(shopName);
        map.setZoom(15);
      });

      this.$markers.push(marker); //
    },
    selectApt: function(shopName) {
      this.$emit('select-shop', shopName);
    },
    getLatLng: function(index, address) {
      const google = this.google;
      const map = this.map;
      const geocoder = new google.maps.Geocoder();
      geocoder.geocode({ address: address }, (results, status) => {
        if (status !== 'OK' || !results[0]) throw new Error(status);
        this.addMarkder(
          map,
          google,
          results[0].geometry.location,
          this.shoplist[index].bizesNm
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
</script>
<style>
.App {
  height: 700px;
  margin-top: 20px;
  width: 100%;
}
</style>
