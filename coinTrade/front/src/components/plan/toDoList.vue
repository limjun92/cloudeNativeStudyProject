<template>
    <div>
        <h2>toDoList</h2>
        {{ day }}
        <div>
          <button v-on:click="openToDoInsert()">등록</button>

          <div v-if="openToDoInsertFlag">
            <input type="text" v-model="inputTitle"/>
            <button v-on:click="toDoInsert()">확인</button>
            <button v-on:click="closeToDoInsert()">취소</button>
          </div>
        </div>
        <div v-if = "toDoList">
          <table class="toDoListTable">
            <!-- HEAD -->
            <thead>
              <tr>
                <th>Title</th>
              </tr>
            </thead>
            <!-- BODY -->
            <tbody>
              <tr class="content" v-for="(toDo, index) in toDoList.toDoList.filter((c)=>c.cancelFlag == false)"
                  :key="index"
                  :draggable="true"
                  @dragstart="onDragStart(index)"
                  @dragover="onDragOver"
                  @drop="onDrop(index)">
                <td>
                    <span>{{toDo.title}}</span>
                </td>
                <td>
                  <input type="checkbox" v-model="toDo.passFlag" @change="handleCheckboxChange('pass', index)"/>
                  {{ toDo.passFlag }}
                </td>
                <td>
                  <button v-on:click="handleCheckboxChange('cancel', index)">
                    삭제
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
    </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

//1. ToDoList 조회
//2. ToDo 등록
//3. ToDoList 드래그 앤 드롭 이벤트
export default {
    setup(){
        const store = useStore();
        const toDoList = computed(() => store.getters['toDoListSearch/getToDoList'].outPut);
        const calendarDate = computed(() => store.getters['calendar/getCalendarDate'])
        return {store, toDoList, calendarDate}
    },
    data(){
      return{
        day : null,

        openToDoInsertFlag: false,

        //2. ToDo 등록시 사용
        inputTitle:"",
      }
    },
    mounted(){
      //1. ToDoList조회를 한다.
      if(this.$route.params.content){
            this.day = this.$route.params.param1
            this.toDoListSearch()
        }
    },
    methods:{
      //2-1. 등록 input open
      openToDoInsert(){
        this.openToDoInsertFlag = true
      },
      //2-2. 등록 input close
      closeToDoInsert(){
        this.openToDoInsertFlag = false
      },
      toDoListSearch(){
        const searchParams = {
          searchParams:{
            userId : 'test',
            day : this.day,
            //year : this.year,
            //month : this.month,
            //date : this.date,
          }
        }
        this.$store.dispatch('toDoListSearch/toDoList', searchParams);
      },
      async toDoInsert(){
        const upsertParams = {
          upsertParams:{
            userId : 'test',
            day : this.day,
            year : this.year,
            month : this.month,
            date : this.date,
            title : this.inputTitle,
            sortNo : this.toDoList?this.toDoList.toDoListCount+1:1
          }
        }
        await this.$store.dispatch('toDoListUpsert/toDoInsert', upsertParams);

        
        //2-3. 등록후 재 조회
        this.toDoListSearch()
        
        //2-4. calendarDate에 ToDoList등록 표시 캘린더에 표시 추가
        for(let date of this.calendarDate){
          if(this.day in date){
            date[this.day][3]++
          }
        }
        
        this.inputTitle =""
      },

      //4. toDoList 수정
      async toDoUpdate(fromIndex, ToIndex){

        const upsertParams = {
          upsertParams : {
            day : this.day,
            rowId : this.toDoList.toDoList[fromIndex].rowId,
            sortNoStart : this.toDoList.toDoList[fromIndex].sortNo,
            sortNoEnd : this.toDoList.toDoList[ToIndex].sortNo,
            passFlag : this.toDoList.toDoList[fromIndex].passFlag,
            //삭제시 "true"
            cancelFlag : this.toDoList.toDoList[fromIndex].cancelFlag
          }
        }
        
        await this.$store.dispatch('toDoListUpsert/toDoUpdate', upsertParams);

        this.toDoListSearch()

        //삭제 업데이트 시 ToDoList횟수[3]-- 한다
        if(this.toDoList.toDoList[fromIndex].cancelFlag){
          for(let date of this.calendarDate){
            if(this.day in date){
              date[this.day][3]--
            }
          }
        }
      },

      //3. ToDoList 드래그 앤 드롭 이벤트
      onDragStart(index) {
      // 드래그 시작 시 실행되는 메서드
      // 드래그한 요소의 인덱스를 저장합니다.
        event.dataTransfer.setData('text/plain', index.toString());
      },
      onDragOver(event) {
        // 드래그한 요소가 다른 요소 위로 이동할 때 실행되는 메서드
        // 기본 동작을 막아야 drop 이벤트가 작동합니다.
        event.preventDefault();
      },
      onDrop(toIndex) {
        // 드롭 시 실행되는 메서드
        // 드롭한 요소의 인덱스를 가져와서 순서를 변경합니다.
        const fromIndex = Number(event.dataTransfer.getData('text/plain'));
        this.toDoUpdate(fromIndex, toIndex)
      },
      handleCheckboxChange(type, index){
        if(type == 'cancel')
          this.toDoList.toDoList[index].cancelFlag = true
        this.toDoUpdate(index, index)
      }
    },
}
</script>

<style scoped>

.toDoListTable{
  margin:auto; 
}
.content{
  background-color: #ffe881;
  height:40px
}

</style>