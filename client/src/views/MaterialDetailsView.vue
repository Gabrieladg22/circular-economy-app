<template>
    <div class="material-details" >
        <h1>Material Details</h1>
        <div class="material-card">
            <h2>{{ material.materialName }}</h2>
            <p>Business ID: {{ material.businessId }}</p>
            <p>Condition: {{ material.condition }}</p>
            <p>Price: {{ material.price }}</p>
            <p>Quantity Kg: {{ material.quantityKg }}</p>
            <p>Created By: {{ material.createdBy }}</p>
            <br>
            <router-link :to="{ name: 'materials' }" class="btn-back">Back to Materials</router-link>
            <div class="actions">
                <button @click="editMaterial">Edit</button>
                <button @click="deleteMaterial">Delete</button>
            </div>
        </div>
    </div>
</template>

<script>
import MaterialService from "../services/MaterialService"; // Importing the MaterialService for API calls

export default {
    name: "MaterialDetailsView",
    data() {
        return {
            material: null,
        };
    },
    created() {
        const id = this.$route.params.id; // Get the material ID from the route parameters
        MaterialService.getMaterial(id)
            .then((response) => {
                this.material = response.data; // Assigning the response data to the material object
            })
            .catch(error => {
                if (error.response) {
                    console.error(`Error getting material. Response received was "${error.response.statusText}".`, error);
                } else if (error.request) {
                    console.error('Error getting material. Server could not be reached.', error);
                } else {
                    console.error('Error getting material. Request could not be created.', error);
                }
            });
        
    },
    methods: {
        editMaterial() {
            this.$router.push(`/edit-material/${this.material.materialId}`); // Redirect to the edit page
        },
        deleteMaterial() {
            const id = this.$route.params.id;
            if (confirm('Are you sure you want to delete this material?')) {
                MaterialService.deleteMaterial(id)
                    .then(() => {
                        this.$router.push({ name: 'materials' }); // Redirect to the materials list after deletion
                        alert('Material deleted successfully');
                    })
                    .catch(error => {
                        if (error.response) {
                            console.error(`Error deleting material. Response received was "${error.response.statusText}".`, error);
                        } else if (error.request) {
                            console.error('Error deleting material. Server could not be reached.', error);
                        } else {
                            console.error('Error deleting material. Request could not be created.', error);
                        }
                    });
            }
        },
       
    },
};
</script>

<style scoped>
.material-details {
    text-align: center;
}
h1 {
    color: beige;
    font-size: 2.0em;
    font-style: italic;
    font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-weight: bold;
    background-color:black;
    width: 300px;
    border-radius: 10px;
    margin-bottom: 5px;
}
.material-card{
    background-color:#5ebaca;
    padding: 16px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    width: 300px;
    border-radius: 12px;
}
.material-card:hover{
    transform: scale(1.02);
}
h2 {
    color: black;
    font-size: 22px;
    margin: 0;
}
p {
    margin: 5px 0;
    font-size: 18px;
    color: white;
    font-weight: 450px;
    font-weight: bold;
}
.btn-back{
    background-color:rgb(216, 234, 241);
    color: black;
    padding: 2px 10px;
    border-radius: 5px;
    font-size: 16px;
}
.actions{
    display: flex;
    margin-top: 10px;
    margin-bottom: -12px;
    justify-content: end;
}
.actions button{
    margin-left: 5px;
    padding: 4px 8px;  
    border-radius: 10px;
    cursor: pointer;
}
.actions button:hover{
    background-color: rgb(216, 234, 241);
}

@media (max-width: 425px) {
    .material-card{
        width: 250px;
    }
    h1{
        font-size: 22px;
        width: 250px;
    }
    .material-details {
        padding-top: 5px;
        padding-left: 66px;
    }
    p {
        font-size: 16px;
    }
    h2{
        font-size: 20px;
    }

}
</style>