<template>
    <h1>BoardList</h1>
    <div class = "board">
        <table class = 'boardList'>
            <thead>
                <tr>
                    <th>타이틀</th>
                    <th class="created_dt">등록일</th>
                    <!-- <th>조회수</th> -->
                </tr>
            </thead>
            <tbody>
                <tr class="content" v-for="(board, index) in boardList.board" :key="index">
                    <td class="title" v-on:click="BoardDetail(board.board_id)">
                        <!-- <li @click="$router.push('/Board/'+ board.board_id)">{{board.title}}</li> -->
                        {{board.title}}
                    </td>
                    <td>
                        {{dateFormat(board.created_Dt)}}
                    </td>
                    <!-- <td>{{board.User_Id}}
 
                    </td> -->
                </tr>
            </tbody>
        </table>
    </div>
    <button :disabled="beforePageAble" v-on:click="beforePage()">이전</button><button :disabled="afterPageAble" v-on:click="NextPage()">이후</button>
        <button v-on:click="boardWrite()">등록</button>
        {{ pageNum }}
    <router-view/>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    data(){
        return{
            pageNum:1,
            pageSize:5,
            router : this.$router
        }
    },
    setup(){
        const store = useStore();
        const user = computed(() => store.getters['login/getUserLogin']);
        
        //1 처음에는 항상 1페이지 5개를 보여준다.

        //게시글 리스트 가져오기
        
        //테스트로 모든 유저 가져오기
        //store.dispatch('login/all_users');

        const boardList = computed(() => store.getters['board/getBoardList']);
        const categoryKey = computed(() => store.getters['board/getCategoryKey']);
        
        return {store, boardList, user, categoryKey}
    },
    computed:{
        //pageNum을 체크해서 1페이지일때 이전 페이지로 이동 못하도록 한다.
        beforePageAble(){
            if(this.pageNum == 1){
                return true
            }else{
                return false
            }
        },
        afterPageAble(){
            console.log(this.boardList.board_count, this.pageSize, this.pageNum)
            if(this.boardList.board_count / this.pageSize > this.pageNum){
                return false
            }else{
                return true
            }
        }
    },
    methods:{
        //다음페이지로 가기
        NextPage(){
            this.pageNum += 1;
            console.log("next", this.user.user_id)
            const storeObj = {
                pageNum : this.pageNum,
                pageSize : this.pageSize,
                //user_id : this.user.user_id,
                user_id : 'jh0508',
                key : this.categoryKey['key'],
                key2 : this.categoryKey['key2'],
                key3 : this.categoryKey['key3'],
            }

            this.$store.dispatch('board/boardListCategory', storeObj);

            //const tmpboardList = computed(() => this.store.getters['board/getBoardList']);

            this.boardList = computed(() => this.store.getters['board/getBoardList']);
            
            },
        //이전페이지로 가기
        beforePage(){
            this.pageNum -= 1;

            const storeObj = {
                pageNum : this.pageNum,
                pageSize : this.pageSize,
                //user_id : this.user.user_id,
                user_id : 'jh0508',
                key : this.categoryKey['key'],
                key2 : this.categoryKey['key2'],
                key3 : this.categoryKey['key3'],
            }

            this.$store.dispatch('board/boardListCategory', storeObj);

            this.boardList = computed(() => this.store.getters['board/getBoardList']);
        },

        //BoardDetailPage확인
        BoardDetail(boardId){
            console.log(boardId)
            //var router = this.$router;
            this.router.push('/Board/'+ boardId)
            this.store.dispatch('board/boardDetail', boardId);
        }, 
        // 게시글 등록 
        boardWrite(){
            this.router.push('/Board/write')
        },

        //DateFormet
        dateFormat(dateString){
            const date = new Date(Date.parse(dateString));
            const krTime = new Date(date.getTime() - (9 * 60 * 60 * 1000));
            const formattedDate = `${krTime.getFullYear()}-${(krTime.getMonth()+1).toString().padStart(2, '0')}-${krTime.getDate().toString().padStart(2, '0')}`;
            const formattedTime = `${krTime.getHours().toString().padStart(2, '0')}:${krTime.getMinutes().toString().padStart(2, '0')}:${krTime.getSeconds().toString().padStart(2, '0')}`;
            return formattedDate + " " + formattedTime
        }
    }
}
</script>

<style scoped>
.board{
    height: 200px;
}
.boardList{
    width:100%;
    margin-left: auto;
    margin-right: auto;
    border-collapse: collapse;
    border:1px solid;
}
.title{
    padding-left: 20px;
    text-align:left;
}
th{
    border: 1px solid;
}
td{
    border: 1px solid;
}
thead tr{
    height: 30px;
}
.content{
    height: 30px;
}
.created_dt{
    width:200px;
}
</style>