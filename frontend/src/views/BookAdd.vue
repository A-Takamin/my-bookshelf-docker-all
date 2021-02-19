<template>
    <Header />
    <div class="main_container">
        <div class="main_inner">
            <h1>新しい本を追加</h1>

            <div class="book_search_btn_container">
                <a href="javascript:void(0)" class="book_search_btn" @click="openModal">本を検索する<br>+</a>
            </div>

            <section>
                <Modal @close="closeModal" @decideBook="decideBook" v-if:='showsModal'/>
            </section>
            
            <form>
                <div class="form_inner">
                    <div class="img_container">
                        <img :src="book.img">
                    </div>

                    <div>
                        <div class="form_elem_container">
                            <label>タイトル</label><input v-model="book.title" type="text">
                        </div>
                        <div class="form_elem_conatiner">
                            <label>読んだ日</label><input v-model="book.readAt" type="date">
                        </div>
                        <div class="form_elem_container">
                            <label>本の評価</label>
                            <input v-model="book.review" type="range" min="1" max="5">
                        </div>
                        
                        <span class="review_stars"> {{ reviewStars }} </span>

                        <div class="form_elem_container">
                            <div>
                                <label>コメント</label>
                            </div>
                            <div>
                                <textarea v-model="book.comments" placeholder="感想を添えましょう"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="book_add_btn_container">
                <a href="javascript:void(0)" class="book_add_btn" @click="addBook">本を登録</a>
            </div>
            <div class="return_btn_container">
                <router-link to="/Home">＜戻る</router-link>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Header from '../components/Header.vue'
import Modal from '../components/Modal.vue'
export default {
    name: 'BookAdd',
    data() {
        return {
            book: {img:'', title:'', readAt:'', review: 0, comments:''},
            showsModal: false,
        }
    },
    computed: {
        reviewStars: function() {
            let stars = ''
            for(let i = 0; i < this.book.review; i++) {
                stars += '★'
            }
            return stars
        }
    },
    methods: {
        addBook: async function() {
            // //POST
            axios.post('http://localhost:8081/mybookshelf/book/'+localStorage.getItem('uid'), {
                title: this.book.title,
                img: this.book.img,
                readAt: this.book.readAt,
                review: this.book.review,
                comments: this.book.comments,
            }, {
                headers: {
                    Authorization: localStorage.getItem('jwt')
                }
            })
            .then( () => {
                this.$router.push('/Home')

            })
        },
        decideBook(book) {
            this.book.title = book.title
            this.book.img   = book.img

            this.closeModal()
        },
        openModal() {
            this.showsModal = true
        },
        closeModal() {
            this.showsModal = false
        },

    },
    components: {
        Modal,
        Header
    }
}
</script>

<style scoped>
.book_search_btn_container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 40px;
}
.book_search_btn {
    background: #f7ebda;
    border-radius: 4px;
    box-shadow: 0px 0px 0px 5px #f7ebda;
    border: dashed 1px #5e4638;
    color: #5e4638;
    text-decoration: none;
    display: block;
    align-items: center;
    padding: 50px 200px;
}

.book_add_btn_container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 20px;
}

.book_add_btn{
    background: #f7ebda;
    border-radius: 4px;
    box-shadow: 0px 0px 0px 5px #f7ebda;
    border: dashed 1px #5e4638;
    color: #5e4638;
    text-decoration: none;
    display: block;
    align-items: center;
    padding: 10px 20px;
}
.img_container {
    margin: 0 30px;
}
.img_container img{
    box-shadow: 0px 0px 10px -5px rgba(0, 0, 0, 0.8);
}
form {
    display: flex;
    justify-content: center;
}
.form_inner {
    display: flex;
    justify-content: center;
    align-items: center;
    /* flex-direction: column; */
}
.form_elem_container {
    display: flex;
    justify-content: flex-start;
    align-items: center;
}
input, textarea{
  width: 300px;
  max-width: 100%;
  border: none;
  margin: 10px;
  padding: 0.5rem 1rem;
  border-radius: 0.3rem;
  background-color: #dcdcdc;
  color: #333;
}
input[type="date"] {
  position: relative;
  border: 1px;
  color: #333;
}
textarea {
    height: 50px;
}
.review_stars {
    color: #ff8c00;
}
.return_btn_container {
    margin: 30px;
}
.return_btn_container a{
    color: #777;
}
</style>