import {createStore} from 'vuex';
import login from "./module/user/login";
import modal from "./module/modal/modal";
//import board from "./module/board/board";
import post from "./module/post/post"
import image from './module/util/image';
import toDoListSearch from './module/toDoList/toDoListSearch'
import toDoListUpsert from './module/toDoList/toDoListUpsert'
import calendar from './module/calendar/calendar'
import scheduleSearch from './module/schedule/scheduleSearch';
import scheduleUpsert from './module/schedule/scheduleUpsert';

export const store = createStore({
    modules : {
        login, 
        modal, 
        post, 
        image, 
        toDoListSearch, 
        toDoListUpsert,
        calendar,
        scheduleSearch,
        scheduleUpsert,
    },
})