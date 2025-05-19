<template>
    <div class="edit-business-form" >
        <h1>Edit Business</h1>
        <form @submit.prevent >
            <div class="form-group">
                <label for="id">Business ID:</label>
                <input type="text" id="id" v-model="business.id" required />
            </div>
            <div class="form-group">
                <label for="businessName">Business Name:</label>
                <input type="text" id="businessName" v-model="business.businessName" required />
            </div>
            <div class="form-group">
                <label for="businessType">Industry:</label>
                <input type="text" id="businessType" v-model="business.businessType" required />
            </div>
            <div class="form-group">
                <label for="city">City:</label>
                <input type="text" id="city" v-model="business.city" required />
            </div>
            <div class="form-group">
                <label for="state">State:</label>
                <input id="text" v-model="business.state" required />
            </div>
            <div class="form-group">
                <label for="country">Country:</label>
                <input id="text" v-model="business.country" required />
            </div>
            <div class="buttons">
                <button type="submit" @click="submitForm">Save Changes</button>
                <button type="button" @click="cancelEdit">Cancel</button>
            </div>
            
        </form>
    </div>
    <!-- <div v-else>
        <p>Loading id business</p>
    </div> -->
</template>

<script>
import BusinessService from "../services/BusinessService"; // Importing the BusinessService for API calls
export default {
    name: "EditBusinessFormView",
    data() {
        return {
            business: {}, // Initialize business data
        };
    },
    created() {
        const businessId = this.$route.params.id;
        console.log("Business ID:", businessId);
        BusinessService.getBusiness(businessId)
        .then(response => {
            this.business = response.data;
        })
        .catch(error => {
            console.error('Failed to fetch business data:', error);
        });
   },
    methods: {
        submitForm(){
           if(confirm('Are you sure you want to update this business?')) {
                // console.log("API#1:", this.business, this.business.id);
                BusinessService.updateBusiness(this.business, this.business.id)
                .then((response) => {
                    // console.log("API#2:", response.data);
                    alert('Business updated successfully');
                    this.$router.push({ name: 'businessProfile', params: { id: this.business.id } }); 
                })
                .catch(error => {
                    if (error.response) {
                        console.error(`Error updating business. Response received was "${error.response.statusText}".`, error);
                    } else if (error.request) {
                        console.error('Error updating business. Server could not be reached.', error);
                    } else {
                        console.error('Error updating business. Request could not be created.', error);
                    }
                });
            }
        
        },
        cancelEdit() {
            const id = this.$route.params.id; 
            this.$router.push(`/businessProfile/${id}`); 
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
    padding: 0px;
    border: 1px solid #ccc;
    border-radius: 8px;
}

label {
    display: block;
    font-weight: bold;
}
input {
    width: 225px;
    padding: 1.2px;
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
    margin-top: 4px;
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
    margin-top: -5px;
}

@media (max-width: 425px) {
    form{
        width: 250px;
    }
    h1{
        font-size: 20px;
        width: 250px;
    }
    .edit-business-form {
        padding-top: 20px;
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