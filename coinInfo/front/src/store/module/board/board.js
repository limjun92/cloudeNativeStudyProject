import axios from 'axios';

let api_url = 'http://localhost:8080/test'
//import {createStore} from "vuex";

const state = {
    BoardList:{},
    BoardDetail:{},
    BoardCategory:{},
    //boardCategory에서 정리한 값 넣어주기
    Category:{},
    CategoryKey:{key:null, key2:null, key3:null}
}

const getters = {
    getBoardList(state){
        return state.BoardList;
    },
    getBoardDetail(state){
        return state.BoardDetail;
    },
    getBoardCategory(state){
        return state.BoardCategory;
    },
    getCategory(state){
        return state.Category;
    },
    getCategoryKey(state){
        return state.CategoryKey;
    }
}

const actions = {
    //게시글 리스트 가져오기
    BoardList(context, storeObj){
        axios.get(`${api_url}/BoardList/${storeObj.pageNum}/${storeObj.pageSize}/${storeObj.user_id}`)
        .then(res => res.data)
        .then(item => (context.commit('setBoardList',item)))
        .catch(error => console.error(error));
    },
    boardDetail(context, boardId){
        axios.get(`${api_url}/BoardDetail/${boardId}`)
        .then(res => res.data)
        .then(item => (context.commit('setBoardDetail',item),console.log(item)))
        .catch(error => console.error(error));
    },
    boardWrite(context, storeObj){
        axios.post(`${api_url}/BoardWrite`, storeObj)
        .then(res => res.data)
        .then(item => (context.commit('setBoardDetail',item)))
        .catch(error => console.error(error));
    },
    boardUpdate(context, storeObj){
        axios.post(`${api_url}/BoardUpdate`, storeObj)
        .then(res => res.data)
        .then(item => (context.commit('setBoardDetail',item)))
        .catch(error => console.error(error));
    },
    //카테고리 목록
    async boardCategory(context){
        await axios.get(`${api_url}/BoardCategory`)
        .then(res => res.data)
        .then(item => (context.commit('setBoardCategory',item)))
        .catch(error => console.error(error));
    },
    //카테고리 게시글 리스트 가져오기
    boardListCategory(context, storeObj){
        axios.get(`${api_url}/BoardListCategory/${storeObj.pageNum}/${storeObj.pageSize}/${storeObj.user_id}/${storeObj.key}/${storeObj.key2}/${storeObj.key3}`)
        .then(res => res.data)
        .then(item => (context.commit('setBoardList',item)))
        .catch(error => console.error(error));
    },
}

const mutations = {
    setBoardList(state, item){
        console.log('item', item)
        state.BoardList = item
    },
    setBoardDetail(state, item){
        state.BoardDetail = item
    },
    setBoardCategory(state, item){
        console.log('item', item)
        state.BoardCategory = item
    },
    setCategory(state, item){
        console.log('item', item)
        state.Category = item
    },
    setCategoryKey(state, item){
        state.CategoryKey['key'] = item['key'];
        state.CategoryKey['key2'] = item['key2'];
        state.CategoryKey['key3'] = item['key3'];
    }
}

export default {
    //[vuex] unknown action type: login/all_users 과 같은 에러가 날때는 아래와 같이 namespaced: true를 지정한다.
    namespaced: true,
    state, 
    getters, 
    actions, 
    mutations
}