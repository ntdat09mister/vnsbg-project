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

export default defineComponent ({
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
            optionProfile: '-1'
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
            if(String(this.avatar)?.length < 2) {
                return false
            }
            else {
                return true;
            }
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
    <div class="menu">
        <nav class="navMenu">
                <router-link class="list" :to="{ name: 'home' }">
                    <div class="icon" :class="{ activeNav: isActiveNavigation('home') }">
                        <IconHome />
                    </div>
                    <div class="text-span" :class="{ activeNav: isActiveNavigation('home') }">
                        <span>Trang chủ</span>
                    </div>
                </router-link>

                <router-link class="list" :to="{ name: 'home' }">
                    <div class="icon">
                        <IconGirl />
                    </div>
                    <div class="text-span">
                        <span>Gái xinh</span>
                    </div>
                </router-link>

                <router-link class="list" :to="{ name: 'home' }">
                    <div class="icon">
                        <IconBra />
                    </div>
                    <div class="text-span">
                        <span>Tuyển chọn</span>
                    </div>
                </router-link>

                <router-link class="list" :to="{ name: 'home' }">
                    <div class="icon">
                        <IconSell />
                    </div>
                    <div class="text-span">
                        <span>Mua bán</span>
                    </div>
                </router-link>

                <router-link class="list" :to="{ name: 'search' }">
                    <div class="icon" :class="{ activeNav: isActiveNavigation('search') }">
                        <IconSearch />
                    </div>
                    <div class="text-span" :class="{ activeNav: isActiveNavigation('search') }">
                        <span>Tìm kiếm</span>
                    </div>
                </router-link>

                <router-link class="list" :to="{ name: 'cart' }">
                    <div class="icon" :class="{ activeNav: isActiveNavigation('cart') }">
                        <IconCart />
                    </div>
                    <div class="text-span" :class="{ activeNav: isActiveNavigation('cart') }">
                        <span>Giỏ hàng</span>
                    </div>
                </router-link>

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
                        <select class="selectUser" v-model="optionProfile" @change="selectProfile()">
                            <option value="-1">{{ customerIdentification }}</option>
                            <option value="1">Thông tin tài khoản</option>
                            <option value="2">Đăng xuất</option>
                        </select>
                    </div>
                </template>
                <template v-if="loggedCustomer == null">
                    <router-link class="list" :to="{ name: 'member' }">
                        <div class="icon" :class="{ activeNav: isActiveNavigation('member') }">
                            <IconUser />
                        </div>
                        <div class="text-span" :class="{ activeNav: isActiveNavigation('member') }">
                            <span>Người dùng</span>
                        </div>
                    </router-link>
                </template>
            <div class="dot"></div>
        </nav>
    </div>
</template>

<style lang="scss">
.menu .navMenu .list .icon:hover {
    transform: rotateY(0deg) scale(1.75);
    z-index: 1;
}
</style>

<style>
.selectUser {
    background-color: #12254e;
    color: #f6f4e6;
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
    width: 30px;
    height: 30px;
    border-radius: 50%;
}

#avatarIcon:hover {
    transform: rotateY(0deg) scale(1.5);
}

.selectUser:hover {
    color: rgb(255, 255, 253);
    transform: rotateY(0deg) scale(1.01);
}
</style>