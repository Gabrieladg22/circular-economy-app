import axios from 'axios';

export default {

  listOfSavings(params = {}) {
    return axios.get('/api/savings', { params })
  },

  getSavings(id) {
    return axios.get(`/api/savings/${id}`)
  },

}