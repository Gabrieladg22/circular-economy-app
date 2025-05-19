<template>
  <div class="cart-app" :class="{ 'dark-mode': isDarkMode }" >
   
    <div id="app">
      <header>
         <header-side @toggle-dark-mode="toggleDarkMode"/>
      </header>
      <nav>
          <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token" class="login-link">
            Logout
          </router-link>
          <router-link v-bind:to="{ name: 'login' }" v-else class="login-link">Login</router-link>
      </nav>
      <main>
        <router-view />
      </main>
    
      <footer>
        <footer-side />

      </footer>
      
   </div>
  </div>
</template>

<script>
import HeaderSide from './components/HeaderSide.vue';
import FooterSide from './components/FooterSide.vue';

export default{
  components: {
    HeaderSide,
    FooterSide,
  },
  data() {
    return {
      isDarkMode: false,
    };
  },
  methods: {
    toggleDarkMode() {
      this.isDarkMode = !this.isDarkMode;
    },
  },
}
</script>

<style scoped>
.cart-app {
  margin: 0;
  padding: 0;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  text-align: center;
  overflow: hidden; 
  background-image: url("src/assets/TechEnvironment3.jpg");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  
}

#app {
  
    display: grid;
    grid-template-areas:
      "header header"
      "nav nav"
      "main main"
      "footer footer";
    grid-template-columns: 1fr 1fr;
    grid-template-rows: auto auto auto auto;
    gap: 20px;
    height: 100%; 
    justify-items: center;
    overflow: hidden; 
}
.dark-mode{
  background-image: url("src/assets/TechEnvironment4.jpg");
  /* background-size: cover;
  background-position: center;
  background-repeat: no-repeat; */
  
}

/* Style the grid areas */
header {
  grid-area: header;
}
.login-link {
  color: #12b7d4;
  font-weight: bold;
  font-size: 18px;
  padding-right: 0;
}

nav {
  display: flex;
  align-items: center;
  justify-content:end;
  grid-area: nav;
  font-weight: bold;
  width: 1100px;
  height: fit-content;
} 

main {
  grid-area: main;
}

footer {
  grid-area: footer;
  text-align: center;
  width: 500px;
}
@media (max-width: 425px) {

  #app {
    grid-template-areas:
      "header "
      "nav "
      "main "
      "footer ";
    grid-template-columns: 1fr ;
    grid-template-rows: auto auto ;
    gap: 5px;
    justify-items: left;
  }

  .login-link {
    color: #12b7d4;
    font-weight: bold;
    font-size: 16px;
    padding-left: 12px;
  }

  nav {
    justify-content: start;
    width: 100px;
  } 
  footer {
  text-align: center;
  width: 350px;
  padding-left: 5px;
}


}

</style>