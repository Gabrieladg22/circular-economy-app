<template>
  <div id="register">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div id="fields">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="name">Name</label>
        <input
          type="text"
          id="name"
          placeholder="Name"
          v-model="user.name"
          required
        />
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <label for="confirmPassword">Confirm password</label>
        <input
          type="password"
          id="confirmPassword"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />

        <label for="address">Address</label>
        <input
          type="text"
          id="address"
          placeholder="Address"
          v-model="user.address"
        />

        <label for="city">City</label>
        <input type="text" id="city" placeholder="City" v-model="user.city" />

        <label for="state">State</label>
        <input
          type="text"
          id="state"
          placeholder="State"
          v-model="user.stateCode"
          maxlength="2"
          required
        />

        <label for="zip">ZIP</label>
        <input
          type="number"
          id="zip"
          placeholder="ZIP"
          v-model="user.zip"
          required
          minlength="5"
          maxlength="5"
        />
        <div></div>
        <div style="text-align:start;">
          <button type="submit">Create Account</button>
        </div>
      </div>
      <hr />
      Have an account?
      <router-link v-bind:to="{ name: 'login' }">Sign in!</router-link>
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
        name: "",
        password: "",
        confirmPassword: "",
        address: "",
        city: "",
        stateCode: "",
        zip: "",
        role: "user",
      },
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.success("Thank you for registering, please sign in.");
              this.$router.push({
                path: "/login",
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                // Show the validation errors
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}':${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
#app {
  height: 100%;
}
#register {
  height: 100%;
  color: whitesmoke;
  
}
h1{
  color: rgb(107, 201, 238);
  font-size: 23px;
  margin-top: 0;
}
#fields {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: auto auto auto auto auto auto auto auto auto;
  gap: 3px;
}
label {
  text-align: right;
  padding-right: 20px;
  font-size: 20px;
  color: whitesmoke;
}
input {
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 13px;
}
input:focus {
  outline: none;
  border-color: #788ea0;
}
button {
  background-color: #548fce;
  color: white;
  padding: 5px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
button:hover {
  background-color: #1c4631;
}
hr {
  margin: 5px 0;
}
a {
  color: rgb(112, 206, 240);
  text-decoration: none;
  font-weight: bold;
  font-size: 18px;
}
a:hover {
  text-decoration: underline;
}

@media (max-width: 425px) {
    h1{
    font-size: 18px;
  }
  label {
    padding-left: 25px;
    font-size: 15px;
  }
  input {
    border-radius: 5px;
    border: 1px solid #ccc;
    font-size: 13px;
  }
  button {
    padding: 3px;
    font-size: 13px;
  }
  a {
    font-size: 15px;
  }
  hr{
    margin-left: 40px;
  }
 

  
}

</style>
