<template>
  <div>
    <h3>PostDetail</h3>
    {{rowId}}
    <button v-on:click="postList()">
      목록
    </button>
    <button v-on:click="postUpdate()">
      수정
    </button>
    <button v-on:click="postDelete()">
      삭제
    </button>
    <div v-if = "postDetail">
      <div class="title">
        <span>{{postDetail.title}}</span>
      </div>
      <div class="category">
        <div>{{postDetail.level1}}</div>
        <div>{{postDetail.level2}}</div>
        <div>{{postDetail.level3}}</div>
      </div>
      <div class="content">
        <MdEditor v-model="postDetail.content"  previewOnly language="en-US" codeTheme="github" previewTheme="github"/>
      </div>
    </div>
  </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";
import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';

//1 post 상세정보 조회
//2 수정화면으로 이동
//3 삭제 후 리스트로 이동
//4 목록 클릭시 Post 리스트 화면 이동 
export default {
  components:{
    MdEditor,
  },
  setup(){
      const store = useStore();
      const postDetail = computed(() => store.getters['post/getPostDetail'].outPut);
      return {store, postDetail}
  },
  data(){
      return{
        router:this.$router,
      }
    },
  computed: {
    rowId(){
      return this.$route.params.id
    }
  },
  mounted(){
    //1 post 상세정보 조회
    const Params = {
      searchParams:{
        rowId : this.rowId
      }
    }
    this.store.dispatch('post/postDetail', Params)
  },
  methods: {
    //2 수정화면으로 이동
    postUpdate(){
      this.router.push('/Post/Update/' + this.rowId)
    },
    //3-1 Post 삭제(activeFlag = 'N') 
    async postDelete(){
      const Params = {
        upsertParams:{
          rowId : this.rowId
        }
      }
      await this.store.dispatch('post/postDelete', Params)

      //3-2 POST LIST로 화면 전환
      let url = localStorage.getItem('returnUrl')
      this.router.push(url)
    },
    //4 목록 클릭시 Post 리스트 화면 이동 
    postList(){
      let url = localStorage.getItem('returnUrl')
      this.router.push(url)
    }
    
  },
}
</script>

<style scoped>
.content{
  text-align: left;
}
</style>