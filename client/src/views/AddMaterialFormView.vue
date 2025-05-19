<template>
    <div class="add-material-form-view">
        <h1>Add New Material</h1>
        <form @submit.prevent>
            <div class="form-group">
                <label for="businessId">Business ID:</label>
                <input type="text" id="businessId" v-model="material.businessId" required />
            </div>
            <div class="form-group">
                <label for="materialName">Category:</label>
                <select type="text" id="materialName" v-model="material.materialName" required>
                    <option>Wood</option>
                    <option>Aluminum</option>
                    <option>Plastic</option>
                    <option>Glass</option>
                    <option>Paper</option>
                    <option>Iron</option>
                    <option>Titanium</option>
                    <option>Copper</option>
                    <option>Cement</option>
                    <option>Marble</option>
                    <option>Granite</option>
                    <option>Nickel</option>
                </select>  
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
                <label for="quantityKg">Quantity Kg:</label>
                <input id="text" v-model="material.quantityKg" required />
            </div>
            <div class="form-group">
                <label for="availableForTrading">Available For Trading:</label>
                <input id="checkbox" v-model="material.availableForTrading" required />
            </div>
            
            <div class="form-btn">
               <button type="submit" @click="submitForm">Add</button>
                <router-link to="/materials">
                    <button type="button">Back</button>
                </router-link>
            </div>
        </form>
    </div>
</template>

<script>
import MaterialService from '../services/MaterialService';
export default {
    name: "AddMaterialFormView",
    data() {
        return {
            material: {
                businessId: "",
                materialName: "",
                condition: "",
                price: "",
                quantityKg: "",
                availableForTrading: true,
            },
        };
    },
    computed:{
        materials(){
            return this.$store.state.materials;
        }
    },
    methods: {
        submitForm() {
            console.log("Material submitted:", this.material);
            MaterialService.addMaterial(this.material)
                .then(response => {
                    if(response.status === 200 || response.status === 201) {
                        const carbonSavings = response.data.carbonSavings;
                        this.material = response.data;
                        console.log("Material added successfully:", this.material);
                        alert(`Material added successfully! By Adding this material, you are helping to reduce waste and promote sustainability. You have saved ${carbonSavings} kg of CO2 emissions.`);
                        this.$router.push({ name: 'materials' });
                    }
                })
                .catch(error => {
                    console.error("Error adding material:", error);
                });
        }
    },
};
</script>

<style scoped>
.add-material-form-view {
    margin: 0;
    padding: 0;
}
h1{
    color: beige;
    font-size: 24px;
    font-style: italic;
    font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-weight: bold;
    background-color:black;
    width: 300px;
    border-radius: 5px;
    margin-bottom: 5px;
    margin-top: 5px;
    padding: 5px;
}
.form-group {
    display: flex;
    margin-bottom: 5px;
    justify-content: space-between;
}
form {
    margin-bottom: 0px;
    background-color: #5ebaca;
    border-radius: 10px;
    padding: 20px;
    width: 300px;
}

.form-group label {
    display: flex;
    align-items: center;
    font-weight: bold;
    margin-bottom: 5px;
    color: black;
    font-size: 16px;
}
.form-group input,select {
    padding: 3.4px;
    border: 1px solid #999;
    border-radius: 4px;
    padding-left: 10px;
    width: 160px;
}

button {
    background-color:rgb(216, 234, 241);
    color: black;
    padding: 3px 15px;
    cursor: pointer;
    border-radius: 5px;
}
.form-btn{
    padding-left: 130px;
    padding-top: 3px;
    padding-bottom: 1px;
}
button:hover{
    background-color: rgb(175, 213, 228);
}

@media (max-width: 425px) {
    h1{
        font-size: 18px;
    }
    .add-material-form-view {
        padding-top: 5px;
        padding-left: 36px;
    }
    .form-group label {
        font-size: 13px;
    }
    .form-group input,
    select {
        width: 160px;
        margin-left: 5px;
        height: 20px;
    }
    button {
        font-size: 14px;
    }

}
</style>


