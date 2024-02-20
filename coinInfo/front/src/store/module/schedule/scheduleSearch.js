import axios from 'axios';

const api_url = process.env.VUE_APP_URL;

const state = {
    scheduleList:{},
    scheduleDetail:{},
    scheduleWrite:{},
}

const getters = {
    getScheduleList(state){
        return state.scheduleList;
    },
    getScheduleDetail(state){
        return state.scheduleDetail;
    },
    getScheduleWrite(state){
        return state.scheduleWrite;
    },
}

const actions = {
    //1. schedule 리스트 가져오기
    async scheduleList(context, searchParams){
        await axios.post(`${api_url}/ScheduleList`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setScheduleList',item)))
        .catch(error => console.error(error));
    },
    async scheduleDetail(context, searchParams){
        await axios.post(`${api_url}/ScheduleDetail`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setScheduleDetail',item)))
        .catch(error => console.error(error));
    },
    scheduleWrite(context, searchParams){
        axios.post(`${api_url}/ScheduleWrite`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setScheduleWrite',item)))
        .catch(error => console.error(error));
    }
}

const mutations = {
    setScheduleList(state, item){
        state.scheduleList = item.serviceResponse;
    },
    setScheduleDetail(state, item){
        state.scheduleDetail = item.serviceResponse;
    },
    setScheduleWrite(state, item){
        state.scheduleWrite = item.serviceResponse;
    }
}

export default {
    namespaced: true,
    state, 
    getters, 
    actions, 
    mutations
}