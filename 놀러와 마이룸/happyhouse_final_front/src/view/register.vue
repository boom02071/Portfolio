<template>
  <main id="main">
    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <ol>
          <li>
            <a><router-link to="/">Home</router-link></a>
          </li>
          <li>회원가입</li>
        </ol>
        <h2>회원가입</h2>
      </div>
    </section>
    <!-- End Breadcrumbs -->
    <make-account></make-account>
  </main>
  <!-- End #main -->
</template>

<script>
import MakeAccount from '../components/MakeAccount.vue';
export default {
  name: 'register',
  components: {
    MakeAccount,
  },
};
</script>
