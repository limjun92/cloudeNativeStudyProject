<template>
    <div>
        <h2>boardCategory</h2>
        <ul>
            <li  v-for = "category1, key in category" :key="key">
                <span v-on:click="BoardList(key,'null','null')">{{ key }}</span>
                <ul>
                    <template  v-for = "category2, key2 in category1" :key="key2">
                        <li v-if = "key2 != 'null'">
                            <span v-on:click="BoardList(key, key2, 'null')">{{ key2 }}</span>
                            <ul>
                                <template   v-for = "category3, key3 in category2" :key="key3">
                                    <li v-if = "key3 != 'null'">
                                        <span v-on:click="BoardList(key, key2, key3)">{{ key3 }}</span>
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
        const boardCategory = computed(() => store.getters['board/getBoardCategory']);
        const categoryKey = computed(() => store.getters['board/getCategoryKey']);
        store.dispatch('board/boardCategory');
        return {store, boardCategory, categoryKey}
    },
    data(){
        return{
            category:{},
            router : this.$router,
        }
    },
    async mounted(){
        await this.store.dispatch('board/boardCategory');
        this.boardCategory.forEach(element =>{
            if(element.category_Level_1 in this.category){
                if(element.category_Level_2 in this.category[element.category_Level_1]){
                    this.category[element.category_Level_1][element.category_Level_2][element.category_Level_3] = element.category_Id
                }else{
                    this.category[element.category_Level_1][element.category_Level_2] = {}
                    this.category[element.category_Level_1][element.category_Level_2][element.category_Level_3] = element.category_Id
                }
            }else{
                this.category[element.category_Level_1] = {}
                this.category[element.category_Level_1][element.category_Level_2] = {}
                this.category[element.category_Level_1][element.category_Level_2][element.category_Level_3] = element.category_Id
            }
        })
        this.store.commit('board/setCategory', this.category);
    },
    methods :{
        BoardList(key, key2, key3){
            const storeKey = {
                key : key,
                key2 : key2,
                key3 : key3,
            }
            this.store.commit('board/setCategoryKey', storeKey);

            this.router.push('/board')

            const storeObj = {
                pageNum : 1,
                pageSize : 5,
                //user_id : user.value.user_id,
                user_id : 'jh0508',
                key : this.categoryKey['key'],
                key2 : this.categoryKey['key2'],
                key3 : this.categoryKey['key3'],
            }
            this.$store.dispatch('board/boardListCategory', storeObj);
        }
    }
}
</script>

<style>

</style>