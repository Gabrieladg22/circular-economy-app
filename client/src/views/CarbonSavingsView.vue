<template>
    <div class="carbon-view">
        <h1>Carbon Savings Dashboard</h1>

        <div class="carbon-savings">
            <p>Track your carbon savings and make a positive impact on the environment!</p>
            <input v-model="filters.businessName" placeholder="Filter by business"  />
            <input v-model="filters.materialName" placeholder="Filter by material"  />
            <button @click="loadSavings">Filter</button>
        
            <div class="savings-view">
                <savings-dashboard v-for="s in savings" :key="s.savingsId" :saving="s" />

            </div>
        </div>
        
    </div>
    
</template>

<script>
import SavingsDashboard from '../components/SavingsDashboard.vue';
import SavingsService from '../services/SavingsService'; // Importing the SavingsService for API calls
export default {
    
    name: "CarbonSavingsView",
    components: {
        SavingsDashboard,
    },
    data() {
        return {
            savings: [],
            filters: {
                businessName: '',
                materialName: ''
            }
        };
    },
    created(){
        if(this.$store.state.savings.length === 0) {
            SavingsService.listOfSavings()
            .then(response => {
            this.savings = response.data;
            })
            .catch(error => {
            if (error.response) {
                console.error(`Error getting savings. Response received was "${error.response.statusText}".`, error);
            } else if (error.request) {
                console.error('Error getting savings. Server could not be reached.', error);
            } else {
                console.error('Error getting savings. Request could not be created.', error);
            }
            });
        } else {
             this.savings = this.$state.store.savings;
        }
    },
    methods: {        
        loadSavings() {
            
            SavingsService.listOfSavings(this.filters)
            .then(response => {
            this.savings = response.data;
            })
            .catch(error => {
            console.error('Error loading savings:', error);
            });
        },
        mounted(){
            this.loadSavings();
        }
    },
};
</script>

<style scoped>

.carbon-savings {
    color: white;
    border-radius: 50px;
}
.savings-view {
    display: flex;
    flex-wrap: wrap;
    overflow-y: auto;
    max-height: 213px;
    width: 800px;
    padding-left: 120px;
}
h1{
    color: beige;
    font-size: 24px;
    margin-top: 0;
    background-color: black;
    padding: 3px;
    border-radius: 5px;
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-style: italic;
    width: 400px;
    display: inline-flex;
    padding-left: 35px;
}
p {
    color: white;
    font-size: 18px;
}
@media (max-width: 425px) {
    .carbon-savings {
        font-size: 14px;
    }
    .savings-view {
        max-height: 150px;
        width: 350px;
        padding-left: 35px;
    }
    h1{
        font-size: 14px;
        padding: 4px;
        width: 220px;
        padding-left: 8px;
        margin-bottom: 0;
    }
    p {
        font-size: 14px;
    }
    input{
        font-size: 13px;
    }

}
</style>