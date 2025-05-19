<template>
    <div class="trades-view">
        <h1>Trade Activity</h1>
        <div>
            <p>Click the buttons below to add or delete a trade</p>
            <router-link to="/createTrade">
                <button>Create Trade</button>
            </router-link>
            <button @click="deleteTrade">Delete Trade</button>
        </div>
        <ul class="trades-list">
            <trade-card  v-for="trade in trades" :key="trade.tradeId" :trade="trade" :getBusinessName="getBusinessName" :selected="trade.tradeId === selectedTradeId" 
            :getMaterialName="getMaterialName" :statusColor="statusColor" @select="handlerTradeSelection"/>
        </ul>
    </div>
</template>

<script>
import TradeCard from "../components/TradeCard.vue"; // Importing the TradeCard component
import TradesService from "../services/TradesService"; // Importing the TradesService for API calls
import BusinessService from "../services/BusinessService"; // Importing the BusinessService for API calls
import MaterialService from "../services/MaterialService"; // Importing the MaterialsService for API calls

export default {
    name: "TradesView",
    components: {
        TradeCard
    },
    data() {
        return {
            selectedTradeId: null,
            trades: [],
            businesses: {},
            materials: {},
        };
    },
    created(){
        this.loadTrades();
        this.loadBusinesses();
        this.loadMaterials(); 
    },               
    methods: {
        loadTrades(){
            if(this.$store.state.trades.length === 0){
                TradesService.listOfTrades()
            .then((response) => {
                this.trades = response.data;
            }).catch((error) => {
                console.error("Error loading trades:", error);
            });
            } else {
             this.trades = this.$store.state.trades;
            }
        },
        loadBusinesses(){
            BusinessService.businesses()
            .then((response) => {
                this.businesses = response.data.reduce((map, b) => {
                    map[b.businessId] = b.businessName;
                    return map;
                }, {});
            }).catch((error) => {
                console.error("Error loading businesses:", error);
            });
        },
        loadMaterials(){
            MaterialService.listOfMaterials()
            .then((response) => {
                this.materials = response.data.reduce((map, m) => {
                    map[m.materialId] = m.materialName;
                    return map;
                }, {});
            }).catch((error) => {
                console.error("Error loading materials:", error);
            });
        },
        getBusinessName(businessId) {
            return this.businesses[businessId];
        },
        getMaterialName(materialId) {
            return this.materials[materialId];
        },
        statusColor(status) {
          switch (status) {
              case "accepted":
                  return "accepted";
               case "rejected":
                  return "rejected";
               case "pending":
                  return "pending";
            }
        },
        handlerTradeSelection(tradeId) {
            this.selectedTradeId = tradeId;
        },
        deleteTrade(){
            if(!this.selectedTradeId){
                alert('Please select a trade to delete');
                return;
            }
            if (confirm('Are you sure you want to delete this trade?')) {
                TradesService.deleteTrade(this.selectedTradeId)
                    .then(() => {
                        alert('Trade deleted successfully');
                        this.trades = this.trades.filter(t => t.tradeId !== this.selectedTradeId); //Removing deleted trade from the list
                        this.selectedTradeId = null; // Reset the selected trade ID
                        this.$router.push({ name: 'trades' }); // Redirect to the trades list after deletion
                        
                    })
                    .catch(error => {
                        if (error.response) {
                            console.error(`Error deleting trade. Response received was "${error.response.statusText}".`, error);
                        } else if (error.request) {
                            console.error('Error deleting trade. Server could not be reached.', error);
                        } else {
                            console.error('Error deleting trade. Request could not be created.', error);
                        }
                    });
            }
        }
    },
};
</script>

<style scoped>
.trades-list {
    margin: 0;
    padding: 0;
    display: block;
    max-height: 225px;
    overflow-y: auto;
}

h1 {
    color: beige;
    background-color: black;
    margin-top: 0;
    font-size: 30px;
    padding: 3px;
    border-radius: 5px;
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-style: italic;
}
p {
    color:whitesmoke;
    font-size: 20px;
    margin: 5px;
}
a{
   padding-left: 220px;
}
button{
    margin-bottom: 5px;
}
button:hover{
    cursor: pointer;
    background-color: rgb(183, 216, 230);
}

@media (max-width: 425px) {
    .trades-list{
        max-height: 150px;
        width: 350px;
        padding-left: 20px;
    }
    h1{
        font-size: 20px;
        margin-top: 10px;
        margin-left: 25px;
        width: 310px;
    }
    p{
        font-size: 16px;
        padding-left: 5px;
    }
    button{
        display: flex;
        margin-bottom: 3px;
        margin-left: 245px;
    }
}

</style>