import axios from "axios";
import { defineStore } from "pinia";
import { cart } from '@/stores/cart'

export const userStore = defineStore('user',{
    state: () => ({
        customerId: sessionStorage.getItem("currentLoginId"),
        loggedCustomer: sessionStorage.getItem("loggedCustomer"),
        rankUser: sessionStorage.getItem('rankUser'),
        mailUser: sessionStorage.getItem('mailUser'),
        amountSpentUser: sessionStorage.getItem('amountSpentUser'),
        phoneNumberUser: sessionStorage.getItem('phoneNumberUser'),
        getUserDetail: 'getUserDetail',
        avatar: sessionStorage.getItem('avatar'),
        doneTrans: sessionStorage.getItem('doneTrans'),
        totalTrans: sessionStorage.getItem('totalTrans')
    }),
    actions: {
        async checkUser(userName: string, password: string) {
            const cartStore = cart()
            // cartStore.$reset()
            // this.$reset()
            return new Promise (async (resolve) => {
                await axios.post(`http://localhost:8080/login`, {
                    "userName": userName,
                    "password": password
                })
                .then(response => {
                    const { data } = response
                    sessionStorage.setItem("currentLoginId", data.customerId);
                    sessionStorage.setItem("loggedCustomer", data.fullName);
                    if (data.statusLogin == "true") {
                        console.log(data)
                        console.log("Xin chào " + data.fullName + " đã đăng nhập vào hệ thống!")
                        window.location.replace('http://localhost:5173/')
                    } else {
                        console.log("Sai tài khoản hoặc mật khẩu, xin hãy thử lại!")
                    }
                    resolve(data)
                })
            })
        },
        async logOut() {
            sessionStorage.removeItem('currentLoginId')
            sessionStorage.removeItem('loggedCustomer')
            sessionStorage.removeItem('rankUser')
            sessionStorage.removeItem('mailUser')
            sessionStorage.removeItem('amountSpentUser')
            sessionStorage.removeItem('phoneNumberUser')
            window.location.replace('http://localhost:5173/member')
        },
        async getUserDetail(customerId: string) {
            return new Promise (async (resolve) => {
                await axios.get(`http://localhost:8080/userDetail?userId=${customerId}`)
                .then(response => {
                    const { data } = response
                    sessionStorage.setItem("rankUser", data.rank);
                    sessionStorage.setItem("mailUser", data.mail);
                    sessionStorage.setItem("amountSpentUser", data.amountSpent);
                    sessionStorage.setItem("phoneNumberUser", data.phoneNumber);
                    sessionStorage.setItem("avatar",data.avatar);
                    sessionStorage.setItem("doneTrans",data.doneTrans);
                    sessionStorage.setItem("totalTrans",data.totalTrans)
                    resolve(data)
                })
            })
        }
    }
})
