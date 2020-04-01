const FormData = require('form-data')
const authentication = require('./authentication')
const axios = require('axios');

const api = axios.create({
  baseURL: "https://api.codenation.dev/v1/challenge/dev-ps/"
})

module.exports = {
  get(){
    return api.get(`generate-data?token=${authentication.token}`)
  },

  post(jsonFile){

    formData = new FormData();
    formData.append('answer', jsonFile);

    return api.post(`submit-solution?token=${authentication.token}`, formData, {
    headers: {
      "Content-Type": `multipart/form-data; boundary=${formData._boundary}`,
    }
    }).then(res=>{
        return res.data
    })
  }
}