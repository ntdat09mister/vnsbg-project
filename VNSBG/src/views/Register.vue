<script lang="ts">
import { defineComponent } from 'vue';
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
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
            password: ''
        }
    }
})
</script>

<template>
    <div class="container">
        <Header />
        <Navigation />
        <div class="card_body">
            <div id="login_center">
                <img id="img1" src="https://img.9gate.net/files/4148393882/2020/12/09/1607480661-thumb-2-group-ngam-gai-uy-tin-chua-day-tai-lieu-hoc-tap-cho-500-anh-em-9gate.jpg" alt="">
                <img id="img2" src="https://nld.mediacdn.vn/zoom/700_438/2019/2/18/photo-1-1550456143846698186341.jpg" alt="">
                <h1>Đăng ký làm thành viên VNSBG</h1>
                <div class="wrapper-register">
                    <form id="form-login">
                        <div class="form-group">
                            <input v-model="userName" type="text" id="username" class="form-input" placeholder="Tên đăng nhập">
                        </div>
                        <div class="form-group">
                            <input v-model="password" required type="password" class="form-input" placeholder="Mật khẩu">
                            <div id="eye">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>
                        <div class="form-group">
                            <input v-model="password" required type="password" class="form-input" placeholder="Nhập lại mật khẩu">
                            <div id="eye">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>
                        <input @click="" id="btn" value="Đăng ký" class="form-submit">
                        <div class="register-suggest">
                            <h5>Bạn đã có tài khoản?</h5>
                            <RouterLink :to="{ name: 'member' }">
                                <h4>Đăng nhập ngay</h4>
                            </RouterLink>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <Footer />
    </div>
</template>