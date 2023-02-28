<script lang="ts">
import Header from '../components/Header.vue'
import Navigation from '../components/Navigation.vue'
import Footer from '../components/Footer.vue'
import { mapState } from 'pinia'
import { useHomeStore } from '../stores/home'
import { defineComponent } from 'vue';
export default defineComponent({
  name: 'Home',
  components: {
    Header,
    Navigation,
    Footer
  },
  computed: {
    ...mapState(useHomeStore, {
      totalPage: 'totalPage',
      setPage: 'setPage',
      listDisplayBaby: 'listDisplayBaby',
      getListBaby: 'getListBaby',
      page: 'page'
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
    this.getListBaby()
  }
})
</script>

<template>
  <div class="container">
    <Header />
    <Navigation />
    <div class="banner">
      <a href="#"><img
          src="https://1.bp.blogspot.com/-iJd0eLfRaqE/YIMzMGF1HRI/AAAAAAAAAPY/3dashLBXrSARgwkyMSyWU06pYmIGx863wCLcBGAsYHQ/w1200-h630-p-k-no-nu/20210424_035030.jpg"
          alt="gaixinhTQ"></a>
    </div>

    <article>
      <h3>Gái xinh nhất trong tuần</h3>
      <div class="new_list">
        <div v-for="item in listDisplayBaby" class="new_item" :key="item.id">
          <h4 id="name1">{{ item.description }}</h4>
          <router-link :to="{ name: 'detail', params: { id: item.id } }">
            <img id="url1" :src="item.url" :alt="item.description">
          </router-link>
          <div id="des1" class="content">
            <div class="item">
              <div id="like">
                <img src="https://png.pngtree.com/element_our/20190602/ourlarge/pngtree-color-smiley-face-expression-image_1410314.jpg" alt="">
              </div>
              <div id="price">
                ${{item.price}}
              </div>
              <div id="vote">
                <img src="https://cdn-icons-png.flaticon.com/512/1601/1601243.png" alt="">
              </div>
            </div>
          </div>
        </div>
      </div>
    </article>

    <article>
      <h3>Nhiều lượt tương tác</h3>
      <div class="new_list">
        <div v-for="item in listDisplayBaby" class="new_item" :key="item.id">
          <h4 id="name1">{{ item.description }}</h4>
          <router-link :to="{ name: 'detail', params: { id: item.id } }">
            <img id="url1" :src="item.url" :alt="item.description">
          </router-link>
          <div id="des1" class="content">
            ${{item.price}}
          </div>
        </div>
      </div>
    </article>

    <div class="navigation">
      <div class="flex">
        <ul>
          <div class="items" v-for="n in totalPage">
            <li :class="{ active: selectPage(n) }" @click="setPage(n)">{{n}}</li>
          </div>
        </ul>
      </div>
    </div>
    <Footer />
  </div>
</template>