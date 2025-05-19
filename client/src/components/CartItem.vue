<template>
    <div class="cart-item">
        <img :src="material.image" :alt="material.name" class="item-image" />
        <div class="item-details">
            <h3 class="item-name">{{ material.name }}</h3>
            <p class="item-price">${{ material.price.toFixed(2) }}</p>
            <div class="item-quantity">
                <button @click="decreaseQuantity" class="quantity-btn">-</button>
                <span>{{ material.quantity }}</span>
                <button @click="increaseQuantity" class="quantity-btn">+</button>
            </div>
        </div>
        <button @click="removeItem" class="remove-btn">Remove</button>
    </div>
</template>

<script>
export default {
    name: "CartItem",
    props: {
        material: {
            type: Object,
            required: true,
        },
    },
    methods: {
        increaseQuantity() {
            this.$emit("update-quantity", this.material.id, this.material.quantity + 1);
        },
        decreaseQuantity() {
            if (this.material.quantity > 1) {
                this.$emit("update-quantity", this.material.id, this.material.quantity - 1);
            }
        },
        removeItem() {
            this.$emit("remove-item", this.material.id);
        },
    },
};
</script>

<style scoped>
.cart-item {
    display: flex;
    align-items: center;
    margin-bottom: 1rem;
    border-bottom: 1px solid #ccc;
    padding-bottom: 1rem;
}

.item-image {
    width: 80px;
    height: 80px;
    object-fit: cover;
    margin-right: 1rem;
}

.item-details {
    flex: 1;
}

.item-name {
    margin: 0;
    font-size: 1.2rem;
}

.item-price {
    color: #555;
}

.item-quantity {
    display: flex;
    align-items: center;
    margin-top: 0.5rem;
}

.quantity-btn {
    background-color: #ddd;
    border: none;
    padding: 0.5rem;
    cursor: pointer;
}

.quantity-btn:hover {
    background-color: #bbb;
}

.remove-btn {
    background-color: #ff4d4d;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    cursor: pointer;
}

.remove-btn:hover {
    background-color: #e60000;
}
</style>