<template>
    <div>
        <h1>home</h1>
        <homeTest/>
        <div class="homeMain">
            <h3> home </h3>
                <h5>오늘의 일정 및 ToDoList표시 (20231002 완료)</h5>
            <h3> image </h3>
                <h5>사진 저장하는 내용 신규 구현</h5>
            <h3> calendar </h3>
                <h5>schedule 수정 기능 구현 (20231001 완료)</h5>
                <h5>오늘 날짜, 주말 날짜 따로 표시 (20231002 완료)</h5>
                <h5>색 표시 (20231001 완료)</h5>
                <h5>ToDo리스트 다룬날로 넘기는 기능 구현</h5>
                <h5>ToDo리스트를 다 완료하면 색 다르게 표시</h5>
            <h3> post </h3>
                <h5>페이징 (20231002 완료)</h5>
            <h3> 기타 </h3>
                <h5>업데이트 정보 저장</h5>
            ==================
            <br/>
            1. POST 상세 보기 화면 및 서비스 V 2023.06.27
            <br/>
            2. POST 등록 화면 및 서비스 V 2023.06.27
            <br/>
            3. POST 수정 화면 및 서비스 V 2023.06.27
            <br/>
            4. 카테고리 DB 및 서비스 구성  V 2023.07.01
            <br/>
            5. view 안에서 라우터 조종하는법 확인 V 2023.06.29
            <br/>
            6. CSS 공부
            <br/>
            7. 이미지를 저장하는법
            <br/>
            8. toDoList활용 방안 생각
        </div>
    </div>
</template>

<script>
import homeTest from '@/components/home/homeTest.vue'

export default {
    name: 'homeT',
    components: {
        homeTest,
    }
}
</script>

<style>
.homeMain{
    text-align: left;
}
</style>