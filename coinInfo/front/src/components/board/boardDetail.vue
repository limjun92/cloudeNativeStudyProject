<template>
    <div>
    <h1>boardDetail</h1>
    <!-- {{ $route.params.id }} -->
    <!-- {{ boardDetail }} -->
    <button v-on:click="boardUpdate()">수정</button>
    <div class="container">
        <div class="containerBoardDetail">
            <div class="title"><h2> {{boardDetail.title}}</h2></div>
            <div class="title"><span> {{boardDetail.category_Level_1}} {{boardDetail.category_Level_2}} {{boardDetail.category_Level_3}}</span></div>
            <div class="content">
                <!--previewTheme : highlight 색-->
                <!--https://github.com/imzbf/md-editor-v3 참고-->
                <MdEditor v-model="boardDetail.content" previewOnly language="en-US" codeTheme="github" previewTheme="github"/>
                <!-- <Markdown :source="boardDetail.content"/> -->
            </div>
        </div>
    </div>
    </div>
</template>

<script>


import {useStore} from "vuex";
import {computed} from "vue";

// import Markdown from 'vue3-markdown-it';
// import 'highlight.js/styles/default.css';

import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
// MdEditor.config({
//   editorConfig: {
//     mermaidTemplate: {
//       flow: `flow tempalte`,
//     },
//     // Default 500ms. It is set to 0ms when preview only and not set.
//     renderDelay: 500
//   }
// });
// import MarkdownItStrikethroughAlt from 'markdown-it-strikethrough-alt';

export default{
    components: {
        // Markdown,
        MdEditor,
    },
    data(){
        return{
            router:this.$router,
        }
    },
    setup(){
        const store = useStore();
        const boardDetail = computed(() => store.getters['board/getBoardDetail']);
        return {boardDetail}
    },
    created(){
    },
    mouted(){
    },
    methods:{
        boardUpdate(){
            this.router.push({
                name:"BoardUpdate",
                query:{board_id: this.boardDetail.board_id}
            })
        }
    }  

}
</script>

<style scoped>
.container{
    width : 100%;
    display: flex;
    border-style: solid;
}
.containerBoardDetail {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.title{
    text-align:left;
    padding: 40px;
}
.content{
    text-align: left;
    padding: 40px;
}
</style>