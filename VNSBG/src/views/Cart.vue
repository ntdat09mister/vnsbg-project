<script lang="ts">
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
import { mapActions, mapState } from 'pinia';
import { defineComponent } from 'vue';
import { cart } from '../stores/cart'
import { userStore } from '@/stores/user'
export default defineComponent({
    name: 'Cart',
    components: {
        Header,
        Navigation,
        Footer
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
    }
})
</script>

<template>
    <div class="container-fillter">
        <Header />
        <Navigation />
        <div class="listing-item-information">
            <div class="menu-cart-page">
                <span>Mã đơn hàng</span>
                <span>Sản phẩm</span>
                <span>Tên</span>
                <span>Giá</span>
                <span>Ngày giao dịch</span>
                <span>Xóa</span>
            </div>
            <div v-for="item in (listingCart as any)" class="listing-item" :key="item.id">

                <div class="code-orders">
                    {{ item.cartItemId }}
                </div>
                <div class="product-img">
                    <router-link :to="{ name: 'detail', params: { id: item.id } }">
                        <img :src="item.url" alt="">
                    </router-link>

                </div>
                <div class="product-name">
                    {{ item.name }}
                </div>
                <div class="product-price">
                    {{ item.price }}
                </div>
                <div class="sales-date">
                    {{ item.salesDate }}
                </div>
                <div class="deleteItem" @click="deleteItemFromCart(item.cartItemId)">
                    <a href="">
                        <img src="https://cdn-icons-png.flaticon.com/512/1345/1345874.png" alt="">
                    </a>
                </div>

            </div>
            <div class="total-payment">
                <h3>Tổng số tiền phải trả: {{ totalPayment }}</h3>
            </div>
            <div class="information-user">
            </div>
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
.listing-item-information {
    width: 980px;
    display: flex;
    flex-direction: column;
}

.menu-cart-page {
    width: 900px;
    height: 50px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-left: 20px;
}

.listing-item {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}

.listing-item img {
    width: 50px;
    height: 50px;
}
</style>