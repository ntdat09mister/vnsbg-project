import { defineStore } from 'pinia'
import axios from 'axios'

export const babyDetailStore = defineStore('babyDetail', {
    state: () => ({
        moreImages: [],
        babyDes: {},
        desData: {}
    }),
    actions: {
        async getListBabyDetail(id: number) {
            return new Promise((resolve) => {
                axios.get(`http://localhost:8080/detailImgs?girlId=${id}`, {})
                .then(response => {
                    const { data } = response
                    this.moreImages = data
                    resolve(data)
                })
            }) 
        },
        async getDescription(id: number) {
            return new Promise((resolve) => {
                axios.get(`http://localhost:8080/detailDescription?girlId=${id}`, {})
                .then(response => {
                    const { data } = response
                    this.babyDes = data
                    resolve(data)
                })
            })
        },
        async getDesData(id: number) {
            return new Promise((resolve) => {
                axios.get(`http://localhost:8080/detail?girlId=${id}`, {})
                .then(response => {
                    const { data } = response
                    this.desData = data
                    resolve(data)
                })
            })
        }
    }
})
