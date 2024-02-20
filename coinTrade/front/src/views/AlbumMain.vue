<template>
    <div>
        <h1>albumMain</h1>
        <albumList/>
    </div>
</template>

<script>

import albumList from '@/components/album/albumList.vue'

export default {
    name: 'albumMain',
    components: {
        albumList,
    }
}
</script>

<style>

</style>