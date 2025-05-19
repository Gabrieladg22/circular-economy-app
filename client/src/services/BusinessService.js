import axios from 'axios';

export default {

  businesses() {
    return axios.get('/api/businesses')
  },

  getBusiness(id) {
    return axios.get(`/api/businesses/${id}`)
  },

  createBusiness(business) {
    return axios.post('/api/businesses', business)
  },

  updateBusiness(business, id) {  
    return axios.put(`/api/businesses/${id}`, business)
  },

  deleteBusiness(id) {
    return axios.delete(`/api/businesses/${id}`)
  }

}
