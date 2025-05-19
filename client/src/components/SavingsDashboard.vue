<template>
  <div class="savings-dashboard">
       
            <div class="savings-card">
                <h3>{{ saving.businessName }}</h3>
                <p>Material: {{ saving.materialName }}</p>
                <p>CO₂ Saved: {{ saving.carbonSavings }} kg</p>
            </div>
            
      
        <!-- <div v-if="savings.length === 0">No savings found.</div> -->
  </div>
</template>

<script>

export default {
  name: 'SavingsDashboard',
  props: {
    saving: Object
  },
  
};
</script>

<style scoped>
.savings-dashboard{
  margin: 10px;
  padding: 20px;
  text-align: center;
  border-radius: 25px;
  border: 2px solid white;
  background-color: #5ebaca;
  color: black;
}
.savings-dashboard:hover{
background-color: white;
  color: rgb(22, 127, 212);
}
@media (max-width: 425px) {
  .savings-dashboard{
    margin: 5px;
    padding: 10px;
    border: 1px solid white;
  }
  .savings-card h3{
    font-size: 14px;
  }
  .savings-card p{
    font-size: 14px;
  }

}


</style>
