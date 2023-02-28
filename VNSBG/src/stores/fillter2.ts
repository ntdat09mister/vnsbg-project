import { defineStore } from 'pinia'
import axios from 'axios'
import { ref, computed } from 'vue'

export const fillterStore2 = defineStore('search2', () => {
    const listFillterBabies = ref([])
    const limit = ref(6)
    const page = ref(1)

    function listDisplayFillterBabiesByNationalityParam(nationality: any, marialStatus: any, heightStart: number, weightStart: number, round1: Number, round2: Number, round3: Number) {
        return new Promise ((resolve) => {
            axios.get(`http://localhost:8080/search?nationality=${nationality}&marialStatus=${marialStatus}&heightStart=${heightStart}&weightStart=${weightStart}&roundMs1Start=${round1}&roundMs2Start=${round2}&roundMs3Start=${round3}`, {})
            .then(response => {
                const { data } = response
                listFillterBabies.value = data
                resolve(null)
            })
        })
    }

    const listDisplayFillterGirls = computed(() => {
        const fromBaby = (page.value - 1) * limit.value
        const toBaby = page.value * limit.value - 1
        return listFillterBabies.value.filter((baby, index) => {
            return index >= fromBaby && index <= toBaby
        })
    })

    const total = computed(() => listFillterBabies.value.length)

    const totalPage = computed(() => Math.ceil(listFillterBabies.value.length / limit.value))

    function setPage(value: number) { page.value = value }

    function getCurrentBaby(id: number): any { 
        return listFillterBabies.value.filter((baby: any) => baby.id === id)[0] 
    }

    return {
        listDisplayFillterGirls,
        total,
        totalPage,
        setPage,
        listDisplayFillterBabiesByNationalityParam,
        getCurrentBaby
    }
})