<template>
  <div>
    <!-- ======= Footer ======= -->
    <footer id="footer">
      <div class="footer-top">
        <div class="container">
          <div class="row">
            <div class="col-lg-4 col-md-6">
              <div class="footer-info">
                <h3 id="footerlogo">놀러와마이룸</h3>
                <p>
                  광주 광산구 하남산단6번로 107<br />
                  대한민국<br /><br />
                  <strong>Phone:</strong> 010 9150 3141<br />
                  <strong>Email:</strong> shycompany@naver.com<br />
                </p>
                <div class="social-links mt-3">
                  <a href="https://twitter.com/" class="twitter"
                    ><i class="bx bxl-twitter"></i
                  ></a>
                  <a href="https://www.facebook.com/" class="facebook"
                    ><i class="bx bxl-facebook"></i
                  ></a>
                  <a href="https://www.instagram.com/" class="instagram"
                    ><i class="bx bxl-instagram"></i
                  ></a>
                  <a
                    href="https://skype.daesung.com/main.asp"
                    class="google-plus"
                    ><i class="bx bxl-skype"></i
                  ></a>
                  <a href="https://www.linkedin.com/" class="linkedin"
                    ><i class="bx bxl-linkedin"></i
                  ></a>
                </div>
              </div>
            </div>

            <div class="col-lg-2 col-md-6 footer-links">
              <h4>Useful Links</h4>
              <ul>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a><router-link to="/">Home</router-link></a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="https://www.zigbang.com/">직방</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="https://www.dabangapp.com/">다방</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a
                    href="https://new.land.naver.com/rooms?ms=35.1932967,126.8191267,15&a=APT:OPST:ABYG:OBYG:GM:OR:VL:DDDGG:JWJT:SGJT:HOJT&e=RETAIL&aa=SMALLSPCRENT&ad=true"
                    >네이버 부동산</a
                  >
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="https://www.r114.com/">부동산114</a>
                </li>
              </ul>
            </div>

            <div class="col-lg-2 col-md-6 footer-links">
              <h4>Our Services</h4>
              <ul>
                <li><i class="bx bx-chevron-right"></i> <a href="#">Vue</a></li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="#">Vuex</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="#">VueBootstrap</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i> <a href="#">VueRouter</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="#">SpringBoot</a>
                </li>
              </ul>
            </div>

            <div class="col-lg-4 col-md-6 footer-newsletter">
              <h4>구독하기</h4>
              <p>
                이메일을 적고 구독을 하세요!
              </p>
              <p>좋은 매물에 대한 정보를 드립니다.</p>
              <form action="" method="post">
                <input type="email" name="email" /><input
                  type="submit"
                  value="Subscribe"
                />
              </form>
            </div>
          </div>
        </div>
      </div>

      <div class="container">
        <div class="copyright">
          &copy; Copyright <strong><span>HappyHouse</span></strong
          >. All Rights Reserved
        </div>
        <div class="credits">
          <!-- All the links in the footer should remain intact. -->
          <!-- You can delete the links only if you purchased the pro version. -->
          <!-- Licensing information: https://bootstrapmade.com/license/ -->
          <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/day-multipurpose-html-template-for-free/ -->
          Designed by <a href="https://ssafy.com/">YR, JH</a>
        </div>
      </div>
    </footer>
    <!-- End Footer -->
  </div>
</template>
<script>
export default {
  name: 'vue-footer',
};
</script>
<style>
#footerlogo {
  text-align: center;
}
</style>
