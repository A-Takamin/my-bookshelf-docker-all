<template>
  <transition name="modal" appear>
    <div class="modal modal-overlay" @click.self="$emit('close')">
      <div class="modal-window">
        <div class="modal-content">
            <input v-model="searchWord" type="text" placeholder="本のタイトルを入力">
            <button @click="searchBooks">検索</button>
        </div>
            
        <div class="search_result_container">
            <div v-for="book in searchResultBooks" :key="book.id" class="book_container">
                <h3>{{ book.title }}</h3>
                <div><img :src="book.img"></div>
                <div><button @click="decideBook">選択</button></div>
            </div>
        </div>

      </div>
    </div>
  </transition>
</template>

<script>
import axios from 'axios'
export default {
    name: 'Modal',
    data() {
        return {
            searchWord: '',
            searchResultBooks: []
        }
    },
    methods: {
        searchBooks: function() {
            axios.get('https://www.googleapis.com/books/v1/volumes?q='+this.searchWord)
            .then( result => {
                const hitBooks = result.data.items
                this.searchResultBooks = []
                hitBooks.forEach(elem => {
                    if( !elem.volumeInfo.imageLinks ) {
                        return // 画像がないものはスキップ
                    }
                    const book = {
                        id: elem.volumeInfo.id,
                        title: elem.volumeInfo.title, 
                        img  : elem.volumeInfo.imageLinks.thumbnail
                       }
                    
                    this.searchResultBooks.push(book)
                });
            })
        },
        decideBook: function(event)  {
            const selectedBook = {
                title: event.target.parentNode.previousElementSibling.previousElementSibling.innerText,
                img: event.target.parentNode.previousElementSibling.firstChild.src
            }
            this.searchResultBooks = []
            this.$emit('decideBook', selectedBook)

        }
    }
}
</script>


<style lang="stylus" scoped>
.book_container{
  height: 260px;
  width: 170px; 
  padding: 10px;

  h3{
    font-size: 14px;
    margin: 5px;
  }
}

.search_result_container {
    display: flex;
    flex-wrap: wrap;

}
.modal {
  &.modal-overlay {
    display: flex;    height: 600px;
    width: 1000px;
    align-items: center;
    justify-content: center;
    position: fixed;
    z-index: 30;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
  }

  &-window {
    background: #fff;
    border-radius: 4px;
    overflow: hidden;
    max-height: 600px;
    max-width: 1000px;
    overflow: auto;
  }

  &-content {
    padding: 10px 20px;
  }

  &-footer {
    background: #ccc;
    padding: 10px;
    text-align: right;
  }
}

// オーバーレイのトランジション
.modal-enter-active, .modal-leave-active {
  transition: opacity 0.4s;

  // オーバーレイに包含されているモーダルウィンドウのトランジション
  .modal-window {
    transition: opacity 0.4s, transform 0.4s;
  }
}

// ディレイを付けるとモーダルウィンドウが消えた後にオーバーレイが消える
.modal-leave-active {
  transition: opacity 0.6s ease 0.4s;
}

.modal-enter, .modal-leave-to {
  opacity: 0;

  .modal-window {
    opacity: 0;
    transform: translateY(-20px);
  }
}
</style>