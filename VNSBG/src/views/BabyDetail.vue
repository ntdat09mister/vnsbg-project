<script lang="ts">
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
import { mapActions, mapState} from 'pinia'
import { useHomeStore } from '../stores/home'
import { babyDetailStore } from '../stores/baby-detail'
import { defineComponent } from 'vue';
export default defineComponent({
    name: 'BabyDetail',
    components: {
        Header,
        Navigation,
        Footer
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
        ...mapActions(babyDetailStore, ['getListBabyDetail','getDescription','getDesData'])
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
                <div class="image-des">
                    <div id="img">
                        <img :src="baby.url" :alt="baby.description">
                    </div>
                </div>
                <div class="img-list">
                    <div class="prod-list">
                        <div v-for="item in moreImages" class="small-img">
                            <img :src="(item as any).url" :alt="(item as any).description">
                        </div>
                    </div>
                </div>
            </div>
            <div class="personal-data">
                <ul class="descriptionUl">
                    <li>ID: {{(desData as any).id}}</li>
                    <li>Tình trạng: {{ marialStatusFormat }}</li>
                    <li>Quốc tịch: {{ nationalityFormat }}</li>
                    <li>Chiều cao: {{(desData as any).height}}cm</li>
                </ul>
                <ul class="descriptionUl">
                    <li>Cân nặng: {{(desData as any).weight}}</li>
                    <li>Số đo vòng 1: {{(desData as any).roundMs1}}cm</li>
                    <li>Số đo vòng 2: {{(desData as any).roundMs2}}cm</li>
                    <li>Số đo vòng 3: {{(desData as any).roundMs3}}cm</li>
                </ul>
            </div>
        </div>
        <Footer />
    </div>
</template>
