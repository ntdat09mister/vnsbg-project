<script lang="ts">
import { defineComponent } from 'vue';
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
import { userStore } from '../stores/user'
import { mapActions } from 'pinia'
export default defineComponent({
    name: 'Member',
    components: {
        Header,
        Navigation,
        Footer
    },
    data() {
        return {
            userName: '',
            password: '',
            test: '2'
        }
    },
    methods: {
        ...mapActions(userStore, ['checkUser']),
        backToHome() {
            this.$router.push({ name: 'home' })
        },
        checkExistUser() {
            return this.checkUser(String(this.userName), String(this.password))
        }
    }
})
</script>

<template>
    <div class="container">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
        <Header />
        <Navigation />
        <div class="login-form">
            <div class="background">
                <div class="shape"></div>
                <div class="shape"></div>
            </div>
            <form>
                <h3>Login In VNSBG</h3>
    
                <label for="username">Tài khoản:</label>
                <input v-model="userName" type="text" autocomplete="on" placeholder="Email or Phone" id="username">
    
                <label for="password">Mật khẩu:</label>
                <input v-model="password" type="password" autocomplete="on" placeholder="Password" id="password">
    
                <button @click="checkExistUser">Log In</button>
                <div class="social">
                    <div class="go"><i class="fab fa-google"></i> Google</div>
                    <div class="fb"><i class="fab fa-facebook"></i> Facebook</div>
                </div>
            </form>
        </div>
        <Footer />
    </div>
</template>
<style media="screen">
*,
*:before,
*:after {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}

.login-form {
    background: #ffffff;
    width: 980px;
    height: 680px;
    margin-bottom: 20px;
    border-radius: 10px;
}

.background {
    margin-top: 20px;
    width: 430px;
    height: 560px;
    position: absolute;
    transform: translate(-50%, -50%);
    left: 50%;
    top: 50%;
    background-color: #080710;
    border-radius: 10px;
}

.background .shape {
    height: 200px;
    width: 200px;
    position: absolute;
    border-radius: 50%;
}

.shape:first-child {
    background: linear-gradient(#1845ad,
            #23a2f6);
    left: -80px;
    top: -80px;
    margin-top: 26px;
}

.shape:last-child {
    background: linear-gradient(to right,
            #ff512f,
            #f09819);
    right: -30px;
    bottom: -80px;
}

form {
    background-color: #080710;
    height: 520px;
    width: 400px;
    background-color: rgba(255, 255, 255, 0.13);
    position: absolute;
    transform: translate(-50%, -50%);
    top: 50%;
    left: 50%;
    border-radius: 10px;
    backdrop-filter: blur(10px);
    border: 2px solid rgba(255, 255, 255, 0.1);
    box-shadow: 0 0 40px rgba(8, 7, 16, 0.6);
    padding: 50px 35px;
    margin-top: 17px;
}

form * {
    font-family: 'Poppins', sans-serif;
    color: #ffffff;
    letter-spacing: 0.5px;
    outline: none;
    border: none;
}

form h3 {
    font-size: 32px;
    font-weight: 500;
    line-height: 42px;
    text-align: center;
}

label {
    display: block;
    margin-top: 30px;
    font-size: 16px;
    font-weight: 500;
}

input {
    display: block;
    height: 50px;
    width: 100%;
    background-color: rgba(255, 255, 255, 0.07);
    border-radius: 3px;
    padding: 0 10px;
    margin-top: 8px;
    font-size: 14px;
    font-weight: 300;
}

::placeholder {
    color: #e5e5e5;
}

button {
    margin-top: 50px;
    width: 100%;
    background-color: #ffffff;
    color: #080710;
    padding: 15px 0;
    font-size: 18px;
    font-weight: 600;
    border-radius: 5px;
    cursor: pointer;
}

.social {
    margin-top: 30px;
    display: flex;
}

.social div {
    background: red;
    width: 150px;
    border-radius: 3px;
    padding: 5px 10px 10px 5px;
    background-color: rgba(255, 255, 255, 0.27);
    color: #eaf0fb;
    text-align: center;
}

.social div:hover {
    background-color: rgba(255, 255, 255, 0.47);
}

.social .fb {
    margin-left: 25px;
}

.social i {
    margin-right: 4px;
}
</style>