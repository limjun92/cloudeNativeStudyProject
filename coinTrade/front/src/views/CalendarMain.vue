<template>
    <div>
        <h1>calendar</h1>
        <calendar/>
    </div>
</template>

<script>

import calendar from '@/components/calendar/calendarOnly.vue'

export default {
    name: 'CalendarMain',
    components: {
        calendar,
    }
}
</script>

<style>

</style>