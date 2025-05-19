<template>
    <div class="add-trade-form">
        <h1>Add New Trade</h1>
        <form @submit.prevent>
            <div class="form-group">
                <label for="materialID">Offered Material ID:</label>
                <input id="materialID" type="number" v-model="trade.offeredMaterialId" required />
            </div>
            <div class="form-group">
                <label for="material">Requested Material ID:</label>
                <input id="material" type="number" v-model="trade.requestedMaterialId" required />
            </div>
            <div class="form-group">
                <label for="businessID">Offered By (BusinessID):</label>
                <input id="businessID" type="number" v-model="trade.offeredByBusinessId" required />
            </div>
            <div class="form-group"> 
                <label for="business">Requested To (BusinessID):</label>
                <input id="business" type="number" v-model="trade.requestedToBusinessId" required />
            </div>
            <div class="form-group">
                <label for="status">Trade Status:</label>
                <select id="status" v-model="trade.tradeStatus" required >
                    <option value="" disabled>Select a status</option>
                    <option value="accepted">Accepted</option>
                    <option value="rejected">Rejected</option>
                    <option value="pending">Pending</option>
                </select>
            </div>
            <div class="form-group">
                <label for="date">Trade Date:</label>
                <input id="date" type="date" v-model="trade.tradeDate" required />
            </div>
            <div class="form-btn">
                <button @click="submitForm" type="submit">Add Trade</button>
            </div>
            
        </form>
    </div>
</template>

<script>
import TradesService from "../services/TradesService"; // Importing the TradesService for API calls
export default {
    name: 'AddTradeFormView',
    components: {},
    data() {
        return {
            trade: {
                offeredMaterialId: "",
                requestedMaterialId: "",
                offeredByBusinessId: "",
                requestedToBusinessId: "",
                tradeStatus: "",
                tradeDate: "",
            },
        }
    },
    methods: {
        submitForm() {
            console.log("trade submitted:", this.trade);
            TradesService.addTrade(this.trade)
                .then(response => {
                    if(response.status === 200 || response.status === 201) {
                        this.trade = response.data;
                        console.log("Trade added successfully:", this.trade);
                        alert('Trade added successfully!');
                        this.$router.push({ name: 'trades' });
                    }
                })
                .catch(error => {
                    console.error("Error adding material:", error);
                });
        }
    }
}
</script>

<style scoped>
h1{
    color: beige;
    font-size: 24px;
    font-style: italic;
    font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-weight: bold;
    background-color:black;
    width: 300px;
    border-radius: 5px;
    margin-bottom: 3px;
    margin-top: 0;
    padding: 2px;
}
.form-group {
    margin-bottom: 0px;
    display: flex;
    flex-direction: column;
}
form {
    background-color: #5ebaca;
    border-radius: 10px;
    padding: 0px;
    width: 300px;
}
.form-group label {
    font-size: 16px;
}
.form-group input,
select {
    border: 1px solid #999;
    border-radius: 4px;
    padding-left: 5px;
    width: 250px;
    margin-left: 25px;
    text-align: center;
}
button {
    background-color:rgb(216, 234, 241);
    color: black;
    padding: 2px 10px;
    cursor: pointer;
    border-radius: 5px;
    margin-bottom: 5px;
}
.form-btn{
    padding-left: 150px;
    padding-top: 5px;
    padding-bottom: 0;
}
button:hover{
    background-color: rgb(175, 213, 228);
}
@media (max-width: 425px) {
    h1{
        font-size: 20px;
    }
    .add-trade-form {
        padding-top: 5px;
        padding-left: 36px;
    }
    .form-group label {
        font-size: 15px;
    }
    .form-group input,
    select {
        width: 240px;
        margin-left: 30px;
        height: 20px;
    }
    button {
        font-size: 14px;
    }

}

</style>