<script lang="ts">
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
import { mapActions, mapState } from 'pinia';
import { defineComponent } from 'vue';
import { cart } from '../stores/cart'
import { userStore } from '@/stores/user'
export default defineComponent ({
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
        totalPayment() {
        let sum = 0;
        for (let i = 0 ;i < this.listingCart.length ; i++) {
            sum += (this.listingCart[i] as any).price
            console.log((this.listingCart[i] as any).price)
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
        <Header/>
        <Navigation/>
        <div class="listing-item-information">
            <div class="nav-information">
                <label for="" id="cart-label-1">Mã đơn hàng</label>
                <label for="" id="cart-label-2">Sản phẩm</label>
                <label for="" id="cart-label-3">Tên</label>
                <label for="" id="cart-label-4">Giá</label>
                <label for="" id="cart-label-5">Ngày giao dịch</label>
                <label for="" id="cart-label-6">Xóa</label>
            </div>
            <div v-for="item in ( listingCart as any )" class="listing-item" :key="item.id">
                <div class="product-detail">
                    <div class="code-orders">
                        {{ item.cartItemId }}
                    </div>
                    <router-link :to="{ name: 'detail', params: { id: item.id } }">
                        <div class="product-img">
                            <img :src="item.url" alt="">
                        </div>
                    </router-link>
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
                        <li :class="{ active:  selectPage(n) }" @click="setPage(n)">{{n}}</li>
                    </div>
                </ul>
            </div>
        </div>
        <Footer/>
    </div>
</template>