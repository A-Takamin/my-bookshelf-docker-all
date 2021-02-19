<template>
    <div>
        <img v-bind:src="imgPath">
        <div>
            <p><span>評価：</span>{{ reviewStars }}</p>
        </div>
    </div> 
</template>

<script>
export default {
    name: 'Book',
    props: {
        img: String,
        review: Number
    },
    data() {
        return {
            imgPath: this.img,
            // reviw: this.review     
        }
    },
    computed: {
        reviewStars: function() {
            let stars = ''
            for(let i = 0; i < this.review; i++) {
                stars += '★'
            }
            return stars
        }
    },

}
</script>

<style scoped>

</style>