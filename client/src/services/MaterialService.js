import axios from 'axios';

export default {

  listOfMaterials(onlyAvailable = true) {
    return axios.get(`/api/materials?isAvailableForTrading=${onlyAvailable}`)
  },

  getMaterial(id) {
    return axios.get(`/api/materials/${id}`)
  },

  addMaterial(material) {
    return axios.post('/api/materials', material)
  },

  updateMaterial(material, id) {  
    return axios.put(`/api/materials/${id}`, material)
  },

  deleteMaterial(id) {
    return axios.delete(`/api/materials/${id}`)
  }

}
