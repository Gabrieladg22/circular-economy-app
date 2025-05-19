<template>
    <div class="new-business-view">
        <h1>Register Your Business</h1>
        <form @submit.prevent>
            <div class="form-group">
                <label for="businessName">Business Name:</label>
                <input type="text" id="businessName" v-model="newBusiness.businessName" required />
            </div>
            <div class="form-group">
                <label for="businessType">Industry:</label>
                <input type="text" id="businessType" v-model="newBusiness.businessType" required />
            </div>
            <div class="form-group">
                <label for="city">City:</label>
                <input type="text" id="city" v-model="newBusiness.city" required />
            </div>
            <div class="form-group">
                <label for="state">State:</label>
                <input id="text" v-model="newBusiness.state" required />
            </div>
            <div class="form-group">
                <label for="country">Country:</label>
                <input id="text" v-model="newBusiness.country" required />
            </div>
            <div class="form-btn">
               <button type="submit" @click="submitForm">Create</button>
                <router-link to="/businesses">
                    <button type="button">Back</button>
                </router-link>
            </div>
            
        </form>
    </div>
</template>

<script>
import BusinessService from '../services/BusinessService';

export default {
    name: "NewBusinessView",
    data() {
        return {
            newBusiness: {
                businessName: "",
                businessType: "",
                city: "",
                state: "",
                country: "",
            },
        };
    },
    computed: {
        businesses(){
            return this.$store.state.businesses;
        }
    },
    methods: {
        submitForm() {
            console.log("Business submitted:", this.newBusiness);
            BusinessService.createBusiness(this.newBusiness)
                .then(response => {
                    if(response.status === 200 || response.status === 201) {
                        this.newBusiness = response.data;
                        alert("Business created successfully:");
                        this.$router.push({ name: 'businesses' });
                    }
                    
                })
                .catch(error => {
                    console.error("Error creating business:", error);
                });
        },
    },
};
</script>

<style scoped>
.new-business-view {
    width: 280px;
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
    margin-bottom: 3.5px;
    margin-top: 0px;
    padding: 5px;
}

form {
    margin-bottom: 0px;
    background-color: #5ebaca;
    border-radius: 10px;
    padding: 7px;
    width: 300px;
}

label {
    display: block;
    font-weight: bold;
    margin-bottom: 0px;
}
input {
    width: 100%;
    padding: 2.5px;
    box-sizing: border-box;
    width: 230px;
}

button {
    background-color:rgb(216, 234, 241);
    color: black;
    padding: 3px 15px;
    cursor: pointer;
    border-radius: 5px;
}
.form-btn{
    display: flex;
    padding-left: 112px;
    padding-top: 5px;
}
button:hover{
    background-color: rgb(175, 213, 228);
}

@media (max-width: 425px) {
    h1{
        font-size: 20px;
    }
    .new-business-view {
        padding-top: 5px;
        padding-left: 36px;
    }
    .form-group label {
        font-size: 15px;
    }
    .form-group input {
        width: 220px;
        margin-left: 5px;
        height: 22px;
    }
    button {
        font-size: 14px;
    }

}


</style>