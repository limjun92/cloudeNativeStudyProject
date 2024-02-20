<template>
    <div>
        <h1>PlanMain</h1>
        <div v-if="content=='scheduleInfo'">
          <scheduleInfo/>
        </div>
        <div v-if="content=='scheduleWrite'">
          <scheduleWrite/>
        </div>
        <div v-if="content=='scheduleUpdate'">
          <scheduleUpdate/>
        </div>
        <div v-if="content=='plan'">
          <toDoList/>
        </div>
    </div>
</template>

<script>
import toDoList from '@/components/plan/toDoList.vue'
import scheduleInfo from '@/components/plan/scheduleInfo.vue'
import scheduleWrite from '@/components/plan/scheduleWrite.vue'
import scheduleUpdate from '@/components/plan/scheduleUpdate.vue'

export default {
    name: 'PlanMain',
    components: {
        toDoList,
        scheduleInfo,
        scheduleWrite,
        scheduleUpdate,
    },
    data(){
      return{
        content: this.$route.params.content
      }
    },
}
</script>

<style>

</style>