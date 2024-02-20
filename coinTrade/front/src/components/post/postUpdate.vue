<template>
    <div>
        <h3>PostUpdate</h3>
        <!-- ===========================================-->
        <!--=================== 제목 ===================-->
        <div>
            <input v-if = "postDetail" type="text" v-model="title"/>
        </div>
        <!-- ===========================================-->
        <!--================== 카테고리 ==================-->
        <div>
            <!-- Level1 -->
            <select v-model="level1Select" v-on:click="changCtgrFlg()" >
                <option
                    v-for="(item, index) in level1"
                    :key="index"
                    :value="item"
                    ><span v-if="item != 'null'">{{ item }}</span>
                </option>
            </select>
            <!-- Level2 -->
            <select v-model="level2Select" v-on:click="changCtgrFlg()" >
                <option
                    v-for="(item, index) in level2"
                    :key="index"
                    :value="item"
                    ><span v-if="item != 'null'">{{ item }}</span>
                </option>
            </select>
            <!-- Level3 -->
            <select v-model="level3Select" v-on:click="changCtgrFlg()" >
                <option
                    v-for="(item, index) in level3"
                    :key="index"
                    :value="item"
                    >
                    <span v-if="item != 'null'">{{ item }}</span>
                </option>
            </select>
            {{ level1Select }}, {{ level2Select }}, {{ level3Select }}
            <div v-if = "CategoryName[level1Select] && CategoryName[level1Select][level2Select] && CategoryName[level1Select][level2Select][level3Select]">
            {{ categoryId }}
            </div>
        </div>
        <!-- ===========================================-->
        <!--=================== 내용 ===================-->
        <div>
            <MdEditor class="markdown" v-model="content" language="en-US" codeTheme="github" previewTheme="github"/>
        </div>
        <!--이미지 리스트-->
        <div>
            <imageList/>
        </div>
        <!--이미지 업로드-->
        <div>
            <imageUpload/>
        </div>
        <button v-on:click="postUpdate()">
            등록
        </button>
    </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import imageUpload from '@/components/util/imageUpload.vue'
import imageList from '@/components/util/imageList.vue'

//1 : PostDetail값 가져오기
//2 : 수정 내용 등록
//3 : 카테고리 적용하기(기존값 가져오기 및 수정가능)
export default {
    components: {
        MdEditor,
        imageUpload,
        imageList
    },
    setup(){
        const store = useStore();
        const postDetail = computed(() => store.getters['post/getPostDetail'].outPut);
        const CategoryName = computed(() => store.getters['post/getCategoryName']);
        return {store, postDetail, CategoryName}
    },
    data() {
        return {
            title: "",
            content: "",
            router:this.$router,

            level1:[],
            level2:[],
            level3:[],
            level1Select:"null",
            level2Select:"null",
            level3Select:"null",
            categoryId:1,
            ctgrFlg:false
        }
    },
    mounted(){
        //1-2 rowId를 사용해 조회를 호출한다.(바로 매핑안하고 또 호출하는 이유는 "새로고침"때문)
        const Params = {
          searchParams:{
            rowId : this.rowId
          }
        }
        this.store.dispatch('post/postDetail', Params)
    },
    watch:{
        //1-3 조회후 response가 감지되면 title과 content를 채워준다.
        postDetail(){
            this.title = this.postDetail.title
            this.content = this.postDetail.content

            //3-1 카테고리 값을 채워준다 level1Select가 변경되면 watch이벤트 발생
            this.level1 = Object.keys(this.CategoryName)
            this.level1Select = this.postDetail.level1
            this.categoryId = this.postDetail.ctgrId
        },
        //2-1. 카테고리 정보 가져오기
        CategoryName(){
            this.level1 = Object.keys(this.CategoryName)
            this.categoryId = this.CategoryName[this.level1Select][this.level2Select][this.level3Select]
        },
        //2-2. level1 이름 변경시 level2, level3 셋팅 
        level1Select(){
            this.level2 = Object.keys(this.CategoryName[this.level1Select])
            if(this.ctgrFlg){
                this.level2Select = null,
                this.level3Select = null
                this.categoryId = this.CategoryName[this.level1Select][this.level2Select][this.level3Select]
            //3-2 Detail에서 수정버튼 클릭 후나 새로고침시 this.ctgrFlg 가 false로 기존값을 셋팅
            }else{
                this.level2Select = this.postDetail.level2
            }
        },
        //2-3. level2 이름 변경시 level3 셋팅 
        level2Select(){
            this.level3 = Object.keys(this.CategoryName[this.level1Select][this.level2Select])
            if(this.ctgrFlg){
                this.level3Select = null
                this.categoryId = this.CategoryName[this.level1Select][this.level2Select][this.level3Select]
            //3-2 Detail에서 수정버튼 클릭 후나 새로고침시 this.ctgrFlg 가 false로 기존값을 셋팅
            }else{
                this.level3Select = this.postDetail.level3
            }
        },
        level3Select(){
            this.categoryId = this.CategoryName[this.level1Select][this.level2Select][this.level3Select]
        }
    },
    computed: {
        //1-1 postDetail에서 수정버튼 클릭 시 router를 통해 의 rowId를 가져온다.
        rowId(){
            return this.$route.params.id
        }
    },
    methods:{
        async postUpdate(){
            //2-1 수정 서비스 호출
            const updateParams = {
                upsertParams:{
                    rowId:this.rowId,
                    userId:"test",
                    title:this.title,
                    content:this.content,
                    categoryId:this.categoryId,
                }
            }
            await this.store.dispatch('post/postUpdate', updateParams)

            //2-2. 화면 전환
            this.router.push('/Post/'+ this.rowId)
        },
        //3-3 combo box(select)중 하나로도 클릭시 ctgrFlg를 true로 변경한다.
        changCtgrFlg(){
            this.ctgrFlg = true;
        }
    }
}
</script>

<style>

select{
    width:100px;
}

.markdown{
    text-align: left;
}

</style>