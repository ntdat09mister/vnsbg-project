<script lang="ts">
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
import { mapState } from 'pinia'
import { useHomeStore } from '../stores/home'
import { defineComponent } from 'vue';
import Product from '@/components/Product.vue'
export default defineComponent({
  name: 'Home',
  components: {
    Header,
    Navigation,
    Footer,
    Product
  },
  computed: {
    ...mapState(useHomeStore, {
      totalPage: 'totalPage',
      setPage: 'setPage',
      listDisplayBaby: 'listDisplayBaby',
      getListBaby: 'getListBaby',
      page: 'page',
      listBabyTopReact: 'listBabyTopReact',
      getListBabyTopReact: 'getListBabyTopReact'
    }),
  },
  data() {
    return {
    }
  },
  methods: {
    pageChange(value: any) {
      this.setPage(value)
    },
    reloadPage() {
      if (localStorage.getItem('reloaded')) {
        localStorage.removeItem('reloaded');
      } else {
        localStorage.setItem('reloaded', '1');
        location.reload();
      }
    },
    selectPage(value: number) {
      return value == this.page
    }
  },
  mounted() {
    this.getListBaby(),
      this.getListBabyTopReact()
  }
})
</script>

<template>
  <div class="flex flex-col justify-between items-center">
    <Header />
    <Navigation />
    <div class="flex flex-col justify-between items-center">
      <div class="banner">
        <a href="#"><img
            src="https://1.bp.blogspot.com/-iJd0eLfRaqE/YIMzMGF1HRI/AAAAAAAAAPY/3dashLBXrSARgwkyMSyWU06pYmIGx863wCLcBGAsYHQ/w1200-h630-p-k-no-nu/20210424_035030.jpg"
            alt="gaixinhTQ"></a>
      </div>
      <h3>Gái xinh nhất trong tuần</h3>
      <div class="w-[980px] h-[500] flex flex-row items-center">
        <div v-for="item in listDisplayBaby"
          class="w-[320px] h-[440px] text-center p-2% border-2 border-solid border-gray-300" :key="item.id">
          <div class="flex flex-col items-center">
            <Product :NameProduct="item.name" :ImgProductUrl="item.url" :Description="item.description"
              :TotalLikes="item.vote" :Price="item.price" :Id="item.id" />
          </div>
        </div>
      </div>
    </div>
    <div class="flex flex-col justify-between items-center">
      <h3>Nhiều lượt tương tác</h3>
      <div class="w-[980px] h-[500] flex flex-row items-center">
        <div v-for="item in (listBabyTopReact as any)"
          class="w-[320px] h-[440px] text-center p-2% border-2 border-solid border-gray-300" :key="item.id">
          <div class="flex flex-col items-center">
            <Product :NameProduct="item.name" :ImgProductUrl="item.url" :Description="item.description"
              :TotalLikes="item.vote" :Price="item.price" :Id="item.id" />
          </div>
        </div>
      </div>
    </div>
    <div>
      <ul class="flex flex-row nav">
        <div v-for="n in totalPage">
          <li class="liPaging flex justify-center items-center cursor-pointer" :class="{ active: selectPage(n) }" @click="setPage(n)">{{ n }}</li>
        </div>
      </ul>
    </div>
    <Footer />
  </div>
</template>

<style>
.nav {
  display: flex;
  padding: 5px 10px;
  background-color: #ddd;
  color: #333;
  text-decoration: none;
  border-radius: 5px;
}

.liPaging {
  width: 54.5px;
  height: 32px;
}

.liPaging:hover {
  background-color: #53c2e4;
  color: #fff;
}
</style>