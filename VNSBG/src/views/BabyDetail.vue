<script lang="ts">
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
import PromoDetail from '@/components/PromoDetail.vue'
import { mapActions, mapState } from 'pinia'
import { useHomeStore } from '../stores/home'
import { babyDetailStore } from '../stores/baby-detail'
import { defineComponent } from 'vue';
export default defineComponent({
    name: 'BabyDetail',
    components: {
        Header,
        Navigation,
        Footer,
        PromoDetail
    },
    data() {
        return {
            baby: { url: '', description: '' }
        }
    },
    computed: {
        ...mapState(useHomeStore, {
            getCurrentBaby: 'getCurrentBaby',
            getListBaby: 'getListBaby'
        }),
        ...mapState(babyDetailStore, {
            moreImages: 'moreImages',
            babyDes: 'babyDes',
            desData: 'desData'
        }),
        marialStatusFormat() {
            if ((this.desData as any).marialStatus == 0) {
                return 'Married'
            } else {
                return "Not married"
            }
        },
        nationalityFormat() {
            if ((this.desData as any).nationality == 0) {
                return 'Vietnam'
            } else {
                return 'Foreign'
            }
        }
    },
    methods: {
        ...mapActions(babyDetailStore, ['getListBabyDetail', 'getDescription', 'getDesData'])
    },
    created() {
        const { id } = this.$route.params
        this.getListBaby().then(() => {
            this.baby = this.getCurrentBaby(Number(id))
        })
        this.getListBabyDetail(Number(id))
        this.getDescription(Number(id))
        this.getDesData(Number(id))
    }
})
</script>

<template>
    <div class="container">
        <Header />
        <Navigation />
        <div class="baby-detail">
            <div class="all-img">
                <img id="avatarBabyDetail" :src="baby.url" :alt="baby.description">
                <div class="prod-list-bonus">
                    <div v-for="item in moreImages" class="small-img">
                        <img :src="(item as any).url" :alt="(item as any).description">
                    </div>
                </div>
            </div>
            <div class="personal-data-action-promo">
                <div class="personal-data-action">
                    <div class="personal-data">
                        <span>ID: {{ (desData as any).id }}</span>
                        <span>Tình trạng: {{ marialStatusFormat }}</span>
                        <span>Quốc tịch: {{ nationalityFormat }}</span>
                        <span>Chiều cao: {{ (desData as any).height }}cm</span>
                        <span>Cân nặng: {{ (desData as any).weight }}</span>
                        <span>Số đo vòng 1: {{ (desData as any).roundMs1 }}cm</span>
                        <span>Số đo vòng 2: {{ (desData as any).roundMs2 }}cm</span>
                        <span>Số đo vòng 3: {{ (desData as any).roundMs3 }}cm</span>
                    </div>
                    <div class="button-action">
                        <img src="https://media3.giphy.com/media/B3ltrgdWWMmxpUOu1m/giphy.gif?cid=ecf05e473vifk6q1x02njrsm1p1e2s29duxj7ccl84zv50xk&rid=giphy.gif&ct=g" >
                        <button id="abc123">
                            Thêm vào giỏ hàng
                        </button>
                    </div>
                </div>
                <div class="promo">
                    <div class="promo-action">
                        <span>Ưu đãi khủng</span>
                    </div>
                    <!-- <div class="promo-detail">
                        <span>Giảm thêm tới 1% cho thành viên VSBG member</span>
                        <br>
                        <span>Bảo vệ sản phẩm toàn diện với dịch vụ bảo hành mở rộng</span>
                        <br>
                        <span>Giảm thêm 5% tối đa 50$ khi thanh toán qua Kredivo</span>
                        <br>
                        <span>Giảm thêm tới 100$ cho đơn hàng từ 2000$ khi thanh toán qua VNPAY</span>
                        <br>
                        <span>Giảm thêm 4% (tối đa 25$) qua ví Moca cho đơn hàng từ 500$</span>
                        <br>
                        <span>Mở thẻ tín dụng Citibank - Nhận voucher giảm đến 100$</span>
                        <br>
                        <span>Giảm thêm 60$ qua thẻ tín dụng JCB cho đơn hàng từ 5000$</span>
                        <br>
                        <span>Thu cũ đổi mới: Giá thu cao - Thủ tục nhanh chóng - Trợ giá tốt nhất</span>
                    </div> -->
                    <PromoDetail SpanPromoDetail="aaa" />
                </div>
            </div>
        </div>
        <Footer />
    </div>
</template>

<style>
.baby-detail {
    width: 980px;
    height: 750px;
    display: flex;
    justify-content: space-around;
    flex-direction: row;
}

.baby-detail .all-img {
    width: 380px;
    height: 750px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.baby-detail .all-img #avatarBabyDetail {
    width: 375px;
    height: 370px;
}

.baby-detail .all-img .prod-list-bonus {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
}

.baby-detail .all-img .prod-list-bonus img {
    width: 182px;
    height: 182px;
}


.personal-data-action-promo {
    width: 580px;
    height: 750px;
    display: flex;
    flex-direction: column;
}

.personal-data-action {
    display: flex;
    flex-direction: row;
    width: 580px;
    height: 370px;
}

.personal-data {
    display: flex;
    flex-direction: column;
}

.personal-data span {
    font-family: 'Lato';
    font-style: normal;
    font-weight: 700;
    font-size: 14px;
    line-height: 42px;
    color: #882d5f;
}

.button-action {
    width: 350px;
    height: 340px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}

.button-action img {
    width: 320px;
    height: 270px;
}

.button-action #abc123 {
    width: 200px;
    height: 45px;
    padding: 0;
    margin: 0;
}

.promo {
    width: 550px;
    height: 373px;
    border: 1px solid #d1d5db;
    border-radius: 10px;
}

.promo-action {
    background-color: #d1d5db;
}

</style>