const state = {
    modalFlag:false,
}

const getters = {
    getModalFlag(state){
        return state.modalFlag;
    },
}

const actions = {
}

const mutations = {
    setModalOpen(state){
        state.modalFlag = true;
    },
    setModalClose(state){
        state.modalFlag = false;
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