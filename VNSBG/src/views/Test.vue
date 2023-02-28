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
    <div class="form">
        <div class="username">
            <Username />
        </div>
        <div class="password">
            <Password />
        </div>
        <div class="button-sign">
            <ButtonSignIn />
        </div>
        <div class="comment">
            <Comment />
        </div>
        <div class="tst">
            <Product />
        </div>
    </div>
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