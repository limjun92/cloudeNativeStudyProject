<template>
    <h1>게시글 등록</h1>
        <!-- <textarea v-model="contents"/> -->
        <!-- <div contenteditable="false">{{ contents }}</div> -->
        <div class="AddWrap">
            <button v-on:click="editForm()">edit</button>
            <button v-on:click="previewForm()">preview</button>
            <table class="tbAdd">
                <colgroup>
                    <col width="10%" />
                    <col width="90%" />
                </colgroup>
                <tr>
                    <th>제목</th>
                    <td v-if = "editFlag==true"><input type="text" v-model="title"/></td>
                    <td v-else class="title" >{{ title }}</td>
                </tr>
                <tr>
                    <th>카테고리</th>
                    <td class='category'>
                        <select v-model="selected1">
                            <option
                                v-for="(item, index) in category1"
                                :key="index"
                                :value="item"
                                >{{ item }}
                            </option>
                        </select>
                        {{ selected1 }}
                        <select v-model="selected2">
                            <option
                                v-for="(item, index) in category2"
                                :key="index"
                                :value="item"
                                >{{ item }}
                            </option>
                        </select>
                        {{ selected2 }}
                        <select v-model="selected3">
                            <option
                                v-for="(item, index) in category3"
                                :key="index"
                                :value="item"
                                >{{ item }}
                            </option>
                        </select>
                        {{ selected3 }}
                        {{ categoryId }}
                    </td>
                </tr>
                <tr v-if = "editFlag==true">
                    <th>내용</th>
                    <td><MdEditor class="markdown" v-model="content" language="en-US" codeTheme="github" previewTheme="github"/></td>
                </tr>
                <tr v-else>
                    <th>내용</th>
                    <td><MdEditor class="markdown" v-model="content" previewOnly language="en-US" codeTheme="github" previewTheme="github"/></td>
                </tr>
            </table>
		</div>
        <div class="btnWrap">
			<button v-on:click="boardWrite()">등록</button>
        </div>	
</template>

<script>
import {useStore} from "vuex";
//import Markdown from 'vue3-markdown-it';
import {computed} from "vue";

import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';

export default {
    components: {
        //Markdown,
        MdEditor
    },
    data() {
        return {
            contents:'<span>테스트코드</span>',
            title: "",
            content: "",
            router : this.$router,
            editFlag : true,

            selected1 : null,
            selected2 : null,
            selected3 : null,
            category : {},
            category1 : [],
            category2 : [],
            category3 : [],
            categoryId : -1
        }
    },
    mounted(){
        this.category1 = Object.keys(this.Category)
    },  
    setup(){
        const store = useStore();
        const boardDetail = computed(() => store.getters['board/getBoardDetail'])
        const Category = computed(() => store.getters['board/getCategory']);

        const BoardCategory = computed(() => store.getters['board/getBoardCategory']);
        return {store, boardDetail, Category, BoardCategory}
    },
    watch: {
        //등록후 변화를 감지해서 등록한 게시물로 이동 
        boardDetail: function () {
            this.router.push('/Board/'+ this.boardDetail.board_id)
        },
        selected1: function(){
            this.category2 = Object.keys(this.Category[this.selected1])
            this.selected2 = null
        },
        selected2: function(){
            if(this.selected2 == null){
                this.category3 = []
            }else{
                this.category3 = Object.keys(this.Category[this.selected1][this.selected2])
            }
            this.selected3 = null
        },
    },
    methods:{
        boardWrite(){

            this.BoardCategory.forEach(element =>{
                if(element.category_Level_1 == this.selected1 && element.category_Level_2 == this.selected2 && element.category_Level_3 == this.selected3){
                    this.categoryId = element.category_Id
                }
            })

            const loginObj={
                categoryId : this.categoryId,
                title : this.title,
                content : this.content,
                user_Id : 'jh0508'
            }
            this.store.dispatch('board/boardWrite', loginObj);
        },
        editForm(){
            this.editFlag = true;
        },
        previewForm(){
            this.editFlag = false;
        }
    }
}
</script>

<style scoped>
.tbAdd{
    border-collapse: collapse;
    width:100%;
}
.tbAdd{
    border-top:1px solid #888;
    table-layout: fixed;
}
.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0;}
.tbAdd td{padding:10px 10px; box-sizing:border-box; vertical-align: top;}
.tbAdd td input{width:100%; min-height:30px; box-sizing:border-box; padding:0 10px;}
.tbAdd td textarea{width:100%; min-height:300px; padding:10px; box-sizing:border-box;}
.btnWrap{text-align:center; margin:20px 0 0 0;}
.btnWrap a{margin:0 10px;}

.markdown{
    text-align: left;
    word-wrap: break-word;
    
}

select{
    width:100px;
}
</style>