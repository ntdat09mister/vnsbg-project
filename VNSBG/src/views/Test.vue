<script lang="ts">
import Username from '@/components/UsernameInput.vue'
import Password from '@/components/PasswordInput.vue'
import ButtonSignIn from '@/components/ButtonSignIn.vue'
import Comment from '@/components/Comment.vue'
import Product from '@/components/Product.vue'
import { defineComponent } from 'vue';
export default defineComponent({
    name: 'test',
    components: {
        Username,
        Password,
        ButtonSignIn,
        Comment,
        Product
    }
})
</script>

<template>
    <button onclick="document.getElementById('srcImg').src='https://gamek.mediacdn.vn/133514250583805952/2021/9/26/photo-1-1632639337166965564522.jpg'">Img 1</button>
    <img id="srcImg" src="https://i.vietgiaitri.com/2022/1/11/netizen-khong-tin-noi-voi-anh-chua-day-thi-cua-gai-xinh-lam-dong-849-6262894.jpg" alt="">
    <button onclick="document.getElementById('srcImg').src='https://i.vietgiaitri.com/2022/1/11/netizen-khong-tin-noi-voi-anh-chua-day-thi-cua-gai-xinh-lam-dong-849-6262894.jpg'">Img 2</button>
</template>

<style>
.tst {
    margin-top: 30px
}
.form {
    display: flex;
    flex-direction: column;
    width: 508px;
    height: 241px;
    margin: 0;
    padding: 0;
}

.username {
    margin: 0;
    padding: 0;
}

</style>