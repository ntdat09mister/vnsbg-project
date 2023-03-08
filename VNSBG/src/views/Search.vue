<script lang="ts">
import { userStore } from '@/stores/user';
import { mapActions, mapState } from 'pinia';
import { defineComponent } from 'vue';
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import { fillterStore2 } from '../stores/fillter2'
import { cart } from '../stores/cart'
import IconAddToCart from '@/components/icons/IconAddToCart.vue';
import IconAddToCart2 from '@/components/icons/IconAddToCart2.vue';
import IconOutOfStock from '@/components/icons/IconOutOfStock.vue'
export default defineComponent({
    name: 'Search2',
    components: {
        Header,
        Navigation,
        IconAddToCart,
        IconAddToCart2,
        IconOutOfStock
    },
    computed: {
        ...mapState(fillterStore2, {
            totalPage: 'totalPage',
            setPage: 'setPage',
            listDisplayFillterGirls: 'listDisplayFillterGirls',
            page: 'page',
            listFillterBabiesGrid: 'listFillterBabiesGrid'
        }),
        ...mapState(userStore, {
            customerId: 'customerId'
        })
    },
    data() {
        return {
            nationality: '-1',
            marialStatus: '-1',
            heightStart: -1,
            weightStart: -1,
            round1: -1,
            round2: -1,
            round3: -1,
            girlId: "-1",
            price: "-1",
            selectedGirls: [] as any,
            isActive: false,
            changeScreen: true
        }
    },
    methods: {
        ...mapActions(fillterStore2, ['listDisplayFillterBabiesByNationalityParam']),
        selectFillterBabies() {
            this.listDisplayFillterBabiesByNationalityParam(this.nationality, this.marialStatus, this.heightStart, this.weightStart, this.round1, this.round2, this.round3)
        },
        ...mapActions(cart, ['addToCart'])
        ,
        addToList(value: string) {
            this.selectedGirls.push(value)
        },
        show(value: any) {
            return !this.selectedGirls.includes(value)
        },
        handleAddToCart(item: any) {
            this.addToCart(this.customerId, item.girlId, item.price)
            this.addToList(item.girlId)
        },
        selectPage(value: number) {
            return value == this.page
        }, changeShowScreen(value: string) {
            if (value == 'list') {
                this.changeScreen = true;
            }
            if (value == 'grid') {
                this.changeScreen = false;
            }
        }

    },
    mounted() {
        this.listDisplayFillterBabiesByNationalityParam(this.nationality, this.marialStatus, this.heightStart, this.weightStart, this.round1, this.round2, this.round3)
    }
})
</script>

<template>
    <div class="container-fillter w-980px" style="margin: 0 auto;">
        <Header />
        <Navigation />
        <div class="container-search">
            <div class="menu-fillter">
                <div class="option-menu">
                    <label class="labelFilter" for="nationality">Quốc gia </label>
                    <select class="selectFilter" name="" id="nationality" v-model="nationality"
                        @change="selectFillterBabies()">
                        <option value="-1">Tất cả</option>
                        <option value="0">Gái Việt</option>
                        <option value="1">Gái Tây</option>
                    </select>
                </div>
                <div class="option-menu">
                    <label class="labelFilter" for="marial-status">Hôn nhân </label>
                    <select class="selectFilter" name="" id="marial-status" v-model="marialStatus"
                        @change="selectFillterBabies()">
                        <option value="-1">Tất cả</option>
                        <option value="0">Chưa có GĐ</option>
                        <option value="1">Đã có GĐ</option>
                    </select>
                </div>
                <div class="option-menu">
                    <label class="labelFilter" for="height">Chiều cao </label>
                    <select class="selectFilter" name="" id="height" v-model="heightStart" @change="selectFillterBabies()">
                        <option value="-1">Tất cả</option>
                        <option value="150">150cm -> 165cm</option>
                        <option value="165">165cm -> 175cm</option>
                        <option value="175">175cm trở lên</option>
                    </select>
                </div>
                <div class="option-menu">
                    <label class="labelFilter" for="weight">Cân nặng </label>
                    <select class="selectFilter" name="" id="weight" v-model="weightStart" @change="selectFillterBabies()">
                        <option value="-1">Tất cả</option>
                        <option value="40">40kg -> </option>
                        <option value="50">50kg -> </option>
                        <option value="55">55kg -></option>
                    </select>
                </div>
                <div class="option-menu">
                    <label class="labelFilter" for="round-1">Vòng 1 </label>
                    <select class="selectFilter" name="" id="round-1" v-model="round1" @change="selectFillterBabies()">
                        <option value="-1">Tất cả</option>
                        <option value="80">80cm -> </option>
                        <option value="85">85cm -> </option>
                        <option value="90">90cm -></option>
                    </select>
                </div>
                <div class="option-menu">
                    <label class="labelFilter" for="round-2">Vòng 2 </label>
                    <select class="selectFilter" name="" id="round-2" v-model="round2" @change="selectFillterBabies()">
                        <option value="-1">Tất cả</option>
                        <option value="45">45cm -> </option>
                        <option value="55">55cm -> </option>
                        <option value="65">65cm -></option>
                    </select>
                </div>
                <div class="option-menu">
                    <label class="labelFilter" for="round-3">Vòng 3 </label>
                    <select class="selectFilter" name="" id="round-3" v-model="round3" @change="selectFillterBabies()">
                        <option value="-1">Tất cả</option>
                        <option value="80">80cm -> </option>
                        <option value="85">85cm -> </option>
                        <option value="95">95cm -></option>
                    </select>
                </div>
                <div class="option-radio-button">
                    <div class="radio-button-swtich">
                        <label @click="changeShowScreen('list')">
                            <input type="radio" name="radio" value="list" checked />
                            <span>List</span>
                        </label>
                        <label @click="changeShowScreen('grid')">
                            <input type="radio" name="radio" value="grid" />
                            <span>Grid</span>
                        </label>
                    </div>
                </div>
            </div>

            <template v-if="changeScreen == true">
                <div class="list-fillter" v-for="item in listDisplayFillterGirls" :key="item.girlId">
                    <div class="item-fillter">
                        <div class="img-fillter-item">
                            <router-link :to="{ name: 'detail', params: { id: item.id } }">
                                <img id="url1" :src="item.url" :alt="item.description">
                            </router-link>
                        </div>
                        <div class="detail-girl">
                            <span>ID: {{ item.girlId }}</span>
                            <span>Tình trạng hôn nhân: {{ item.marialStatus }}</span>
                            <span>Quốc tịch: {{ item.nationality }}</span>
                            <span>Chiều cao: {{ item.height }}</span>
                            <span>Cân nặng: {{ item.weight }}</span>
                            <span>Số đo vòng 1: {{ item.roundMs1 }}</span>
                            <span>Số đo vòng 2: {{ item.roundMs2 }}</span>
                            <span>Số đo vòng 3: {{ item.roundMs3 }}</span>
                            <span>Đánh giá khách hàng: {{ item.ratingVote }}/5</span>
                            <span>Giá: {{ item.price }}</span>
                        </div>
                        <div class="buy-now-add-to-cart">
                            <div id="btnAddToCart" class="add-to-cart-icon">
                                <span class="span-icon-router-cart">Thêm vào giỏ hàng</span>
                                <div v-if="show(item.girlId)" @click="handleAddToCart(item)">
                                    <IconAddToCart2 />
                                </div>
                                <div v-else>
                                    <IconOutOfStock />
                                </div>
                            </div>
                            <div class="order-icon-span">
                                <span class="span-icon-router-cart">Mua ngay</span>
                                <router-link :to="{ name: 'cart' }" @click="addToCart(customerId, item.girlId, item.price)">
                                    <IconAddToCart />
                                </router-link>
                            </div>
                        </div>
                    </div>
                </div>

            </template>
            <template v-if="changeScreen == false">
                <article>
                    <div class="grid-view-filter">
                        <div class="box" v-for="item in listDisplayFillterGirls" :key="item.girlId">
                            <router-link :to="{ name: 'detail', params: { id: item.id } }">
                                <div class="imgBx">
                                    <img id="url1" :src="item.url" :alt="item.description">
                                </div>
                                <div class="content">
                                    <div class="p-infoUser">
                                        <p>
                                            ID: {{ item.girlId }}
                                            <br>
                                            Chiều cao: {{ item.height }}
                                            <br>
                                            Cân nặng: {{ item.weight }}
                                            <br>
                                            Vòng 1: {{ item.roundMs1 }}cm
                                            <br>
                                            Vòng 2: {{ item.roundMs2 }}cm
                                            <br>
                                            Vòng 3: {{ item.roundMs3 }}cm
                                            <br>
                                            Giá: {{ item.price }}$
                                        </p>
                                    </div>
                                </div>
                            </router-link>
                        </div>
                    </div>
                </article>
            </template>
            <div class="navigation">
                <div class="flex">
                    <ul>
                        <div class="items" v-for="n in totalPage">
                            <li :class="{ active: selectPage(n) }" @click="setPage(n)">{{ n }}</li>
                        </div>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
.menu-fillter {
    width: 980px;
    height: 80px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
}

.option-menu {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 110px;
    height: 80px;
}

.option-radio-button {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 150px;
    height: 80px;
}

.list-detail-fillter {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.item-fillter {
    width: 980px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.item-fillter img {
    width: 270px;
    height: 408px;
}

.buy-now-add-to-cart {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    width: 400px;
    height: 412px;
}

.buy-now-add-to-cart .order-icon {
    width: 200px;
    height: 400px;
}

.buy-now-add-to-cart .add-to-cart-icon {
    width: 200px;
    height: 400px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.detail-girl {
    width: 220px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
</style>

<style lang="scss">
$primary-color: #00005c; // Change color here. C'mon, try it! 
$text-color: mix(#000, $primary-color, 64%);

.radio-button-swtich {
    display: flex;
}

label {
    cursor: pointer;
    font-weight: 500;
    position: relative;
    overflow: hidden;

    input {
        position: absolute;
        left: -9999px;

        &:checked+span {
            background-color: mix(#fff, $primary-color, 84%);

            &:before {
                box-shadow: inset 0 0 0 0.4375em $primary-color;
            }
        }
    }

    span {
        display: flex;
        align-items: center;
        padding: 0.375em 0.75em 0.375em 0.375em;
        border-radius: 99em; // or something higher...
        transition: 0.25s ease;
        color: #000;

        &:hover {
            background-color: mix(#f3f167, $primary-color, 84%);
        }

        &:before {
            display: flex;
            flex-shrink: 0;
            content: "";
            background-color: #fff;
            width: 1.5em;
            height: 1.5em;
            border-radius: 50%;
            margin-right: 0.375em;
            transition: 0.25s ease;
            box-shadow: inset 0 0 0 0.125em $primary-color;
        }
    }
}

/* ----------------------------- */

.grid-view-filter {
    position: relative;
    width: 980px;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    transform-style: preserve-3d;
    perspective: 500px;
    margin: auto;
}

.grid-view-filter .box {
    position: relative;
    width: 270px;
    height: 408px;
    background: #000;
    transition: 0.5s;
    transform-style: preserve-3d;
    overflow: hidden;
    margin-right: 15px;
    margin-top: 45px;
}

.grid-view-filter:hover .box {
    transform: rotateY(25deg);
}

.grid-view-filter .box:hover~.box {
    transform: rotateY(-25deg);
}

.grid-view-filter .box:hover {
    transform: rotateY(0deg) scale(1.25);
    z-index: 1;
    box-shadow: 0 25px 40px rgba(0, 0, 0, 0.5);
}

.grid-view-filter .box .imgBx {
    position: absolute;
    top: 0;
    left: 0;
    width: 270px;
    height: 408px;
}

.grid-view-filter .box .imgBx:before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(180deg, #b7b0b0, #000);
    z-index: 1;
    opacity: 0;
    transition: 0.5s;
    mix-blend-mode: multiply;
}

.grid-view-filter .box:hover .imgBx:before {
    opacity: 1;
}

.grid-view-filter .box .imgBx img {
    position: absolute;
    top: 0;
    left: 0;
    width: 270px;
    height: 408px;
    object-fit: cover;
}

.grid-view-filter .box .content {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    display: flex;
    padding: 20px;
    align-items: flex-end;
    box-sizing: border-box;
}

.grid-view-filter .box .content h2 {
    color: #fff;
    transition: 0.5s;
    text-transform: uppercase;
    margin-bottom: 5px;
    font-size: 20px;
    transform: translateY(200px);
    transition-delay: 0.3s;
}

.grid-view-filter .box:hover .content h2 {
    transform: translateY(0px);
}

.grid-view-filter .box .content p {
    color: #fff;
    transition: 0.5s;
    font-size: 14px;
    transform: translateY(200px);
    transition-delay: 0.4s;
}

.grid-view-filter .box:hover .content p {
    transform: translateY(0px);
}

.selectFilter {
    background-color: #dfe6f5;
    width: 80px;
    height: 18px;
    border-radius: 2px;
}

.labelFilter {
    font-family: 'Times New Roman', serif;
    font-size: 15px;
}

.span-icon-router-cart {
    font-family: 'Poppins';
    font-style: normal;
    font-weight: 600;
    font-size: 25px;
    line-height: 21px;
    /* identical to box height */

    letter-spacing: 0.1px;

    color: #171725;
}

.order-icon-span {
    display: flex;
    flex-direction: column;
    justify-content: center;
}
</style>