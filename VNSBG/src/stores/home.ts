import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useHomeStore = defineStore('home', () => {
    const listBaby = ref([])
    const limit = ref(3)
    const page = ref(1)
    const listBabyTopReact = ref([])

    function getListBaby() {
        return new Promise((resolve) => {
            axios.get('http://localhost:8080/girl', {})
            .then(response => {
                const { data } = response
                listBaby.value = data
                resolve(null)
            })
        })
    }

    function getListBabyTopReact() {
        return new Promise((resolve) => {
            axios.get('http://localhost:8080/topGirlReact', {})
            .then(response => {
                const { data } = response
                listBabyTopReact.value = data
                resolve(null)
            })
        })
    }

    const listDisplayBaby = computed(() => {
        const fromBaby = (page.value - 1) * limit.value
        const toBaby = page.value * limit.value - 1
        return listBaby.value.filter((baby, index) => {
            return index >= fromBaby && index <= toBaby
        })
    })

    const total = computed(() => listBaby.value.length)

    const totalPage = computed(() => Math.ceil(listBaby.value.length / limit.value))

    function setPage(value: number) { page.value = value }

    function getCurrentBaby(id: number): any { 
        return listBaby.value.filter((baby: any) => baby.id === id)[0] 
    }

    return { listDisplayBaby, getListBaby, setPage, total, totalPage, getCurrentBaby, page, listBabyTopReact, getListBabyTopReact}
})
