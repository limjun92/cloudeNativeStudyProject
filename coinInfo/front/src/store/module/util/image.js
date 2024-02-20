import axios from 'axios';

const api_url = process.env.VUE_APP_URL;

const state = {
    imageList:{},
    imageDelete:{},
}

const getters = {
    getImageList(state){
        return state.imageList;
    },
    getImageDelete(state){
        return state.imageDelete;
    },
}

const actions = {
    //1. Image Insert(Upload)
    async imageInsert(context, formData){
        await axios.post(`${api_url}/ImageInsert`, formData,{
            headers:{
                'Content-Type':'multipart/form-data'
            }
        })
        .then(res => res.data)
        //.then(item => (context.commit('setImageInsert',item)))
        .catch(error => console.error(error));
    },
    //2. Image 리스트 가져오기
    async imageList(context, searchParams){
        await axios.post(`${api_url}/ImageList`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setImageList',item)))
        .catch(error => console.error(error));
    },
    //3. Image 삭제
    async imageDelete(context, searchParams){
        await axios.post(`${api_url}/ImageDelete`, searchParams)
        .then(res => res.data)
        .then(item => (context.commit('setImageDelete',item)))
        .catch(error => console.error(error));
    },
}
const mutations = {
    // setImageInsert(state, item){
    // },
    setImageList(state, item){
        state.imageList = item.serviceResponse
    },
    setImageDelete(state, item){
        state.imageDelete = item.serviceResponse
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