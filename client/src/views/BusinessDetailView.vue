<!-- This is a view to display materials added by a specific business. -->
<template>
    <div class="business-detail-view" v-if="business">
        <h1>Business Profile</h1>
        <div class="detail-card">
            <h2>{{ business.businessName }}</h2>
            <p>Industry: <br>{{ business.businessType }}</p>
            <p>Location: <br> {{ business.city }}, {{ business.state }}, {{ business.country }}</p>
            <br>
            <router-link :to="{ name: 'businesses' }" class="btn-back">Back to Businesses</router-link>
            <div class="actions">
                <button @click="editBusiness(business)">Edit</button>
                <button @click="deleteBusiness">Delete</button>
            </div>
        </div>
    </div>
    <div v-else>
        <p>Loading...</p>
    </div>
</template>

<script>
import BusinessService from "../services/BusinessService"; // Importing the BusinessService for API calls

export default {
    data() {
        return {
            business: null,
        };
    },

    created(){
        const id = this.$route.params.id; // Get the business ID from the route parameters
        BusinessService.getBusiness(id)
            .then((response) => {
                this.business = response.data; // Assigning the response data to the business object
            })
            .catch(error => {
                if (error.response) {
                    console.error(`Error getting business. Response received was "${error.response.statusText}".`, error);
                } else if (error.request) {
                    console.error('Error getting business. Server could not be reached.', error);
                } else {
                    console.error('Error getting business. Request could not be created.', error);
                }
            });
    },
    methods:{
        editBusiness(){
        this.$router.push(`/edit-business/${this.business.businessId}`); // Redirect to the edit page
       },
        deleteBusiness(){
          const id = this.$route.params.id;
           if(confirm('Are you sure you want to delete this business?')) {
                BusinessService.deleteBusiness(id)
                .then(() => {
                    this.$router.push({ name: 'businesses' }); // Redirect to the businesses list after deletion
                    alert('Business deleted successfully');
                })
                .catch(error => {
                    if (error.response) {
                        console.error(`Error deleting business. Response received was "${error.response.statusText}".`, error);
                    } else if (error.request) {
                        console.error('Error deleting business. Server could not be reached.', error);
                    } else {
                        console.error('Error deleting business. Request could not be created.', error);
                    }
                });
            }
        
        },
    }
};
</script>

<style scoped>
.business-detail-view {
    text-align: center;
}
.business-detail-view h1{
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
.detail-card{
    background-color:#5ebaca;
    padding: 23px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    width: 300px;
    border-radius: 12px;
}
.detail-card:hover{
    transform: scale(1.02);
}
.detail-card h2{
    color: black;
    font-size: 22px;
    margin: 0;
}
.detail-card p{
    margin: 10px 0;
    font-size: 18px;
    color: white;
    font-weight: 450px;
}
.btn-back{
    background-color:rgb(216, 234, 241);
    color: black;
    padding: 5px 10px;
    border-radius: 5px;
    font-size: 16px;
}
.actions{
    display: flex;
    margin-top: 20px;
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
    .detail-card{
        width: 250px;
        height: 250px;
    }
    .business-detail-view {
        padding-top: 5px;
        padding-left: 66px;
    }
    .business-detail-view h1{
        font-size: 22px;
        font-style: italic;
        width: 250px;
        border-radius: 10px;
        margin-bottom: 5px;
    }
    .detail-card p {
        font-size: 15px;
    }
    .detail-card h2{
        font-size: 18px;
    }
    .btn-back{
        font-size: 14px;
    }
    .actions{
        margin-top: 10px;
    }

}

</style>