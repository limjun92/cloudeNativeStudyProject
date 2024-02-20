<template>
    <h2>calendar</h2>
    <div>
        <h2>{{ year }} {{ month }}</h2>
        <div>
            <button v-on:click="dayClick(today)">toDoToday</button>
        </div>
        <div>
            <button v-on:click="getBeforeMonth()">이전</button>
            <button v-on:click="getNextMonth()">이후</button>
            <button v-on:click="getThisMonth()">초기화</button>
        </div>
        <div class = 'calendalContainer'>
            <table class = 'calendal'>
                <thead>
                    <tr>
                        <th scope="col" v-for="day in days" :key="day">{{day}}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="calendalTr" v-for="(week, index) in calendarDate" :key="index">
                        <td class="tdOne" :class="[date[5]?'light':'black', today==key?'skyBlue':'light']" scope="row" v-for="(date, key) in week" :key="key" v-on:mousedown="dayDown(key)" v-on:mouseup="dayUp(key)">
                            <div class="date" :class="date[6]?'weekday':'weekend'">
                                {{date[2]}}
                            </div>
                            <div>
                                <span class="toDoList1" v-if="date[3]" @mousedown.stop @mouseup.stop v-on:click="dayClick(key)">toDo</span>
                                <span class="toDoList2" v-if="!date[3]" @mousedown.stop @mouseup.stop v-on:click="dayClick(key)">toDo</span>
                            </div>
                            <div v-if="schedules[key]">
                                <div v-for="(schedule, index) in schedules[key]" :key="index">
                                    <div>
                                        <div v-if="schedule['evenly']==0" class="schedule schedule0" :style="{ width: (14.2857*schedule['len']) + '%', backgroundColor: schedule['color']}" @mousedown.stop @mouseup.stop v-on:click="scheduleInfo(schedule['rowId'])">{{schedule['title']}}</div>
                                        <div v-if="schedule['evenly']==1" class="schedule schedule1" :style="{ width: (14.2857*schedule['len']) + '%', backgroundColor: schedule['color']}" @mousedown.stop @mouseup.stop v-on:click="scheduleInfo(schedule['rowId'])">{{schedule['title']}}</div>
                                        <div v-if="schedule['evenly']==2" class="schedule schedule2" :style="{ width: (14.2857*schedule['len']) + '%', backgroundColor: schedule['color']}" @mousedown.stop @mouseup.stop v-on:click="scheduleInfo(schedule['rowId'])">{{schedule['title']}}</div>
                                        <div v-if="schedule['evenly']==3" class="schedule schedule3" :style="{ width: (14.2857*schedule['len']) + '%', backgroundColor: schedule['color']}" @mousedown.stop @mouseup.stop v-on:click="scheduleInfo(schedule['rowId'])">{{schedule['title']}}</div>
                                    </div>
                                </div>
                            </div>
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

export default {
    components: {
    },

    setup(){
        const store = useStore();
        //현재 달의 날짜 정보
        const calendarDate = computed(() => store.getters['calendar/getCalendarDate'])
        const calendarInfoList = computed(() => store.getters['calendar/getCalendarInfoList']);
        const scheduleList = computed(() => store.getters['scheduleSearch/getScheduleList']);
        return {store, calendarDate, calendarInfoList, scheduleList}
    },

    data(){
        return{
            router:this.$router,

            year:-1,
            month:-1,
            today:"",
            days:['일','월','화','수','목','금','토'],
            week:{},

            //캘린더[0:년도, 1:월, 2:날, 3:ToDoList수, 4:, 5:현재월포함여부, 6:요일번호 ]
            dates:[],
            date:null,

            openToDo:false,

            isActive: true,

            startDate:"",
            endDate:"",

            weekNum:0,

            schedules:{},
        }
    },
    created(){
        this.date = new Date();
        this.today = this.dateToString(this.date)
        this.getWeekDate(this.date)
    },
    watch:{
        scheduleList(){
            if(this.scheduleList['outPut'])
                this.DrawScheduleList()
        }
    },  
    methods:{
        //캘린더 생성
        async getWeekDate(dateinfo){
            this.year = dateinfo.getFullYear();
            this.month = dateinfo.getMonth()+1;
            this.getLastMonthLastWeek(dateinfo);
            this.getThisMonthWeeks(dateinfo);
            this.getNextMonthFirstWeeks();

            //달력에 ToDoList, 일기 정보 읽어오기
            await this.CalendarInfoSearch();
            //스케줄 읽어오기
            this.getScheduleList();
            //ToDoList, 일기 정보 읽어와서 dates에 적용하기
            this.CalendarWriteInfo();

            this.store.commit("calendar/setCalendarDate", this.dates)
            
        },
        //저번달 마지막 주 날짜
        getLastMonthLastWeek(dateinfo){
            let date = new Date(dateinfo.getFullYear(), dateinfo.getMonth(), 0);
            let lastDate = date.getDate();
            let lastDay = date.getDay();
            let lastMonth = this.month-1;
            let lastYear = this.year;
            if(lastMonth == 0){
                lastMonth=12
                lastYear-=1
            }
            if(lastDay == 6)
                return;
            for(let i = lastDate-lastDay; i<=lastDate; i++){
                this.week[lastYear + "-" + (lastMonth<10?"0"+lastMonth:lastMonth) + "-" + (i<10?"0"+i:i)] = [lastYear, lastMonth, i, 0, 0, 0, this.weekNum]
                this.weekNum++;
                if(this.weekNum==7)
                    this.weekNum = 0;
            }
        },
        //이번달 총 날짜 
        getThisMonthWeeks(dateinfo){
            let date = new Date(dateinfo.getFullYear(), dateinfo.getMonth()+1, 0);
            let nowDate = date.getDate();
            for(let i = 1; i<=nowDate; i++){
                this.week[this.year + "-" + (this.month<10?"0"+this.month:this.month) + "-" + (i<10?"0"+i:i)] = [this.year,this.month,i, 0, 0, 1, this.weekNum]
                if(Object.keys(this.week).length == 7){
                    this.dates.push(this.week);
                    this.week = {};
                }
                this.weekNum++;
                if(this.weekNum==7)
                    this.weekNum = 0;
            }
        },
        //다음달 첫째 주 날짜
        getNextMonthFirstWeeks(){
            let nextDate = 1
            let nextMonth = this.month+1;
            let nextYear = this.year;
            if(nextMonth == 13){
                nextMonth = 1
                nextYear+=1
            }
            while(this.dates.length!=6){
                //this.week.push([nextYear, nextMonth, nextDate]);
                this.week[nextYear + "-" + (nextMonth<10?"0"+nextMonth:nextMonth) + "-" + (nextDate<10?"0"+nextDate:nextDate)] = [nextYear, nextMonth, nextDate, 0, 0, 0, this.weekNum]
                nextDate++;
                if(Object.keys(this.week).length == 7){
                    this.dates.push(this.week);
                    this.week = {};
                }
                this.weekNum++;
                if(this.weekNum==7)
                    this.weekNum = 0;
            }
        },
        //다음달로 가기
        getNextMonth(){
            this.dates = [];
            this.week = {};
            this.date = new Date(this.date.getFullYear(), this.date.getMonth()+1, 1);
            this.getWeekDate(this.date)
        },
        //이전달로 가기
        getBeforeMonth(){
            this.dates = [];
            this.week = {};
            this.date = new Date(this.date.getFullYear(), this.date.getMonth()-1, 1);
            this.getWeekDate(this.date)
        },
        //초기화
        getThisMonth(){
            this.dates = [];
            this.week = {};
            this.date = new Date();
            this.getWeekDate(this.date)
        },
        //trace
        dayClick(day){
            event.stopPropagation()
            let url = '/Calendar/plan/'+ day
            this.router.push(url)
        },

        //scheduleInfo 클릭이벤트
        scheduleInfo(rowId){
            event.stopPropagation()
            let url = '/Calendar/scheduleInfo/'+ rowId
            this.router.push(url)
        },
        //=====================================================
        //========= CalendarInfo ==============================
        //=====================================================
        async CalendarInfoSearch(){
            const searchParams = {
                searchParams:{
                    userId : 'test',
                    startDay : Object.keys(this.dates[0])[0],
                    endDay : Object.keys(this.dates[5])[6],
                }
            }
            await this.$store.dispatch('calendar/calendarInfoList', searchParams);
        },
        CalendarWriteInfo(){
            for(let date of this.dates){
                for(let oneDate in date){
                    if(oneDate in this.calendarInfoList){
                        //toDoListFlag 값이 있으면 3번 1로 변경
                        if(this.calendarInfoList[oneDate]["toDoListCount"])
                            date[oneDate][3] = this.calendarInfoList[oneDate]["toDoListCount"]
                    }
                }
            }
        },
        //=====================================================
        //============ schedule ===============================
        //=====================================================
        dayDown(key){
            this.startDate = key
        },
        dayUp(key){
            this.endDate = key

            //큰날짜를 선택하고 작은 날짜로 갈경우 자리를 바꾸어 준다.
            if(this.endDate < this.startDate){
                let tmp = this.startDate
                this.startDate = this.endDate
                this.endDate = tmp
            }

            let url = '/Calendar/scheduleWrite/'+ this.startDate + '/' + this.endDate
            this.router.push(url)
            //this.makeSchedule()
        },

        //ScheduleList정보 가져오기
        async getScheduleList(){
            const searchParams = {
                searchParams:{
                    userId : 'test',
                }
            }
            await this.$store.dispatch('scheduleSearch/scheduleList', searchParams);
        },

        //ScheduleList를 그리기
        DrawScheduleList(){
            this.schedules = {}
            let evenly = []
            
            for(let date of this.scheduleList['outPut']['scheduleList']){
                //======================================================
                //일정순서를 정하기 위해 evenly를 고르게 해준다.
                let check = false;
                for(let i = 0; i<evenly.length; i++){
                    if(evenly[i] < date['startDay']){
                        check = true;
                        evenly[i] = date['endDay']
                        date['evenly'] = i
                        break;
                    }
                }
                if(!check){
                    evenly.push(date['endDay'])
                    date['evenly'] = evenly.length-1
                }
                //======================================================
                //
                
                let sDate = new Date(date['startDay'])
                let eDate = new Date(date['endDay'])
                let DayNum = sDate.getDay()

                let diff = Math.abs(new Date(eDate).getTime() - new Date(sDate).getTime());
                diff = Math.ceil(diff / (1000 * 60 * 60 * 24)) + 1;
                

                //oneDate를 키값으로 사용하기 위해 oneDateTmp를 계산용으로 사용
                let oneDateTmp = this.dateToString(sDate)
                let tmpDate

                //해당주 색칠할 길이 초기화
                let len = 0;
                if(DayNum + diff >= 7){
                    //색칠할 길이 계산
                    len = 7 - DayNum;
                    //색칠할 총 길이에서 색칠한 길이를 빼준다
                    diff -= len

                    this.pushSchedules(oneDateTmp, date, len)
                }
                //색칠길이가 7보다 크거나 같으면 전체 주를 칠하면서 간다
                while(diff >= 7){
                    //시작날짜 계산
                    tmpDate = new Date(oneDateTmp);
                    tmpDate.setDate(tmpDate.getDate() + len);
                    //색칠길이 계산
                    len = 7;
                    diff -= len

                    oneDateTmp = this.dateToString(tmpDate)
                    this.pushSchedules(oneDateTmp, date, len)

                }
                //색칠할 부분이 7보다 작고 0보다 클경우
                if(diff>0){
                    //시작날짜 계산
                    tmpDate = new Date(oneDateTmp);
                    tmpDate.setDate(tmpDate.getDate() + len);
                    //색칠길이 계산
                    len = diff;

                    oneDateTmp = this.dateToString(tmpDate)
                    this.pushSchedules(oneDateTmp, date, len)

                }
            }
            
        },
        //===============================================
        //function영역
        //===============================================
        //Date를 YYYY-MM-DD 형태의 String으로 변환
        dateToString(date){
            let year = date.getFullYear();
            let month = ('0' + (date.getMonth() + 1)).slice(-2);
            let day = ('0' + date.getDate()).slice(-2);
            date = `${year}-${month}-${day}`;
            return date
        },
        pushSchedules(dateString, date, len){
            if(this.schedules[dateString]){
                this.schedules[dateString].push({'rowId':date['rowId'], 'title':date['title'], 'len': len, 'evenly': date['evenly'], 'color': date['color']})
            }else{
                this.schedules[dateString] = []
                this.schedules[dateString].push({'rowId':date['rowId'], 'title':date['title'], 'len': len, 'evenly': date['evenly'], 'color': date['color']})
            }
        }
    }
}
</script>

<style scoped>
th {
    border:1px solid;
}
th, td{
    text-align: right;
    vertical-align: top;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}


.calendal{
    width : 100%;
    height: 800px;
    margin-left: auto;
    margin-right: auto;
    border-collapse: collapse;
    border:1px solid;
    overflow: hidden;
    text-overflow: ellipsis;

}

tr{
    height: 16.66%;
}

/* 
달력의 색 구분
light: 현재 월
black: 전월 또는 다음 월
*/
.light{
    background-color:white;
}
.black{
    background-color: #bdc3c7;
}
.skyBlue{
    background-color: #ffeaa7;
}
.date{
    height: 20px;
}
.tdOne{
    border:1px solid;
    width: 14.2857%;
    padding: 0px;
}
.toDoList1{
    background-color: yellowgreen;
    width: 20px;
    height: 20px;
}
.toDoList2{
    background-color: #bdc3c7;
    width: 20px;
    height: 20px;
}


.calendalTr{
    position: relative;
    text-align: left;
}

.schedule{
    text-align: left;
}
.schedule0{
    position: absolute;
    color:white;
    height: 20px;
    top:40px;
}

.schedule1{
    position: absolute;
    color:white;
    height: 20px;
    top:60px;
}

.schedule2{
    position: absolute;
    color:white;
    height: 20px;
    top:80px;
}
.schedule3{
    position: absolute;
    color:white;
    height: 20px;
    top:100px;
}
.weekend{
    color:red
}

.weekday{
    color:black
}
</style>