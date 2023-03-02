import { defineStore } from 'pinia'
import axios from 'axios'
import { userStore } from './user'
import { ref, computed } from 'vue'

export const cart = defineStore('cart', () => {
    const listCartItems = ref([])
    const limit = ref(5)
    const page = ref(1)

    function listingCartApi() {
        const user = userStore();
        const { customerId } = user
        console.log(customerId)
        return new Promise((resolve) => {
            axios.get(`http://localhost:8080/listCart?customerId=${customerId}`, {})
                .then(response => {
                    const { data } = response
                    listCartItems.value = data
                    resolve(data)
                })
        })
    }

    function addToCart(customerId: string | null, girlId?: string, price?: string) {
        return new Promise((resolve) => {
            axios.post(`http://localhost:8080/addToCart?customerId=${customerId}&girlId=${girlId}&price=${price}`)
            resolve(0);
        })
    }

    function deleteItemFromCart(value : string) {
        return new Promise ((resolve) => {
            axios.delete(`http://localhost:8080/removeCartItem?cartItemId=${value}`)
            resolve(0);
            listCartItems.value = listCartItems.value.filter(item => {
                const { cartItemId } = item
                cartItemId != value
            })
        })
    }

    const listingCart = computed(() => {
        const fromBaby = (page.value - 1) * limit.value
        const toBaby = page.value * limit.value - 1
        return listCartItems.value.filter((baby, index) => {
            return index >= fromBaby && index <= toBaby
        })
    })

    const total = computed(() => listCartItems.value.length)

    const totalPage = computed(() => Math.ceil(listCartItems.value.length / limit.value))

    function setPage(value: number) { page.value = value }

    function getCurrentBaby(id: number): any { 
        return listCartItems.value.filter((baby: any) => baby.id === id)[0]
    }

    return {
        listingCartApi,
        addToCart,
        deleteItemFromCart,
        listingCart,
        total,
        totalPage,
        setPage,
        getCurrentBaby,
        page,
        listCartItems
    }
})