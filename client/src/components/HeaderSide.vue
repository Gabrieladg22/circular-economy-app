<template>
    <header class='header-side'>
        <img src="src/assets/logo-png.png" alt="CE Logo" />
        <h1>Circular Economy</h1>
        <div>
            <button id="darkModeToggle" class="toggle-switch" v-on:click="$emit('toggle-dark-mode')"></button>
            <div class="menu-icon" @click="toggleMenu">
            &#9776;
            </div>
            <div v-if="isMenuOpen" class="menu">
                <ul>
                    <li><router-link to="/businesses" @click="closeMenu">Businesses</router-link></li>
                    <li><router-link to="/materials" @click="closeMenu">Materials</router-link></li>
                    <li><router-link to="/trades" @click="closeMenu">Trades</router-link></li>
                    <li><router-link to="/cart" @click="closeMenu">Cart</router-link></li>
                </ul>
            </div>
        </div>
    </header>
</template>

<script>
export default {
    name: 'HeaderSide',
    data() {
        return {
            isMenuOpen: false
        };
    },
    methods: {
        toggleMenu() {
            this.isMenuOpen = !this.isMenuOpen;
        },
        closeMenu() {
            this.isMenuOpen = false;
        }
    }
};
</script>

<style scoped>
.header-side {
    color: #12b7d4;
    display: flex;
    justify-content: center;
    width: 1300px;
    height: 120px;
    box-shadow: 0 4px 5px 0 rgba(10, 26, 11, 0.14), 0 1px 0px 0 rgba(0,0,0,0.12), 0 20px 4px -1px rgba(0,0,0,0.2);
}
h1{
    font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    font-style: oblique;
    width: 700px;
    color:#12b7d4;
    margin-top: 10px;
    font-size: 40px;
    text-align: center;
    padding-top: 25px;
    padding-left: 200px;
}
img {
   border-style:double;
   border-radius: 65%;
   height: 110px;
   width: 115px;
   margin-top: 10px;
   margin-left: -5px;
}

.header-side .toggle-switch{
   background-color: #12b7d4;
   border-radius: 20px;
   position: relative;
   border: none;
   height: 28px;
   width: 50px;
   max-width: 100%;
   box-sizing: border-box;
   margin-left: 250px;
   transition: background-color 0.3s ease;
   cursor: pointer;
   margin-top: 20px;
}
 .toggle-switch::before{
   content: "";
   position: absolute;
   height: 22px;
   width: 22px;
   left: 3px;
   top: 3px;
   background-color: rgb(255, 255, 255);
   border-radius: 50%;
   transition: transform 0.3s ease;
}
.dark-mode .toggle-switch::before{
   transform: translateX(22px);
}

.menu-icon {
    font-size: 30px;
    cursor: pointer;
    margin-left: 230px;
    margin-top: 14px;
}
.menu{
    position: absolute;
    top: 110px;
    right: 22px;
    background-color: #b9ecd0;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    
}
.menu ul {
    list-style: none;
    padding: 0;
    margin: 0;
}
.menu li {
    padding: 10px 20px;
    border-bottom: #12b7d4 1px solid;
}
.menu a {
    text-decoration: none;
    color: black;
}
.menu li:hover {
    background-color: #4cd39f;
}

@media (max-width: 425px) {
    .header-side {
        justify-content: start;
        width: 50px;
    }
    h1{
        width: 300px;
        font-size: 20px;
        text-align: start;
        padding-top: 20px;
        padding-left: 70px;
    }
    img {
        border-style: double;
        border-radius: 50%;
        height: 60px;
        max-width: 60px;
        margin-top: 20px;
        margin-left: 13px;
    }
    .header-side .toggle-switch{
        height: 26px;
        width: 45px;
        margin-left: 75px;
        margin-top: 20px;
    }
    .menu-icon {
        font-size: 25px;
        margin-left: 50px;
        margin-top: 5px;
    }
    .menu{
        top: 90px;
        right: 18px;
        border-radius: 5px;
    }
    .menu li {
        padding: 5px 5px;
    }
    .menu a {
        font-size: 14px;
    }

}

</style>