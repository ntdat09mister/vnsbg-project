import { defineStore } from 'pinia'
import axios from 'axios'
export const fillterStore = defineStore ('search', {
    state: () => ({
        fillterBabies: []
    }),
    actions: {
        async fillterListBabies(nationality: number) {
            return new Promise((resolve) =>{
                axios.get(`http://localhost:8080/search?nationality=${nationality}`,{})
                .then(response => {
                    const { data } = response
                    this.fillterBabies = data
                    resolve(data)
                })
            })
        }
    }

})