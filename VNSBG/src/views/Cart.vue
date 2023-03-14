<script lang="ts">
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
import { mapActions, mapState } from 'pinia';
import { defineComponent } from 'vue';
import { cart } from '../stores/cart'
import { userStore } from '@/stores/user'
import IconDeleteItemCart from '@/components/icons/IconDeleteCart.vue'
export default defineComponent({
    name: 'Cart',
    components: {
        Header,
        Navigation,
        Footer,
        IconDeleteItemCart
    },
    computed: {
        ...mapState(cart, {
            page: 'page',
            listingCart: 'listingCart',
            totalPage: 'totalPage',
            setPage: 'setPage',
            listCartItems: 'listCartItems',
            totalPayment() {
                let sum = 0;
                for (let i = 0; i < this.listCartItems.length; i++) {
                    sum += (this.listCartItems[i] as any).price
                    console.log((this.listCartItems[i] as any).price)
                }
                return sum;
            }
        }),
        ...mapState(userStore, {
            customerId: 'customerId'
        })
    },
    created() {
        this.listingCartApi()
    },
    methods: {
        ...mapActions(cart, ['listingCartApi']),
        ...mapActions(cart, ['deleteItemFromCart']),
        selectPage(value: number) {
            return value == this.page
        }
    },
    data() {
        return {
            optionsMenu: [
                { id: 1, text: "Mã đơn hàng" },
                { id: 2, text: "Sản phẩm" },
                { id: 3, text: "Tên" },
                { id: 4, text: "Giá" },
                { id: 5, text: "Ngày giao dịch" },
                { id: 6, text: "Xóa" }
            ]
        }
    }
})
</script>

<template>
    <div class="flex flex-col justify-center items-center">
        <Header />
        <Navigation />
        <div class="flex flex-row justify-around list-cart">
            <div class="flex flex-row justify-around" v-for="option in optionsMenu" :key="option.id">
                <div>
                    <span class="flex flex-row justify-around items-center spanColCart">{{ option.text }}</span>
                </div>
            </div>
        </div>
        <div v-for="item in (listingCart as any)" class="flex flex-row justify-around items-center list-cart" :key="item.id">
            <div class="flex border-content-cart">
                <div>
                    <span class="flex flex-row justify-around items-center spanColCartvalue">{{ item.cartItemId }}</span>
                </div>
                <div class="flex flex-row justify-around items-center spanColCartvalue product-img">
                    <router-link :to="{ name: 'detail', params: { id: item.id } }">
                        <img :src="item.url" alt="">
                    </router-link>
                </div>
                <div>
                    <span class="flex flex-row justify-around items-center spanColCartvalue">{{ item.name }}</span>
                </div>
                <div>
                    <span class="flex flex-row justify-around items-center spanColCartvalue">{{ item.price }}</span>
                </div>
                <div>
                    <span class="flex flex-row justify-around items-center spanColCartvalue">{{ item.salesDate }}</span>
                </div>
                <div class="flex flex-row justify-around items-center spanColCartvalue" @click="deleteItemFromCart(item.cartItemId)">
                    <a href="">
                        <IconDeleteItemCart />
                    </a>
                </div>
            </div>
        </div>
        <div class="total-payment">
            <h3>Tổng số tiền phải trả: {{ totalPayment }}</h3>
        </div>
        <div class="navigation">
            <div class="flex">
                <ul>
                    <div class="items" v-for="n in totalPage">
                        <li :class="{ active: selectPage(n) }" @click="setPage(n)">{{ n }}</li>
                    </div>
                </ul>
            </div>
        </div>
        <Footer />
    </div>
</template>

<style>
.border-content-cart {
    border: 3px solid #F1F1F5;
    border-radius: 10px;
}
.spanColCart {
    width: 179px;
    height: 33px;
    color: aliceblue;
    background-color: rgb(0, 0, 0);
}

.spanColCartvalue {
    width: 179px;
    height: 50px;
}

.list-cart {
    width: 1078px;
    margin-bottom: 10px;
}

.list-cart-value {
    width: 1000px;
}

.product-img img {
    width: 50px;
    height: 50px;
}
</style>