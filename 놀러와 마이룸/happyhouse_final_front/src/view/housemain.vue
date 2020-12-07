<template>
  <div>
    <section id="hero" class="d-flex align-items-center">
      <div
        class="container position-relative"
        data-aos="fade-up"
        data-aos-delay="500"
      >
        <h1>놀러와마이룸</h1>
        <h2>원하는 집을 찾자.</h2>
        <form action="https://map.naver.com/v5/search/" method="get">
          <input type="text" name="" />
          <input type="submit" value="검색하기" />
        </form>
        <h2></h2>
        <router-link
          id="gotomap"
          to="/housedeal"
          class="btn-get-started scrollto"
          role="button"
          >지도로 찾아보기</router-link
        >
      </div>
    </section>
    <!-- End Hero -->
  </div>
</template>

<script>
export default {
  name: 'housedeal',
  components: {},
};
</script>
