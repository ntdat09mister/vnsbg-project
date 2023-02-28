<script lang="ts">
import { userStore } from '@/stores/user';
import { mapActions, mapState } from 'pinia';
import { defineComponent } from 'vue';
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import { fillterStore2 } from '../stores/fillter2'
import { cart } from '../stores/cart'
export default defineComponent({
    name: 'Search2',
    components: {
        Header,
        Navigation
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
        },changeShowScreen(value: string) {
           if(value == 'list') {
            this.changeScreen = true;
           } 
           if(value == 'grid') {
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
    <div class="container-fillter">
        <Header />
        <Navigation />
        <div class="container-fillter-custom">
            <div class="menu-fillter">
                <div class="option-menu">
                    <label class="labelFilter" for="nationality">Quốc gia </label>
                    <select class="selectFilter" name="" id="nationality" v-model="nationality" @change="selectFillterBabies()">
                        <option value="-1">Tất cả</option>
                        <option value="0">Gái Việt</option>
                        <option value="1">Gái Tây</option>
                    </select>
                </div>
                <div class="option-menu">
                    <label class="labelFilter" for="marial-status">Hôn nhân </label>
                    <select class="selectFilter" name="" id="marial-status" v-model="marialStatus" @change="selectFillterBabies()">
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
                <div class="option-menu">
                    <div class="radio-button-swtich" >
                            <label @click="changeShowScreen('list')">
                                <input type="radio" name="radio" value="list" checked/>
                                <span>List</span>
                            </label>
                            <label @click="changeShowScreen('grid')">
                                <input type="radio" name="radio" value="grid"/>
                                <span>Grid</span>
                            </label>
                    </div>
                </div>
            </div>

            <template v-if="changeScreen == true">
                <div class="list-detail-fillter">
                    <article>
                        <div class="list-fillter" v-for="item in listDisplayFillterGirls" :key="item.girlId">
                            <div class="item-fillter">
                                <div class="img-fillter-item">
                                    <router-link :to="{ name: 'detail', params: { id: item.id } }">
                                        <img id="url1" :src="item.url" :alt="item.description">
                                    </router-link>
                                </div>
                                <div class="detail-girl">
                                    <div class="item">
                                        <div class="fillter-girlDetail-item">
                                            ID: {{ item.girlId }}
                                        </div>
                                        <div class="fillter-girlDetail-item">
                                            Tình trạng hôn nhân: {{ item.marialStatus }}
                                        </div>
                                        <div class="fillter-girlDetail-item">
                                            Quốc tịch: {{ item.nationality }}
                                        </div>
                                        <div class="fillter-girlDetail-item">
                                            Chiều cao: {{ item.height }}
                                        </div>
                                        <div class="fillter-girlDetail-item">
                                            Cân nặng: {{ item.weight }}
                                        </div>
                                        <div class="fillter-girlDetail-item">
                                            Số đo vòng 1: {{ item.roundMs1 }}
                                        </div>
                                        <div class="fillter-girlDetail-item">
                                            Số đo vòng 2: {{ item.roundMs2 }}
                                        </div>
                                        <div class="fillter-girlDetail-item">
                                            Số đo vòng 3: {{ item.roundMs3 }}
                                        </div>
                                        <div class="fillter-girlDetail-item">
                                            Đánh giá khách hàng: {{ item.ratingVote }}/5
                                        </div>
                                        <div class="fillter-girlDetail-item">
                                            Giá: {{ item.price }}
                                        </div>
                                    </div>
                                </div>
                                <div class="order-icon">
                                    <router-link :to="{ name: 'cart' }"
                                        @click="addToCart(customerId,item.girlId,item.price)">
                                        <button id="button1">Mua ngay</button>
                                    </router-link>
                                    <img src="https://www.magiktheatre.org/wp-content/uploads/2017/07/buy-now-icon-e1554504115847.png"
                                        alt="">
                                </div>
                                <div id="btnAddToCart" class="add-to-cart-icon">
                                    <div v-if="show(item.girlId)" @click="handleAddToCart(item)">
                                        <button id="button2">Thêm vào giỏ hàng</button>
                                    </div>
                                    <img v-if="show(item.girlId)"
                                        src="data:image/png;base64,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"
                                        alt="">
                                    <img v-else
                                        src="https://thumbs.dreamstime.com/b/sold-out-sign-trendy-vector-icon-template-hanging-frame-design-200322804.jpg"
                                        alt="">
                                </div>
                            </div>
                        </div>
                    </article>
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
                                        Vòng 1: {{ item.roundMs1}}cm
                                        <br>
                                        Vòng 2: {{ item.roundMs2}}cm
                                        <br>
                                        Vòng 3: {{ item.roundMs3}}cm
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
                            <li :class="{ active: selectPage(n) }" @click="setPage(n)">{{n}}</li>
                        </div>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

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
		&:checked + span {
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
.grid-view-filter .box:hover ~ .box {
    transform: rotateY(-25deg);
}
.grid-view-filter .box:hover {
    transform: rotateY(0deg) scale(1.25);
    z-index: 1;
    box-shadow: 0 25px 40px rgba(0,0,0,0.5);
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

</style>