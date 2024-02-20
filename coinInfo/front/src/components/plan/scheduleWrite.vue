<template>
    <div>
        <h2>scheduleWrite</h2>
        {{ startDay }}
        {{ endDay }}
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
        <button v-on:click="scheduleWrite()">
            등록
        </button>
    </div>
</template>

<script>

import {useStore} from "vuex";

export default {
    setup(){
        const store = useStore();
        return {store}
    },
    data(){
        return{
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
            this.startDay = this.$route.params.param1
            this.endDay = this.$route.params.param2
        }
    },
    methods:{
        async scheduleWrite(){
            //Schedule 등록 서비스 호출
            const Params = {
                upsertParams:{
                    userId:'test',
                    startDay: this.startDay,
                    endDay:this.endDay,
                    title: this.title,
                    content:this.content,
                    color:this.color,
                }
            }
            await this.store.dispatch('scheduleUpsert/scheduleWrite', Params)

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