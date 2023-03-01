<script lang="ts">
import { defineComponent } from 'vue';
import IconLike from '@/components/icons/IconLike.vue'
import IconPrice from './icons/IconPrice.vue';
import router from '@/router';

export default defineComponent({
    props: {
        NameProduct: { type: String, require: true },
        ImgProductUrl: { type: String, require: true },
        Description: { type: String, require: true },
        TotalLikes: { type: String, requrie: true },
        Price: { type: String, require: true },
        Id: { type: Number, require: true }
    },
    components: {
        IconLike,
        IconPrice
    },
    methods: {
        handleClick(id: number) {
            router.push({ name: 'detail', params: { id: Number(id) } })
        }
    }
})
</script>

<template>
    <div class="container-product">
        <span id="container-product-span1">{{ NameProduct }}</span>
        <a href="" @click="handleClick(Number(Id))"><img :src="ImgProductUrl" alt=""></a>
        <span id="container-product-span2">{{ Description }}</span>
        <div class="like-price">
            <div class="likes-price">
                <IconLike />
                <span>{{ TotalLikes }}</span>
            </div>

            <div class="likes-price">
                <IconPrice />
                <span>{{ Price }}</span>
            </div>
        </div>
    </div>
</template>

<style>
.container-product {
    width: 320px;
    height: 400px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
}

.container-product img {
    width: 230px;
    height: 280px;
}

#container-product-span1 {
    font-family: 'Lato';
    font-style: normal;
    font-weight: 700;
    font-size: 20px;
    line-height: 42px;
}

#container-product-span2 {
    width: 300px;
    font-family: 'Lato';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 21px;
    /* identical to box height, or 150% */
    color: #8181A5;
}

.like-price {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 250px;
    height: 20px;
}

.likes-price {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}
</style>