<template>
    <div>
        <Header />
        <div class="main_container">
            <div class="main_inner">
                <h1>本の詳細</h1>

                <form>
                    <div class="form_inner">
                        <div class="img_container">
                            <img :src="book.img">
                        </div>

                        <div>
                            <div class="form_elem_container">
                                <label>タイトル</label><input v-model="book.title" type="text" class="form_input" readonly>
                            </div>
                            <div class="form_elem_conatiner">
                                <label>読んだ日</label><input v-model="book.readAt" type="date" class="form_input" readonly>
                            </div>
                            <div class="form_elem_container">
                                <label>本の評価</label>
                                <input class="review_stars form_input" v-model="reviewStars" type="text" readonly>
                            </div>
                            

                            <div class="form_elem_container">
                                <div>
                                    <label>コメント</label>
                                </div>
                                <div>
                                    <textarea v-model="book.comments" placeholder="感想を添えましょう" class="form_input" readonly></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <div class="btn_section" v-if="isModify">
                    <div class="btn_container">
                        <a href="javascript:void(0)" @click="deleteBook">削除</a>
                    </div>
                    <div class="btn_container">
                        <a href="javascript:void(0)" @click="finishModify">確定</a>
                    </div>
                </div>

                <div class="btn_section" v-else>
                    <div class="btn_container">
                        <router-link to="/Home">＜戻る</router-link>
                    </div>
                    <div class="btn_container">
                        <a href="javascript:void(0)" @click="startModify">編集</a>
                    </div>
                </div>


            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Header from '../components/Header.vue'

export default {
    name: 'BookDetail',
    props: {
        id: String
    },
    components: {
        Header
    },
    data() {
        return {
            book: {},
            reviewStars: '',
            isModify: false
        }
    },
    created() {
        axios.get(process.env.VUE_APP_BACKEND_ORIGIN+'/mybookshelf/book/'+localStorage.getItem('uid')+'/'+this.id, {
                headers: {
                    Authorization: localStorage.getItem('jwt')
                }
            })
        .then( res => {
            this.book = res.data
            this.countReviewStars(this.book.review)
        })
    },
    methods: {
        countReviewStars: function(reviewNum) {
            let stars = ''
            for(let i = 0; i < reviewNum; i++) {
                stars += '★'
            }
            this.reviewStars = stars
        },
        startModify: function() {
            document.getElementsByClassName('form_input').forEach(elem => {
                elem.removeAttribute('readonly', 'readonly')
                elem.style.backgroundColor = '#dcdcdc'
            });
            this.isModify = true
        },
        finishModify: function() {
            //PUT
            axios.put(process.env.VUE_APP_BACKEND_ORIGIN+'/mybookshelf/book/'+localStorage.getItem('uid')+'/'+this.book.serialNo, {
                title: this.book.title,
                img: this.book.img,
                readAt: this.book.readAt,
                review: this.book.review,
                comments: this.book.comments,
            }, {
                headers: {
                    Authorization: localStorage.getItem('jwt')
                }
            }).then( () => {
                document.getElementsByClassName('form_input').forEach(elem => {
                    elem.readOnly = true
                    elem.style.backgroundColor = '#eee'
                    this.isModify = false
                })
            })
        },
        deleteBook: function() {
            const deletesFlg = confirm('この本を削除しますか？')
            if(deletesFlg) {
                axios.delete(process.env.VUE_APP_BACKEND_ORIGIN+'/mybookshelf/book/'+localStorage.getItem('uid')+'/'+this.book.serialNo, {
                    headers: {
                        Authorization: localStorage.getItem('jwt')
                    }
                }, {
                    
                }).then( () => {
                    this.$router.push('/Home')
                })
            }
        }
    },
}
</script>

<style scoped>
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
  background-color: #eee;
  color: #333;
}
input:focus, textarea:focus {
    outline: none;
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
.btn_section {
    display: flex;
    justify-content: center;
}

.btn_container {
    margin: 30px;
}
.btn_container a{
    color: #777;
}
</style>