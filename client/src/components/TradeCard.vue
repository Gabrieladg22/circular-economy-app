<template>
    <li :class="['trade-card', { selected }]" @click="$emit('select', trade.tradeId)">
        <p>📦{{ getMaterialName(trade.offeredMaterialId) }} ➡️ {{ getMaterialName(trade.requestedMaterialId) }} </p>
        <p>🔁{{ getBusinessName(trade.offeredByBusinessId) }} ➡️ {{ getBusinessName(trade.requestedToBusinessId) }}</p>
        <p>📅 Date: {{ trade.tradeDate }} | Status: <span :class="statusColor(trade.tradeStatus)"> {{ trade.tradeStatus }}</span></p>
    </li>  

</template>

<script>
export default {
    name: "TradeCard",
    props: {
        trade: Object,
        getBusinessName: Function,
        getMaterialName: Function, 
        statusColor: Function,
        selected: Boolean,
    },
    emits: ["select"],
};
</script>

<style scoped>
.trade-card {
  display: block;
  color: black;
  background-color:#5ebaca;
  padding: 2px;
  font-weight: bold;
  border-bottom: 3.3px solid white;
  cursor: pointer;
}
.trade-card:hover {
  background-color: white;
}
.accepted {
  color: rgb(5, 87, 5);
  font-weight: bold;
}
.rejected {
  color: rgb(214, 63, 63);
  font-weight: bold;
}
.pending {
  color: rgb(204, 92, 17);
  font-weight: bold;
}
.trade-card.selected:hover {
  background-color: #003f5c; 
  color: white;
  border: 2px solid red;
}
</style>