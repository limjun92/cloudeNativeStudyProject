<template>
    <div>
        <h3>PostInsert</h3>
        <div> 
            <table class="tbAdd">
                <colgroup>
                    <col width="10%" />
                    <col width="80%" />
                </colgroup>
                <tr>
                    <th>제목</th>
                    <td><input type="text" v-model="title"/></td>
                </tr>
               
                <tr>
                    <th>카테고리</th>
                    <td>
                        <!-- Level1 -->
                        <select v-model="level1Select">
                            <option
                                v-for="(item, index) in level1"
                                :key="index"
                                :value="item"
                                ><span v-if="item != 'null'">{{ item }}</span>
                            </option>
                        </select>
                        <!-- Level2 -->
                        <select v-model="level2Select">
                            <option
                                v-for="(item, index) in level2"
                                :key="index"
                                :value="item"
                                ><span v-if="item != 'null'">{{ item }}</span>
                            </option>
                        </select>
                        <!-- Level3 -->
                        <select v-model="level3Select">
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
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><MdEditor class="markdown" v-model="content" language="en-US" codeTheme="github" previewTheme="github"/></td>
                </tr>
            </table>
            <div>
                <imageList/>
            </div>
            <div>
                <imageUpload/>
            </div>
            <button v-on:click="postInsert()">
                등록
            </button>
        </div>
    </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import imageUpload from '@/components/util/imageUpload.vue'
import imageList from '@/components/util/imageList.vue'

export default {
    components: {
        MdEditor,
        imageUpload,
        imageList
    },
    setup(){
        const store = useStore();
        //1-2. 등록한 값의 RowId를 가져온다(추가 조회용)
        const PostInsertOut = computed(() => store.getters['post/getPostInsert'].outPut);
        const CategoryName = computed(() => store.getters['post/getCategoryName']);
        return {store, PostInsertOut, CategoryName}
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
        }
    },
    mounted() {
        this.level1 = Object.keys(this.CategoryName)
    },
    watch:{
        //2-1. 카테고리 정보 가져오기
        CategoryName(){
            this.level1 = Object.keys(this.CategoryName)
            this.categoryId = this.CategoryName[this.level1Select][this.level2Select][this.level3Select]
        },
        //2-2. level1 이름 변경시 level2, level3 셋팅 
        level1Select(){
            this.level2 = Object.keys(this.CategoryName[this.level1Select])
            this.level2Select = null,
            this.level3Select = null
            this.categoryId = this.CategoryName[this.level1Select][this.level2Select][this.level3Select]
        },
        //2-3. level2 이름 변경시 level3 셋팅 
        level2Select(){
            this.level3 = Object.keys(this.CategoryName[this.level1Select][this.level2Select])
            this.level3Select = null
            this.categoryId = this.CategoryName[this.level1Select][this.level2Select][this.level3Select]
        },
        level3Select(){
            this.categoryId = this.CategoryName[this.level1Select][this.level2Select][this.level3Select]
        }
    },
    methods:{
        async postInsert(){
            //1-1. 등록 서비스 호출
            const insertParams = {
                upsertParams:{
                    userId:"test",
                    title:this.title,
                    content:this.content,
                    categoryId:this.categoryId,
                }
            }
            await this.store.dispatch('post/postInsert', insertParams)

            //1-3. 화면 전환
            this.router.push('/Post/'+ this.PostInsertOut.rowId)
        }
    }
}
</script>

<style scoped>

.tbAdd{
    table-layout: fixed;
    width:100%;
}
select{
    width:100px;
}

.markdown{
    text-align: left;
}

</style>