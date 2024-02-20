import axios from 'axios';

const api_url = process.env.VUE_APP_URL;

const state = {
    toDoInsert:{},
    toDoUpdate:{},
}

const getters = {
    getToDoInsert(state){
        return state.toDoInsert;
    },
    getToDoUpdate(state){
        return state.toDoUpdate;
    },
}

const actions = {
    //1. toDoList 등록
    async toDoInsert(context, upsertParams){
        await axios.post(`${api_url}/ToDoInsert`, upsertParams)
        .then(res => res.data)
        .then(item => (context.commit('setToDoInsert',item)))
        .catch(error => console.error(error));
    },

    //2. toDoList 수정
    async toDoUpdate(context, upsertParams){
        await axios.post(`${api_url}/ToDoUpdate`, upsertParams)
        .then(res => res.data)
        .then(item => (context.commit('setToDoUpdate',item)))
        .catch(error => console.error(error));
    }
}

const mutations = {
    setToDoInsert(state, item){
        state.toDoInsert = item.serviceResponse;
    },
    setToDoUpdate(state, item){
        state.toDoUpdate = item.serviceResponse;
    }
}

export default {
    namespaced: true,
    state, 
    getters, 
    actions, 
    mutations
}