<script lang="ts">
import { defineComponent } from 'vue';
import { userStore } from '@/stores/user';
import { mapActions, mapState } from 'pinia';
import IconHome from '@/components/icons/IconHome.vue';
import IconGirl from '@/components/icons/IconGirl.vue';
import IconBra from '@/components/icons/IconBra.vue';
import IconSell from '@/components/icons/IconSell.vue';
import IconSearch from '@/components/icons/IconSearch.vue';
import IconCart from '@/components/icons/IconCart.vue';
import IconUser from '@/components/icons/IconUser.vue';

export default defineComponent({
    computed: {
        ...mapState(userStore, {
            loggedCustomer: 'loggedCustomer',
            customerId: 'customerId',
            avatar: 'avatar'
        }),
        customerIdentification(this: any) {
            if (this.loggedCustomer != null) {
                return this.loggedCustomer
            } else {
                return 'Người dùng'
            }
        }
    },
    data() {
        return {
            optionProfile: '-1',
            listOptionsMenu: [
                { id: 1, text: 'Trang chủ' },
                { id: 2, text: 'Gái xinh' },
                { id: 3, text: 'Tuyển chọn' },
                { id: 4, text: 'Tìm kiếm' },
                { id: 5, text: 'Giỏ hàng' },
                { id: 6, text: 'Người dùng' }
            ]
        }
    },
    methods: {
        ...mapActions(userStore, {
            logOut: 'logOut',
            getUserDetail: 'getUserDetail'
        }),
        isActiveNavigation(value: string) {
            return this.$route.name == value;
        },
        selectProfile() {
            if (this.optionProfile == '2') {
                this.logOut()
                console.log("Log Out Succesfully!")
                this.$router.push({ name: 'memeber' })
                location.reload();
            }
            if (this.optionProfile == '1' || this.optionProfile == '-1') {
                this.getUserDetail(String(this.customerId))
                this.$router.push({ name: 'profile' })
            }
        },
        checkAvatar() {
            if (String(this.avatar)?.length < 2) {
                return false
            }
            else {
                return true;
            }
        },
        routerView(viewName: String) {
            this.$router.push({ name: String(viewName) })
        }
    },
    components: {
        IconHome,
        IconGirl,
        IconBra,
        IconSell,
        IconSearch,
        IconCart,
        IconUser
    },
    mounted() {
        this.checkAvatar()
    }
})

</script>

<template>
    <div class="flex justify-center items-center">
        <div class="flex justify-center flex-col items-center option-menu-cart cursor-pointer" @click="routerView('home')">
            <IconHome />
            <span>Trang chủ</span>
        </div>
        <div class="flex justify-center flex-col items-center option-menu-cart" @click="routerView('home')">
            <IconGirl />
            <span>Gái xinh</span>
        </div>
        <div class="flex justify-center flex-col items-center option-menu-cart" @click="routerView('home')">
            <IconBra />
            <span>Tuyển chọn</span>
        </div>
        <div class="flex justify-center flex-col items-center option-menu-cart" @click="routerView('home')">
            <IconSell />
            <span>Mua bán</span>
        </div>
        <div class="flex justify-center flex-col items-center option-menu-cart cursor-pointer" @click="routerView('search')">
            <IconSearch />
            <span>Tìm kiếm</span>
        </div>
        <div class="flex justify-center flex-col items-center option-menu-cart cursor-pointer" @click="routerView('cart')">
            <IconCart />
            <span>Giỏ hàng</span>
        </div>
        <template v-if="loggedCustomer != null">
            <div class="icon" :class="{ activeNav: isActiveNavigation('member') }">
                <template v-if="checkAvatar()">
                    <img :src="String(avatar)" alt="" id="avatarIcon">
                </template>
                <template v-else>
                    <IconUser />
                </template>
            </div>
            <div class="text-span" :class="{ activeNav: isActiveNavigation('member') }">
                <select class="selectUser cursor-pointer" v-model="optionProfile" @change="selectProfile()">
                    <option class="cursor-pointer" value="-1">{{ customerIdentification }}</option>
                    <option class="cursor-pointer" value="1">Thông tin tài khoản</option>
                    <option class="cursor-pointer" value="2">Đăng xuất</option>
                </select>
            </div>
        </template>
        <template v-if="loggedCustomer == null">
            <div class="flex justify-center flex-col items-center option-menu-cart cursor-pointer" @click="routerView('member')">
                <IconUser />
                <span>Người dùng</span>
            </div>
        </template>
        <div class="dot"></div>
    </div>
</template>

<style lang="scss">
.menu .navMenu .list .icon:hover {
    transform: rotateY(0deg) scale(1.75);
    z-index: 1;
}
</style>

<style>
.option-menu-cart {
    width: 120px;
    height: 70px;
}
.selectUser {
    color: #000000;
    padding: 0px;
    width: 168px;
    border: none;
    font-size: 15px;
    box-shadow: 0 5px 25px rgba(0, 0, 0, 0.2);
    -webkit-appearance: button;
    appearance: button;
    outline: none;
}

.optionProfile::before {
    content: "\f13a";
    font-family: FontAwesome;
    position: absolute;
    top: 0;
    right: 0;
    width: 20%;
    height: 100%;
    text-align: center;
    font-size: 28px;
    line-height: 45px;
    color: rgba(255, 255, 255, 0.5);
    background-color: rgba(255, 255, 255, 0.1);
    pointer-events: none;
}

.optionProfile:hover::before {
    color: rgba(255, 255, 255, 0.6);
    background-color: rgba(255, 255, 255, 0.2);
}

.optionProfile select option {
    padding: 30px;
}

#avatarIcon {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    cursor: pointer;
}
</style>