<template>
  <div class="business-card">
        <table class="header-table">
            <thead>
                <tr>
                    <th>Business ID</th>
                    <th>Business Name</th>
                </tr>
            </thead>
        </table>
        <div class="scrollable-body">
            <table class="body-table">
                <tbody>
                    <tr v-for="b in businesses" :key="b.id" @click="goToDetails(b)">
                        <td>{{ b.businessId }}</td>
                        <td>{{ b.businessName }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
  </div>
</template>

<script>
export default {
    name: "BusinessCard",
    computed: {
        businesses(){
            return this.$store.state.businesses;
        }
    },
    methods: {
        goToDetails(business) {
            this.$router.push({ name: 'businessProfile', params: { id: business.businessId } });
        }
    }
};
</script>

<style scoped>
.business-card {
    display: block;
    color: white;
    padding: 0;
    cursor: pointer;
    font-weight: bold;
    text-align: justify;
}
td {
    text-align: center;
    color: white;
    background-color:#5ebaca;
    padding: 10px 65px;
    cursor: pointer;
    font-size: 17px;
    font-weight: bold;
    
} 
td:hover{
    color: black;
}
th{
    color: black;
    padding-right: 50px;
    padding-bottom: 15px;
    padding-top: 15px;
    background-color: white;
    text-align: center;
    font-size: 18px;
    
} 
.header-table, .body-table{
    width: 100%;
    border-collapse: collapse;
    
}
.scrollable-body{
    display: block;
    max-height: 170px;
    overflow-y: auto;
    
}
.business-card:hover {
    background-color: white;
    color: rgb(35, 112, 175);
}

@media (max-width: 425px) {
    .business-card {
        width:326px;
    }
    td {
        padding: 10px 25px;
        font-size: 14px;
    } 
    th{
        padding-left: 15px;
        padding-bottom: 10px;
        padding-top: 10px;
        font-size: 15px;
    } 
    .scrollable-body{
        max-height: 120px;
    }

}
</style>