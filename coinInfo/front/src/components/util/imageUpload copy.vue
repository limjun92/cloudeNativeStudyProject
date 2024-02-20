<template>
  <div>
    <h1>imageUpload</h1>
    <div>
        <input multiple @change="imageUpload()" ref="images" type="file" />
        <span>ImageResize?</span>
        <input type="checkbox" v-model="resizeFlag"/>
        <button v-on:click="imageInsert()">등록</button>
    </div>
    <img v-for = "(item,i) in imageUploaded" :key="i" :src="item" alt="올린 이미지" />
  </div>
</template>

<script>
import {useStore} from "vuex";

export default {
    setup(){
      const store = useStore();
      return {store}
  },
    data(){
        return{
            images: null,
            imageUploaded: [],
            resizeFlag: false,
        }
    },
    methods:{
        //1-1 이미지 선택
        imageUpload(){
            this.imageUploaded = []
            this.images = this.$refs.images.files

            Object.keys(this.images).forEach(key=>{
                //화면에 보여주기 위해 URL.createObjectURL 사용
                this.imageUploaded.push(URL.createObjectURL(this.images[key]))
            })
        },
        //1-2 이미지 등록
        async imageInsert(){
            //사진 전송을 위해 FormDate() 사용
            if(this.images){
                var formData = new FormData();
                formData.append("resizeFlag", this.resizeFlag)
                formData.append("apiUrl", process.env.VUE_APP_URL + "/Image" )
                Object.keys(this.images).forEach(key=>{
                    formData.append("image", this.images[key])
                })
                await this.store.dispatch('image/imageInsert', formData)

                //등록 후 조회 호출
                const Params = {
                    searchParams:{
                        userId : 'test'
                    }
                }
                this.$store.dispatch('image/imageList', Params);
            }
        }
    }
}
</script>

<style scoped>

img{
    width:200px
}

</style>