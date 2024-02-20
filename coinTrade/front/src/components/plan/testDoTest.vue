<template>
    <h3>testTestDo</h3>
    {{ getModalParams }}
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    setup(){
        const store = useStore();
        const getModalParams = computed(() => store.getters['modal/getModalParams'])
        return {store, getModalParams}
    }
}
</script>

<style>

</style>