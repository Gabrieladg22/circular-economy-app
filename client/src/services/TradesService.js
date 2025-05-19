import axios from 'axios';

export default {

  listOfTrades() {
    return axios.get('/api/trades')
  },

  getTrade(id) {
    return axios.get(`/api/trades/${id}`)
  },

  addTrade(trade) {
    return axios.post('/api/trades', trade)
  },

  updateTrading(trade, id) {  
    return axios.put(`/api/trades/${id}`, trade)
  },

  deleteTrade(id) {
    return axios.delete(`/api/trades/${id}`)
  }

}