<template>
    <div>
    <!-- {{ array }} -->
        <div v-for="(layer, layerIndex) in map" :key="layerIndex">
            <span v-for="(row, rowIndex) in layer" :key="rowIndex">
                <span class="one" v-if="row!=' '">{{ row }}</span>
                <span class="one" v-else>&nbsp;&nbsp;</span>
            </span>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            len: 0,
            map: [],
            mapZIndex:[],
            node:[],
            nodeShow:[],
            PI:-1,
            close:false
        }
    },
    created(){
        this.PI = Math.PI
        this.createArray();
    },
    beforeUnmount() {
        this.close = true;
    },
    methods: {
        async createArray() {
            this.len = 40
            let x = -5;
            let y = 6;
            let z = 3;
            this.map = new Array(this.len+1).fill(' ').map(() => new Array(this.len+1).fill(' '));
            this.mapZIndex = new Array(this.len+1).fill(this.len).map(() => new Array(this.len+1).fill(this.len));

            for(var i = -this.len/4;i<=this.len/4;i++){
                for(var j = -this.len/4;j<=this.len/4;j++){
                    this.map[i+this.len/2][j+this.len/2] = 'A'
                    this.node.push([i,j,-this.len/4,'A'])
                    this.node.push([i,j,this.len/4,'X'])
                    this.node.push([i,-this.len/4,j,'*'])
                    this.node.push([i,this.len/4,j,'^'])
                    this.node.push([-this.len/4,i,j,'1'])
                    this.node.push([this.len/4,i,j,'2'])
                }
            }
            for(let i = 0;i<1000;i++){
                await new Promise(resolve => setTimeout(resolve, 50));
                this.rotate(x, y, z)
                if(i%30 ==0){
                    x= x*(-1)
                    y= y*(-1)
                    //z= z*(-1)
                }
                //5 4 -5
                if(this.close)
                    break;
            }
            
        },

        rotate(angleX, angelY, angelZ){
            this.map = new Array(this.len+1).fill(' ').map(() => new Array(this.len+1).fill(' '));
            this.mapZIndex = new Array(this.len+1).fill(this.len).map(() => new Array(this.len+1).fill(this.len));
            this.nodeShow = []
            this.ratate_X(angleX,angelY,angelZ)
            let x = -1
            let y = -1
            let z = -1
            for(let i = 0;i<this.node.length;i++){
                x = this.nodeShow[i][0]
                y = this.nodeShow[i][1]
                z = this.node[i][2]
                if(this.mapZIndex[x + this.len/2][y + this.len/2] == this.len || this.mapZIndex[x + this.len/2][y + this.len/2] > z){
                    this.mapZIndex[x + this.len/2][y + this.len/2] = z
                    this.map[this.nodeShow[i][0] + this.len/2][this.nodeShow[i][1] + this.len/2] = this.node[i][3]
                }
            }
        },
        ratate_X(angleX, angelY, angelZ){
            let tmp = []
            let sinX = Math.sin((this.PI*angleX)/180)
            let cosX = Math.cos((this.PI*angleX)/180)
            let sinY = Math.sin((this.PI*angelY)/180)
            let cosY = Math.cos((this.PI*angelY)/180)
            let sinZ = Math.sin((this.PI*angelZ)/180)
            let cosZ = Math.cos((this.PI*angelZ)/180)

            for(let i = 0;i<this.node.length;i++){
                this.roatateCalculate(tmp, sinX, cosX, sinY, cosY, sinZ, cosZ, this.node[i][0], this.node[i][1], this.node[i][2], this.node[i][3])
            }
            this.node = tmp
        },
        roatateCalculate(tmp, sinX, cosX, sinY, cosY, sinZ, cosZ, x, y, z, draw){
            let X = (cosX*cosY)*x + (cosX*sinY*sinZ - sinX*cosZ)*y + (cosX*sinY*cosZ + sinX*sinZ)*z
            let Y = (sinX*cosY)*x + (sinX*sinY*sinZ + cosX*cosZ)*y + (sinX*sinY*cosZ - cosX*sinZ)*z
            let Z = (-sinY)*x + (cosY*sinZ)*y + (cosY*cosZ)*z
            tmp.push([X, Y, Z, draw])
            this.nodeShow.push([Math.round(X), Math.round(Y), Math.round(Z)])
        },
  },
  
}
</script>

<style scoped>
.one{
    display: inline-block;
    width:15px;
    height:15px;
}
</style>