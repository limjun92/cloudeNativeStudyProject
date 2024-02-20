import axios from 'axios';

const api_url = process.env.VUE_APP_URL;

const state = {
    calendarDate:[],
    calendarInfoList:{},
}

const getters = {
    getCalendarDate(state){
        return state.calendarDate;
    },
    getCalendarInfoList(state){
        return state.calendarInfoList;
    },
}

const actions = {
    //1. toDo 리스트 가져오기
    async calendarInfoList(context, searchParams){
        await axios.post(`${api_url}/CalendarInfoList`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setCalendarInfoList',item)))
        .catch(error => console.error(error));
    },
}

const mutations = {
    setCalendarDate(state, item){
        state.calendarDate = item;
    },
    setCalendarInfoList(state, item){
        state.calendarInfoList = {}
        if(item.serviceResponse.outPut!=null){
            for(let i of item.serviceResponse.outPut.calendarInfo){
                state.calendarInfoList[i["day"].substr(0,10)] = i
            }
        }
    },
}

export default {
    namespaced: true,
    state, 
    getters, 
    actions, 
    mutations
}