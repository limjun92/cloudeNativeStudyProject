<template>
    <div>
        <h3>ImageList</h3>
        <div v-if = "imageList">
            <table>
                <tbody>
                    <tr v-for = "img, key in imageList.image" :key="key">
                        <td v-on:click="imageSelect(img.url)">
                            {{ img.name }}
                        </td>
                        <td v-on:click="imageSelect(img.url)">
                            {{ img.path }}
                        </td>
                        <td>
                            <button v-on:click="imageDelete(img.rowId, img.key)">삭제</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div v-if="url">
            <img class="image" :src= "`${url}`"/>
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
    data(){
        return{
            url: null,
            image: null,
            pageSize: 5,
        }
    },
    mounted(){
        //1. 이미지 리스트를 가져온다
        const Params = {
            searchParams:{
                userId : 'test',
                page : 1,
                pageSize : this.pageSize,
            }
        }
        this.$store.dispatch('image/imageList', Params);
    },
    methods:{
        //2. 이미지 선택시 보여준다
        imageSelect(imgName){
            this.url = imgName
        },
        //3. 이미지 삭제
        async imageDelete(rowId, key){
            //보여주는 부분 초기화
            this.image = null
            const upsertParams = {
                upsertParams:{
                    rowId : rowId,
                    key : key
                }
            }
            await this.$store.dispatch('image/imageDelete', upsertParams);

            const searchParams = {
                searchParams:{
                    userId : 'test',
                    page : 1,
                    pageSize : this.pageSize,
                }
            }
            this.$store.dispatch('image/imageList', searchParams);
        }

    }
}
</script>

<style scoped>

.image{
    width:500px;
}

</style>