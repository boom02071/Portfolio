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
            <li>관심 지역</li>
          </ol>
          <h2>My Heart House</h2>
        </div>
      </section>
      <!-- End Breadcrumbs -->

      <section class="inner-page col-md-auto">
        <my-heart-list></my-heart-list>
      </section>
    </main>
    <!-- End #main -->
  </div>
</template>

<script>
import MyHeartList from '../components/MyHeartList.vue';
export default {
  name: 'heart',
  components: {
    MyHeartList,
  },
};
</script>
