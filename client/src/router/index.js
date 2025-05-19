import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import RegisterView from '../views/RegisterView.vue'
import MainView from '../views/MainView.vue'
import BusinessesView from '../views/BusinessesView.vue'
import BusinessDetailView from '../views/BusinessDetailView.vue'
import MaterialView from '../views/MaterialView.vue'
import MaterialDetailsView from '../views/MaterialDetailsView.vue'
import TradesView from '../views/TradesView.vue'
import CarbonSavingsView from '../views/CarbonSavingsView.vue'
import CartView from '../views/CartView.vue'
import EditBusinessFormView from '../views/EditBusinessFormView.vue'
import NewBusinessView from '../views/NewBusinessView.vue'
import AddMaterialFormView from '../views/AddMaterialFormView.vue'
import EditMaterialFormView from '../views/EditMaterialFormView.vue'
import AddTradeFormView from '../views/AddTradeFormView.vue'


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
    {
      path: '/mainView',
      name: 'mainView',
      component: HomeView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: LogoutView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/",
      name: "home",
      component: MainView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/businesses",
      name: "businesses",
      component: BusinessesView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/businessProfile/:id",
      name: "businessProfile",
      component: BusinessDetailView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/edit-business/:id",
      name: "editBusiness",
      component: EditBusinessFormView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/createBusiness",
      name: "CreateBusiness",
      component: NewBusinessView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/materials",
      name: "materials",
      component: MaterialView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/materialProfile/:id",
      name: "materialProfile",
      component: MaterialDetailsView,
      meta: {
        requiresAuth: true
      }

    },
     {
      path: "/createMaterial",
      name: "CreateMaterial",
      component: AddMaterialFormView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/edit-material/:id",
      name: "editMaterial",
      component: EditMaterialFormView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/trades",
      name: "trades",
      component: TradesView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/createTrade",
      name: "CreateTrade",
      component: AddTradeFormView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/savings",
      name: "savings",
      component: CarbonSavingsView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/cart",
      name: "cart",
      component: CartView,
      meta: {
        requiresAuth: true
      }
    }
  ];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
