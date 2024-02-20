<template>
    <div>
        <h3>postCategory</h3>
        <ul>
            <li  v-for = "category1, key in category" :key="key">
                <span v-on:click="postList(key,'null','null')">
                    <span v-if="key=='null'" >전체조회</span>
                    <span v-else>{{ key }}</span>
                </span>
                <ul>
                    <template  v-for = "category2, key2 in category1" :key="key2">
                        <li v-if = "key2 != 'null'">
                            <span v-on:click="postList(key, key2, 'null')">{{ key2 }}</span>
                            <ul>
                                <template   v-for = "category3, key3 in category2" :key="key3">
                                    <li v-if = "key3 != 'null'">
                                        <span v-on:click="postList(key, key2, key3)">{{ key3 }}</span>
                                    </li>
                                </template>
                            </ul>
                        </li>
                    </template>
                </ul>
            </li>
        </ul>
    </div>
</template>

<script>

import {useStore} from "vuex";
import {computed} from "vue";

export default {
    setup(){
        const store = useStore();
        const postCategory = computed(() => store.getters['post/getPostCategory'].outPut);
        return {store, postCategory}
    },
    data(){
      return{
        category:{null:{null:{null:0}}},
        router:this.$router,
      }
    },
    mounted(){
        const Params = {
            searchParams:{
                activeFlag : 'Y'
            }
        }
        this.store.dispatch('post/postCategory', Params)
    },
    watch: {
        //1. 카테고리데이터를 가져오고 보여주기 위해 변환
        postCategory: function () {
            this.postCategory.postCategoryList.forEach(element =>{
                if(element.level1 in this.category){
                    if(element.level2 in this.category[element.level1]){
                        this.category[element.level1][element.level2][element.level3] = element.rowId
                    }else{
                        this.category[element.level1][element.level2] = {}
                        this.category[element.level1][element.level2][element.level3] = element.rowId
                    }
                }else{
                    this.category[element.level1] = {}
                    this.category[element.level1][element.level2] = {}
                    this.category[element.level1][element.level2][element.level3] = element.rowId
                }
            })
            this.store.commit('post/setCategoryName', this.category)
        }
    },
    methods:{
        //2. 카테고리 클릭시 카테고리 param을 넘겨준다.
        postList(key, key2, key3){
            let url
            if(key!="null" && key2!="null" && key3!="null"){
                url = '/Post/List/1/'+ key + '/' + key2 + '/' + key3
                this.router.push(url)
            }
            else if(key!="null" && key2!="null"){
                url = '/Post/List/1/'+ key + '/' + key2
                this.router.push(url)
            }
            else if(key!="null"){
                url = '/Post/List/1/'+ key
                this.router.push(url)
            }
            else{
                url = '/Post/List/1'
                this.router.push(url)
            }
            //3. localStorage에 호출 URL정보를 저장한다.
            //PostDetail에서 목록으로 돌아올때 마지막 URL리스트로 돌아오기 위함
            localStorage.setItem('returnUrl', url)
        }
    }
}
</script>

<style>

</style>