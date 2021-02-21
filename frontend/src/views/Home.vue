<template>
    <div>
        <Header :showsLogout="true"/>
        <h1>マイ本棚</h1>
        <section class="bookshelf_container">
            <div class="bookshelf">
                <div v-for="book in books" :key="book.serialNo" @click="detail(book.serialNo)" class="book_container">
                    <!-- 本 -->
                    <Book v-bind:img="book.img" v-bind:review="book.review" />
                </div>
            </div>
        </section>
        <section class="book_add_container">
            <router-link to="/bookAdd">本を追加する<br>+</router-link>
        </section>
    </div>
</template>

<script>
import axios from 'axios';
import firebase from 'firebase'
import Book from '../components/Book.vue'
import Header from '../components/Header.vue'

export default {
    name: 'Home',
    data() {
        return {
            books: []
        }
    },
    components: {
        Book,
        Header
    },
    methods: {
        logOut: function () {
            firebase.auth().signOut().then(() => {
                localStorage.removeItem('jwt')
                localStorage.removeItem('uid')
                this.$router.push('/login')
            })
        },
        detail: function(bookId) {
            this.$router.push({ 
                name: 'BookDetail',
                params: {
                    id: bookId
                    // serialNo: book.serialNo,
                    // img: book.img,
                    // title: book.title,
                    // readAt: book.readAt,
                    // review: book.review,
                    // comments: book.comments,
                    // userId: book.userId
                }
            })
        }
    },created() {
        axios.get(process.env.VUE_APP_BACKEND_ORIGIN+'/mybookshelf/book/'+localStorage.getItem('uid'), {
                headers: {
                    Authorization: localStorage.getItem('jwt')
                }
            })
        .then( res => {
            this.books = res.data
        })
    }


}
</script>

<style scoped>
.bookshelf_container {
    display: flex;
    justify-content: center;
}
.bookshelf {
    min-height: 250px;
    width: 1050px;
    display: flex;
    justify-content: flex-start;
    align-items: flex-end;
    padding: 0.5em 1em;
    margin: 2em 0;
    color: #5d627b;
    background: white;
    border-top: solid 5px #5d627b;
    box-shadow: 0 3px 5px rgba(0, 0, 0, 0.22);
    flex-wrap: wrap;
}
.book_container {
    margin: 10px;
}
.book_container:hover {
    cursor: pointer;
}
.book_add_container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 20px;
}
.book_add_container a{
    background: #f7ebda;
    border-radius: 4px;
    box-shadow: 0px 0px 0px 5px #f7ebda;
    border: dashed 1px #5e4638;
    color: #5e4638;
    text-decoration: none;
    display: block;
    align-items: center;
    padding: 20px 40px;
}
</style>