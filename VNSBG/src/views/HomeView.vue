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
  <div class="">
    <Header />
    <Navigation />

    <div class="top-girl">
      <div class="banner">
        <a href="#"><img
            src="https://1.bp.blogspot.com/-iJd0eLfRaqE/YIMzMGF1HRI/AAAAAAAAAPY/3dashLBXrSARgwkyMSyWU06pYmIGx863wCLcBGAsYHQ/w1200-h630-p-k-no-nu/20210424_035030.jpg"
            alt="gaixinhTQ"></a>
      </div>
      <h3>Gái xinh nhất trong tuần</h3>
      <div class="new_list">
        <div v-for="item in listDisplayBaby" class="new_item" :key="item.id">
          <div class="content-product">
            <Product :NameProduct="item.name" :ImgProductUrl="item.url" :Description="item.description"
              :TotalLikes="item.vote" :Price="item.price" :Id="item.id"/>
          </div>
        </div>
      </div>
    </div>
    <div class="top-girl">
      <h3>Nhiều lượt tương tác</h3>
      <div class="new_list">
        <div v-for="item in (listBabyTopReact as any)" class="new_item" :key="item.id">
          <div class="content-product">
            <Product :NameProduct="item.name" :ImgProductUrl="item.url" :Description="item.description"
              :TotalLikes="item.vote" :Price="item.price" :Id="item.id"/>
          </div>
        </div>
      </div>
    </div>
    <div class="navigation">
      <div class="flex">
        <ul>
          <div class="items" v-for="n in totalPage">
            <li :class="{ active: selectPage(n) }" @click="setPage(n)">{{ n }}</li>
          </div>
        </ul>
      </div>
    </div>
    <Footer />
  </div>
</template>

<style>
.top-girl {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}

.new_list {
  width: 980px;
  height: 500px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.new_list .new_item img:hover {
  transform: rotateY(0deg) scale(1.05);
}

.content-product {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.new_list .new_item {
  width: 320px;
  height: 440px;
  padding: 2%;

  border: 2px solid #ccc;
  margin-right: 5px;
  text-align: center;
}

.new_list .new_item h4 {
  font-size: 16px;
  color: rgb(31, 28, 25);
}
</style>