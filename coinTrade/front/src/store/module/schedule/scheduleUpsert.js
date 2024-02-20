import axios from 'axios';

const api_url = process.env.VUE_APP_URL;

const state = {
    scheduleWrite:{},
    scheduleDelete:{},
    scheduleUpdate:{},
}

const getters = {
    getScheduleWrite(state){
        return state.scheduleWrite;
    },
    getScheduleDelete(state){
        return state.scheduleDelete;
    },
    getScheduleUpdate(state){
        return state.scheduleUpdate;
    },
}

const actions = {
    async scheduleWrite(context, searchParams){
        await axios.post(`${api_url}/ScheduleWrite`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setScheduleWrite',item)))
        .catch(error => console.error(error));
    },
    async scheduleDelete(context, searchParams){
        await axios.post(`${api_url}/ScheduleDelete`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setScheduleDelete',item)))
        .catch(error => console.error(error));
    },
    async scheduleUpdate(context, searchParams){
        await axios.post(`${api_url}/ScheduleUpdate`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setScheduleUpdate',item)))
        .catch(error => console.error(error));
    },
}

const mutations = {
    setScheduleWrite(state, item){
        state.scheduleWrite = item.serviceResponse;
    },
    setScheduleDelete(state, item){
        state.scheduleDelete = item.serviceResponse;
    },
    setScheduleUpdate(state, item){
        state.scheduleUpdate = item.serviceResponse;
    },
}

export default {
    namespaced: true,
    state, 
    getters, 
    actions, 
    mutations
}