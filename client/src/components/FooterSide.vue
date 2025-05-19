<template>
    <footer class='footer-side'>
        <nav>
          <ul>
              <li><a @click="goTo('home')">Home</a></li>
              <li><a @click="redirectTo('savings')">Dashboard</a></li>
          </ul>
        </nav>
        <p>&copy; 2025 Gabriela Diaz. All rights reserved.</p>
    </footer>
</template>

<script>
export default {
   name: 'FooterSide',
   methods: {
      goTo(home) {
         this.$router.push({ name: home });
      },
      redirectTo(savings) {
         this.$router.push({ name: savings });
      }
   }
};
</script>

<style scoped>
.footer-side{
   font-size: 17px;
   height: 95px;
}
nav{
   display: flex;
   padding-top: 0;
   padding-left: 90px;
}
/* Styling nav ul to separate Home and Dashboard*/
nav ul{
   list-style: none;
   display: inline-flex;
   gap: 15px;
   padding-left: 85px;
}
nav ul li {
   position: relative;
   cursor: pointer;
}
nav ul li a{
   text-decoration: none;
   color: rgb(117, 228, 248);
   font-weight: bold;
   font-size: 19px;
   
}

nav ul li:not(:last-child)::after {
   content: "|";
   font-size: 20px;
   position: absolute;
   right: -10px;
   color: rgb(179, 20, 20);
}

nav ul li a:hover {
   color: rgb(231, 112, 14);
} 
p{
   color: whitesmoke;
   margin: 0;
}

@media (max-width: 425px) {
   nav{
      padding-left: 40px;
   }
   nav ul li a{
      font-size: 15px;
   }
   nav ul li:not(:last-child)::after {
      font-size: 15px;
   }
   p{
      font-size: 13px;

   }


}
</style>