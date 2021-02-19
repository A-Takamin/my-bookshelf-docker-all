<template>
  <header>
    <div class="service_name_container">
      <router-link to="/">My Bookshelf</router-link>
    </div>
    <div class="logout_container">
      <a v-if="isShow" href="javascript:void(0)" @click="logOut">logout</a>
    </div>
  </header>   
</template>
  
<script>
import firebase from 'firebase'
export default {
    data() {
      return {
        isShow: this.showsLogout
      }
    },
    props: {
      showsLogout: Boolean
    },
    methods: {
      logOut: function () {
          firebase.auth().signOut().then(() => {
              localStorage.removeItem('jwt')
              localStorage.removeItem('uid')
              this.$router.push('/')
          })
      },
    }
}
</script>

<style scoped>
  header {
    background-color:#755f52;
    height :5vh;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  header a {
    color: white;
    text-decoration: none;
    font-size: 25px;
  }
  .service_name_container {
    padding-left: 10px;
  }
  .logout_container {
    padding-right: 10px;
  }
</style>