<template>
    <div class="cart-view">
        <h1>Your Cart</h1>
        <div v-if="cartItems.length">
            <ul>
                <li v-for="item in cartItems" :key="item.materialId">
                    <span>{{ item.materialName }}</span>
                    <span>{{ item.quantityKg }}</span>
                    <span>{{ item.price }}</span>
                    <span>{{ item.isAvailableForTrading }}</span>
                    <button @click="removeFromCart(item.id)">Remove</button>
                </li>
            </ul>
            <div class="total">
                <strong>Total:</strong> {{ totalPrice }}
            </div>
            <button @click="checkout">Checkout</button>
        </div>
        <div v-else>
            <p>Your cart is empty.</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "CartView",
    data() {
        return {
            cartItems: [],
        };
    },
    computed: {
        totalPrice() {
            return this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
        },
    },
    methods: {
        removeFromCart(itemId) {
            this.cartItems = this.cartItems.filter(item => item.id !== itemId);
        },
        checkout() {
            alert("Proceeding to checkout...");
            // Add checkout logic here
        },
    },
    filters: {
        currency(value) {
            return `$${value.toFixed(2)}`;
        },
    },
};
</script>

<style scoped>
.cart-view {
    padding: 20px;
    color: white;
}

.cart-view ul {
    list-style: none;
    padding: 0;
}

.cart-view li {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
}

.total {
    margin-top: 20px;
    font-size: 1.2em;
}

button {
    cursor: pointer;
}
</style>