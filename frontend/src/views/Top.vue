<template>
    <section>
        <Header />
        <!-- <div class="top">
            <img src="@/assets/book.jpg">
        </div> -->
        <div class="main_container">
            <div class="main_inner">
                <div class="main_text">
                    <h1>あなただけの本棚を<br>作りませんか？</h1>
                    <p>My Bookshelfは読んだ本の感想をいつまでも保存します</p>
                </div>
                <div class="main_form">
                    <div>
                        <router-link to="/login">ログイン</router-link>
                    </div>
                    <div>
                        <router-link to="/register">新規登録</router-link>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
import Header from '../components/Header.vue'

export default {
  name: 'Home',
  components: {
      Header
  },
  created() {
      if(localStorage.getItem('uid')) {
        this.$router.push('/Home')
      }
  }
}
</script>

<style scoped>
    section {
        height: 100vh;
        background-image: url("~@/assets/book.jpg");
        background-size: cover;
        color: #fffafa;
        /* text-shadow: 1px 1px 0  #ffdab9,-1px 1px 0  #ffdab9,1px -1px 0  #ffdab9,-1px -1px 0 #ffdab9; */
        position: relative;
    }
    .main_container {
        position: absolute;
        right: 10%;
        top: 40%;
        display: flex;
    }
    .main_form {
        display: flex;
        justify-content: center;
    }
    .main_form div {
        padding: 20px;
    }
    .main_form a {
        display: inline-block;
        padding: 0.5em 1em;
        text-decoration: none;
        background: #ffe9c7;
        color: #5e4638;
        border-radius: 4px;
        box-shadow: 0px 0px 0px 5px #ffe9c7;
        border: dashed 1px #5e4638;
    }

    h1 {
        font-size: 50px;
    }
</style>