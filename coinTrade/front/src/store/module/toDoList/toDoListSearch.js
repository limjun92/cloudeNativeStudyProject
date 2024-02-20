import axios from 'axios';

const api_url = process.env.VUE_APP_URL;

const state = {
    toDoList:{},
}

const getters = {
    getToDoList(state){
        return state.toDoList;
    },
}

const actions = {
    //1. toDo 리스트 가져오기
    toDoList(context, searchParams){
        axios.post(`${api_url}/ToDoList`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setToDoList',item)))
        .catch(error => console.error(error));
    },
}

const mutations = {
    setToDoList(state, item){
        state.toDoList = item.serviceResponse;
    }
}

export default {
    namespaced: true,
    state, 
    getters, 
    actions, 
    mutations
}