<template>
    <div>
        <h2>scheduleUpdate</h2>
        <input v-model="startDay">
        <input v-model="endDay">
        <select v-model="color">
            <option 
                v-for="(item, index) in colorSelectList"
                :key="index" 
                :style="{backgroundColor: item}"
                >
                <span >{{ item }}</span>
            </option
            >
        </select>
        <div>
            {{ color }}
        </div>
        <div>
            title
            <input v-model="title">
        </div>
        <div>
            content
            <textarea v-model="content"></textarea>
        </div>
        <button v-on:click="scheduleUpdate()">
            등록
        </button>
    </div>
</template>

<script>

import {useStore} from "vuex";
import {computed} from "vue";

export default {
    setup(){
        const store = useStore();
        const scheduleDetailInfo = computed(() => store.getters['scheduleSearch/getScheduleDetail'].outPut);
        return {store, scheduleDetailInfo}
    },
    data(){
        return{
            rowId:"",

            startDay:"",
            endDay:"",
            

            title: "",
            content:"",

            colorSelectList:[
                "#e74c3c",
                "#3498db",
                "#9b59b6",
                "#2ecc71",
                "#7f8c8d",
                "#f1c40f"
            ],
            color:"#7f8c8d",
        }
    },
    mounted(){
      if(this.$route.params.content){
            this.rowId = this.$route.params.param1
            this.scheduleDetail()

            this.startDay = this.scheduleDetailInfo["startDay"].substr(0, 10);
            this.endDay = this.scheduleDetailInfo["endDay"].substr(0, 10);
            this.title = this.scheduleDetailInfo["title"]
            this.content = this.scheduleDetailInfo["content"]
            this.color = this.scheduleDetailInfo["color"]
        }
    },
    methods:{
        async scheduleDetail(){
            const Params = {
                searchParams:{
                    rowId : this.rowId,
                    userId : 'test'
                }
            }
            await this.store.dispatch('scheduleSearch/scheduleDetail', Params)
        },
        async scheduleUpdate(){
            //Schedule 등록 서비스 호출
            const Params = {
                upsertParams:{
                    rowId:this.rowId,
                    userId:'test',
                    startDay: this.startDay,
                    endDay:this.endDay,
                    title: this.title,
                    content:this.content,
                    color:this.color,
                }
            }
            await this.store.dispatch('scheduleUpsert/scheduleUpdate', Params)

            //Schedule 조회 서비스 호출
            //조회 서비스 호출시 calendarOnly Watch에서 변경 감지 후 Schedule일정 draw
            const searchParams = {
                searchParams:{
                    userId : 'test',
                }
            }
            this.$store.dispatch('scheduleSearch/scheduleList', searchParams);

            //등록시 modal Close
            this.store.commit('modal/setModalClose')
            let url = '/Calendar'
            this.$router.push(url)
        }
    },
}
</script>

<style>
</style>