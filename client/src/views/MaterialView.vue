<template>
    <div class="material-view">
        <h1>Available Materials</h1>
        <div>
            <p>Click the button below to add a material!</p>
            <router-link to="/createMaterial">
                <button>Add Material</button>
            </router-link>
        </div>
        <div class="material-card" >
            <material-list /> 

        </div>
    </div>
</template>

<script>
import MaterialList from "../components/MaterialList.vue"; // Importing the MaterialList component
import MaterialService from "../services/MaterialService"; // Importing the MaterialService for API calls

export default {
    name: "MaterialView",
    components: {
        MaterialList
    },
    data() {
        return {
            materials: [], // Array to hold material data
        };
    },
    created() {
        if(this.$store.state.materials.length === 0) {
            MaterialService.listOfMaterials(true) // Fetching the list of materials from the API
            .then((response) => {
                this.materials = response.data;
                this.$store.commit('SET_MATERIALS' , response.data);
            })
            .catch(error => {
                if (error.response) {
                    console.error(`Error getting materials. Response received was "${error.response.statusText}".`, error);
                } else if (error.request) {
                    console.error('Error getting materials. Server could not be reached.', error);
                } else {
                    console.error('Error getting materials. Request could not be created.', error);
                }
            });
        } else {
            this.materials = this.$store.state.materials;
        }
    },
    methods: {
        createMaterial() {
            this.$router.push({ name: "CreateMaterial" });
        }
    },
    
};
</script>

<style scoped>
.material-card{
    max-height: 210px;
}
h1{
    color: beige;
    font-size: 30px;
    margin-top: 0;
    background-color: black;
    padding: 3px;
    border-radius: 5px;
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-style: italic;
}
p{
    color:whitesmoke;
    font-size: 20px;
}
a{
   padding-left: 323px; 
}
button{
    margin-bottom: 5px;
}
button:hover{
    cursor: pointer;
    background-color: rgb(183, 216, 230);
}
@media (max-width: 425px) {
    .material-card {
        max-height: 180px;
        width: 350px;
        padding-left: 20px;
    }
    h1{
        font-size: 20px;
        margin-top: 5px;
        margin-left: 20px;
        width: 320px;
    }
    p{
        font-size: 16px;
    }
    a{
        padding-left: 225px; 
    }

}
</style>



 