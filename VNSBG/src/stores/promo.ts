import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

export const promoStore = defineStore('promo', () => {
    const listPromoes = ref([])
    function getListPromoes() {
        return new Promise((resolve) => {
            axios.get(`http://localhost:8080/promoDetail`, {})
            .then(response => {
                const { data } = response
                listPromoes.value = data
                resolve(null)
            })
        })
    }
    return { getListPromoes, listPromoes }
})