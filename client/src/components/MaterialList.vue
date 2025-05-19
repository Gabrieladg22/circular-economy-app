<template>
 <div class="material-list">
        <table class="header">
            <thead>
                <tr>
                    <th>Material ID</th>
                    <th>Material Name</th>
                </tr>
            </thead>
        </table>
        <div class="scrollable">
            <table class="body">
                <tbody>
                    <tr v-for="m in materials" :key="m.id" @click="goToDetails(m)">
                        <td>{{ m.materialId }}</td>
                        <td>{{ m.materialName }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
  </div>
</template>

<script>
export default {
    name: "MaterialList",
    computed: {
        materials(){
            return this.$store.state.materials;
        }
    },
    methods: {
        goToDetails(material) {
            this.$router.push({ name: 'materialProfile', params: { id: material.materialId } });
        }
    }
};
</script>

<style scoped>
.material-list {
    display: block;
    color: white;
    padding: 0px;
    cursor: pointer;
    font-weight: bold;
    text-align: justify;
}
td {
    text-align: center;
    color: white;
    background-color:#5ebaca;
    padding: 10px 65px;
    cursor: pointer;
    font-size: 17px;
    font-weight: bold;
    
} 
td:hover{
    color: black;
}
th{
    color: black;
    padding-right: 50px;
    padding-bottom: 15px;
    padding-top: 15px;
    background-color: white;
    text-align: center;
    font-size: 18px;
    
} 
.header, .body{
    width: 100%;
    border-collapse: collapse;
    
}
.scrollable{
    display: block;
    max-height: 170px;
    overflow-y: auto;
    
}
.material-list:hover {
    background-color: white;
    color: rgb(35, 112, 175);
}


@media (max-width: 425px) {
    .material-list {
        width:326px;
    }
    td {
        padding: 10px 25px;
        font-size: 14px;
    } 
    th{
        padding-left: 15px;
        padding-bottom: 10px;
        padding-top: 10px;
        font-size: 15px;
    } 
    .scrollable{
        max-height: 120px;
    }

}
</style>