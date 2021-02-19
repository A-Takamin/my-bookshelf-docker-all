<template>
  <Header />
  <div class="main_container">
    <h1>ログインする</h1>
    <form>
      <input type="email" placeholder="メールアドレス" v-model="email"> 
      <input type="password" placeholder="パスワード" v-model="password">
      <a class="submit" href="javascript:void(0)" @click="login">ログイン</a>
    </form>
    <div>
        <p>アカウントを持っていませんか？
            <router-link to="/register">新規登録はこちら</router-link>
        </p>    
    </div>
    <!-- <div>
        <p>SNSでログインする</p>
        <div><a>twitter</a></div>
        <div><a>facebook</a></div>
    </div> -->
  </div>
</template>

<script>
import firebase from 'firebase'
import Header from '../components/Header.vue'

export default {
  name: 'Login',
  components: {
      Header
  },
  data: function () {
    return {
      email: '',
      password: '',
      isShow: true
    }
  },
  methods: {
    login: function () {
      firebase.auth().signInWithEmailAndPassword(this.email, this.password)
      .then(res => {
        res.user.getIdToken().then( token => {
            // localStorageに保存
            localStorage.setItem('jwt', token)
            localStorage.setItem('uid', res.user.uid)
        })
        this.$router.push('/Home')
      }, err => {
        alert(err.message)
      })
    }
  }
}
</script>

<style scoped>
.main_container {
  margin: 50px 0;
}

form {
  width: 20%;
  margin: 10px auto 50px;
  min-width: 9rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  
}

input {
  width: 100%;
  max-width: 100%;
  border: none;
  margin: 0.6rem 0;
  padding: 0.5rem 1rem;
  border-radius: 0.3rem;
  background-color: #dcdcdc;
  color: darken(#f9f9f9, 50%);
}

.submit {
  margin-top: 20px;
  width: 30%;
  display: inline-block;
  padding: 0.5em 1em;
  text-decoration: none;
  background: #ffe9c7;
  color: #5e4638;
  border-radius: 4px;
  box-shadow: 0px 0px 0px 5px #ffe9c7;
  border: dashed 1px #5e4638;
}


</style>