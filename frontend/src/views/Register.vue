<template>
    <div>
        <Header />
        <div class="main_container">
            <h1>新規登録する</h1>
            <form>
                <input type="email" placeholder="メールアドレス" v-model="email"> 
                <input type="password" placeholder="パスワード" v-model="password">
                <input type="password" placeholder="パスワード（再入力）" v-model="password_again">
                <a class="submit" href="" @click="register">新規登録</a>
            </form>
            <div>
                <p>アカウントを持っていますか？
                    <router-link to="/login">ログインはこちら</router-link>
                </p>    
            </div>
            <!-- <div>
                <p>SNSで新規登録する</p>
                <div><a>twitter</a></div>
                <div><a>facebook</a></div>
            </div> -->
        </div>
    </div>
</template>

<script>
import firebase from 'firebase'
import axios from 'axios'
import Header from '../components/Header.vue'

export default {
    name: 'Register',
    data() {
        return {
            email: '',
            password: '',
            password_again: '',
            errorMsg: ''
        }
    },
    components: {
        Header
    },
    methods: {
        register: function() {

            firebase.auth().createUserWithEmailAndPassword(this.email, this.password)
            .then( res => {

                res.user.getIdToken().then( token => {
                    axios.post(process.env.VUE_APP_BACKEND_ORIGIN+'/mybookshelf/user/' + res.user.uid, {}, { 
                        headers: {'Authorization' : token}
                    })
                    .then( () => { 
                        // localStorageに保存
                        localStorage.setItem('jwt', token)
                        localStorage.setItem('uid', res.user.uid)
                        // ホーム画面へ遷移 
                        this.$router.push('/Home')
                    })
                    .catch( ee => {
                        alert(ee.message)
                    })
                })


            }).catch( e => {
                alert(e.message)
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