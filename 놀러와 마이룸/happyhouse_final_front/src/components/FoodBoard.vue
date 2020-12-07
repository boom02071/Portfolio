<template>
  <div>
    <section class="resume-section" id="experience">
      <h5>
        <svg
          width="1em"
          height="1em"
          viewBox="0 0 16 16"
          class="bi bi-egg-fried"
          fill="currentColor"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill-rule="evenodd"
            d="M13.665 6.113a1 1 0 0 1-.667-.977L13 5a4 4 0 0 0-6.483-3.136 1 1 0 0 1-.8.2 4 4 0 0 0-3.693 6.61 1 1 0 0 1 .2 1 4 4 0 0 0 6.67 4.087 1 1 0 0 1 1.262-.152 2.5 2.5 0 0 0 3.715-2.905 1 1 0 0 1 .341-1.113 2.001 2.001 0 0 0-.547-3.478zM14 5c0 .057 0 .113-.003.17a3.001 3.001 0 0 1 .822 5.216 3.5 3.5 0 0 1-5.201 4.065 5 5 0 0 1-8.336-5.109A5 5 0 0 1 5.896 1.08 5 5 0 0 1 14 5z"
          />
          <circle cx="8" cy="8" r="3" />
        </svg>
      </h5>
      <h1 class="mb-5">
        {{ shopName }}
      </h1>
      <div v-if="reviews.length">
        <div
          class="resume-section-content"
          v-for="(review, index) in reviews"
          :key="index"
        >
          <div
            class="d-flex flex-column flex-md-row justify-content-between mb-5"
          >
            <div class="flex-grow-1">
              <h3 class="mb-0">
                <svg
                  width="1em"
                  height="1em"
                  viewBox="0 0 16 16"
                  class="bi bi-award-fill"
                  fill="currentColor"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M8 0l1.669.864 1.858.282.842 1.68 1.337 1.32L13.4 6l.306 1.854-1.337 1.32-.842 1.68-1.858.282L8 12l-1.669-.864-1.858-.282-.842-1.68-1.337-1.32L2.6 6l-.306-1.854 1.337-1.32.842-1.68L6.331.864 8 0z"
                  />
                  <path
                    d="M4 11.794V16l4-1 4 1v-4.206l-2.018.306L8 13.126 6.018 12.1 4 11.794z"
                  />
                </svg>
                {{ review.title }}
              </h3>
              <div class="subheading mb-3 text-primary">
                {{ review.writer }}
              </div>
              <p>
                {{ review.content }}
              </p>
            </div>
            <div class="flex-shrink-0">
              <span class="text-danger">{{ star(review.star) }}</span>
            </div>
          </div>
          <hr v-if="review.star > 0" />
        </div>
      </div>
      <div v-else>
        <h4>등록된 리뷰가 없습니다. 지금 리뷰를 작성해보세요.</h4>
      </div>
    </section>
  </div>
</template>
<script>
import http from '@/utils/http-common.js';
export default {
  name: 'FoodBoard',
  data() {
    return {
      reviews: [],
    };
  },
  props: {
    shopName: String,
    flag: Boolean,
  },
  watch: {
    // reviews: function() {
    //   http.get('/house/food/' + this.shopName).then(({ data }) => {
    //     this.reviews = data;
    //   });
    // },
    flag: function() {
      setTimeout(() => {
        console.log('업데이트중');
        this.getReview();
      }, 1000);
    },
    shopName: function() {
      http.get('/house/food/' + this.shopName).then(({ data }) => {
        this.reviews = data;
      });
    },
  },
  methods: {
    star: function(num) {
      var str = '';
      for (var i = 0; i < num; i++) str += '★';
      return str;
    },
    getReview: function() {
      http.get('/house/food/' + this.shopName).then(({ data }) => {
        this.reviews = data;
      });
    },
  },
};
</script>
<style>
h3 {
  text-align: left;
}
div {
  text-align: left;
}
.subheading {
  margin-top: 7px;
}
</style>
