<template>
  <div class="albumcontainer">
    <div class="albumBundle" v-for="(item, index) in albumList" :key="index">
      <div class="album" v-for="(i, cindex) in item" :key="cindex">
        <img class="albumImage" :src= "`${i['url']}`"/>
      </div>
    </div>
  </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    setup(){
        const store = useStore();
        const imageList = computed(() => store.getters['image/getImageList'].outPut);
        return {store, imageList}
    },

    data() {
      return {
        page: 1,
        pageSize: 12,
        albumList:[],
        albumLen:0,
      };
    },

    watch:{
      imageList(){
        if(this.imageList!=null){
          this.albumList.push(this.imageList['image'])
        }
      }
    },
  
    mounted() { 
      // 컴포넌트가 마운트되면 window의 scroll 이벤트 리스너 등록
      window.addEventListener('scroll', this.handleScroll);
      
      //첫 이미지 조회
      const Params = {
            searchParams:{
                userId : 'test',
                page : this.page,
                pageSize : this.pageSize,
            }
        }
        this.$store.dispatch('image/imageList', Params);
    },

    beforeUnmount() { 
      // 컴포넌트 제거 전에 scroll 이벤트 리스너 해제
      window.removeEventListener('scroll', this.handleScroll);
    },

    methods: {
      async handleScroll() {
        if ((window.innerHeight + window.scrollY) >= document.body.scrollHeight-5 && this.imageList!=null && this.albumLen < this.imageList['imageCount']) { 
          // 스크롤이 하단에 도달한 경우
          this.loadMore();
          await this.sleep(200)
        }
    },
    
    async loadMore() {
      this.page++;
      const Params = {
          searchParams:{
              userId : 'test',
              page : this.page,
              pageSize : this.pageSize,
          }
      }
      await this.$store.dispatch('image/imageList', Params);
    },
    sleep (delay) {
      var start = new Date().getTime();
      while (new Date().getTime() < start + delay);
    }
   }
};
</script>

<style scoped>

.albumBundle{
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(4, 20vw);
  grid-gap: 0.2rem;
  padding-bottom: 0.2rem;
}
.albumImage{
  width:100%;
  height:100%;
  object-fit:cover;
}

@media (max-width: 500px) {
  .albumBundle {
    grid-template-rows: repeat(4, 20vh);
  }
}

</style>