<template>
    <div class="main-view">
        
        <div class="app-info">
          <p><strong>Welcome!</strong> <br/> Our platform empowers businesses to exchange surplus or unused materials,
          reduce waste and promote sustainability. Whether you are a small local shop or a large organization, 
          you can register your business, list available materials, and find what others are offering.
          <br><em>All in one Place!</em></p> 
          
        </div>
        <div class="heading">
            <p> Beyond trading, our app helps you measure the carbon emissions you have saved by reusing instead
              of discarding materials. Join a growing community of environmentally conscious businesses working
              together toward a greener future while also saving money and resources.
          </p>
        </div>
          
       
    </div>
</template>

<script>
export default {
    name: "MainView",
};
</script>

<style scoped>

.main-view {
   width: auto;
   display: grid;
   grid-template-columns: 1fr 1.2fr;
   grid-column: 1 / span 2;
   grid-row: 2 / span 3;
   grid-template-rows: 180px 170px;
   grid-template-areas:
   "information ."
   "heading .";
   
}
.app-info {
   grid-area: information;
   padding-top: 30px;
   color: whitesmoke;
   text-align: justify;
   max-width: 550px;
 }
 .heading{
   grid-area: heading;
   padding-top: 40px;
   color: whitesmoke;
   text-align: justify;
   max-width: 550px;
}

h1 {
  color: #ebdbdb;
}

p {
  color: #e7d9d9;
  font-size: 18px;
}
strong{
  font-size: 22px;
}
em{
  font-size: 20px;
  color: #3dbed4;
}

@media (max-width: 425px) {
  .main-view {
    padding-left: 0;
    grid-template-columns: 1fr;
    grid-template-rows: auto auto;
    grid-template-areas:
      "information"
      "heading";
      width: fit-content;
      display: inline-block;
   
  }

  .app-info {
    max-width: 350px;
    padding-top: 0px;
    padding-left: 12px;
    height: 120px;
  }
  .heading {
    max-width: 350px;
    padding-top: 0px;
    padding-left: 12px;
    height: 120px;
  }
  p {
    font-size: 13px;

  }
  strong {
    font-size: 13px;
  }
  em {
    font-size: 13px;
  }
}
/* @media (min-width: 1024px) {
  .main-view {
    padding: 0 2rem;
  }
} */

</style>