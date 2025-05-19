<template>
    <div class="edit-material-form" >
        <h1>Edit Material</h1>
        <form @submit.prevent >
            <div class="form-group">
                <label for="materialId">Material ID:</label>
                <input type="text" id="materialId" v-model="material.id" required />
            </div>
            <div class="form-group">
                <label for="materialName">Name:</label>
                <input type="text" id="materialName" v-model="material.materialName" required />
            </div>
            <div class="form-group">
                <label for="condition">Condition:</label>
                <input type="text" id="condition" v-model="material.condition" required />
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input id="price" v-model="material.price" required />
            </div>
            <div class="form-group">
                <label for="quantity">Quantity Kg:</label>
                <input id="text" v-model="material.quantityKg" required />
            </div>
            <div class="form-group">
                <label for="createdBy">Created By:</label>
                <input id="text" v-model="material.createdBy" required />
            </div>
            <div class="form-group">
                <label for="available">Is Available:</label>
                <input id="text" v-model="material.availableForTrading" required />
            </div>
            <div class="buttons">
                <button type="submit" @click="submitForm">Save Changes</button>
                <button type="button" @click="cancelEdit">Cancel</button>
            </div>
            
        </form>
    </div>
</template>

<script>
import MaterialService from '../services/MaterialService'; 
export default {
    name: "EditMaterialFormView",
    data() {
        return {
            material: {}, 
        };
    },
    created() {
        const materialId = this.$route.params.id;
        console.log("Business ID:", materialId);
        MaterialService.getMaterial(materialId)
        .then(response => {
            this.material = response.data;
        })
        .catch(error => {
            console.error('Failed to fetch material data:', error);
        });
   },
    methods: {
        submitForm(){
           if(confirm('Are you sure you want to update this material?')) {
                // console.log("API#1:", this.business, this.business.id);
                MaterialService.updateMaterial(this.material, this.material.id)
                .then((response) => {
                    // console.log("API#2:", response.data);
                    alert('Material updated successfully');
                    this.material = response.data;
                    this.$router.push({ name: 'materialProfile', params: { id: this.material.id } }); 
                })
                .catch(error => {
                    if (error.response) {
                        console.error(`Error updating material. Response received was "${error.response.statusText}".`, error);
                    } else if (error.request) {
                        console.error('Error updating material. Server could not be reached.', error);
                    } else {
                        console.error('Error updating material. Request could not be created.', error);
                    }
                });
            }
        
        },
        cancelEdit() {
            const id = this.$route.params.id; 
            this.$router.push(`/materialProfile/${id}`); 
        },
    },
};
</script>

<style scoped>
.buttons {
    display: flex;
    justify-content: end;
    padding-right: 35px;
}
form{
    background-color: #5ebaca;
    width: 300px;
    border: 1px solid #ccc;
    border-radius: 8px;
}

label {
    display: block;
    font-weight: bold;
}
input {
    width: 225px;
    padding: 0.2px;
    border: 1px solid #ccc;
    border-radius: 4px;
    text-align: center;
}

button {
    padding: 0 10px;
    background-color: white;
    color: black;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 1px;
    margin-bottom: 5px;
}

button:hover {
    background-color: rgb(216, 234, 241);
}
h1{
    color: beige;
    font-size: 2.0em;
    font-style: italic;
    font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-weight: bold;
    background-color:black;
    width: 300px;
    border-radius: 5px;
    margin-bottom: 3px;
    margin-top: -35px;
}
@media (max-width: 425px) {
    form{
        width: 250px;
    }
    h1{
        font-size: 20px;
        width: 250px;
    }
    .edit-material-form {
        padding-top: 40px;
        padding-left: 60px;
    }
    .form-group label {
        font-size: 15px;
    }
    .form-group input {
        width: 180px;
        margin-left: 5px;
        height: 18px;
    }
    button {
        margin-top: 5px;
        font-size: 14px;
    }

}
</style>