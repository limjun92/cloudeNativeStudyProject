<template>
    <div>
        <h2>ScheduleInfo</h2>
        <div v-if="scheduleDetailInfo">
            <div>
                {{ scheduleDetailInfo["startDay"] }}
            </div>
            <div>
                {{ scheduleDetailInfo["endDay"] }}
            </div>
            <div>
                {{ scheduleDetailInfo["title"] }}
            </div>
            <div>
                {{ scheduleDetailInfo["content"] }}
            </div>
        </div>
        <div>
            <button v-on:click="scheduleDelete()">삭제</button>
            <button v-on:click="scheduleUpdate()">수정</button>
        </div>
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
        }
    },
    mounted(){
        if(this.$route.params.content){
            this.rowId = this.$route.params.param1
            this.scheduleDetail()
        }
    },
    methods: {
         //1 schedule 상세정보 조회
         async scheduleDetail(){
            const Params = {
                searchParams:{
                    rowId : this.rowId,
                    userId : 'test'
                }
            }
            await this.store.dispatch('scheduleSearch/scheduleDetail', Params)
        },

        //2. schedule 삭제
        async scheduleDelete(){
            const Params = {
                upsertParams:{
                    rowId: this.rowId
                }
            }
            await this.store.dispatch('scheduleUpsert/scheduleDelete', Params)

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
        },
        scheduleUpdate(){
            this.store.commit('modal/setModalClose')
            let url = '/Calendar/scheduleUpdate/'+ this.rowId
            this.$router.push(url)
        }
    }
}
</script>

<style>

</style>