<!-- This view will display all businesses -->
<template>
    <div class="businesses-view">
        <h1>Registered Businesses</h1>
        <div>
            <p>Click the button below to register your business!</p>
            <router-link to="/createBusiness">
                <button>Create Business</button>
            </router-link>
        </div>
        <div class="businesses-card">
            <business-card   />
        </div>
        <!-- v-for="b in businesses" :key="b.id" :business="b" -->
    </div>
</template>

<script>
import BusinessCard from "../components/BusinessCard.vue"; // Importing the BusinessCard component
import BusinessService from "../services/BusinessService"; // Importing the BusinessService for API calls

export default {
    name: "BusinessesView",
    components: {
        BusinessCard
    },
    
    data() {
        return {
            businesses: [], // Array to hold business data
        };
    },
    created() {
        if(this.$store.state.businesses.length === 0){
            BusinessService.businesses()
            .then((response) => {
                this.businesses = response.data; // Assigning the response data to the businesses array
                this.$store.commit('SET_BUSINESSES' , response.data);
            })
            .catch(error => {
                if (error.response) {
                    console.error(`Error getting products. Response received was "${error.response.statusText}".`, error);
                } else if (error.request) {
                    console.error('Error getting products. Server could not be reached.', error);
                } else {
                    console.error('Error getting products. Request could not be created.', error);
                }
            });
        } else {
            this.businesses = this.$store.state.businesses;
        }
        
    },
    methods: {
        // Method to handle business creation
        createBusiness() {
            this.$router.push({ name: "CreateBusiness" });
        }
    }
};
</script>

<style scoped>
.businesses-card {
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
    .businesses-card {
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
        padding-left: 215px; 
    }

}
</style>