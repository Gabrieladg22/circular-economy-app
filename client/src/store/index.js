import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      businesses: [],
      materials: [],
      trades: [],
      savings: [],
      selectedBusiness: null,
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_BUSINESSES(state, businesses){
        state.businesses = businesses;
      },
      SET_MATERIALS(state, materials){
        state.materials = materials;
      },
      SET_TRADES(state, trades){
        state.trades = trades;
      },
      SET_SAVINGS(state, savings){
        state.savings = savings;
      }
    },

  })
  return store;
}