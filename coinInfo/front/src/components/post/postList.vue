<template>
  <div>
    <h3>PostList</h3>
    <div>
      {{this.$route.params.level1}}
      {{this.$route.params.level2}}
      {{this.$route.params.level3}}
      
    </div>
    <!--post 생성여부까지 확인한다(데이터를 가져온 후 랜더링)-->
    <div v-if = "postList" class="postList">
      <table class = "postListTable">
        <!-- HEAD -->
        <thead>
          <tr>
            <th>NO</th>
            <th>Title</th>
            <th>등록일</th>
          </tr>
        </thead>
        <!-- BODY -->
        <tbody>
          <tr class="content" v-for="(post, index) in postList.post" :key="index">
            <td>
              {{ index + 1 + (pageSize * ($route['params']['page'] - 1))}}
            </td>
            <td class="title" v-on:click="postDetail(post.rowId)">
                {{post.title}}
            </td>
            <td>
                {{post.createDt.substr(0, 10)}}
            </td>
          </tr>
        </tbody>
      </table>
      <div>
        <button v-on:click="postLeft()" v-bind:disabled = "$route['params']['page']=='1'">
          ◁
        </button>
        {{$route['params']['page'] * pageSize}}
        <button v-on:click="postRight()" v-bind:disabled = "($route['params']['page'] * pageSize) >= postList['postCount']">
          ▷
        </button>
      </div>
      <!--등록 버튼-->
      <div>
        <button v-on:click="postInsert()">
          등록
        </button>
      </div>
    </div>
    
  </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

//1. PostList를 가져온다.
//2. Post 등록 화면 이동
//3. Post 상세 화면 이동
//4. Category에 따라 조회
//5. 페이징
export default {
    setup(){
        const store = useStore();

        const postList = computed(() => store.getters['post/getPostList'].outPut);
        return {store, postList}
    },
    data(){
      return{
        route:this.$route,
        routeFlg:true,
        pageSize:10,

        nextUrl:"",
      }
    },
    mounted(){
      //1. PostList를 가져온다.(새로고침)
      const Params = {
        searchParams:{
          userId : 'test',
          activeFlag : 'Y',
          page : this.$route.params.page,
          pageSize : this.pageSize,
          level1 : this.$route.params.level1?this.$route.params.level1:null,
          level2 : this.$route.params.level2?this.$route.params.level2:null,
          level3 : this.$route.params.level3?this.$route.params.level3:null
        }
      }
      this.$store.dispatch('post/postList', Params);
    },
    watch:{
      //4. Category 클릭시 route의 변경을 감지해서 Category값으로 조회
      $route(){
        if(this.routeFlg){
          const Params = {
            searchParams:{
              userId : 'test',
              activeFlag : 'Y',
              page : this.$route.params.page,
              pageSize : this.pageSize,
              level1 : this.$route.params.level1?this.$route.params.level1:null,
              level2 : this.$route.params.level2?this.$route.params.level2:null,
              level3 : this.$route.params.level3?this.$route.params.level3:null
            }
          }
          this.$store.dispatch('post/postList', Params);
        }
      }
    },
    methods:{
      //3. Post 상세 화면 이동
      postDetail(rowId){
        this.routeFlg = false
        this.$router.push('/Post/'+ rowId)
      },
      //2. Post 등록 화면 이동
      postInsert(){
        this.routeFlg = false
        this.$router.push('/Post/Write')
      },

      //5. 페이지 이동 구현
      postRight(){
        this.nextUrl = ""
        this.makeUrl(1)

        this.$router.push(this.nextUrl)
      },
      postLeft(){
        this.nextUrl = ""
        this.makeUrl(-1)

        this.$router.push(this.nextUrl)
      },
      //function
      makeUrl(way){
        let params = this.$route['params']
        this.nextUrl = '/Post/List/' + ((params['page']*=1) + way)

        for(let key in params){
            if(key == 'page')
              continue;
            this.nextUrl += '/' + params[key]
        }
      },
    }
    
}
</script>

<style scoped>
.title{
  text-align: left;
}
</style>