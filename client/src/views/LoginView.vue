<template>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1>Please Sign In</h1>
      <br>
      <div id="fields">
        <label for="username">Username:&NonBreakingSpace;</label>
        <input
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <br>
        <label for="password">Password:&NonBreakingSpace;</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        
        <div><br><button type="submit">Sign in</button></div>
      </div>
      <br>
      <hr/>
      Need an account? <router-link v-bind:to="{ name: 'register' }">Register!</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
#app {
  height: 100%;
  
}
#login {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  
}
#login form {
  background-color: whitesmoke;
  padding: 5px;
  width: 350px;
  margin: 0 auto;
  border-radius: 5px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}
#login h1 {
  text-align: center;
  color: #333;
}
#login #fields {
  display: flex;
  flex-direction: column;
  gap: 0;
}
#login label {
  font-weight: bold;
}
#login input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
#login button {
  background-color: #548fce;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
#login button:hover {
  background-color: #0056b3;
}
#login hr {
  margin: 10px 0;
}
#login a {
  color: #007bff;
  text-decoration: none;
}
#login a:hover {
  text-decoration: underline;
}

@media (max-width: 425px) {

  #login {
    padding-left: 75px;
    padding-top: 20px;
    height: 260px;
  }
  #login form {
    width: 220px;
    height: 275px;
    padding: 5px;
    font-size: 13px;
  }
  #login h1 {
    font-size: 16px;
    margin-bottom: -5px;
  }
  #login label {
    font-size: 14px;
  }
  #login input {
    padding: 12px;
    font-size: 10px;
    width: 170px;
    margin-left: 25px;
  }
  #login button {
    padding: 4px;
    font-size: 12px;
  }
  #login hr {
    margin: 0;
  }
  #login a {
    padding-bottom: 5px;
    font-size: 12px;
  }
}

</style>
