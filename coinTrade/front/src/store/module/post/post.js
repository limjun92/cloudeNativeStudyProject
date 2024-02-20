import axios from 'axios';

const api_url = process.env.VUE_APP_URL;
//let api_url = 'http://localhost:8080/test'
//let api_url = 'http://ec2-15-165-15-254.ap-northeast-2.compute.amazonaws.com:8080/test'


const state = {
    postList:{},
    postDetail:{},
    postInsert:{},
    postUpdate:{},
    postDelete:{},
    postCategory:{},
    categoryName:{},
}

const getters = {
    getPostList(state){
        return state.postList;
    },
    getPostDetail(state){
        return state.postDetail;
    },
    getPostInsert(state){
        return state.postInsert;
    },
    getPostUpdate(state){
        return state.postUpdate;
    },
    getPostDelete(state){
        return state.postDelete;
    },
    getPostCategory(state){
        return state.postCategory;
    },
    getCategoryName(state){
        return state.categoryName;
    },
}

const actions = {
    //1. POST 리스트 가져오기
    postList(context, searchParams){
        axios.post(`${api_url}/PostList`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setPostList',item)))
        .catch(error => console.error(error));
    },
    //2. POST 클릭시 Detail가져오기
    postDetail(context, searchParams){
        axios.post(`${api_url}/PostDetail`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setPostDetail',item)))
        .catch(error => console.error(error));
    },
    //3. POST 등록
    async postInsert(context, upsertParams){
        await axios.post(`${api_url}/PostInsert`, upsertParams)
        .then(res => res.data)
        .then(item => (context.commit('setPostInsert',item)))
        .catch(error => console.error(error));
    },
    //4. POST 수정
    async postUpdate(context, upsertParams){
        await axios.post(`${api_url}/PostUpdate`, upsertParams)
        .then(res => res.data)
        .then(item => (context.commit('setPostUpdate',item)))
        .catch(error => console.error(error));
    },
    //5. POST 삭제 (activeFlag = 'N')
    async postDelete(context, upsertParams){
        await axios.post(`${api_url}/PostDelete`, upsertParams)
        .then(res => res.data)
        .then(item => (context.commit('setPostDelete',item)))
        .catch(error => console.error(error));
    },
    //6. Category리스트를 가져온다.
    postCategory(context, searchParams){
        axios.post(`${api_url}/PostCategory`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setPostCategory',item)))
        .catch(error => console.error(error));
    },
}

const mutations = {
    setPostList(state, item){
        state.postList = item.serviceResponse;
    },
    setPostDetail(state, item){
        state.postDetail = item.serviceResponse;
    },
    setPostInsert(state, item){
        state.postInsert = item.serviceResponse;
    },
    setPostUpdate(state, item){
        state.postUpdate = item.serviceResponse;
    },
    setPostDelete(state, item){
        state.postDelete = item.serviceResponse;
    },
    setPostCategory(state, item){
        state.postCategory = item.serviceResponse;
    },

    //선언
    //등록, 수정에서 카테고리를 사용용도
    setCategoryName(state, item){
        state.categoryName = item;
    },
}

export default {
    //[vuex] unknown action type: login/all_users 과 같은 에러가 날때는 아래와 같이 namespaced: true를 지정한다.
    namespaced: true,
    state, 
    getters, 
    actions, 
    mutations
}