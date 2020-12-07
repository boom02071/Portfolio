<template>
  <main id="main">
    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <ol>
          <li>
            <a><router-link to="/">Home</router-link></a>
          </li>
          <li>회원정보수정</li>
        </ol>
        <h2>회원정보수정</h2>
      </div>
    </section>
    <!-- End Breadcrumbs -->
    <change-name></change-name>
  </main>
  <!-- End #main -->
</template>

<script>
import ChangeName from '../components/ChangeName.vue';
export default {
  name: 'updatepage',
  components: {
    ChangeName,
  },
};
</script>
