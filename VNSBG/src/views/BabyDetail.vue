<script lang="ts">
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
import PromoDetail from '@/components/PromoDetail.vue'
import { mapActions, mapState } from 'pinia'
import { useHomeStore } from '../stores/home'
import { babyDetailStore } from '../stores/baby-detail'
import { defineComponent } from 'vue';
import { promoStore } from '@/stores/promo'
import { cart } from '@/stores/cart'
import { userStore } from '@/stores/user'
import router from '@/router'
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
            baby: { url: '', description: '' },
            soldOut: "1"
        }
    },
    computed: {
        ...mapState(userStore, {
            customerId: 'customerId'
        }),
        ...mapState(useHomeStore, {
            getCurrentBaby: 'getCurrentBaby',
            getListBaby: 'getListBaby'
        }),
        ...mapState(babyDetailStore, {
            moreImages: 'moreImages',
            babyDes: 'babyDes',
            desData: 'desData'
        }),
        ...mapState(promoStore, {
            listPromoes: 'listPromoes',
            getListPromoes: 'getListPromoes'
        }),
        marialStatusFormat() {
            if ((this.desData as any).marialStatus == 0) {
                return 'Đã chồng con'
            } else {
                return "Chưa lập gia đình"
            }
        },
        nationalityFormat() {
            if ((this.desData as any).nationality == 0) {
                return 'Việt Nam 100%'
            } else {
                return 'Gái tây ợ'
            }
        }
    },
    methods: {
        ...mapActions(babyDetailStore, ['getListBabyDetail', 'getDescription', 'getDesData']),
        ...mapActions(cart,['addToCart']),
        handleAddToCart(customerId: String, id: String, price: String) {
            if(this.customerId?.length != 0) {
                this.addToCart(String(customerId), String(id), String(price))
            }
            router.push({name:'cart'})
            alert("Đã thêm em gái này vào giỏ hàng!" )
        }
    },
    created() {
        const { id } = this.$route.params
        this.getListBaby().then(() => {
            this.baby = this.getCurrentBaby(Number(id))
        })
        this.getListBabyDetail(Number(id))
        this.getDescription(Number(id))
        this.getDesData(Number(id))
    },
    mounted() {
        this.getListPromoes()
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
                        <img
                            src="https://media3.giphy.com/media/B3ltrgdWWMmxpUOu1m/giphy.gif?cid=ecf05e473vifk6q1x02njrsm1p1e2s29duxj7ccl84zv50xk&rid=giphy.gif&ct=g">
                        <button id="abc123" @click="handleAddToCart(String(customerId), (desData as any).id, (desData as any).price)">
                            Book ngay nào
                        </button>
                    </div>
                </div>
                <div class="promo">
                    <div class="promo-action">
                        <span>Ưu đãi khủng từ VNSBG</span>
                    </div>
                    <div v-for="item in listPromoes">
                        <PromoDetail :SpanPromoDetail="(item as any).promoDetail" />
                    </div>
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

.baby-detail .all-img .prod-list-bonus img:hover {
    transform: rotateY(0deg) scale(1.25);
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

.button-action #abc123:hover {
    background-color: bisque;
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

.promo-action span {
    font-family: 'Poppins';
    font-style: normal;
    font-weight: 600;
    font-size: 24px;
    line-height: 36px;
    /* identical to box height, or 150% */

    letter-spacing: 0.1px;

    color: #171725;

}

#avatarBabyDetail:hover {
    transform: rotateY(0deg) scale(1.25);
}
</style>